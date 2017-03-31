package com.hjianfei.museum_beacon_exhibition.model.fragment.dynasty.dynasty_info;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHistory;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryHistoryInFoIndicatorImpl implements ChinaHistoryHistoryInFoIndicator {
    @Override
    public void getInitChinaHistoryHistory(String type, String page, final onFinishedListener listener, String search_condition) {
        NetWorkUtils.getApi().getChinaHistoryHistory(type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryHistory>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(Constants.TAG, e.toString());
                        listener.onError();

                    }

                    @Override
                    public void onNext(ChinaHistoryHistory chinaHistoryHistory) {

                        listener.onInitChinaHistoryHistoryFinished(chinaHistoryHistory);
                    }
                });
    }

    @Override
    public void getRefreshChinaHistoryHistory(String type, String page, final onFinishedListener listener, String search_condition) {
        NetWorkUtils.getApi().getChinaHistoryHistory(type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryHistory>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(Constants.TAG, e.toString());
                        listener.onError();

                    }

                    @Override
                    public void onNext(ChinaHistoryHistory chinaHistoryHistory) {

                        listener.onRefreshChinaHistoryHistoryFinished(chinaHistoryHistory);
                    }
                });
    }

    @Override
    public void getLoadChinaHistoryHistory(String type, String page, final onFinishedListener listener, String search_condition) {
        NetWorkUtils.getApi().getChinaHistoryHistory(type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryHistory>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(Constants.TAG, e.toString());
                        listener.onError();

                    }

                    @Override
                    public void onNext(ChinaHistoryHistory chinaHistoryHistory) {

                        listener.onLoadChinaHistoryHistoryFinished(chinaHistoryHistory);
                    }
                });
    }
}
