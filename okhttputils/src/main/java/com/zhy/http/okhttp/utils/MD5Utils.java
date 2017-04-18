/*
 * Copyright (c) 2015.
 * All Rights Reserved.
 */

package com.zhy.http.okhttp.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * Created by macchen on 15/3/26.
 */
public class MD5Utils {

    /**
     * 禁止构造
     */
    private MD5Utils() {
        throw new AssertionError();
    }
    /**
     * MD5 加密
     * <p>如果传入的字符串无法通过utf-8获取字节数组，则抛出{@link UnsupportedEncodingException}，返回null</p>
     *
     * @param str 需要加密的字符串
     * @return
     */
    public static String getMD5Str(String str) {
        String md5 = null;
        try {
            md5 = getMD5Str(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return md5;
    }

    /**
     * MD5 加密
     *
     * @param data 需要加密的字符串的字节数组
     * @return
     */
    public static String getMD5Str(byte[] data) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(data);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString();
    }

    /**
     * MD5 加密（多个对象同时加密)
     *
     * @param objects 多个对象
     * @return
     */
    public static String getMD5Str(Object... objects) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object object : objects) {
            stringBuilder.append(object.toString());
        }
        return getMD5Str(stringBuilder.toString());
    }
}
