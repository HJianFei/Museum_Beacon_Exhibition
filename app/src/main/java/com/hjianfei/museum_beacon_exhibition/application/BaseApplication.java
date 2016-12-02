package com.hjianfei.museum_beacon_exhibition.application;

import android.app.Application;

import com.brtbeacon.sdk.BRTBeacon;
import com.brtbeacon.sdk.BRTBeaconManager;
import com.brtbeacon.sdk.utils.L;
import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.smssdk.SMSSDK;

/**
 * Created by HJianFei on 2016/8/26.
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;

    private BRTBeaconManager beaconManager;

    private Set<BRTBeacon> brtBeacons;
    private List<String> step;
    private List<Integer> step_type;
    List<BeaconAppreciate> mBeaconAppreciateList;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        //Mob 短信验证码
        SMSSDK.initSDK(this, "19132ee4c2cc0", "d4558a302572fbb2657b34b394d06f0c");
        //友盟分享
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx2a66162383eda523", "c68e8e4e28fa82d6db1b57cc73ea88b7");
        PlatformConfig.setQQZone("1105746947", "8ipjCPurLMpe97dZ");
        PlatformConfig.setSinaWeibo("208018229", "a91e96352d72eac75528bac1bfef3046");
        Config.REDIRECT_URL = "http://sns.whalecloud.com/sina2/callback";

        // 开启log打印
        L.enableDebugLogging(true);
        //获取单例
        beaconManager = BRTBeaconManager.getInstance(this);
        // 开启Beacon扫描服务
        beaconManager.startService();
        brtBeacons = new HashSet<>();
        step = new ArrayList<>();
        step_type = new ArrayList<>();
        mBeaconAppreciateList = new ArrayList<>();

    }

    public static BaseApplication getInstance() {
        return instance;
    }

    /**
     * 获取Beacon管理对象
     *
     * @return BRTBeaconManager
     */
    public BRTBeaconManager getBRTBeaconManager() {
        return beaconManager;
    }

    /**
     * 全局管理Beacon
     *
     * @return
     */
    public Set<BRTBeacon> getBrtBeacons() {
        return brtBeacons;
    }

    public List<String> getStep() {
        return step;
    }

    public List<Integer> getStep_type() {
        return step_type;
    }

    public List<BeaconAppreciate> getmBeaconAppreciateList() {
        return mBeaconAppreciateList;
    }

}
