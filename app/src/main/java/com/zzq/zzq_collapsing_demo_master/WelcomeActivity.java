package com.zzq.zzq_collapsing_demo_master;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zzq.zzq_collapsing_demo_master.base.RxBaseActivity;
import com.zzq.zzq_collapsing_demo_master.p.IWelcomePsersenter;
import com.zzq.zzq_collapsing_demo_master.v.AWelcomeContract;

/**
 * Created by 志强 on 2017.3.22.
 */

public class WelcomeActivity extends RxBaseActivity<IWelcomePsersenter> implements AWelcomeContract.View {

    private ImageView splash;
    private Button btn_wel_skip_Button;
//    private ScaleAnimation scaleAnimation;

    @Override
    public int getLayoutId() {
        Log.i("", "声明周期执行:" + "getLayoutId方法");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_welcome;
    }

    @Override
    protected void initVariables() {
        Log.i("", "声明周期执行:" + "initVariables方法");

//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        try {
//            Thread.sleep(3 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        cachedThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                getPersenter().sendWelcomeRequest(1, 1);
//            }
//        });


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 2000);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();

    }

    @Override
    public void initView() {
        Log.i("", "声明周期执行:" + "initView方法");
        splash = (ImageView) findViewById(R.id.splash);
        btn_wel_skip_Button = (Button) findViewById(R.id.btn_wel_skip_Button);
        btn_wel_skip_Button.getBackground().setAlpha(100);
    }

    @Override
    protected void initDataAndLoadData() {
        SystemClock.sleep(2000);
        getPersenter().sendWelcomeRequest(1, 1);
        Log.i("", "声明周期执行:" + "initDataAndLoadData方法");
    }

    @Override
    public IWelcomePsersenter getPersenter() {
        Log.i("", "声明周期执行:" + "getPersenter方法");
        return new IWelcomePsersenter(mActivity, this);
    }

    @Override
    public void getWelcomeResult(String result) {
        Log.i("", "声明周期执行:" + "getWelcomeResult方法");
        initAnim(result);

        btn_wel_skip_Button.setVisibility(View.VISIBLE);
        btn_wel_skip_Button.setText(getResources().getString(R.string.skip_welcome));
    }

    /**
     *
     */
    @Override
    public void showGirl() {
//        Log.i("", "声明周期执行:" + "showGirl方法");
//        MyToast.showToast(mActivity, "执行showGirl无参方法");
//        Glide.with(mActivity)
//                .load(R.drawable.welcome)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(splash);
    }

    private void initAnim(String imgUrl) {

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(8000);
        splash.startAnimation(scaleAnimation);

        //缩放动画监听
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                Glide.with(mActivity)
                        .load(imgUrl)
                        .animate(animation)
                        .into(splash);
                btn_wel_skip_Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(mActivity, MainActivity.class));
                        finish();
                    }
                });
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(mActivity, MainActivity.class));
                finish();
//                ActivityManager.getInstance().finishActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("", "声明周期执行:" + "onCreate方法");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("", "声明周期执行:" + "onRestart方法");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("", "声明周期执行:" + "onStart方法");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("", "声明周期执行:" + "onResume方法");


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("", "声明周期执行:" + "onPause方法");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("", "声明周期执行:" + "onStop方法");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("", "声明周期执行:" + "onDestroy方法");
    }

}
