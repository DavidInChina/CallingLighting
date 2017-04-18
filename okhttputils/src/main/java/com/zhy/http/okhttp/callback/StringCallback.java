package com.zhy.http.okhttp.callback;

import android.graphics.Bitmap;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class StringCallback extends Callback {
    @Override
    public Object parseNetworkResponse(Response response, int id) throws IOException {
        return response.body().string();
    }
}
