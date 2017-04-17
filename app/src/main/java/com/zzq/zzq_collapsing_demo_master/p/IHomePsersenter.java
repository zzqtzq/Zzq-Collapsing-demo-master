package com.zzq.zzq_collapsing_demo_master.p;

import android.app.Activity;

import com.zzq.zzq_collapsing_demo_master.api.RxResultHelper;
import com.zzq.zzq_collapsing_demo_master.api.netUtils.SchedulersCompat;
import com.zzq.zzq_collapsing_demo_master.entity.WelfareEntity;
import com.zzq.zzq_collapsing_demo_master.p.base.IBasePsersenter;
import com.zzq.zzq_collapsing_demo_master.utils.MyToast;
import com.zzq.zzq_collapsing_demo_master.v.AHomeContract;

import rx.Observer;
import rx.Subscription;

/**
 * Created by 志强 on 2017.3.8.
 */

public class IHomePsersenter extends IBasePsersenter<AHomeContract.View> implements AHomeContract.Psersenter {
    public IHomePsersenter(Activity mActivity, AHomeContract.View mView) {
        super(mActivity, mView);
    }

    /**
     * 发送网络请求方法
     */
    @Override
    public void sendHomeResult(int pageSize, int page) {
        showLoading();
        //Rxjava + refo
        Subscription subscription = mHttpApi.getHomeResult(pageSize, page)
                .compose(SchedulersCompat.applyIoSchedulers())
                .compose(RxResultHelper.handleResult()).subscribe(new Observer<WelfareEntity>() {
                    @Override
                    public void onCompleted() {
                        //执行中
                        LoadingDiaogDismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //错误反馈
                        LoadingDiaogDismiss();
                        MyToast.showToast(mActivity, e.getMessage().toString());
                    }

                    @Override
                    public void onNext(WelfareEntity welfareEntity) {
                        //完成

                        if (welfareEntity != null) {
                            mView.getHomeResult(welfareEntity);
                        }
                    }
                });
        addSubscrebe(subscription);
    }
}
