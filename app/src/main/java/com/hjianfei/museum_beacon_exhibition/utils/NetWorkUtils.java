package com.hjianfei.museum_beacon_exhibition.utils;


import com.hjianfei.museum_beacon_exhibition.canstants.Urls;
import com.hjianfei.museum_beacon_exhibition.utils.api.Api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by HJianFei on 2016/8/26.
 */

public class NetWorkUtils {

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    private static Api api;

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    public static Retrofit getRetrofit() {

        if (null == mRetrofit) {

            if (null == mOkHttpClient) {
                mOkHttpClient = Okhttp3Util.getOkHttpClient();
            }

            //Retrofit2后使用build设计模式
            mRetrofit = new Retrofit.Builder()
                    //设置服务器路径
                    .baseUrl(Urls.API_SERVER)
                    //添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置使用okhttp网络请求
                    .client(mOkHttpClient)
                    .build();

        }

        return mRetrofit;
    }

    public static Api getApi() {
        if (api == null) {
            api = getRetrofit().create(Api.class);
        }
        return api;

    }
}
