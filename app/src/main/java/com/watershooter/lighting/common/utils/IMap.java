/*
 * Copyright (c) 2015.
 * All Rights Reserved.
 */

package com.watershooter.lighting.common.utils;


import java.util.HashMap;


/**
 * 自定义IMap,继承于HashMap,用于防止解析Json时出现{@link NullPointerException}异常
 */
public class IMap extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    /**
     * 解析json时错误的返回数据格式
     */
    private final String NULL = "null";

    private final String ERROR_STRING = "";

    /**
     * 如果getInt为空时的默认值
     */
    private int DEFAULT_INT_VALUE = 0;

    /**
     * 如果getString为空时的默认值
     */
    private String DEFAULT_STRING_VALUE = "";

    /**
     * 如果getBoolean为空时的默认值
     */
    private Boolean DEFAULT_BOOLEAN_VALUE = false;

    /**
     * 如果getFloat为空时的默认值
     */
    private float DEFAULT_FLOAT_VALUE = 0.0f;

    /**
     * 获取一个整型的数据
     * <p>指定默认值为{@link #DEFAULT_INT_VALUE}</p>
     *
     * @param key 需要获取值对应的key
     * @return
     */
    public int getInt(Object key) {

        return getInt(key, DEFAULT_INT_VALUE);
    }

    /**
     * 获取一个整型的数据
     * <p>判断指定的键是否存在并且不抛出{@link Exception}，存在则获取值，反之返回默认值</p>
     *
     * @param key          需要获取值对应的key
     * @param defaultValue 默认值
     * @return
     */
    public int getInt(Object key, int defaultValue) {
        int value = 0;
        if (super.containsKey(key)) {
            try {
                value = Integer.parseInt(super.get(key).toString().trim());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                value = defaultValue;
                //LogUtils.i(key + " convert int errors!");
            }
        } else {
            value = defaultValue;
           // LogUtils.i("In the JSON string does not exist in the " + key);
        }
        return value;
    }

    /**
     * 获取一个字符串的数据
     * <p>指定默认值为{@link #DEFAULT_STRING_VALUE}</p>
     *
     * @param key 需要获取值对应的key
     * @return
     */
    public String getString(Object key) {

        return getString(key, DEFAULT_STRING_VALUE);
    }

    /**
     * 获取一个字符串的数据
     * <p>判断指定的键是否存在并且不抛出{@link Exception}，存在则获取值，反之返回默认值</p>
     *
     * @param key          需要获取值对应的key
     * @param defaultValue 默认值
     * @return
     */
    public String getString(Object key, String defaultValue) {

        String value = "";
        if (super.containsKey(key)) {
            try {
                value = super.get(key).toString();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                value = defaultValue;
               // LogUtils.i(key + " convert String errors!");
            }
            if (NULL.equals(value) || ERROR_STRING.equals(value)
                    || "".equals(value)) {
                value = defaultValue;
            }
        } else {
            value = defaultValue;
           // LogUtils.i("In the JSON string does not exist in the " + key);
        }
        return value;
    }

    /**
     * 获取一个布尔型的数据
     * <p>指定默认值为{@link #DEFAULT_BOOLEAN_VALUE}</p>
     *
     * @param key 需要获取值对应的key
     * @return
     */
    public boolean getBoolean(Object key) {

        return getBoolean(key, DEFAULT_BOOLEAN_VALUE);
    }

    /**
     * 获取一个布尔型的数据
     * <p>判断指定的键是否存在并且不抛出{@link Exception}，存在则获取值，反之返回默认值</p>
     *
     * @param key          需要获取值对应的key
     * @param defaultValue 默认值
     * @return
     */
    public boolean getBoolean(Object key, boolean defaultValue) {

        boolean value = false;
        if (super.containsKey(key)) {
            try {
                value = Boolean.parseBoolean(super.get(key).toString().trim());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                value = defaultValue;
                //LogUtils.i(key + " convert Boolean errors!");
            }

        } else {
            value = defaultValue;
            //LogUtils.i("In the JSON string does not exist in the " + key);
        }
        return value;
    }

    /**
     * 获取一个浮点型的数据
     * <p>指定默认值为{@link #DEFAULT_FLOAT_VALUE}</p>
     *
     * @param key 需要获取值对应的key
     * @return
     */
    public float getFloat(Object key) {

        return getFloat(key, DEFAULT_FLOAT_VALUE);
    }

    /**
     * 获取一个浮点型的数据
     * <p>判断指定的键是否存在并且不抛出{@link Exception}，存在则获取值，反之返回默认值</p>
     *
     * @param key          需要获取值对应的key
     * @param defaultValue 默认值
     * @return
     */
    public float getFloat(Object key, float defaultValue) {

        float value;
        if (super.containsKey(key)) {
            try {
                value = Float.parseFloat(super.get(key).toString().trim());
            } catch (Exception e) {
                value = defaultValue;
                e.printStackTrace();
               // LogUtils.i(key + " convert Float errors!");
            }
        } else {
            value = defaultValue;
           // LogUtils.i("In the JSON string does not exist in the " + key);
        }
        return value;
    }

    @Override
    public Object get(Object key) {

        // TODO Auto-generated method stub
        if (!this.containsKey(key)) {
            return null;
        }
        return super.get(key);
    }

}
