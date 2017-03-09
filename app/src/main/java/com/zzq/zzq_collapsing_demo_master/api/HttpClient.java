package com.zzq.zzq_collapsing_demo_master.api;


import com.zzq.zzq_collapsing_demo_master.common.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class HttpClient {

    private final HttpApi mHttpApi;

    public HttpClient() {


        String BaseUrl = Constants.isTest ? Constants.MyBaseUrl : Constants.MyBaseUrl;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(OkHttpManager.getInstance())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        mHttpApi = retrofit.create(HttpApi.class);
    }

    public HttpApi getHttpApi() {
        return mHttpApi;
    }
}
