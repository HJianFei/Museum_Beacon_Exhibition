package com.hjianfei.museum_beacon_exhibition.model.user_info;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
<<<<<<< HEAD
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import java.util.Map;

import okhttp3.MultipartBody;
=======
import com.hjianfei.museum_beacon_exhibition.bean.UploadFile;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
>>>>>>> tmp
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/11/21.
 */

public class UserInfoIndicatorImpl implements UserInfoIndicator {

    @Override
<<<<<<< HEAD
    public void changeAvatar(MultipartBody parts, String user_phone, final onFinishedListener listener) {

        NetWorkUtils.getApi().uploadFile(parts, user_phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultCode>() {
=======
    public void changeAvatar(File file, String user_phone, final onFinishedListener listener) {
        //创建RequestBody对象
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        NetWorkUtils.getApi().uploadFile(requestBody, user_phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadFile>() {
>>>>>>> tmp
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
<<<<<<< HEAD
                    public void onNext(ResultCode resultCode) {
                        listener.changeAvatarSuccess(resultCode);
=======
                    public void onNext(UploadFile uploadFile) {
//                        listener.changeAvatarSuccess();
>>>>>>> tmp

                    }
                });
    }

    @Override
    public void changePassword(Map<String, Object> map, final onFinishedListener listener) {
        NetWorkUtils.getApi().updateUserPassword(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultCode>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();
                    }

                    @Override
                    public void onNext(ResultCode resultCode) {
                        listener.changePasswordSuccess(resultCode);
                    }
                });

    }

    @Override
    public void changeName(Map<String, Object> map, final onFinishedListener listener) {
        NetWorkUtils.getApi().changeUserName(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultCode>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();
                    }

                    @Override
                    public void onNext(ResultCode resultCode) {
                        listener.changeNickNameSuccess(resultCode);
                    }
                });

    }

    @Override
    public void changePhone(Map<String, Object> map, final onFinishedListener listener) {
        NetWorkUtils.getApi().changeUserPhone(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultCode>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        listener.onError();
                    }

                    @Override
                    public void onNext(ResultCode resultCode) {
                        listener.changePhoneSuccess(resultCode);

                    }
                });

    }
}
