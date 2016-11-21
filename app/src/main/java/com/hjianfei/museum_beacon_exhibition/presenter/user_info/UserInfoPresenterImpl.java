package com.hjianfei.museum_beacon_exhibition.presenter.user_info;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.model.user_info.UserInfoIndicator;
import com.hjianfei.museum_beacon_exhibition.model.user_info.UserInfoIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.change_password.ChangePasswordView;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by HJianFei on 2016/11/21.
 */

public class UserInfoPresenterImpl implements UserInfoPresenter, UserInfoIndicator.onFinishedListener {

    private ChangePasswordView mChangePasswordView;
    private UserInfoIndicator mUserInfoIndicator;

    public UserInfoPresenterImpl(ChangePasswordView mChangePasswordView) {

        this.mChangePasswordView = mChangePasswordView;
        mUserInfoIndicator = new UserInfoIndicatorImpl();

    }

    @Override
    public void changeAvatar(RequestBody requestBody) {

    }

    @Override
    public void changePassword(Map<String, Object> map) {
        mUserInfoIndicator.changePassword(map, this);

    }

    @Override
    public void changeNickName(Map<String, Object> map) {

    }

    @Override
    public void changePhone(Map<String, Object> map) {

    }

    @Override
    public void changeAvatarSuccess(ResultCode resultCode) {

    }

    @Override
    public void changePasswordSuccess(ResultCode resultCode) {
        mChangePasswordView.changePasswordSuccess(resultCode);

    }

    @Override
    public void changeNickNameSuccess(ResultCode resultCode) {

    }

    @Override
    public void changePhoneSuccess(ResultCode resultCode) {

    }

    @Override
    public void onError() {
        mChangePasswordView.showError();
    }
}
