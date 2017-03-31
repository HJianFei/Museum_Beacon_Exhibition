package com.hjianfei.museum_beacon_exhibition.model.activity.register;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/11/16.
 */

public class RegisterIndicatorImpl implements RegisterIndicator {
    @Override
    public void registerUser(Map<String, Object> map, final onRegisterFinishedListener listener) {
        NetWorkUtils.getApi().registerUser(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultCode>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.d(Constants.TAG, "完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(Constants.TAG, "错误" + e.toString());
                        listener.onRegisterError();
                    }

                    @Override
                    public void onNext(ResultCode resultCode) {
                        listener.onRegisterSuccess(resultCode);

                    }
                });
    }
}
