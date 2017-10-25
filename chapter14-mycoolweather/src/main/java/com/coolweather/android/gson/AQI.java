package com.coolweather.android.gson;

/**
 * Created by Archer on 2017/10/24.
 */

public class AQI {
    public AQICity city;

    public class AQICity {
        public String aqi;
        public String pm25;
    }
}
