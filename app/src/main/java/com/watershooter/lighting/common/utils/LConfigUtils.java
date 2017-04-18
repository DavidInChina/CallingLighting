/*
 * Copyright (c) 2015.
 * All Rights Reserved.
 */

package com.watershooter.lighting.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences处理工具类
 * Created by macchen on 15/3/26.
 */
public class LConfigUtils {
    /**
     * 默认获取{@link SharedPreferences}时的flag
     */
    private final static int PREFERENCES_MODE = Context.MODE_PRIVATE;

    /**
     * 调用{@link #getInt(Context, String)}时默认的值
     * <p>{@link #getInt(Context, String)}如果传入的key不存在，返回{@link #INT_PREFERENCES_DEFAULT_VALUE}</p>
     */
    private final static int INT_PREFERENCES_DEFAULT_VALUE = 0;

    /**
     * 调用{@link #getLong(Context, String)}时的默认值
     */
    private final static long LONG_PREFERENCES_DEFAULT_VALUE = 0;

    /**
     * 调用{@link #getFloat(Context, String)}时的默认值
     */
    private final static float FLOAT_PREFERENCES_DEFAULT_VALUE = 0f;

    /**
     * 调用{@link #getBoolean(Context, String)}时的默认值
     */
    private final static boolean BOOLEAN_PREFERENCES_DEFAULT_VALUE = false;

    /**
     * 调用{@link #getString(Context, String)}时的默认值
     */
    private final static String STRING_PREFERENCES_DEFAULT_VALUE = "";

    /**
     * 分隔SharedPreferences的配置文件名与字段,默认用'.'号分隔文件名与字段
     * {@link Context#getSharedPreferences(String, int)}第一个参数表示为NAME
     * {@link SharedPreferences.Editor#putInt(String, int)}第一个参数表示为KEY
     * <pre>
     *     android.keyname = (NAME='android',KEY='keyname')
     *     share.edit = (NAME='share',KEY='edit')
     * </pre>
     */
    public final static String SPLIT_CHAR = "\\.";


    /**
     * 禁止构造
     */
    private LConfigUtils() {
        throw new AssertionError();
    }

    /**
     * 获取指定的SharedPreferences
     *
     * @param context 上下文
     * @param name    配置文件名称
     * @return
     */
    public static SharedPreferences getPreferences(Context context, String name) {
        SharedPreferences preferences = context.getSharedPreferences(name,
                PREFERENCES_MODE);
        return preferences;
    }

    /**
     * 设置整型的SharedPreferences
     * <p>如果传入的name_key参数为空或不是按指定格式定义则抛出{@link RuntimeException}</p>
     *
     * @param context  上下文
     * @param name_key 配置文件_关键词
     * @param value    值
     * @param mode     模式
     */
    public static void setInt(Context context, String name_key, int value,
                              int mode) {
        String[] splits = name_key.split(SPLIT_CHAR);
        if (splits.length != 2) {
            throw new RuntimeException(
                    "输入的配置文件名和字段不正确");
        }
        SharedPreferences preferences = context.getSharedPreferences(splits[0],
                mode);
        preferences.edit().putInt(splits[1], value).commit();
    }

    /**
     * 调用{@link #setInt(Context, String, int, int)},传入默认的数据获取模式{@link #PREFERENCES_MODE}
     *
     * @param context  上下文
     * @param name_key 配置文件_关键词
     * @param value    关键词的值
     */
    public static void setInt(Context context, String name_key, int value) {

        setInt(context, name_key, value, PREFERENCES_MODE);
    }

    /**
     * 从配置文件中获取指定字段名的整型值
     * <p>如果传入的name_key参数为空或不是按指定格式定义则抛出{@link RuntimeException}</p>
     *
     * @param context      上下文
     * @param name_key     配置文件名_关键词
     * @param defaultValue 默认值
     * @param mode         模式
     * @return
     */
    public static int getInt(Context context, String name_key,
                             int defaultValue, int mode) {
        String[] splits = name_key.split(SPLIT_CHAR);
        if (splits.length != 2) {
            throw new RuntimeException(
                    "输入的配置文件名和字段不正确");
        }
        SharedPreferences preferences = context.getSharedPreferences(splits[0],
                mode);
        return preferences.getInt(splits[1], defaultValue);
    }

