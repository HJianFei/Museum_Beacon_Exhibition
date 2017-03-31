package com.hjianfei.museum_beacon_exhibition.model.activity.register;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;

import java.util.Map;

/**
 * Created by HJianFei on 2016/11/16.
 */

public interface RegisterIndicator {

    interface onRegisterFinishedListener {
        /**
         * 用户注册成功
         */
        void onRegisterSuccess(ResultCode resultCode);

        /**
         * 用户注册失败
         */
        void onRegisterError();
    }

    /**
     * 用户注册接口
     *
     * @param map
     */
    void registerUser(Map<String, Object> map, onRegisterFinishedListener listener);
}
