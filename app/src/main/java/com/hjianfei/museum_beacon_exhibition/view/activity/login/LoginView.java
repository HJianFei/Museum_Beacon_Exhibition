package com.hjianfei.museum_beacon_exhibition.view.activity.login;

import com.hjianfei.museum_beacon_exhibition.bean.LoginResult;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

/**
 * Created by HJianFei on 2016/11/16.
 */

public interface LoginView extends BaseView {

    /**
     * 用户登录成功
     */
    void loginSuccess(LoginResult loginResult);

}