    /**
     * 调用{@link #getInt(Context, String, int, int)}从SharedPreferences中获取一个int数
     * <p>默认的mode为{@link #PREFERENCES_MODE}</p>
     *
     * @param context      上下文
     * @param name_key     配置文件名_关键词
     * @param defaultValue 默认值
     * @return
     */
    public static int getInt(Context context, String name_key, int defaultValue) {

        return getInt(context, name_key, defaultValue, PREFERENCES_MODE);
    }

    /**
     * 调用{@link #getInt(Context, String, int)}从SharedPreferences中获取一个int数
     * <p>默认的mode为{@link #PREFERENCES_MODE}</p>
     * <p>如果获取的文件名和关键词不存在则返回默认值{@link #INT_PREFERENCES_DEFAULT_VALUE}</p>
     *
     * @param context  上下文
     * @param name_key 配置文件名_关键词
     * @return
     */
    public static int getInt(Context context, String name_key) {

        return getInt(context, name_key, INT_PREFERENCES_DEFAULT_VALUE);
    }


    /**
     * 设置长整型的SharedPreferences
     * <p>如果传入的name_key参数为空或不是按指定格式定义则抛出{@link RuntimeException}</p>
     *
     * @param context  上下文
     * @param name_key 配置文件_关键词
     * @param value    值
     * @param mode     模式
     */
    public static void setLong(Context context, String name_key, long value,
                               int mode) {
        String[] splits = name_key.split(SPLIT_CHAR);
        if (splits.length != 2) {
            throw new RuntimeException(
                    "输入的配置文件名和字段不正确");
        }
        SharedPreferences preferences = context.getSharedPreferences(splits[0],
                mode);
        preferences.edit().putLong(splits[1], value).commit();
    }

    /**
     * 调用{@link #setLong(Context, String, long, int)}设置长整型的SharedPreferences
     * <p>指定默认的获取方式{@link #PREFERENCES_MODE}</p>
     *
     * @param context  上下文
     * @param name_key 配置文件_关键词
     * @param value    值
     */
    public static void setLong(Context context, String name_key, long value) {
        setLong(context, name_key, value, PREFERENCES_MODE);
    }

    /**
     * 从配置文件中获取指定字段名的长整型值
     * <p>如果传入的name_key参数为空或不是按指定格式定义则抛出{@link RuntimeException}</p>
     *
     * @param context      上下文
     * @param name_key     配置文件名_关键词
     * @param defaultValue 默认值
     * @param mode         模式
     * @return
     */
    public static Long getLong(Context context, String name_key,
                               long defaultValue, int mode) {
        String[] splits = name_key.split(SPLIT_CHAR);
        if (splits.length != 2) {
            throw new RuntimeException(
                    "输入的配置文件名和字段不正确");
        }
        SharedPreferences preferences = context.getSharedPreferences(splits[0],
                mode);
        return preferences.getLong(splits[1], defaultValue);
    }

    /**
     * 调用{@link #getLong(Context, String, long, int)}从配置文件中获取指定字段名的长整型值
     * <p>指定获取的方式为私有的{@link #PREFERENCES_MODE}</p>
     *
     * @param context      上下文
     * @param name_key     配置文件名_关键词
     * @param defaultValue 默认值
     * @return
     */
    public static long getLong(Context context, String name_key,
                               long defaultValue) {

        return getLong(context, name_key, defaultValue, PREFERENCES_MODE);
    }

    /**
     * 调用{@link #getLong(Context, String, long)}从配置文件中获取指定字段名的长整型值
     * <p>指定获取的方式为私有的{@link #PREFERENCES_MODE}</p>
     * <p>指定获取到的默认值{@link #LONG_PREFERENCES_DEFAULT_VALUE}</p>
     *
     * @param context  上下文
     * @param name_key 配置文件名_关键词
     * @return
     */
    public static long getLong(Context context, String name_key) {

        return getLong(context, name_key, LONG_PREFERENCES_DEFAULT_VALUE);
    }


    /**
     * 设置布尔型的SharedPreferences
     * <p>如果传入的name_key参数为空或不是按指定格式定义则抛出{@link RuntimeException}</p>
     *
     * @param context  上下文
     * @param name_key 配置文件_关键词
     * @param value    值
     * @param mode     模式
     */
    public static void setBoolean(Context context, String name_key,
                                  boolean value, int mode) {
        String[] splits = name_key.split(SPLIT_CHAR);
        if (splits.length != 2) {
            throw new RuntimeException(
                    "输入的配置文件名和字段不正确");
        }
        SharedPreferences preferences = context.getSharedPreferences(splits[0],
                mode);
        preferences.edit().putBoolean(splits[1], value).commit();
    }

