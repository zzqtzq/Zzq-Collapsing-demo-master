package com.zzq.zzq_collapsing_demo_master.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zzq.zzq_collapsing_demo_master.utils.ActivityManagerUtils;

/**
 * Created by 志强 on 2017.4.14.
 */

public abstract class AppActivity extends BaseActivity {
    //获取第一个fragment
    protected abstract BaseFragment getFirstFragment();

    //获取Intent
    protected void handleIntent(Intent intent) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        //避免重复添加Fragment
        if (null == getSupportFragmentManager().getFragments()) {
            BaseFragment firstFragment = getFirstFragment();
            //SplashFragment
            if (null != firstFragment) {
                addFragment(firstFragment);
            }
        }

        ActivityManagerUtils.getInstance().addActivity(this);
    }

    @Override
    protected int getContentViewId() {
        return 0;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    public void onClick(View v) {

    }
}
