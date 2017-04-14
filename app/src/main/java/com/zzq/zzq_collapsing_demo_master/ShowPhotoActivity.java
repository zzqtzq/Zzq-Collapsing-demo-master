package com.zzq.zzq_collapsing_demo_master;

import com.zzq.zzq_collapsing_demo_master.base.RxBaseActivity;
import com.zzq.zzq_collapsing_demo_master.p.IShowPhotoPsersenter;
import com.zzq.zzq_collapsing_demo_master.v.AShowPhotoContract;

/**
 * Created by 志强 on 2017.4.13.
 */

public class ShowPhotoActivity extends RxBaseActivity<IShowPhotoPsersenter> implements AShowPhotoContract.View {
    @Override
    public int getLayoutId() {
        return R.layout.activity_showphoto;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    public void initView() {

    }

    @Override
    protected void initDataAndLoadData() {

    }

    @Override
    public IShowPhotoPsersenter getPersenter() {
        return new IShowPhotoPsersenter(mActivity, this);
    }

    @Override
    public void getPhotoUrlToSDResult(String imgUrl) {

    }
}
