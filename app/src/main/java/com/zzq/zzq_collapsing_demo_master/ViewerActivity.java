package com.zzq.zzq_collapsing_demo_master;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.WallpaperManager;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.transition.Transition;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxMenuItem;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;
import com.zzq.zzq_collapsing_demo_master.databinding.ViewerActivityBinding;
import com.zzq.zzq_collapsing_demo_master.entity.Image;
import com.zzq.zzq_collapsing_demo_master.fragment.ViewerFragment;
import com.zzq.zzq_collapsing_demo_master.rx.RxFiles;
import com.zzq.zzq_collapsing_demo_master.rx.RxGlide;
import com.zzq.zzq_collapsing_demo_master.utils.ImmersiveUtil;
import com.zzq.zzq_collapsing_demo_master.utils.InOutAnimationUtils;
import com.zzq.zzq_collapsing_demo_master.utils.ObservableListPagerAdapterCallback;
import com.zzq.zzq_collapsing_demo_master.utils.SimpleTransitionListener;
import com.zzq.zzq_collapsing_demo_master.utils.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ooo.oxo.library.widget.PullBackLayout;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by 志强 on 2017.4.25.
 */

public class ViewerActivity extends RxAppCompatActivity implements PullBackLayout.Callback {
    private static final String TAG = "ViewerActivity";

    private static final String AUTHORITY_IMAGES = BuildConfig.APPLICATION_ID + ".images";

    private ObservableArrayList<Image> images;

    private ViewerActivityBinding binding;

    private Adapter adapter;

    private ObservableListPagerAdapterCallback listener;

    private ColorDrawable background;

    private static String makeFileName(Image image) {
        return String.format(Locale.US, "%d.%s", image.getCreatedAt().getTime(), image.getType());
    }

    private Observable<Void> menuItemClicks(@IdRes int id) {
        return RxMenuItem.clicks(binding.toolbar.getMenu().findItem(id));
    }

    private Observable<File> ensureExternalDirectory(String name) {
        return RxFiles.mkdirsIfNotExists(new File(Environment.getExternalStorageDirectory(), name));
    }

    private Observable<File> download(Image image) {
        return RxGlide.download(Glide.with(this), image.getUrl());
    }

    private Observable<File> save(Image image, File destination) {
        return download(image).flatMap(tmp -> RxFiles.copy(tmp, destination));
    }

    private Observable<File> saveIfNeeded(Image image) {
        return ensureExternalDirectory("Mr.Mantou")
                .map(directory -> new File(directory, makeFileName(image)))
                .flatMap(file -> file.exists()
                        ? Observable.just(file)
                        : save(image, file));
    }

    private Observable.Transformer<Void, Void> ensurePermissions(@NonNull String... permissions) {
        return source -> source
                .compose(RxPermissions.getInstance(this).ensure(permissions))
                .filter(granted -> {
                    if (granted) {
                        return true;
                    } else {
                        ToastUtil.shorts(this, R.string.permission_required);
                        return false;
                    }
                })
                .map(granted -> null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.viewer_activity);

        setTitle(null);

        binding.toolbar.setNavigationOnClickListener(v -> supportFinishAfterTransition());
        binding.toolbar.inflateMenu(R.menu.viewer);

        binding.puller.setCallback(this);

        supportPostponeEnterTransition();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getEnterTransition().addListener(new SimpleTransitionListener() {
                @Override
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                public void onTransitionEnd(Transition transition) {
                    getWindow().getEnterTransition().removeListener(this);
                    fadeIn();
                }
            });
        } else {
            fadeIn();
        }

        background = new ColorDrawable(Color.BLACK);
        binding.getRoot().setBackground(background);

        adapter = new Adapter();

