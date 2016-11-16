package com.hjianfei.museum_beacon_exhibition.presenter.activity.login;

import com.hjianfei.museum_beacon_exhibition.bean.LoginResult;
import com.hjianfei.museum_beacon_exhibition.model.activity.login.LoginIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.login.LoginIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.login.LoginView;

import java.util.Map;

/**
 * Created by HJianFei on 2016/11/16.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginIndicator.onLoginFinishedListener {

    private LoginView mLoginView;
    private LoginIndicator mlLoginIndicator;

    public LoginPresenterImpl(LoginView mLoginView) {
        this.mLoginView = mLoginView;
        mlLoginIndicator = new LoginIndicatorImpl();
    }

    @Override
    public void onLoginSuccess(LoginResult loginResult) {
        mLoginView.loginSuccess(loginResult);

    }

    @Override
    public void onLoginError() {
        mLoginView.showError();

    }

    @Override
    public void loginUser(Map<String, Object> map) {
        mlLoginIndicator.loginUser(map, this);

    }
}
