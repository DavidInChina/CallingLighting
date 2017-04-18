package com.zhy.http.okhttp.callback;

import android.app.AlertDialog;
import android.content.Context;

import com.zhy.http.okhttp.utils.ToastUtils;

import dmax.dialog.SpotsDialog;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public abstract class Callback {
    protected Class resultClass;
    protected Context mContext;
    protected boolean showLoading = true;//默认显示加载中
    AlertDialog dialog;

    /**
     * UI Thread
     *
     * @param request
     */
    public void onBefore(Request request, int id) {
        if (showLoading) {
            dialog = new SpotsDialog(mContext, "加载中...");
            dialog.show();
        }

    }

    /**
     * UI Thread
     *
     * @param
     */
    public void onAfter(int id) {
        if (showLoading) {
            dialog.dismiss();
        }
    }

    /**
     * UI Thread
     *
     * @param progress
     */
    public void inProgress(float progress, long total, int id) {

    }

    /**
     * if you parse reponse code in parseNetworkResponse, you should make this method return true.
     *
     * @param response
     * @return
     */
    public boolean validateReponse(Response response, int id) {
        return response.isSuccessful();
    }

    /**
     * Thread Pool Thread
     *
     * @param response
     */
    public abstract Object parseNetworkResponse(Response response, int id) throws Exception;

    public void onError(Call call, Exception e, int id) {
        ToastUtils.showError(mContext, "请求失败!");
    }

    public abstract void onSuccess(Object response, int id);

    public abstract void onSuccess();

    public abstract void onFault();


    public abstract void onResponse(Object response, int id) throws Exception;


    public static Callback CALLBACK_DEFAULT = new Callback() {

        @Override
        public Object parseNetworkResponse(Response response, int id) throws Exception {
            return null;
        }

        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onSuccess(Object response, int id) {

        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onFault() {

        }

        @Override
        public void onResponse(Object response, int id) {

        }

    };

}