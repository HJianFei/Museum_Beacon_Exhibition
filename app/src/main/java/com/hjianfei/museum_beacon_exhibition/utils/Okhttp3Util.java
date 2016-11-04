package com.hjianfei.museum_beacon_exhibition.utils;


import com.hjianfei.museum_beacon_exhibition.application.BaseApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by HJianFei on 2016/8/26.
 */

public class Okhttp3Util {

    private static OkHttpClient mOkHttpClient;

    //设置缓存目录
    private static File cacheDirectory = new File(BaseApplication.getInstance().getApplicationContext().getCacheDir().getAbsolutePath(), "beacon");
    private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);

    /**
     * 获取OkHttpClient对象
     *
     * @return
     */
    public static OkHttpClient getOkHttpClient() {

        if (null == mOkHttpClient) {
            //同样okhttp3后也使用build设计模式
            mOkHttpClient = new OkHttpClient.Builder()
                    //设置请求读写的超时时间
                    .connectTimeout(3, TimeUnit.SECONDS)
                    .writeTimeout(3, TimeUnit.SECONDS)
                    .readTimeout(3, TimeUnit.SECONDS)
                    .cache(cache)
                    .build();
        }

        return mOkHttpClient;
    }
}
