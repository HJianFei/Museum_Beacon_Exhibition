package com.hjianfei.museum_beacon_exhibition.application;

import android.app.Application;

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

    private Set<Integer> brtBeacons;
    private Set<Integer> notify;
    private String step;
    List<BeaconAppreciate> mBeaconAppreciateList;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        //Mob 短信验证码
        SMSSDK.initSDK(this, "19132ee4c2cc0", "d4558a302572fbb2657b34b394d06f0c");
        //友盟分享
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wxf1d84e5ebfc6a5e7", "27eb2bd2d9a518d02e558084b8291c03");
        PlatformConfig.setQQZone("1105746947", "8ipjCPurLMpe97dZ");
        PlatformConfig.setSinaWeibo("3496605578", "525296be2bdc29f70bf65d2d2bf4bba7");
        Config.REDIRECT_URL = "http://sns.whalecloud.com/sina2/callback";

        // 开启log打印
        L.enableDebugLogging(true);
        //获取单例
        beaconManager = BRTBeaconManager.getInstance(this);
        // 开启Beacon扫描服务
        beaconManager.startService();
        brtBeacons = new HashSet<>();
        notify = new HashSet<>();
        step = new String();
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
    public Set<Integer> getBrtBeacons() {
        return brtBeacons;
    }

    public Set<Integer> getNotify() {
        return notify;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public List<BeaconAppreciate> getmBeaconAppreciateList() {
        return mBeaconAppreciateList;
    }
}
