package com.coolweather.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import org.litepal.LitePalApplication;

/**
 * Created by Archer on 2017/10/24.
 */
public class WeatherApp extends LitePalApplication {
    private static Context context;
    private static Thread mainThread;
    private static long mainThreadId;
    private static Looper mainThreadLooper;
    private static Handler handler;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        mainThread = Thread.currentThread();
        mainThreadId = android.os.Process.myTid();
        mainThreadLooper = getMainLooper();
        handler = new Handler();
        super.onCreate();
    }

    public static Context getContext() {
        return context;
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static long getMainThreadId() {
        return mainThreadId;
    }

    public static Looper getMainThreadLooper() {
        return mainThreadLooper;
    }

    public static Handler getHandler() {
        return handler;
    }
}
