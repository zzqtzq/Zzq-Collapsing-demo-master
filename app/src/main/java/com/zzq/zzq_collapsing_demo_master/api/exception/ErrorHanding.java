package com.zzq.zzq_collapsing_demo_master.api.exception;

/**
 *
 */
public class ErrorHanding {

    public ErrorHanding() {

    }

    public static String handleError(Throwable throwable) {
        String message;
        if (throwable instanceof ServerException) {
            message = throwable.getMessage();
        } else {
            message = "连接服务器失败";
        }
        return message;
    }

}
