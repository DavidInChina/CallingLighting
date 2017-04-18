package com.watershooter.lighting.common.utils;

import android.content.Context;
import android.os.Bundle;

import com.watershooter.lighting.common.bundle.BundleType;
import com.watershooter.lighting.common.bundle.BundleValue;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * 内存清理后数据的保存和恢复工具类
 * Created by macchen on 15/4/28.
 */
public class BundleUtils {

    /**
     * 禁止构造方法访问
     */
    private BundleUtils() {
        throw new AssertionError();
    }

    /**
     * 保存数据
     *
     * @return
     */
    public static void save(Context mContext, Bundle outSate) {
        Class<?> handlerType = mContext.getClass();

        //开始注解
        Field[] fields = handlerType.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                try {
                    BundleValue bundleValue = field.getAnnotation(BundleValue.class);
                    if(bundleValue==null)
                        continue;
                    field.setAccessible(true);
                    BundleType value = bundleValue.type();
                    String name = bundleValue.name();
                    if (name != null) {
                        name = field.getName();
                    }
                    LogUtils.i("BundleUtils--save--"+name+"-----"+field.get(mContext));
                    switch (value) {
                        case BOOLEAN:
                            outSate.putBoolean(name, field.getBoolean(mContext));
                            break;
                        case BYTE:
                            outSate.putByte(name, field.getByte(mContext));
                            break;
                        case DOUBLE:
                            outSate.putDouble(name, field.getDouble(mContext));
                            break;
                        case FLOAT:
                            outSate.putFloat(name, field.getFloat(mContext));
                            break;
                        case INTEGER:
                            outSate.putInt(name, field.getInt(mContext));
                            break;
                        case LONG:
                            outSate.putLong(name, field.getLong(mContext));
                            break;
                        case SERIALIZABLE:
                            outSate.putSerializable(name, (Serializable) field.get(mContext));
                            break;
                        case SHORT:
                            outSate.putShort(name, field.getShort(mContext));
                            break;
                        case STRING:
                            outSate.putString(name, (String) field.get(mContext));
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    LogUtils.e(field.getName() + "保存错误!");
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 恢复数据
     *
     * @return
     */
    public static void restore(Context mContext, Bundle savedInstanceState) {
        Class<?> handlerType = mContext.getClass();

        //开始注解
        Field[] fields = handlerType.getDeclaredFields();
        LogUtils.i("fields--length---"+fields.length);
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                try {
                    BundleValue bundleValue = field.getAnnotation(BundleValue.class);
                    if(bundleValue==null)
                        continue;
                    field.setAccessible(true);
                    BundleType value = bundleValue.type();
                    String name = bundleValue.name();
                    if (name != null) {
                        name = field.getName();
                    }
                    if (savedInstanceState.containsKey(name)) {
                        LogUtils.i("BundleUtils--restore--"+name+"-----"+field.get(mContext));
                        switch (value) {
                            case BOOLEAN:
                                field.setBoolean(mContext, savedInstanceState.getBoolean(name));
                                break;
                            case BYTE:
                                field.setByte(mContext, savedInstanceState.getByte(name));
                                break;
                            case DOUBLE:
                                field.setDouble(mContext, savedInstanceState.getDouble(name));
                                break;
                            case FLOAT:
                                field.setFloat(mContext, savedInstanceState.getFloat(name));
                                break;
                            case INTEGER:
                                field.setInt(mContext, savedInstanceState.getInt(name));
                                break;
                            case LONG:
                                field.setLong(mContext, savedInstanceState.getLong(name));
                                break;
                            case SERIALIZABLE:
                                field.set(mContext, savedInstanceState.getSerializable(name));
                                break;
                            case SHORT:
                                field.setShort(mContext, savedInstanceState.getShort(name));
                                break;
                            case STRING:
                                field.set(mContext, savedInstanceState.getString(name));
                                break;
                            default:
                                break;
                        }
                    }
                } catch (Exception e) {
                    LogUtils.e(field.getName() + "恢复错误!");
                    e.printStackTrace();
                }
            }

        }
    }
}