    /**
     * 调用{@link #setBoolean(Context, String, boolean, int)}设置布尔型的SharedPreferences
     * <p>指定获取的模式为{@link #PREFERENCES_MODE}</p>
     *
     * @param context  上下文
     * @param name_key 配置文件_关键词
     * @param value    值
     */
    public static void setBoolean(Context context, String name_key,
                                  boolean value) {

        setBoolean(context, name_key, value, PREFERENCES_MODE);
    }

    /**
     * 从配置文件中获取指定字段名的布尔值
     * <p>如果传入的name_key参数为空或不是按指定格式定义则抛出{@link RuntimeException}</p>
     *
     * @param context      上下文
     * @param name_key     配置文件名_关键词
     * @param defaultValue 默认值
     * @param mode         模式
     * @return
     */
    public static boolean getBoolean(Context context, String name_key,
                                     boolean defaultValue, int mode) {
        String[] splits = name_key.split(SPLIT_CHAR);
        if (splits.length != 2) {
            throw new RuntimeException(
                    "输入的配置文件名和字段不正确");
        }
        SharedPreferences preferences = context.getSharedPreferences(splits[0],
                mode);
        return preferences.getBoolean(splits[1], defaultValue);
    }

    /**
     * 调用{@link #getBoolean(Context, String, boolean, int)}从配置文件中获取指定字段名的布尔值
     * 指定默认的获取模式{@link #PREFERENCES_MODE}
     *
     * @param context      上下文
     * @param name_key     配置文件名_关键词
     * @param defaultValue 默认值
     * @return
     */
    public static boolean getBoolean(Context context, String name_key,
                                     boolean defaultValue) {

        return getBoolean(context, name_key, defaultValue, PREFERENCES_MODE);
    }

    /**
     * 调用{@link #getBoolean(Context, String, boolean)}从配置文件中获取指定字段名的布尔值
     * 指定默认的获取模式{@link #PREFERENCES_MODE}
     * 指定的默认值{@link #BOOLEAN_PREFERENCES_DEFAULT_VALUE}
     *
     * @param context  上下文
     * @param name_key 配置文件名_关键词
     * @return
     */
    public static boolean getBoolean(Context context, String name_key) {

        return getBoolean(context, name_key, BOOLEAN_PREFERENCES_DEFAULT_VALUE);
    }


    /**
     * 设置浮点型的SharedPreferences
     * <p>如果传入的name_key参数为空或不是按指定格式定义则抛出{@link RuntimeException}</p>
     *
     * @param context  上下文
     * @param name_key 配置文件_关键词
     * @param value    值
     * @param mode     模式
     */
    public static void setFloat(Context context, String name_key, float value,
                                int mode) {
        String[] splits = name_key.split(SPLIT_CHAR);
        if (splits.length != 2) {
            throw new RuntimeException(
                    "输入的配置文件名和字段不正确");
        }
        SharedPreferences preferences = context.getSharedPreferences(splits[0],
                mode);
        preferences.edit().putFloat(splits[1], value).commit();
    }

    /**
     * 调用{@link #setFloat(Context, String, float, int)}设置浮点型的SharedPreferences
     * 指定默认的获取模式{@link #PREFERENCES_MODE}
     *
     * @param context  上下文
     * @param name_key 配置文件_关键词
     * @param value    值
     */
    public static void setFloat(Context context, String name_key, float value) {

        setFloat(context, name_key, value, PREFERENCES_MODE);
    }

    /**
     * 从配置文件中获取指定字段名的浮点型值
     * <p>如果传入的name_key参数为空或不是按指定格式定义则抛出{@link RuntimeException}</p>
     *
     * @param context      上下文
     * @param name_key     配置文件名_关键词
     * @param defaultValue 默认值
     * @param mode         模式
     * @return
     */
    public static float getFloat(Context context, String name_key,
                                 float defaultValue, int mode) {
        String[] splits = name_key.split(SPLIT_CHAR);
        if (splits.length != 2) {
            throw new RuntimeException(
                    "输入的配置文件名和字段不正确");
        }
        SharedPreferences preferences = context.getSharedPreferences(splits[0],
                mode);
        return preferences.getFloat(splits[1], defaultValue);
    }

    /**
     * 调用{@link #getFloat(Context, String, float, int)}从配置文件中获取指定字段名的浮点型值
     * 指定默认的获取模式{@link #PREFERENCES_MODE}
     *
     * @param context      上下文
     * @param name_key     配置文件名_关键词
     * @param defaultValue 默认值
     * @return
     */
    public static float getFloat(Context context, String name_key,
                                 float defaultValue) {
        return getFloat(context, name_key, defaultValue, PREFERENCES_MODE);
    }

