package com.watershooter.lighting.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.watershooter.lighting.common.utils.LogUtils;

import java.util.List;

public class TelListenerService extends Service {
    // 电话管理器
    private TelephonyManager telephonyManager;
    // 监听器对象
    private PhoneListener listener;
    private String phoneNum = "";
    private int type = 0;//0来电,1去电
    private boolean lighting = false;
    private Camera camera;
    private Boolean isShanshuo;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 服务创建的时候调用的方法
     */
    @Override
    public void onCreate() {
        // 后台监听电话的呼叫状态。
        // 得到电话管理器
        LogUtils.e("创建监听电话服务!");
        telephonyManager = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        listener = new PhoneListener();
        telephonyManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (null != intent && null != intent.getStringExtra("CallNum")) {
            phoneNum = intent.getStringExtra("CallNum");
            type = 1;//去电调用
            Log.e("去电:", phoneNum);
        }
        return super.onStartCommand(intent, flags, startId);
    }


    private class PhoneListener extends PhoneStateListener {
        // 当电话的呼叫状态发生变化的时候调用的方法
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            try {
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE://空闲状态。
                        if (lighting) {
                            //关闭闪光灯
                            closeStar();
                        }
                        break;
                    case TelephonyManager.CALL_STATE_RINGING://零响状态。
                        phoneNum = incomingNumber;
                        type = 0;//来电调用
                        Log.e("来电:", incomingNumber);
                        //这里开启闪光灯
                        lighting = true;
                        openStar();
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK://通话状态
                        if (lighting) {
                            //关闭闪光灯
                            closeStar();
                        }
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 开启闪烁
     */
    public void openStar() {
        isShanshuo = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isShanshuo) {
                    open();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    close();
                    try {
                        Thread.sleep(100);
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


    /**
     * 判断当前应用是否后台运行
     *
     * @param context
     * @return
     */
    public boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    LogUtils.e(String.format("Background App:" + appProcess.processName));
                    return true;
                } else {
                    LogUtils.e(String.format("Foreground App:", appProcess.processName));
                    return false;
                }
            }
        }
        return false;
    }


    /**
     * 服务销毁的时候调用的方法
     * 保护监听服务
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        // 取消电话的监听,采取线程守护的方法，当一个服务关闭后，开启另外一个服务，除非你很快把两个服务同时关闭才能完成
        Intent i = new Intent(this, TelProtectService.class);
        startService(i);
        listener = null;
    }
}