/*
 * Copyright (c) 2015.
 * All Rights Reserved.
 */

package com.watershooter.lighting.common.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.File;

/**
 * 储存设备工具类
 * Created by macchen on 15/3/26.
 */
public class StorageUtils {

    /**
     * 禁止构造
     */
    private StorageUtils() {
        throw new AssertionError();
    }

    /**
     * 判断储存设备是否挂载
     *
     * @return
     */
    public static boolean isMount() {

        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取储存设备路径
     *
     * @return
     */
    public static String getStorageDirectory() {

        File file = getStorageFile();
        if (file == null) {
            return null;
        }
        return file.getAbsolutePath();
    }

    /**
     * 获取储存设备路径
     *
     * @return
     */
    public static File getStorageFile() {
        return Environment.getExternalStorageDirectory();
    }


    /**
     * 获取root目录路径
     *
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    /**
     * 获取储存设备的总大小
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long getStorageVolume() {

        File file = getStorageFile();
        StatFs sFs = new StatFs(file.getPath());
        long blockSize = sFs.getBlockSize();
        int total = sFs.getBlockCount();
        long size = total * blockSize;
        return size;
    }

    /**
     * 获取储存器总大小并格式化输出 *
     */
    public static String getStorageVolumeFormat(Context context) {
        long size = getStorageVolume();
        return formatSize(context, size);
    }

    /**
     * 获取储存器的可用空间大小
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long getUsableVolumn() {
        File file = getStorageFile();
        StatFs sFs = new StatFs(file.getPath());
        long blockSize = sFs.getBlockSize();
        int avialable_blocks = sFs.getAvailableBlocks();
        long avialable = avialable_blocks * blockSize;
        return avialable;
    }

    /**
     * 获取储存器可用空间并格式化输出 *
     */
    public static String getUsableVolumnFormat(Context context) {
        long size = getUsableVolumn();
        return formatSize(context, size);
    }

    /**
     * 格式化指定大小
     * <pre>
     *     100 = 100B
     *     1000 = 1KB
     *     1000*1024 = 1M
     *     1000*1024*1024 = 1G
     * </pre>
     *
     * @param context
     * @param size
     * @return
     */
    public static String formatSize(Context context, long size) {
        return Formatter.formatFileSize(context, size);
    }
}
