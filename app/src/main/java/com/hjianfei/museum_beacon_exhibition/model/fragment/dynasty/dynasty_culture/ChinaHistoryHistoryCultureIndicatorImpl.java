package com.hjianfei.museum_beacon_exhibition.model.fragment.dynasty.dynasty_culture;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryCulture;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryHistoryCultureIndicatorImpl implements ChinaHistoryHistoryCultureIndicator {
    @Override
    public void getInitChinaHistoryCulture(String type, String page, final onFinishedListener listener, String search_condition) {
        NetWorkUtils.getApi().getChinaHistoryCulture(type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryCulture>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(Constants.TAG, e.toString());
                        listener.onError();

                    }

                    @Override
                    public void onNext(ChinaHistoryCulture chinaHistoryCulture) {

                        listener.onInitChinaHistoryHistoryCultureFinished(chinaHistoryCulture);
                    }
                });
    }

    @Override
    public void getRefreshChinaHistoryCulture(String type, String page, final onFinishedListener listener, String search_condition) {
        NetWorkUtils.getApi().getChinaHistoryCulture(type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryCulture>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(Constants.TAG, e.toString());
                        listener.onError();

                    }

                    @Override
                    public void onNext(ChinaHistoryCulture chinaHistoryCulture) {

                        listener.onRefreshChinaHistoryHistoryCultureFinished(chinaHistoryCulture);
                    }
                });
    }

    @Override
    public void getLoadChinaHistoryCulture(String type, String page, final onFinishedListener listener, String search_condition) {
        NetWorkUtils.getApi().getChinaHistoryCulture(type, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryCulture>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(Constants.TAG, e.toString());
                        listener.onError();

                    }

                    @Override
                    public void onNext(ChinaHistoryCulture chinaHistoryCulture) {

                        listener.onLoadChinaHistoryHistoryCultureFinished(chinaHistoryCulture);
                    }
                });
    }
}
