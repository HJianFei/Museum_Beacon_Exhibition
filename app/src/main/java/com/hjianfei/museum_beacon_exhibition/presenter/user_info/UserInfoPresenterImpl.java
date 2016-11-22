package com.hjianfei.museum_beacon_exhibition.presenter.user_info;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.model.user_info.UserInfoIndicator;
import com.hjianfei.museum_beacon_exhibition.model.user_info.UserInfoIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.change_password.ChangePasswordView;
import com.hjianfei.museum_beacon_exhibition.view.activity.change_phone.ChangePhoneView;
import com.hjianfei.museum_beacon_exhibition.view.activity.personal.PersonalView;

import java.io.File;
import java.util.Map;

/**
 * Created by HJianFei on 2016/11/21.
 */

public class UserInfoPresenterImpl implements UserInfoPresenter, UserInfoIndicator.onFinishedListener {

    private ChangePasswordView mChangePasswordView;
    private PersonalView mPersonalView;
    private ChangePhoneView mChangePhoneView;
    private UserInfoIndicator mUserInfoIndicator;

    public UserInfoPresenterImpl(ChangePasswordView mChangePasswordView) {

        this.mChangePasswordView = mChangePasswordView;
        mUserInfoIndicator = new UserInfoIndicatorImpl();

    }

    public UserInfoPresenterImpl(PersonalView mPersonalView) {
        this.mPersonalView = mPersonalView;
        mUserInfoIndicator = new UserInfoIndicatorImpl();
    }

    public UserInfoPresenterImpl(ChangePhoneView mChangePhoneView) {
        this.mChangePhoneView = mChangePhoneView;
        mUserInfoIndicator = new UserInfoIndicatorImpl();
    }

    @Override
    public void changeAvatar(File file) {
        mUserInfoIndicator.changeAvatar(file, this);

    }

    @Override
    public void changePassword(Map<String, Object> map) {
        mUserInfoIndicator.changePassword(map, this);

    }

    @Override
    public void changeNickName(Map<String, Object> map) {
        if (null != mPersonalView) {
            mPersonalView.hideDialog();
        }
        mUserInfoIndicator.changeName(map, this);
    }

    @Override
    public void changePhone(Map<String, Object> map) {
        mUserInfoIndicator.changePhone(map, this);
    }

    @Override
    public void changeAvatarSuccess(ResultCode resultCode) {


    }

    @Override
    public void changePasswordSuccess(ResultCode resultCode) {
        if (null != mChangePasswordView) {
            mChangePasswordView.hideDialog();
        }
        mChangePasswordView.changePasswordSuccess(resultCode);

    }

    @Override
    public void changeNickNameSuccess(ResultCode resultCode) {
        if (null != mPersonalView) {
            mPersonalView.hideDialog();
        }
        mPersonalView.changeNameSuccess(resultCode);
    }

    @Override
    public void changePhoneSuccess(ResultCode resultCode) {
        if (null != mChangePhoneView) {
            mChangePhoneView.hideDialog();
        }
        mChangePhoneView.changePhoneSuccess(resultCode);

    }

    @Override
    public void onError() {
        mChangePasswordView.showError();
    }
}