        binding.pager.setAdapter(adapter);
        binding.pager.setCurrentItem(getIntent().getIntExtra("index", 0));
        binding.pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    fadeOut();
                }
            }
        });

        listener = new ObservableListPagerAdapterCallback(adapter);
        images.addOnListChangedCallback(listener);

        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                Image image = images.get(binding.pager.getCurrentItem());
                sharedElements.clear();
                sharedElements.put(String.format("%s.image", image.getObjectId()), getCurrent().getSharedElement());
            }
        });

        menuItemClicks(R.id.share)
                .compose(bindToLifecycle())
                .compose(ensurePermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                .map(avoid -> getCurrentImage())
                .doOnNext(image -> MobclickAgent.onEvent(this, "share", image.getObjectId()))
                .observeOn(Schedulers.io())
                .flatMap(this::saveIfNeeded)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::notifyMediaScanning)
                .map(Uri::fromFile)
                .retry()
                .subscribe(uri -> {
                    final Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("image/jpeg");
                    intent.putExtra(Intent.EXTRA_STREAM, uri);
                    startActivity(Intent.createChooser(intent, getString(R.string.share_title)));
                });

        menuItemClicks(R.id.save)
                .compose(bindToLifecycle())
                .compose(ensurePermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                .map(avoid -> getCurrentImage())
                .doOnNext(image -> MobclickAgent.onEvent(this, "save", image.getObjectId()))
                .observeOn(Schedulers.io())
                .flatMap(this::saveIfNeeded)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::notifyMediaScanning)
                .retry()
                .subscribe(file -> {
                    ToastUtil.shorts(this, R.string.save_success, file.getPath());
                });

        final WallpaperManager wm = WallpaperManager.getInstance(this);

        menuItemClicks(R.id.set_wallpaper)
                .compose(bindToLifecycle())
                .map(avoid -> getCurrentImage())
                .doOnNext(image -> MobclickAgent.onEvent(this, "set_wallpaper", image.getObjectId()))
                .observeOn(Schedulers.io())
                .flatMap(this::download)
                .observeOn(AndroidSchedulers.mainThread())
                .map(file -> FileProvider.getUriForFile(this, AUTHORITY_IMAGES, file))
                .retry()
                .subscribe(uri -> {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        startActivity(wm.getCropAndSetWallpaperIntent(uri));
                    } else {
                        try {
                            wm.setStream(getContentResolver().openInputStream(uri));
                            ToastUtil.shorts(this, R.string.set_wallpaper_success);
                        } catch (IOException e) {
                            Log.e(TAG, "Failed to set wallpaper", e);
                            ToastUtil.shorts(this, e.getMessage(), e);
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        images.removeOnListChangedCallback(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private Image getCurrentImage() {
        return images.get(binding.pager.getCurrentItem());
    }

    public void fadeIn() {
        InOutAnimationUtils.animateIn(binding.toolbar, R.anim.viewer_toolbar_fade_in);
        ImmersiveUtil.exit(this);
    }

    public void fadeOut() {
        InOutAnimationUtils.animateOut(binding.toolbar, R.anim.viewer_toolbar_fade_out);
        ImmersiveUtil.enter(this);
    }

    public void toggleFade() {
        if (binding.toolbar.getVisibility() == View.VISIBLE) {
            fadeOut();
        } else {
            fadeIn();
        }
    }

    @Override
    public void onPullStart() {
        InOutAnimationUtils.animateOut(binding.toolbar, R.anim.viewer_toolbar_fade_out);
        ImmersiveUtil.exit(this);
    }

    @Override
    public void onPull(float progress) {
        progress = Math.min(1f, progress * 3f);
        background.setAlpha((int) (0xff * (1f - progress)));
    }

    @Override
    public void onPullCancel() {
        fadeIn();
    }

    @Override
    public void onPullComplete() {
        supportFinishAfterTransition();
    }

    public ViewerFragment getCurrent() {
        return (ViewerFragment) adapter.instantiateItem(binding.pager, binding.pager.getCurrentItem());
    }

    @Override
    public void supportFinishAfterTransition() {
        Intent data = new Intent();
        data.putExtra("index", binding.pager.getCurrentItem());
        setResult(RESULT_OK, data);

        ImmersiveUtil.exit(this);

        super.supportFinishAfterTransition();
    }

    private void notifyMediaScanning(File file) {
        MediaScannerConnection.scanFile(getApplicationContext(),
                new String[]{file.getPath()}, null, null);
    }

    private class Adapter extends FragmentStatePagerAdapter {

        public Adapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            Image image = images.get(position);

            Bundle arguments = new Bundle();
            arguments.putParcelable("image", image);

            if (position == getIntent().getIntExtra("index", 0)) {
                arguments.putString("thumbnail", getIntent().getStringExtra("thumbnail"));
            }

            Fragment fragment = new ViewerFragment();
            fragment.setArguments(arguments);

            return fragment;
        }

        @Override
        public int getCount() {
            return images.size();
        }

    }

}
