package com.hjianfei.museum_beacon_exhibition.presenter.user_info;

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

public interface UserInfoPresenter {

<<<<<<< HEAD
    void changeAvatar(MultipartBody parts, String user_phone);
=======
    void changeAvatar(File file, String user_phone);
>>>>>>> tmp

    void changePassword(Map<String, Object> map);

    void changeNickName(Map<String, Object> map);

    void changePhone(Map<String, Object> map);
}
