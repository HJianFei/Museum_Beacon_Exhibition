package com.hjianfei.museum_beacon_exhibition.application;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by HJianFei on 2016/8/26.
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setSinaWeibo("1648468770", "e6c09fafbaef7929b14adc4ed242cd7d");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

    }

    public static BaseApplication getInstance() {
        return instance;
    }

}
