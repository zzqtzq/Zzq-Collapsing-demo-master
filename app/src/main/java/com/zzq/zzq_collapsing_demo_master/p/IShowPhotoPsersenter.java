package com.zzq.zzq_collapsing_demo_master.p;

import android.app.Activity;

import com.zzq.zzq_collapsing_demo_master.p.base.IBasePsersenter;
import com.zzq.zzq_collapsing_demo_master.v.AShowPhotoContract;

/**
 * Created by 志强 on 2017.4.13.
 */

public class IShowPhotoPsersenter extends IBasePsersenter<AShowPhotoContract.View> implements AShowPhotoContract.Psersenter {
    public IShowPhotoPsersenter(Activity mActivity, AShowPhotoContract.View mView) {
        super(mActivity, mView);
    }

    /**
     * 保存图片
     *
     * @param imgUrl
     */
    @Override
    public void savaPhotoUrlSD(String imgUrl) {

    }
}
