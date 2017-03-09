package com.zzq.zzq_collapsing_demo_master.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 志强 on 2017.1.4.
 */

public class MyToast {

    /**
     * 之前显示的内容
     */
    private static String oldMsg;
    /**
     * Toast对象
     */
    private static Toast toast = null;
    /**
     * 第一次时间
     */
    private static long oneTime = 0;
    /**
     * 第二次时间
     */
    private static long twoTime = 0;

    /**
     * 显示Toast
     *
     * @param context
     * @param message
     */
    public static void showToast2(Context context, String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(17, 0, 0);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (message.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = message;
                toast.setText(message);
                toast.show();
            }
        }
        oneTime = twoTime;
    }


    public static void showToast(Context context, String text) {
        if (context != null) {
            if (text != null && !text.equals("")) {
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.setGravity(17, 0, 0);

                try {
                    toast.show();
                } catch (Exception var4) {
                    var4.printStackTrace();
                }
            }

        }
    }

    public static void showToast(Context context, int id, int errCode) {
        if (context != null) {
            String text = context.getString(id);
            if (errCode != 0) {
                text = text + " (" + errCode + ")";
            }

            if (text != null && !text.equals("")) {
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.setGravity(17, 0, 0);

                try {
                    toast.show();
                } catch (Exception var6) {
                    var6.printStackTrace();
                }
            }

        }
    }

    public static void showToast(Context context, int id) {
        if (context != null) {
            String text = context.getString(id);
            if (text != null && !text.equals("")) {
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.setGravity(17, 0, 0);
                try {
                    toast.show();
                } catch (Exception var5) {
                    var5.printStackTrace();
                }
            }
        }
    }

    /**
     * 自定义title下方显示,
     *
     * @param msg
     */
   /* private static void makeToast(String msg) {
        if (toastView == null) {
            LayoutInflater inflater = (LayoutInflater) AppApplication.getInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            params = new LinearLayout.LayoutParams(AppApplication.mScreenWidth, ViewGroup.LayoutParams.MATCH_PARENT);
            toastView = inflater.inflate(R.layout.toast_custom_prompt, null);
        }
        TextView tv = (TextView) toastView.findViewById(R.id.tvTitleToast);
        tv.setLayoutParams(params);
        tv.setText(msg);

        toast = new Toast(AppApplication.getInstance());
        float hOffset = AppApplication.getInstance().getResources().getDimension(R.dimen.common_title_height);
        toast.setGravity(Gravity.TOP, 0, (int) hOffset);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastView);
        toast.show();
    }*/

}
