package com.zzq.zzq_collapsing_demo_master.v;

import com.zzq.zzq_collapsing_demo_master.p.IPsersenter;

/**
 * Created by 志强 on 2017.3.22.
 */

public interface AWelcomeContract {
    /**
     * 网络请求返回结果
     */
    interface View extends IView {
        void getWelcomeResult(String result);

        void showGirl();
    }

    /**
     * 发送网路请求
     */
    interface Psersenter extends IPsersenter {
        void sendWelcomeRequest(int page, int pageSize);
    }
}
