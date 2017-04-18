/*
 * Copyright (c) 2015.
 * All Rights Reserved.
 */

package com.watershooter.lighting.common.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * JSON数据处理工具类
 * Created by macchen on 15/3/26.
 */
public class JSONUtils {

    /**
     * 禁止构造
     */
    private JSONUtils() {
        throw new AssertionError();
    }

    /**
     * 将指定的json字符串数据转成 {@link IMap}<String, Object>对象
     * <pre>
     *     fromJson("") = new IMap();
     *     fromJson("[{'aaa':'bbb'}] = return {@link #fromJson(JSONObject)}
     *     fromJson("{'aaa':'bbb'} = return {@link #fromJson(JSONObject)}
     * </pre>
     *
     * @param jsonStr 解析的json字符串
     * @return
     */
    public static IMap fromJson(String jsonStr) {
        try {
            if (jsonStr.startsWith("[") && jsonStr.endsWith("]")) {
                jsonStr = "{\"fakelist\":" + jsonStr + "}";
            }
            JSONObject json = new JSONObject(jsonStr);
            return fromJson(json);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return new IMap();
    }

    /**
     * 将指定的JSON对象转成 {@link IMap}<String, Object>对象
     *
     * @param json 有效的{@link JSONObject}对象
     * @return
     * @throws JSONException 当传入的{@link JSONObject}是无效的则抛出{@link JSONException}
     */
    public static IMap fromJson(JSONObject json) throws JSONException {
        IMap map = new IMap();
        Iterator<String> iKey = json.keys();
        while (iKey.hasNext()) {
            String key = iKey.next();
            Object value = json.opt(key);
            if (JSONObject.NULL.equals(value)) {
                value = null;
            }
            if (value != null) {
                if (value instanceof JSONObject) {
                    value = fromJson((JSONObject) value);
                } else if (value instanceof JSONArray) {
                    value = fromJson((JSONArray) value);
                }
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * 将指定的JSON数组转成 {@link IMap}<String, Object>数组对象
     *
     * @param array
     * @return
     * @throws JSONException
     */
    public static ArrayList<Object> fromJson(JSONArray array)
            throws JSONException {
        ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0, size = array.length(); i < size; i++) {
            Object value = array.opt(i);
            if (value instanceof JSONObject) {
                value = fromJson((JSONObject) value);
            } else if (value instanceof JSONArray) {
                value = fromJson((JSONArray) value);
            }
            list.add(value);
        }
        return list;
    }

    /**
     * 将指定的 {@link Map}对象转成json字符串
     *
     * @param map
     * @return
     */
    public static String fromJsonHashMap(Map map) {
        try {
            return getJSONObject(map).toString();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return "";
    }

    /**
     * Map对象转换成{@link JSONObject}对象
     *
     * @param map Map对象
     * @return
     * @throws JSONException
     */
    @SuppressWarnings("unchecked")
    public static JSONObject getJSONObject(Map map) throws JSONException {
        JSONObject json = new JSONObject();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = map.get(key);
            if (value instanceof IMap) {
                value = getJSONObject((Map) value);
            } else if (value instanceof ArrayList<?>) {
                value = getJSONArray((ArrayList<Object>) value);
            }
            json.put(key, value);
        }
        return json;
    }

    /**
     * 将List数组转换成JSON数组
     *
     * @param list
     * @return
     * @throws JSONException
     */
    @SuppressWarnings("unchecked")
    public static JSONArray getJSONArray(ArrayList<Object> list)
            throws JSONException {
        JSONArray array = new JSONArray();
        for (Object value : list) {
            if (value instanceof Map) {
                value = getJSONObject((Map) value);
            } else if (value instanceof ArrayList<?>) {
                value = getJSONArray((ArrayList<Object>) value);
            }
            array.put(value);
        }
        return array;
    }
}
