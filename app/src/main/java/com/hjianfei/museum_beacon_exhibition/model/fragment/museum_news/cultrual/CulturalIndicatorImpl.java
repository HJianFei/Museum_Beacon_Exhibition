package com.hjianfei.museum_beacon_exhibition.model.fragment.museum_news.cultrual;

import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/11/5.
 */

public class CulturalIndicatorImpl implements CulturalIndicator {
    @Override
    public void getInitAppreciatesData(String type, String page, final onFinishedListener listener, String search_condition) {
        NetWorkUtils.getApi().getAllAppreciatesByType("广东省博物馆",type, page, search_condition)
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
                        listener.onInitAppreciatesFinished(appreciates.getAppreciates());


                    }
                });

    }

    @Override
    public void getRefreshAppreciatesData(String type, String page, final onFinishedListener listener, String search_condition) {
        NetWorkUtils.getApi().getAllAppreciatesByType("广东省博物馆",type, page, search_condition)
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
                        listener.onRefreshAppreciatesFinished(appreciates.getAppreciates());


                    }
                });
    }

    @Override
    public void getLoadAppreciatesData(String type, String page, final onFinishedListener listener, String search_condition) {
        NetWorkUtils.getApi().getAllAppreciatesByType("广东省博物馆",type, page, search_condition)
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
                        listener.onLoadAppreciatesFinished(appreciates.getAppreciates());


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
