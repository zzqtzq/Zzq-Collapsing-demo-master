package com.zzq.zzq_collapsing_demo_master;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.zzq.zzq_collapsing_demo_master.base.RxBaseActivity;
import com.zzq.zzq_collapsing_demo_master.entity.WelfareEntity;
import com.zzq.zzq_collapsing_demo_master.p.IMainPsersenter;
import com.zzq.zzq_collapsing_demo_master.utils.Bimp;
import com.zzq.zzq_collapsing_demo_master.utils.FileUtils;
import com.zzq.zzq_collapsing_demo_master.utils.MyToast;
import com.zzq.zzq_collapsing_demo_master.v.AMainContract;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static com.zzq.zzq_collapsing_demo_master.utils.Bimp.drr;

/**
 * Created by 志强 on 2017.3.10.
 */

public class HomeActivity extends RxBaseActivity<IMainPsersenter> implements AMainContract.View {
    private Button btn_Select_img, btm_send_img;

    private ImageView img_send_imageView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables() {

    }

    /**
     * 初始化
     */
    @Override
    public void initView() {
        btn_Select_img = (Button) findViewById(R.id.btn_Select_img);
        btm_send_img = (Button) findViewById(R.id.btm_send_img);
        img_send_imageView = (ImageView) findViewById(R.id.img_send_imageView);
        btn_Select_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PopupWindows(HomeActivity.this, btn_Select_img);
            }
        });
        btm_send_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.showToast(mActivity, "111");
                if (Bimp.drr.size() > 0) {
                    MyToast.showToast(mActivity, "222");
                    getPersenter().sendMainImage(1, file);
                }
            }
        });
    }

    @Override
    protected void initDataAndLoadData() {

    }

    @Override
    public IMainPsersenter getPersenter() {
        return new IMainPsersenter(mActivity, this);
    }


    @Override
    public void getIsendImageMethod(WelfareEntity mWelfareEntity) {

    }

    public class PopupWindows extends PopupWindow {

        public PopupWindows(Context mContext, View parent) {

            View view = View
                    .inflate(mContext, R.layout.item_popupwindows, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.fade_ins));
            LinearLayout ll_popup = (LinearLayout) view
                    .findViewById(R.id.ll_popup);
            // ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
            // R.anim.push_bottom_in_2));
            setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            setBackgroundDrawable(new BitmapDrawable());
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            update();

            Button bt1 = (Button) view
                    .findViewById(R.id.item_popupwindows_camera);
            Button bt2 = (Button) view
                    .findViewById(R.id.item_popupwindows_Photo);
            Button bt3 = (Button) view
                    .findViewById(R.id.item_popupwindows_cancel);
            bt1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    photo();
                    dismiss();
                }
            });
            bt2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(
                            // 相册
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                    dismiss();
                }
            });
            bt3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });

        }
    }

    private static final int TAKE_PICTURE = 0;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int CUT_PHOTO_REQUEST_CODE = 2;
    private static final int SELECTIMG_SEARCH = 3;
    private String path = "";
    private Uri photoUri;
    File file = null;

    public void photo() {
        try {
            Intent openCameraIntent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);

            String sdcardState = Environment.getExternalStorageState();
            String sdcardPathDir = android.os.Environment
                    .getExternalStorageDirectory().getPath() + "/formats/";

            if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
                // 有sd卡，是否有tempImage文件夹
                File fileDir = new File(sdcardPathDir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                // 是否有headImg文件
                file = new File(sdcardPathDir + "imageName" + ".JPEG");
            }
            if (file != null) {
                path = file.getPath();
                photoUri = Uri.fromFile(file);
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(openCameraIntent, TAKE_PICTURE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private float dp = 10f;

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == -1) {// 拍照
                    startPhotoZoom(photoUri);
                }
                break;
            case RESULT_LOAD_IMAGE:
                if (resultCode == RESULT_OK && null != data && requestCode == 1) {// 相册返回
                    Uri uri = data.getData();
                    if (uri != null) {
                        startPhotoZoom(uri);
                    }
                }
                break;
            case CUT_PHOTO_REQUEST_CODE:
                if (resultCode == RESULT_OK && null != data && requestCode == 2) {// 裁剪返回

                    try {
                        Bitmap bitmap = Bimp.getLoacalBitmap(drr.get(drr
                                .size() - 1));
                        bitmap = Bimp.createFramedPhoto(480, 480, bitmap,
                                (int) (dp * 1.6f));
                        // bitmap = Bimp.createFramedPhoto(1280, 1280, bitmap,
                        // (int) (dp * 0.6f));
                        // bmp.add(bitmap);
                        // gridviewInit();
                        img_send_imageView.setImageBitmap(bitmap);// 图片赋值
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(HomeActivity.this, "未知错误!",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    int jg = drr.size();
                    drr.remove(jg - 1);
                }
                break;
            case SELECTIMG_SEARCH:
                if (resultCode == RESULT_OK && null != data) {
                }
                break;
        }
    }

    String imageName = "myImageIcon";

    private void startPhotoZoom(Uri uri) {
        try {
            // 获取系统时间 然后将裁剪后的图片保存至指定的文件夹
            SimpleDateFormat sDateFormat = new SimpleDateFormat(
                    "yyyyMMddhhmmss");
            String address = sDateFormat.format(new java.util.Date());
            if (!FileUtils.isFileExist("")) {
                FileUtils.createSDDir("");

            }
            drr.add(FileUtils.SDPATH + imageName + ".JPEG");
            Uri imageUri = Uri.parse("file:///sdcard/formats/" + imageName
                    + ".JPEG");

            final Intent intent = new Intent("com.android.camera.action.CROP");

            // 照片URL地址
            intent.setDataAndType(uri, "image/*");

            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 1080);
            intent.putExtra("outputY", 1080);
            // intent.putExtra("outputX", 1280);
            // intent.putExtra("outputY", 1280);
            // 输出路径
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            // 输出格式
            intent.putExtra("outputFormat",
                    Bitmap.CompressFormat.JPEG.toString());
            // 不启用人脸识别
            intent.putExtra("noFaceDetection", false);
            intent.putExtra("return-data", false);
            startActivityForResult(intent, CUT_PHOTO_REQUEST_CODE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
