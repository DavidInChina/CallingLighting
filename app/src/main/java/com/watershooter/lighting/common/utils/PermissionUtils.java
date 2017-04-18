package com.watershooter.lighting.common.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by davidinchina on 2017/1/16.
 * 权限工具类
 */

public class PermissionUtils {

    public interface CheckPermissionListener {
        //某个权限已被允许
        public void getPermissionEnabled(int permissionCode);
    }

    /**
     * 使得权限被允许
     *
     * @param mContext
     * @param permission     要请求的权限全称
     * @param permissionName 要请求的权限名称
     * @return
     */
    public static void makePermisionEnable(Context mContext, CheckPermissionListener listener,
                                           String permissionName, int permissionCode, String permission) {
        PackageManager pm = mContext.getPackageManager();
        boolean enable = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission(permission, mContext.getPackageName()));
        if (enable) {//拥有这个权限,就可以执行下一步
            listener.getPermissionEnabled(permissionCode);
        } else {//没有这个权限,需要申请,申请成功就执行,否则不执行
            LogUtils.e("木有这个权限");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0系统的动态权限申请

            } else {//6.0以下的权限,提示
                showConfirmDialog(mContext, listener, permissionName, permissionCode);
            }
        }
    }

    public static void requestPermission() {

    }

    public static void showConfirmDialog(final Context mContext, final CheckPermissionListener listener, String permissionName, final int permissionCode) {
        final MaterialDialog dialog = new MaterialDialog(mContext);
        dialog.setTitle("温馨提示").setMessage("敏感权限 " + permissionName +
                " 未被系统允许,需要移步应用管理设置此权限,当前继续运行可能有未知错误。");
        dialog.setPositiveButton("运行", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getPermissionEnabled(permissionCode);
            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showWarning(mContext, "操作已取消!");
            }
        }).setCanceledOnTouchOutside(false).show();
    }
}
