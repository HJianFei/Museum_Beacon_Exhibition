package com.hjianfei.museum_beacon_exhibition.application;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.smssdk.SMSSDK;

/**
 * Created by HJianFei on 2016/8/26.
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        //Mob 短信验证码
        SMSSDK.initSDK(this, "19132ee4c2cc0", "d4558a302572fbb2657b34b394d06f0c");
        //友盟分享
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx2a66162383eda523", "c68e8e4e28fa82d6db1b57cc73ea88b7");
        PlatformConfig.setSinaWeibo("208018229", "a91e96352d72eac75528bac1bfef3046");
        PlatformConfig.setQQZone("1105746947", "8ipjCPurLMpe97dZ");

    }

    public static BaseApplication getInstance() {
        return instance;
    }

}
