package com.zzq.zzq_collapsing_demo_master.app;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.squareup.okhttp.OkHttpClient;
import com.zzq.zzq_collapsing_demo_master.utils.ContextUtil;

import java.io.File;
import java.io.InputStream;

/**
 * Created by SQ on 16/6/17.
 */
public class App extends Application {

    private static App appContext;

    public File cacheDir;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        ContextUtil.init(this);
        setupGlide(appContext);
    }

    public static App getAppContext() {
        return appContext;
    }

    /**
     * 图片加载配制（自定义缓存路径）
     */
    private void setupGlide(Context context) {

        OkHttpClient mOkHttpClient = new OkHttpClient();

        Glide.get(this).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(mOkHttpClient));

        cacheDir = Glide.getPhotoCacheDir(context);
    }

}
