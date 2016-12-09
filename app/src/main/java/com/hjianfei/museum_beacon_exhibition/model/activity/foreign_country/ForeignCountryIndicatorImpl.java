package com.hjianfei.museum_beacon_exhibition.model.activity.foreign_country;

import com.hjianfei.museum_beacon_exhibition.bean.ForeignHistory;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/9.
 */

public class ForeignCountryIndicatorImpl implements ForeignCountryIndicator {
    @Override
    public void onInit(String country, String type, String page, String search_condition, final onFinishedListener listener) {
        NetWorkUtils.getApi().getForeignHistory(country, type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ForeignHistory>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(ForeignHistory foreignHistory) {
                        listener.onInitFinished(foreignHistory);

                    }
                });
    }

    @Override
    public void onRefreshCh(String country, String type, String page, String search_condition, final onFinishedListener listener) {
        NetWorkUtils.getApi().getForeignHistory(country, type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ForeignHistory>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(ForeignHistory foreignHistory) {
                        listener.onRefreshFinished(foreignHistory);

                    }
                });
    }

    @Override
    public void onLoadMore(String country, String type, String page, String search_condition, final onFinishedListener listener) {
        NetWorkUtils.getApi().getForeignHistory(country, type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ForeignHistory>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(ForeignHistory foreignHistory) {
                        listener.onLoadMoreFinished(foreignHistory);

                    }
                });
    }
}