    /**
     * 调用{@link #getFloat(Context, String, float)}从配置文件中获取指定字段名的浮点型值
     * 指定默认的获取模式{@link #PREFERENCES_MODE}
     * 指定默认值{@link #FLOAT_PREFERENCES_DEFAULT_VALUE}
     *
     * @param context  上下文
     * @param name_key 配置文件名_关键词
     * @return
     */
    public static float getFloat(Context context, String name_key) {

        return getFloat(context, name_key, FLOAT_PREFERENCES_DEFAULT_VALUE);
    }


    /**
     * 设置字符串型的SharedPreferences
     * <p>如果传入的name_key参数为空或不是按指定格式定义则抛出{@link RuntimeException}</p>
     *
     * @param context  上下文
     * @param name_key 配置文件_关键词
     * @param value    值
     * @param mode     模式
     */
    public static void setString(Context context, String name_key,
                                 String value, int mode) {
        String[] splits = name_key.split(SPLIT_CHAR);
        if (splits.length != 2) {
            throw new RuntimeException(
                    "输入的配置文件名和字段不正确");
        }
        SharedPreferences preferences = context.getSharedPreferences(splits[0],
                mode);
        preferences.edit().putString(splits[1], value).commit();
    }

    /**
     * 设置字符串型的SharedPreferences
     * <p>如果传入的name_key参数为空或不是按指定格式定义则抛出{@link RuntimeException}</p>
     * <p>指定默认的获取模式{@link #PREFERENCES_MODE}</p>
     *
     * @param context  上下文
     * @param name_key 配置文件_关键词
     * @param value    值
     */
    public static void setString(Context context, String name_key, String value) {

        setString(context, name_key, value, PREFERENCES_MODE);
    }

    /**
     * 从配置文件中获取指定字段名的字符串值
     * <p>如果传入的name_key参数为空或不是按指定格式定义则抛出{@link RuntimeException}</p>
     *
     * @param context      上下文
     * @param name_key     配置文件名_关键词
     * @param defaultValue 默认值
     * @param mode         模式
     * @return
     */
    public static String getString(Context context, String name_key,
                                   String defaultValue, int mode) {
        String[] splits = name_key.split(SPLIT_CHAR);
        if (splits.length != 2) {
            throw new RuntimeException(
                    "输入的配置文件名和字段不正确");
        }
        SharedPreferences preferences = context.getSharedPreferences(splits[0],
                mode);
        return preferences.getString(splits[1], defaultValue);
    }

    /**
     * 从配置文件中获取指定字段名的字符串值
     * <p>如果传入的name_key参数为空或不是按指定格式定义则抛出{@link RuntimeException}</p>
     * <p>指定默认的获取模式{@link #PREFERENCES_MODE}</p>
     *
     * @param context      上下文
     * @param name_key     配置文件名_关键词
     * @param defaultValue 默认值
     * @return
     */
    public static String getString(Context context, String name_key,
                                   String defaultValue) {

        return getString(context, name_key, defaultValue, PREFERENCES_MODE);
    }

    /**
     * 从配置文件中获取指定字段名的字符串值
     * <p>如果传入的name_key参数为空或不是按指定格式定义则抛出{@link RuntimeException}</p>
     * <p>指定默认的获取模式{@link #PREFERENCES_MODE}</p>
     * <p>指定默认值{@link #STRING_PREFERENCES_DEFAULT_VALUE}</p>
     *
     * @param context  上下文
     * @param name_key 配置文件名_关键词
     * @return
     */
    public static String getString(Context context, String name_key) {

        return getString(context, name_key, STRING_PREFERENCES_DEFAULT_VALUE);
    }

    /**
     * 清空配置文件中的所有值
     *
     * @param context 上下文
     * @param preName 配置文件名称
     * @param mode    获取模式
     */
    public static void clearPreferences(Context context, String preName,
                                        int mode) {

        SharedPreferences preferences = context.getSharedPreferences(preName,
                mode);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 清空配置文件中的所有值
     * <p>调用{@link #clearPreferences(Context, String, int)}</p>
     * <p>指定模式{@link #PREFERENCES_MODE}</p>
     *
     * @param context 上下文
     * @param preName 配置文件名称
     */
    public static void clearPreferences(Context context, String preName) {

        clearPreferences(context, preName, PREFERENCES_MODE);
    }

}
