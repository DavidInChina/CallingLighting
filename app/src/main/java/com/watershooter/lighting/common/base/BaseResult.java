package com.watershooter.lighting.common.base;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;


public class BaseResult {

	 private String msg;
	 private int code;
	 private Object data;
	 private JsonElement result;


	/**
	 * 访问网络返回的提示
	 * 
	 * @return
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
	
	  /**
     * 获得信息码
     * @return 0：失败 1：成功 2:解析数据失败 3:网路加载错误
     * 
     */
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

	public JsonElement getResult() {
		return result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


	public void toResultType(String typeName) {
	        if (typeName == null || "".equals(typeName)) {
	            return;
	        }
	        try {
	            Class<?> c = Class.forName(typeName);
	            Gson gson = new GsonBuilder().create();
				setData(gson.fromJson(result, c));
	        } catch (ClassNotFoundException e) {
	        	setCode(2);
	            e.printStackTrace();
				Log.i("------->>>>>","解析数据失败");
	        }
	    }



}
