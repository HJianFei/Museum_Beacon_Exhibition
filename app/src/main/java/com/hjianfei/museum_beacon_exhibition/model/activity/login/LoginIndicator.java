package com.hjianfei.museum_beacon_exhibition.model.activity.login;

import com.hjianfei.museum_beacon_exhibition.bean.LoginResult;

import java.util.Map;

import retrofit2.http.FieldMap;

/**
 * Created by HJianFei on 2016/11/16.
 */

public interface LoginIndicator {

    interface onLoginFinishedListener {
        void onLoginSuccess(LoginResult loginResult);

        void onLoginError();
    }

    void loginUser(@FieldMap Map<String, Object> map, onLoginFinishedListener listener);
}
