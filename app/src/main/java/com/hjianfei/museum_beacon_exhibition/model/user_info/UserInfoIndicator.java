package com.hjianfei.museum_beacon_exhibition.model.user_info;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;

import java.util.Map;

import okhttp3.MultipartBody;

/**
 * Created by HJianFei on 2016/11/21.
 */

public interface UserInfoIndicator {

    interface onFinishedListener {

        void changeAvatarSuccess(ResultCode resultCode);

        void changePasswordSuccess(ResultCode resultCode);

        void changeNickNameSuccess(ResultCode resultCode);

        void changePhoneSuccess(ResultCode resultCode);

        void onError();
    }

    void changeAvatar(MultipartBody parts, String user_phone, onFinishedListener listener);

    void changePassword(Map<String, Object> map, onFinishedListener listener);

    void changeName(Map<String, Object> map, onFinishedListener listener);

    void changePhone(Map<String, Object> map, onFinishedListener listener);
}
