/*
 * Copyright (c) 2015.
 * All Rights Reserved.
 */

package com.watershooter.lighting.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * 屏幕计算工具类
 * <ul>
 * <li>{@link #getWidth(Context)}</li>
 * <li>{@link #getHeight(Context)}</li>
 * <li>{@link #getStatusHeight(Context)}</li>
 * <li>{@link #snapShotWithoutStatusBar(Activity)}</li>
 * <li>{@link #snapShotWithStatusBar(Activity)}</li>
 * </ul>
 * Created by macchen on 15/3/26.
 */
public class WindowUtils {

    /**
     * 屏幕的宽度
     */
    private static int width = -1;
    /**
     * 屏幕的高度
     */
    private static int height = -1;

    /**
     * 禁止构造
     */
    private WindowUtils() {
        throw new AssertionError();
    }

    /**
     * 计算屏幕的宽高
     *
     * @param context
     */
    private static void calc(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels;
    }

    /**
     * 获取屏幕的宽度
     * <p>如果宽度未初始化则调用{@link #calc(Context)}来计算一次初始化宽高</p>
     *
     * @param context 上下文
     * @return
     */
    public static int getWidth(Context context) {
        if (width == -1) {
            calc(context);
        }
        return width;
    }

    /**
     * 获取屏幕的高度
     * <p>如果高度未初始化则调用{@link #calc(Context)}来计算一次初始化宽高</p>
     *
     * @param context 上下文
     * @return
     */
    public static int getHeight(Context context) {
        if (height == -1) {
            calc(context);
        }
        return height;
    }

    /**
     * 获取状态栏的高度
     *
     * @param context 上下文
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity 活动窗口
     * @return
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getWidth(activity);
        int height = getHeight(activity);
        Bitmap bp;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity 活动窗口
     * @return
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int width = getWidth(activity);
        int height = getHeight(activity);
        Bitmap bp;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;

    }
}
