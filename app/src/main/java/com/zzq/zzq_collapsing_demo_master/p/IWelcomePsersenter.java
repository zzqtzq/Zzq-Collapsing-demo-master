package com.zzq.zzq_collapsing_demo_master.p;

import android.app.Activity;

import com.zzq.zzq_collapsing_demo_master.api.RxResultHelper;
import com.zzq.zzq_collapsing_demo_master.api.netUtils.SchedulersCompat;
import com.zzq.zzq_collapsing_demo_master.entity.WelfareEntity;
import com.zzq.zzq_collapsing_demo_master.p.base.IBasePsersenter;
import com.zzq.zzq_collapsing_demo_master.utils.MyToast;
import com.zzq.zzq_collapsing_demo_master.v.AWelcomeContract;

import rx.Observer;
import rx.Subscription;

/**
 * Created by 志强 on 2017.3.22.
 */

public class IWelcomePsersenter extends IBasePsersenter<AWelcomeContract.View> implements AWelcomeContract.Psersenter {

    public IWelcomePsersenter(Activity mActivity, AWelcomeContract.View mView) {
        super(mActivity, mView);
    }

    /**
     * 发送请求
     */

    @Override
    public void sendWelcomeRequest(int page, int pageSize) {
        Subscription subscription = mHttpApi.getWelcomeGirl(page, pageSize)
                .compose(SchedulersCompat.applyIoSchedulers())
                .compose(RxResultHelper.handleResult()).subscribe(new Observer<WelfareEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MyToast.showToast(mActivity, e.getMessage().toString());
                        mView.showGirl();
                    }

                    @Override
                    public void onNext(WelfareEntity welfareEntity) {
                        if (welfareEntity != null) {
                            mView.getWelcomeResult(welfareEntity.getResults().get(0).getUrl());
                        }
                    }
                });
    }
}
