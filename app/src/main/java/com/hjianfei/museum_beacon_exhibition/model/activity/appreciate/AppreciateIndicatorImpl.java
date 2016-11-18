package com.hjianfei.museum_beacon_exhibition.model.activity.appreciate;


import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 创建时间： 2016/9/19.
 * 作者：HJianFei
 * 功能描述：
 */

public class AppreciateIndicatorImpl implements AppreciateIndicator {

    @Override
    public void onInitAppreciateByType(String tag, final onFinishListener listener, String page) {
        NetWorkUtils.getApi().getAllAppreciatesByType(tag, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Appreciates>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(Appreciates appreciates) {
                        listener.onInitAppreciateFinished(appreciates.getAppreciates());


                    }
                });
    }

    @Override
    public void refreshAppreciateByType(String tag, final onFinishListener listener, String page) {
        NetWorkUtils.getApi().getAllAppreciatesByType(tag, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Appreciates>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(Appreciates appreciates) {
                        listener.onRefreshAppreciateSuccess(appreciates.getAppreciates());


                    }
                });
    }

    @Override
    public void loadMoreAppreciateByType(String tag, final onFinishListener listener, String page) {
        NetWorkUtils.getApi().getAllAppreciatesByType(tag, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Appreciates>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(Appreciates appreciates) {
                        listener.onLoadMoreAppreciateSuccess(appreciates.getAppreciates());


                    }
                });
    }

    @Override
    public void updateAppreciateViewCount(Map<String, Object> map) {
        NetWorkUtils.getApi().updateAppreciateViewCount(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultCode>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResultCode resultCode) {

                    }
                });
    }
}
