package com.hjianfei.museum_beacon_exhibition.canstants;

import android.os.Environment;

/**
 * Created by HJianFei on 2016/11/3.
 */

public class Constants {

    //home页面定位请求码
    public static final int HOME_LOCATION = 10001;

    //home页面定位返回码
    public static final int HOME_RESULT_CODE = 10002;
    //日志输出标志
    public static final String TAG = "onResponse";
    //手机号码国家区域zone
    public static final String MOBILE = "86";

    /**
     * 权限常量相关
     */
    //读写SD卡权限
    public static final int WRITE_EXTERNAL_CODE = 0x01;
    //蓝牙是否打开
    public static final int REQUEST_ENABLE_BT = 0x02;
    //百度地图权限申请
    public static final int BAIDU_READ_PHONE_STATE = 0x03;
    //数据缓存
    public static final String PHONE = "USER_PHONE";
    public static final String NAME = "USER_NAME";

    //动画时长
    public static final int DURATION = 1200;

    /**
     * 用户头像存放目录
     */
    public static final String FILE_URI = Environment.getExternalStorageDirectory().getPath() + "/museum_exhibition";
}
