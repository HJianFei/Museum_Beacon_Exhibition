package com.hjianfei.museum_beacon_exhibition.application;

import android.app.Application;

/**
 * Created by HJianFei on 2016/8/26.
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;

    }

    public static BaseApplication getInstance() {
        return instance;
    }

}
