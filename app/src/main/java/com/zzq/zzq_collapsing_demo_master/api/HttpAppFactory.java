package com.zzq.zzq_collapsing_demo_master.api;

/**
 *
 */
public class HttpAppFactory {

    private static HttpAppApi mHttpAppApi = null;
    private static final Object WATCH = new Object();

    public HttpAppFactory() {

    }

    public static HttpAppApi getHttpAppApi() {

        synchronized (WATCH) {
            if (mHttpAppApi == null) {
                mHttpAppApi = new HttpAppClient().getHttpAppApi();
            }
        }
        return mHttpAppApi;
    }
}
