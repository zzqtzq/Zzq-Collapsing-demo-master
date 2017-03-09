package com.zzq.zzq_collapsing_demo_master.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * 加载数据dialog ProgressDialogUtil.java
 *
 * @author 曾志强 创建于：2016-4-27
 */
public class ProgressDialogUtil {
    private static ProgressDialog dialog;

    public static ProgressDialog show(Context context) {
        if (dialog != null && dialog.isShowing()) {
            return dialog;
        }
        dialog = new ProgressDialog(context);
        dialog.setMessage("加载中。。。");
        // dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
    }

    public static ProgressDialog show(Context context, String str) {
        if (dialog != null && dialog.isShowing()) {
            return dialog;
        }
        dialog = new ProgressDialog(context);
        dialog.setMessage(str + "。。。");
        // dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
    }

    public static void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
