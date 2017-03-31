package com.hjianfei.museum_beacon_exhibition.model.activity.history_check;

import com.hjianfei.museum_beacon_exhibition.bean.HistoryCheck;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/9.
 */

public class HistoryCheckIndicatorImpl implements HistoryCheckIndicator {
    @Override
    public void onInit(String page, String search_condition, final onFinishedListener listener) {
        NetWorkUtils.getApi().getHistoryCheck(page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HistoryCheck>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(HistoryCheck check) {
                        listener.onInitFinished(check);

                    }
                });
    }

    @Override
    public void onRefresh(String page, String search_condition, final onFinishedListener listener) {
        NetWorkUtils.getApi().getHistoryCheck(page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HistoryCheck>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(HistoryCheck check) {
                        listener.onRefreshFinished(check);

                    }
                });
    }

    @Override
    public void onLoadMore(String page, String search_condition, final onFinishedListener listener) {
        NetWorkUtils.getApi().getHistoryCheck(page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HistoryCheck>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(HistoryCheck check) {
                        listener.onLoadMoreFinished(check);

                    }
                });
    }
}
