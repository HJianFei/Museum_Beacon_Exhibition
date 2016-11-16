package com.hjianfei.museum_beacon_exhibition.model.activity.login;

import com.hjianfei.museum_beacon_exhibition.bean.LoginResult;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import java.util.Map;

import retrofit2.http.FieldMap;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/11/16.
 */

public class LoginIndicatorImpl implements LoginIndicator {
    @Override
    public void loginUser(@FieldMap Map<String, Object> map, final onLoginFinishedListener listener) {
        NetWorkUtils.getApi().loginUser(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResult>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.d(Constants.TAG, "completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onLoginError();

                    }

                    @Override
                    public void onNext(LoginResult loginResult) {
                        listener.onLoginSuccess(loginResult);

                    }
                });
    }
}
