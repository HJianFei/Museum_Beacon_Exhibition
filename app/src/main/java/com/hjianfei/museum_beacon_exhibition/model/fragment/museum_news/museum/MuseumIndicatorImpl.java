package com.hjianfei.museum_beacon_exhibition.model.fragment.museum_news.museum;

import com.hjianfei.museum_beacon_exhibition.bean.Museum;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/11/5.
 */

public class MuseumIndicatorImpl implements MuseumIndicator {
    @Override
    public void getInitMuseumsData(String type, String page, final onFinishedListener listener, String search_condition) {
        NetWorkUtils.getApi().getAllMuseums(type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Museum>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Museum museum) {
                        listener.onInitMuseumsFinished(museum.getMuseums());

                    }
                });
    }

    @Override
    public void getRefreshMuseumsData(String type, String page, final onFinishedListener listener, String search_condition) {
        NetWorkUtils.getApi().getAllMuseums(type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Museum>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Museum museum) {
                        listener.onRefreshMuseumsFinished(museum.getMuseums());

                    }
                });
    }

    @Override
    public void getLoadMuseumsData(String type, String page, final onFinishedListener listener, String search_condition) {
        NetWorkUtils.getApi().getAllMuseums(type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Museum>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Museum museum) {
                        listener.onLoadMuseumsFinished(museum.getMuseums());

                    }
                });
    }

    @Override
    public void updateMuseumViewCount(Map<String, Object> map) {
        NetWorkUtils.getApi().updateMuseumViewCount(map)
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
