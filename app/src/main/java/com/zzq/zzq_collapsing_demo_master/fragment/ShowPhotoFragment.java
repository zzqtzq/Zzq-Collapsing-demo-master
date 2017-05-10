package com.zzq.zzq_collapsing_demo_master.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zzq.zzq_collapsing_demo_master.R;
import com.zzq.zzq_collapsing_demo_master.adapter.GirlAdapter;
import com.zzq.zzq_collapsing_demo_master.base.BaseFragment;
import com.zzq.zzq_collapsing_demo_master.entity.WelfareEntity;
import com.zzq.zzq_collapsing_demo_master.widget.PinchImageView;

import java.util.ArrayList;

/**
 * Created by 志强 on 2017.4.14.
 */

public class ShowPhotoFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    private ViewPager view_pager;
    private GirlAdapter mAdapter;
    private LinearLayout rootView;
    private ArrayList<WelfareEntity.ResultsEntity> datas;
    private int current;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = super.onCreateView(inflater, container, savedInstanceState);

        return mView;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        rootView = (LinearLayout) view.findViewById(R.id.rootView);
        view_pager = (ViewPager) view.findViewById(R.id.view_pager);
        Bundle bundle = getArguments();
        if (bundle != null) {
            datas = bundle.getParcelableArrayList("girls");
            current = bundle.getInt("current");
        }
        mAdapter = new GirlAdapter(mActivity, datas);
        view_pager.setAdapter(mAdapter);
        view_pager.setCurrentItem(current);
        view_pager.setOnPageChangeListener(this);
        ViewCompat.setTransitionName(rootView, "flag");

    }

    public static ShowPhotoFragment newInstance(ArrayList<Parcelable> datas, int current) {
        Bundle bundle = new Bundle();
        ShowPhotoFragment fragment = new ShowPhotoFragment();
        bundle.putParcelableArrayList("girls", datas);
        bundle.putInt("current", current);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_girls;
    }

    /**
     * 分享图片方法
     */
    public void shareGirl() {
        int curr = view_pager.getCurrentItem();
        String url = datas.get(curr).getUrl();
//        FileUtils.
//            if (FileUtils.saveBitmapToFile(bitmap, "share")) {
//        Uri imageUri = Uri.fromFile(new File(FileUtils.SDPATH + "/share.png"));
//        Intent shareIntent = new Intent();
//        shareIntent.setAction(Intent.ACTION_SEND);
//        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
//        shareIntent.setType("image*//**//*");
//        startActivity(Intent.createChooser(shareIntent, "分享MeiZhi到"));
//        PinchImageView imageView = getCurrentImageView();
//        Drawable drawable = imageView.getDrawable();
////        if (drawable != null) {
//        Bitmap bitmap = BitmapUtil.drawableToBitmap(drawable);
//        FileUtils.saveBitmapToFile(bitmap, "share");
//            } else {
//                Snackbar.make(rootView, "大爷，分享出错了哦~", Snackbar.LENGTH_LONG).show();
//            }
//        }
        /*if (drawable != null) {
            Bitmap bitmap = BitmapUtil.drawableToBitmap(drawable);
            Observable.just(FileUtils.saveBitmapToFile(bitmap, "share"))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean isSuccess) {
                            if (isSuccess) {
                                //由文件得到uri
                                Uri imageUri = Uri.fromFile(new File(FileUtils.SDPATH + "/share.png"));
                                Intent shareIntent = new Intent();
                                shareIntent.setAction(Intent.ACTION_SEND);
                                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                                shareIntent.setType("image*//**//*");
                                startActivity(Intent.createChooser(shareIntent, "分享MeiZhi到"));
                            } else {
                                Snackbar.make(rootView, "大爷，分享出错了哦~", Snackbar.LENGTH_LONG).show();
                            }
                        }
                    });
        }*/
    }

    public void saveGirl() {
        try {
            PackageManager packageManager = getActivity().getPackageManager();
            Intent intent = new Intent();
            intent = packageManager.getLaunchIntentForPackage("com.tencent.mm");
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Intent viewIntent = new
                    Intent("android.intent.action.VIEW", Uri.parse("http://weixin.qq.com/"));
            startActivity(viewIntent);
        }

//        String imgUrl = datas.get(view_pager.getCurrentItem()).getUrl();
//        PinchImageView imageView = getCurrentImageView();
//        Bitmap bitmap = BitmapUtil.drawableToBitmap(imageView.getDrawable());
//
//        Observable.just(BitmapUtil.saveBitmap(bitmap, Constants.dir, imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length()), true))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Boolean>() {
//                    @Override
//                    public void call(Boolean isSuccess) {
//                        if (isSuccess) {
//                            Snackbar.make(rootView, "大爷，下载好了呢~", Snackbar.LENGTH_LONG).show();
//                        } else {
//                            Snackbar.make(rootView, "大爷，下载出错了哦~", Snackbar.LENGTH_LONG).show();
//                        }
//                    }
//                });

//        boolean isSuccess = BitmapUtil.saveBitmap(bitmap, Constants.dir, imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length()), true);
//        if (isSuccess) {
//            Snackbar.make(mRootView, "大爷，下载好了呢~", Snackbar.LENGTH_LONG).show();
//        } else {
//            Snackbar.make(mRootView, "大爷，下载出错了哦~", Snackbar.LENGTH_LONG).show();
//        }
    }

    private PinchImageView getCurrentImageView() {
        View currentItem = mAdapter.getPrimaryItem();
        if (currentItem == null) {
            return null;
        }
        PinchImageView imageView = (PinchImageView) currentItem.findViewById(R.id.img);
        if (imageView == null) {
            return null;
        }
        return imageView;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
