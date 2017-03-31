package com.hjianfei.museum_beacon_exhibition.model.activity.china_history_hey_day;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHeyDay;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class HistoryHeyDayIndicatorImpl implements HistoryHeyDayIndicator {
    @Override
    public void onInitChinaHistoryHeyDay(String page, String search_condition, final onFinishedListener listener) {
        NetWorkUtils.getApi().getChinaHistoryHeyDay(page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryHeyDay>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(ChinaHistoryHeyDay chinaHistoryHeyDay) {
                        listener.onInitFinished(chinaHistoryHeyDay);

                    }
                });
    }

    @Override
    public void onRefreshChinaHistoryHeyDay(String page, String search_condition, final onFinishedListener listener) {
        NetWorkUtils.getApi().getChinaHistoryHeyDay(page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryHeyDay>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(ChinaHistoryHeyDay chinaHistoryHeyDay) {
                        listener.onRefreshFinished(chinaHistoryHeyDay);

                    }
                });
    }

    @Override
    public void onLoadMoreChinaHistoryHeyDay(String page, String search_condition, final onFinishedListener listener) {
        NetWorkUtils.getApi().getChinaHistoryHeyDay(page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryHeyDay>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(ChinaHistoryHeyDay chinaHistoryHeyDay) {
                        listener.onLoadMoreFinished(chinaHistoryHeyDay);

                    }
                });
    }
}
