package com.zzq.zzq_collapsing_demo_master.p;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.zzq.zzq_collapsing_demo_master.p.base.IBasePsersenter;
import com.zzq.zzq_collapsing_demo_master.utils.MyToast;
import com.zzq.zzq_collapsing_demo_master.v.AMainContract;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 志强 on 2017.4.3.
 */

public class IMainPsersenter extends IBasePsersenter<AMainContract.View> implements AMainContract.Psersenter {

    public IMainPsersenter(Activity mActivity, AMainContract.View mView) {
        super(mActivity, mView);
    }

    /**
     * 图片上传方法
     *
     * @param file
     */
    @Override
    public void sendMainImage(int id, File file) {
        MyToast.showToast(mActivity, "333");
        OkHttpClient client = new OkHttpClient();
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        String boundary = "xx--------------------------------------------------------------xx";
        MultipartBody mBody = new MultipartBody.Builder(boundary).setType(MultipartBody.FORM)
            /* 上传一个普通的String参数 , key 叫 "p" */
                .addFormDataPart("id", "1")
            /* 底下是上传了1个文件 */
                .addFormDataPart("headurl", "img", fileBody)
                .build();
         /* 下边的就和post一样了 */
        Request request = new Request.Builder().url("http://192.168.1.112:8080/wooforbes/updateHead").post(mBody).build();
        client.newCall(request).enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                final String bodyStr = response.body().string();
                final boolean ok = response.isSuccessful();
                mActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        if (ok) {
                            Log.i("", "图片上传返回结果success:" + bodyStr);
                            Toast.makeText(mActivity, bodyStr, Toast.LENGTH_SHORT).show();
                        } else {
                            Log.i("", "图片上传返回结果error:" + bodyStr);
                            Toast.makeText(mActivity, "server error : " + bodyStr, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            public void onFailure(Call call, final IOException e) {
                mActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(mActivity, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
