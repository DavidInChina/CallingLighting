package com.watershooter.lighting.common.utils;

import android.content.Context;

import com.sdsmdg.tastytoast.TastyToast;

/**
 * Created by davidinchina on 2016/11/10.
 */

public class ToastUtils {
    public static void showWarning(Context mContext, String msg) {
        TastyToast.makeText(mContext, msg, TastyToast.LENGTH_SHORT, TastyToast.WARNING);
    }

    public static void showSuccess(Context mContext, String msg) {
        TastyToast.makeText(mContext, msg, TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
    }

    public static void showError(Context mContext, String msg) {
        TastyToast.makeText(mContext, msg, TastyToast.LENGTH_SHORT, TastyToast.ERROR);
    }

    public static void showInfo(Context mContext, String msg) {
        TastyToast.makeText(mContext, msg, TastyToast.LENGTH_SHORT, TastyToast.INFO);
    }

    public static void showDefault(Context mContext, String msg) {
        TastyToast.makeText(mContext, msg, TastyToast.LENGTH_SHORT, TastyToast.DEFAULT);
    }
}
