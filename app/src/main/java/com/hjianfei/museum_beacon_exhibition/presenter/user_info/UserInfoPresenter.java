package com.hjianfei.museum_beacon_exhibition.presenter.user_info;

import java.util.Map;

import okhttp3.MultipartBody;


/**
 * Created by HJianFei on 2016/11/21.
 */

public interface UserInfoPresenter {

    void changeAvatar(MultipartBody parts, String user_phone);


    void changePassword(Map<String, Object> map);

    void changeNickName(Map<String, Object> map);

    void changePhone(Map<String, Object> map);
}
