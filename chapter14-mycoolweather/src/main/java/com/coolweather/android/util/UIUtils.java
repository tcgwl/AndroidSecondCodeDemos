package com.coolweather.android.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;

import com.coolweather.android.WeatherApp;

/**
 * Created by Archer on 2017/10/24.
 */

public class UIUtils {
    /**得到上下文*/
    public static Context getContext() {
        return WeatherApp.getContext();
    }

    /**得到Resouce对象*/
    public static Resources getResource() {
        return getContext().getResources();
    }

    /**得到String.xml中的字符串*/
    public static String getString(int resId) {
        return getResource().getString(resId);
    }

    /**得到String.xml中的字符串数组*/
    public static String[] getStringArr(int resId) {
        return getResource().getStringArray(resId);
    }

    /**得到colors.xml中的颜色*/
    public static int getColor(int colorId) {
        return getResource().getColor(colorId);
    }

    /**得到应用程序的包名*/
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**得到主线程id*/
    public static long getMainThreadId() {
        return WeatherApp.getMainThreadId();
    }

    /**得到主线程Handler*/
    public static Handler getMainThreadHandler() {
        return WeatherApp.getHandler();
    }

    /**安全的执行一个任务*/
    public static void postTaskSafely(Runnable task) {
        int curThreadId = android.os.Process.myTid();

        if (curThreadId == getMainThreadId()) { // 如果当前线程是主线程
            task.run();
        } else { // 如果当前线程不是主线程
            getMainThreadHandler().post(task);
        }
    }
}
