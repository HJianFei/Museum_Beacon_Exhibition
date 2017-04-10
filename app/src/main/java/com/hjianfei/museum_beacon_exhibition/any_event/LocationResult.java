package com.hjianfei.museum_beacon_exhibition.any_event;

/**
 * Created by HJFei on 2017/3/31.
 */

public class LocationResult {

    private String city_name;

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public LocationResult(String city_name) {
        this.city_name = city_name;
    }
}
