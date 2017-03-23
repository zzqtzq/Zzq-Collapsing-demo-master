package com.zzq.zzq_collapsing_demo_master.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zzq.zzq_collapsing_demo_master.p.IPsersenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 志强 on 2017.3.8.
 */

public abstract class RxBaseActivity<T extends IPsersenter> extends AppCompatActivity {
    protected Unbinder unbinder;
    protected T mPersenter;
    protected Activity mActivity;
    private Handler mHandler = new Handler();
    private Runnable mLoadingRunnable = new Runnable() {
        @Override
        public void run() {
            initView();
            initVariables();
            initDataAndLoadData();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        mActivity = this;
        mPersenter = getPersenter();
//        initView();
//        initVariables();
//        initDataAndLoadData();
        getWindow().getDecorView().post(() -> mHandler.post(mLoadingRunnable));
    }

    //获取布局
    public abstract int getLayoutId();

    /**
     * 初始化变量 包括intent带的数据
     */
    protected abstract void initVariables();

    /**
     * 初始化View 属性设置  初始状态等等
     */
    public abstract void initView();

    /**
     * 初始化数据并获取数据
     */
    protected abstract void initDataAndLoadData();

    public abstract T getPersenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPersenter != null) mPersenter.detachView();
    }
}
