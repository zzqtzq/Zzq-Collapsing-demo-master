package com.zzq.zzq_collapsing_demo_master.api;


import com.zzq.zzq_collapsing_demo_master.common.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class HttpAppClient {

    private final HttpAppApi mHttpAppApi;

    public HttpAppClient() {

        String baseUrl = Constants.isTest ? Constants.ZhiHuUrl : Constants.ZhiHuUrl;


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkHttpManager.getInstance())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        mHttpAppApi = retrofit.create(HttpAppApi.class);
    }

    public HttpAppApi getHttpAppApi() {
        return mHttpAppApi;
    }
}
