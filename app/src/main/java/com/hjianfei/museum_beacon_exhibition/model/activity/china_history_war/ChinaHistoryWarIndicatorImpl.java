package com.hjianfei.museum_beacon_exhibition.model.activity.china_history_war;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryOldenWar;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryWarIndicatorImpl implements ChinaHistoryWarIndicator {
    @Override
    public void onInit(String type, String page, String search_condition, final onFinishedListener listener) {
        NetWorkUtils.getApi().getChinaHistoryOldenWars(type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryOldenWar>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(ChinaHistoryOldenWar chinaHistoryOldenWar) {

                        listener.onInitFinished(chinaHistoryOldenWar);
                    }
                });
    }

    @Override
    public void onRefreshCh(String type, String page, String search_condition, final onFinishedListener listener) {
        NetWorkUtils.getApi().getChinaHistoryOldenWars(type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryOldenWar>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(ChinaHistoryOldenWar chinaHistoryOldenWar) {

                        listener.onRefreshFinished(chinaHistoryOldenWar);
                    }
                });
    }

    @Override
    public void onLoadMore(String type, String page, String search_condition, final onFinishedListener listener) {
        NetWorkUtils.getApi().getChinaHistoryOldenWars(type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryOldenWar>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(ChinaHistoryOldenWar chinaHistoryOldenWar) {

                        listener.onLoadMoreFinished(chinaHistoryOldenWar);
                    }
                });
    }
}
