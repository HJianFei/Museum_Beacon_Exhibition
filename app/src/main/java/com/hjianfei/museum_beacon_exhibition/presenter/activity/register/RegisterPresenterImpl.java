package com.hjianfei.museum_beacon_exhibition.presenter.activity.register;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.model.activity.register.RegisterIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.register.RegisterIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.register.RegisterView;

import java.util.Map;

/**
 * Created by HJianFei on 2016/11/16.
 */

public class RegisterPresenterImpl implements RegisterPresenter, RegisterIndicator.onRegisterFinishedListener {

    private RegisterView mRegisterView;
    private RegisterIndicator mRegisterIndicator;

    public RegisterPresenterImpl(RegisterView mRegisterView) {
        this.mRegisterView = mRegisterView;
        mRegisterIndicator = new RegisterIndicatorImpl();
    }

    @Override
    public void registerUser(Map<String, Object> map) {

        mRegisterIndicator.registerUser(map, this);

    }

    @Override
    public void onRegisterSuccess(ResultCode resultCode) {

        if (null != mRegisterView) {
            mRegisterView.hideDialog();
        }
        mRegisterView.registerUserFinished(resultCode);

    }

    @Override
    public void onRegisterError() {
        mRegisterView.showError();

    }
}
