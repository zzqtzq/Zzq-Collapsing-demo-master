package com.zzq.zzq_collapsing_demo_master.v;

import com.zzq.zzq_collapsing_demo_master.entity.WelfareEntity;
import com.zzq.zzq_collapsing_demo_master.p.IPsersenter;

/**
 * Created by 志强 on 2017.3.8.
 */

public interface AHomeContract {
    /**
     * 网络请求返回结果
     */
    interface View extends IView {
        void getHomeResult(WelfareEntity mWelfareEntity);

    }

    /**
     * 发送网络请求
     */
    interface Psersenter extends IPsersenter {
        void sendHomeResult(int pageSize, int page);

    }
}
