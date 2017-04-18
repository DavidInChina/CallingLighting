/*
 * Copyright (c) 2015.
 * All Rights Reserved.
 */

package com.watershooter.lighting.common.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * 应用程序工具类
 * Created by macchen on 15/3/26.
 */
public class AppUtils {

    /**
     * 禁止方法构造
     */
    private AppUtils() {
        throw new AssertionError();
    }

    /**
     * 判断应用程序是否在后台运行
     * <ul>
     * <li>需要在AndroidManifest.xml中添加android.permission.GET_TASK权限</li>
     * </ul>
     *
     * @param context 上下文
     * @return 如果用户在前台则返回true, 否则返回false
     */
    public static boolean isApplicationInBackground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName topActivity = taskList.get(0).topActivity;
            if (topActivity != null && !topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取应用程序名称
     * <p>如果命名没有发现则抛出{@link PackageManager.NameNotFoundException}异常</p>
     *
     * @return
     */
    public static String getApplicationName(Context context) {

        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }

    /**
     * 获取软件版本号
     * <p>如果命名没有发现则抛出{@link PackageManager.NameNotFoundException}异常</p>
     *
     * @param context 上下文
     * @return
     */
    public static int getVersionCode(Context context) {

        int versionCode = 0;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取软件版本名称
     * <p>如果命名没有发现则抛出{@link PackageManager.NameNotFoundException}异常</p>
     *
     * @param context 上下文
     * @return
     */
    public static String getVersionName(Context context) {

        String versionCode = null;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return versionCode;
    }
}
