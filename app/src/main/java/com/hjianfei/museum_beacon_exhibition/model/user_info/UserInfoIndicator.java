package com.hjianfei.museum_beacon_exhibition.model.user_info;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;

<<<<<<< HEAD
import java.util.Map;

import okhttp3.MultipartBody;

=======
import java.io.File;
import java.util.Map;

>>>>>>> tmp
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

<<<<<<< HEAD
    void changeAvatar(MultipartBody parts, String user_phone, onFinishedListener listener);
=======
    void changeAvatar(File file, String user_phone, onFinishedListener listener);
>>>>>>> tmp

    void changePassword(Map<String, Object> map, onFinishedListener listener);

    void changeName(Map<String, Object> map, onFinishedListener listener);

    void changePhone(Map<String, Object> map, onFinishedListener listener);
}
