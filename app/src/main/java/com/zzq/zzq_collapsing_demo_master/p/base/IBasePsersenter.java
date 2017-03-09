package com.zzq.zzq_collapsing_demo_master.p.base;

import android.app.Activity;
import android.app.ProgressDialog;

import com.zzq.zzq_collapsing_demo_master.api.HttpApi;
import com.zzq.zzq_collapsing_demo_master.api.HttpAppApi;
import com.zzq.zzq_collapsing_demo_master.api.HttpAppFactory;
import com.zzq.zzq_collapsing_demo_master.api.HttpFactory;
import com.zzq.zzq_collapsing_demo_master.api.exception.ErrorHanding;
import com.zzq.zzq_collapsing_demo_master.p.IPsersenter;
import com.zzq.zzq_collapsing_demo_master.utils.MyToast;
import com.zzq.zzq_collapsing_demo_master.utils.ProgressDialogUtil;
import com.zzq.zzq_collapsing_demo_master.v.IView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 志强 on 2017.3.8.
 */

public abstract class IBasePsersenter<T extends IView> implements IPsersenter {
    protected Activity mActivity;
    protected T mView;
    protected CompositeSubscription mCompositeSubscription;
    protected static final HttpApi mHttpApi = HttpFactory.getHttpApi();
    protected static final HttpAppApi mHttpAppApi = HttpAppFactory.getHttpAppApi();
    private ProgressDialog loadingDiaog;

    public IBasePsersenter(Activity mActivity, T mView) {
        this.mActivity = mActivity;
        this.mView = mView;
    }

    protected void showLoading(String text) {
        loadingDiaog = ProgressDialogUtil.show(mActivity, text);
        if (!mActivity.isFinishing()) {
            loadingDiaog.show();
        }
    }

    protected void showLoading() {
        loadingDiaog = ProgressDialogUtil.show(mActivity);
        if (!mActivity.isFinishing()) {
            loadingDiaog.show();
        }
    }

    protected void LoadingDiaogDismiss() {

        if (loadingDiaog != null) {
            loadingDiaog.dismiss();
        }

    }

    protected void showHint(String msg) {
        MyToast.showToast(mActivity, msg);
    }


    protected void handleError(Throwable throwable) {
        MyToast.showToast(mActivity, ErrorHanding.handleError(throwable));
    }

    protected void handleError(String text) {
        MyToast.showToast(mActivity, text);
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
