package com.hjianfei.museum_beacon_exhibition.model.activity.china_hisroty_people;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryPeople;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryPeopleIndicatorImpl implements ChinaHistoryPeopleIndicator {
    @Override
    public void onInitChinaHistoryPeople(String type, String page, String search_condition, final onFinishedListener listener) {
        NetWorkUtils.getApi().getChinaHistoryPeople(type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryPeople>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        LogUtils.d(Constants.TAG, e.toString());
                        listener.onError();
                    }

                    @Override
                    public void onNext(ChinaHistoryPeople chinaHistoryPeople) {
                        listener.onInitFinished(chinaHistoryPeople);

                    }
                });
    }

    @Override
    public void onRefreshChinaHistoryPeople(String type, String page, String search_condition, final onFinishedListener listener) {
        NetWorkUtils.getApi().getChinaHistoryPeople(type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryPeople>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        LogUtils.d(Constants.TAG, e.toString());
                        listener.onError();
                    }

                    @Override
                    public void onNext(ChinaHistoryPeople chinaHistoryPeople) {
                        listener.onRefreshFinished(chinaHistoryPeople);

                    }
                });
    }

    @Override
    public void onLoadMoreChinaHistoryPeople(String type, String page, String search_condition, final onFinishedListener listener) {
        NetWorkUtils.getApi().getChinaHistoryPeople(type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryPeople>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        LogUtils.d(Constants.TAG, e.toString());
                        listener.onError();
                    }

                    @Override
                    public void onNext(ChinaHistoryPeople chinaHistoryPeople) {
                        listener.onLoadMoreFinished(chinaHistoryPeople);

                    }
                });
    }
}
