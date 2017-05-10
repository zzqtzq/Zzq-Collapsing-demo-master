/*
 * Mr.Mantou - On the importance of taste
 * Copyright (C) 2015-2016  XiNGRZ <xxx@oxo.ooo>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.zzq.zzq_collapsing_demo_master.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;
import com.zzq.zzq_collapsing_demo_master.ViewerActivity;
import com.zzq.zzq_collapsing_demo_master.databinding.ViewerFragmentBinding;
import com.zzq.zzq_collapsing_demo_master.entity.Image;
import com.zzq.zzq_collapsing_demo_master.net.GlideRequestListenerAdapter;
import com.zzq.zzq_collapsing_demo_master.utils.EnterTransitionCompat;
import com.zzq.zzq_collapsing_demo_master.utils.SimpleTransitionListener;
import com.zzq.zzq_collapsing_demo_master.widget.RxBindingFragment;


public class ViewerFragment extends RxBindingFragment<ViewerFragmentBinding> {

    private static final String TAG = "ViewerFragment";

    private Image image;
    private String thumbnail;

    private boolean hasSharedElementTransition = false;

    private boolean isTransitionExecuted = false;

    private View sharedElement;

    public ViewerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        image = getArguments().getParcelable("image");
        thumbnail = getArguments().getString("thumbnail");

        hasSharedElementTransition = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                !TextUtils.isEmpty(thumbnail);
    }

    @Nullable
    @Override
    public ViewerFragmentBinding onCreateBinding(LayoutInflater inflater,
                                                 @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState) {
        return ViewerFragmentBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        binding.image.setSingleTapListener(((ViewerActivity) getActivity())::toggleFade);
        binding.image.setDoubleTapListener(((ViewerActivity) getActivity())::fadeOut);

        binding.setImage(image);

        sharedElement = binding.thumbnail;

        if (savedInstanceState != null) {
            isTransitionExecuted = savedInstanceState.getBoolean("transition_executed", false);
        }

        loadImage();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("transition_executed", isTransitionExecuted);
        super.onSaveInstanceState(outState);
    }

    public View getSharedElement() {
        return sharedElement;
    }

    public void startPostponedEnterTransition() {
        if (hasSharedElementTransition) {
            getActivity().supportStartPostponedEnterTransition();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void loadImage() {
        if (hasSharedElementTransition && !isTransitionExecuted) {
            isTransitionExecuted = true;
            loadThumbnail();
            EnterTransitionCompat.addListener(getActivity().getWindow(), new SimpleTransitionListener() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    EnterTransitionCompat.removeListener(getActivity().getWindow(), this);
                    loadFullImage();
                }
            });
        } else {
            loadFullImage();
        }
    }

    private void loadThumbnail() {
        Glide.with(this).load(thumbnail)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade(0)
                .listener(new GlideRequestListenerAdapter<String, GlideDrawable>() {
                    @Override
                    protected void onComplete() {
                        startPostponedEnterTransition();
                    }
                })
                .into(binding.thumbnail);
    }

    private void loadFullImage() {
        Glide.with(this).load(image.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade(0)
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .listener(new GlideRequestListenerAdapter<String, GlideDrawable>() {
                    @Override
                    protected void onSuccess(GlideDrawable resource) {
                        sharedElement = binding.image;
                        fadeInFullImage();
                    }
                })
                .into(binding.image);
    }

    private void fadeInFullImage() {
        binding.fade.setDisplayedChild(1);
    }

}
