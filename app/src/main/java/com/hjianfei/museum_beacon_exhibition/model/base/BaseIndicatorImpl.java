package com.hjianfei.museum_beacon_exhibition.model.base;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/3.
 */

public class BaseIndicatorImpl implements BaseIndicator {
    @Override
    public void saveCollection(Map<String, Object> map, final onFinishListener listener) {
        NetWorkUtils.getApi().saveCollection(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultCode>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(Constants.TAG,e.getMessage());

                    }

                    @Override
                    public void onNext(ResultCode resultCode) {

                        listener.onSaveCollectionSuccess(resultCode);


                    }
                });
    }
}
