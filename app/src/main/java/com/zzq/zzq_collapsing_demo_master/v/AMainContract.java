package com.zzq.zzq_collapsing_demo_master.v;

import com.zzq.zzq_collapsing_demo_master.entity.WelfareEntity;
import com.zzq.zzq_collapsing_demo_master.p.IPsersenter;

import java.io.File;

/**
 * Created by 志强 on 2017.4.3.
 */

public interface AMainContract {
    /**
     * 发送网络请求返回结果
     */

    interface View extends IView {
        void getIsendImageMethod(WelfareEntity mWelfareEntity);
    }

    /**
     * 发送网络请求方法
     */
    interface Psersenter extends IPsersenter {
        void sendMainImage(int id, File file);

    }
}
