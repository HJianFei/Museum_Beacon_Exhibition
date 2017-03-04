package com.hjianfei.museum_beacon_exhibition.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by Administrator on 2017/3/4 0004.
 */

public class SystemUtils {

    /**
     * 判断应用是否已经启动
     * @param context 一个context
     * @param packageName 要判断应用的包名
     * @return boolean
     */
    public static boolean isAppAlive(Context context, String packageName){
        ActivityManager activityManager =
                (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos
                = activityManager.getRunningAppProcesses();
        for(int i = 0; i < processInfos.size(); i++){
            if(processInfos.get(i).processName.equals(packageName)){
                LogUtils.d("onResponse",
                        String.format("the %s is running, isAppAlive return true", packageName));
                return true;
            }
        }
        LogUtils.d("onResponse",
                String.format("the %s is not running, isAppAlive return false", packageName));
        return false;
    }
}
