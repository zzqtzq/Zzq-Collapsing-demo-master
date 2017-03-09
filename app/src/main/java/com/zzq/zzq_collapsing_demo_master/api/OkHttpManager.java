package com.zzq.zzq_collapsing_demo_master.api;


import com.orhanobut.logger.Logger;
import com.zzq.zzq_collapsing_demo_master.app.App;
import com.zzq.zzq_collapsing_demo_master.common.Constants;
import com.zzq.zzq_collapsing_demo_master.utils.NetWorkUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *
 */
public class OkHttpManager {

    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getInstance() {

        if (mOkHttpClient == null) {
            synchronized (OkHttpManager.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .readTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                            .connectTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                            .addInterceptor(new OKHTTPLogInterceptor())
                            .addNetworkInterceptor(new HttpCacheInterceptor())
//                            .cache(cache)
                            .build();
                }
            }
        }

        return mOkHttpClient;
    }


    static class OKHTTPLogInterceptor implements Interceptor {


        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();

            Response response = chain.proceed(chain.request());

            okhttp3.MediaType mediaType = response.body().contentType();

            String content = response.body().string();


            Logger.e(mediaType.toString());

            Logger.e(request.toString());

            Logger.init("json");
            Logger.json(content);


            if (response.body() != null) {
                // 深坑！
                // 打印body后原ResponseBody会被清空，需要重新设置body
                ResponseBody body = ResponseBody.create(mediaType, content);
                return response.newBuilder().body(body).build();
            } else {
                return response;
            }
        }
    }

    static class HttpCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            if (!NetWorkUtil.isNetConnected(App.getAppContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
//                Logger.e("no network");
            }

            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.isNetConnected(App.getAppContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build();
            }
        }
    }

}
