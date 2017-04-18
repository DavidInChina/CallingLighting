package com.bdkj.tele.record.common.base;


/**
 * 参数
 * Created by macchen on 15/5/13.
 */
public class BaseParams {

    protected String mResultClassName = "";
    protected String mRequestPath = "";

    /**
     * 获取接口请求地址
     *
     * @return
     * @author wanglx 2014年7月29日
     */
    public String getRequestPath() {
        return "" + mRequestPath;
    }

    public void setmRequestPath(String mRequestPath) {
        this.mRequestPath = mRequestPath;
    }

    /**
     * 获取 果 名
     *
     * @return
     * @author wanglx 2014年7月29日
     */
    public String getResultClassName() {
        return mResultClassName;
    }

    public void setmResultClassName(String mResultClassName) {
        this.mResultClassName = mResultClassName;
    }
}
