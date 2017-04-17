package com.zzq.zzq_collapsing_demo_master.v;

import com.zzq.zzq_collapsing_demo_master.p.IPsersenter;

/**
 * Created by 志强 on 2017.4.13.
 */

public interface AShowPhotoContract {
    /**
     * 图片下载方法
     */
    interface View extends IView {
        void getPhotoUrlToSDResult(String imgUrl);
    }

    interface Psersenter extends IPsersenter {
        void savaPhotoUrlSD(String imgUrl);
    }
}
