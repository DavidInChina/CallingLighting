package com.watershooter.lighting;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.watershooter.lighting.activity.MainActivity;
import com.watershooter.lighting.common.base.Constants;
import com.watershooter.lighting.common.utils.FileUtils;
import com.watershooter.lighting.common.utils.IntentUtils;
import com.watershooter.lighting.common.utils.StorageUtils;
import com.watershooter.lighting.common.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;


/**
 * Created by DavidXu on 2015/9/6 0006.
 * <b>项目名：</b>电话销售<br/>
 * <b>包名：</b>com.bdkj.tele.record<br/>
 * <b>文件名：</b>ApplicationContext.java<br/>
 * <b>版本信息：</b><br/>
 * <b>日期：</b>2016/9/30<br/>
 * <b>Copyright (c)</b> 2015-2016南京红米网络科技-版权所有<br/>
 */


/**
 * <b>类名称：</b>ApplicationContext<br/>
 * <b>类描述：</b>应用入口，初始化应用配置系统变量<br/>
 * <b>创建人：</b>DavidXu<br/>
 * <b>修改人：</b>DavidXu<br/>
 * <b>修改时间：</b>2016/9/30<br/>
 * <b>修改备注：</b>无<br/>
 *
 * @version 1.0.0<br/>
 */
public class ApplicationContext extends Application {
    public static Context mContext;


    private static ApplicationContext application;

    public static ApplicationContext getApplication() {
        return application;
    }


    public final static int RE_START_APP = 0;
    public static Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RE_START_APP:
                    final Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mContext.startActivity(intent);
                    ((Activity) mContext).finish();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        application = this;
        initOkHttp();
    }

    public static void deleteDownload() {
        if (StorageUtils.isMount()) {
            File file = new File(StorageUtils.getStorageFile(),
                    Constants.UPLOAD_DOWNLOAD_DIRECTORY);
            if (!file.exists()) {
                FileUtils.createFolder(file);
            }
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    file2.delete();
                }
            }
        } else {
            Toast.makeText(mContext, "存储设备未挂载!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 删除用户信息
     *
     * @return
     */


    public void initOkHttp() {
        ClearableCookieJar cookieJar1 = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

//        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("TAG"))
                .cookieJar(cookieJar1)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
        OkHttpUtils.initClient(okHttpClient);

    }

    /**
     * 重启应用
     */
    public static void restartApplication(Context mContext) {
        final Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
        ((Activity) mContext).finish();
    }

    /**
     * 延时重启应用
     */
    public static void delayRestartApplication(Context mContext1) {
        mContext = mContext1;//更新了当前的上下文对象
        handler.sendEmptyMessageDelayed(RE_START_APP, 2000);
    }

    /**
     * 错误重启应用
     */
    public static void errorRestartApplication(Context mContext) {
        ToastUtils.showError(mContext, "数据错误重启!");
        final Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

    /**
     * 显示主页面
     *
     * @param context
     */
    public static void showMainActivity(Context context) {
        IntentUtils.launcher(context, MainActivity.class);
    }
}
