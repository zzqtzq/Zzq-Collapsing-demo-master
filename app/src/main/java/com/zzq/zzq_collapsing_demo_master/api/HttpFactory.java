package com.zzq.zzq_collapsing_demo_master.api;

/**
 *
 */
public class HttpFactory {

    private static HttpApi mHttpApi = null;
    private static final Object WATCH = new Object();

    public HttpFactory() {

    }

    public static HttpApi getHttpApi() {
        synchronized (WATCH) {
            if (mHttpApi == null) {
                mHttpApi = new HttpClient().getHttpApi();
            }
        }
        return mHttpApi;
    }
}
