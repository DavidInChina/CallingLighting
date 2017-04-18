package com.bdkj.tele.record.common.base;

import com.watershooter.lighting.ApplicationContext;
import com.watershooter.lighting.common.utils.AppUtils;
import com.watershooter.lighting.common.utils.LConfigUtils;
import com.watershooter.lighting.common.utils.LogUtils;
import com.watershooter.lighting.common.utils.WindowUtils;
import com.zhy.http.okhttp.utils.Base64Util;
import com.zhy.http.okhttp.utils.MD5Utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by davidinchina on 16/9/30.
 */

public class Params {
    /**
     * 共同的参数部分
     *
     * @return
     */
    public static Map<String, String> getCommonParams() {
        Map<String, String> param = new HashMap<>();
        param.put("cVer", AppUtils.getVersionName(ApplicationContext.mContext));
        param.put("network", "wifi");
        param.put("client", "android");
        param.put("osVer", android.os.Build.VERSION.RELEASE);
        param.put("screen", WindowUtils.getWidth(ApplicationContext.mContext) + "x" + WindowUtils.getHeight(ApplicationContext.mContext));
        String uuid = LConfigUtils.getString(ApplicationContext.mContext, "appconfig.uuid");
        if (uuid == null || "".equals(uuid)) {
            uuid = UUID.randomUUID().toString();
            LConfigUtils.setString(ApplicationContext.mContext, "appconfig.uuid", uuid);
        }
        param.put("uuid", uuid);
        return param;
    }

    /**
     * 获取参数名
     *
     * @return
     */
    public static String getParamName() {
        return "parameters";
    }

    /**
     * 获取验证码参数
     *
     * @return
     */
    public static String getSendVerifyCode(String phone, int type) {
        Map<String, String> param = getCommonParams();
        param.put("phone", phone);
        param.put("type", type + "");
//        LinkedHashMap params = getCommonParams();
        JSONObject jsonObject = new JSONObject(param);
        LogUtils.e(jsonObject.toString());//做一下打印
        return Base64Util.getBase64(jsonObject.toString());
    }
}
