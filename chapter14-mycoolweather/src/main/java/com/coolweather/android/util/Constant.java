package com.coolweather.android.util;

/**
 * Created by archer on 2017/10/23.
 */

public class Constant {
    public static final String BASE_URL = "http://guolin.tech/api/";
    public static final String CHINA_URL = BASE_URL + "china";
    public static final String WEATHER_URL = BASE_URL + "weather";
    private static final String WEATHER_KEY = "376f01873ff34cf592dd78a689ad6993";
    public static final String WEATHER_URL_WITH_KEY = WEATHER_URL + "?key=" + WEATHER_KEY;
    public static final String BING_PIC_URL = BASE_URL + "bing_pic";
}
