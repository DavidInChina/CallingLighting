package com.watershooter.lighting.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.watershooter.lighting.R;
import com.watershooter.lighting.baseviews.BaseFragmentActivity;
import com.watershooter.lighting.common.utils.ToastUtils;
import com.watershooter.lighting.service.TelListenerService;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseFragmentActivity {

    @BindView(R.id.btn_open_light)
    TextView btnOpenLight;
    @BindView(R.id.btn_close_light)
    TextView btnCloseLight;
    private Camera camera;
    private Boolean isShanshuo;
    private final int PERMISSION_PHONE = 04;//监听电话code
    @Override
    protected int getViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if (null == savedInstanceState) {
            checkPermissionEnable(PERMISSION_PHONE);
        }
    }
    /**
     * 权限检查以及申请的回调结果
     *
     * @param permissionCode
     */
    @Override
    public void getPermissionEnabled(int permissionCode) {
        switch (permissionCode) {
            case PERMISSION_PHONE:
                initCallService();
                break;
        }
    }
    public void initCallService() {
        Intent i = new Intent(this, TelListenerService.class);
        i.putExtra("CallNum", "");
        startService(i);
//        ToastUtils.showSuccess(mContext, "已启动电话监听!");
    }

    // 检测当前设备是否配置闪光灯
    boolean checkFlashlight() {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            ToastUtils.showError(mContext, "当前设备没有闪光灯");
            return false;
        }
        return true;
    }

    /**
     * 开启闪烁
     */
    public void openStar() {
        isShanshuo = true;
        btnOpenLight.setEnabled(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isShanshuo) {
                    open();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    close();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 关闭闪烁
     */
    public void closeStar() {
        isShanshuo = false;
        btnOpenLight.setEnabled(true);
    }

    /**
     * 打开闪光灯
     *
     * @return
     */
    private void open() {
        try {
            camera = Camera.open();
            camera.startPreview();
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭闪光灯
     *
     * @return
     */
    private void close() {
        try {
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameters);
            camera.release();
            camera = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.btn_open_light, R.id.btn_close_light})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_open_light:
                if (checkFlashlight()) {
                    openStar();
                }
                break;
            case R.id.btn_close_light:
                closeStar();
                break;
        }
    }

}
