package com.hjianfei.museum_beacon_exhibition.model.activity.china_dynasty;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaDynasty;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/7.
 */

public class ChinaDynastyIndicatorImpl implements ChinaDynastyIndicator {
    @Override
    public void onInitChinaDynasty(String page, final onFinishedListener listener) {
        NetWorkUtils.getApi().getChinaDynasty(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaDynasty>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();
                    }

                    @Override
                    public void onNext(ChinaDynasty chinaDynasty) {

                        listener.onInitFinished(chinaDynasty);
                    }
                });
    }

    @Override
    public void onRefreshChinaDynasty(String page, final onFinishedListener listener) {
        NetWorkUtils.getApi().getChinaDynasty(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaDynasty>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();
                    }

                    @Override
                    public void onNext(ChinaDynasty chinaDynasty) {

                        listener.onRefreshFinished(chinaDynasty);
                    }
                });
    }

    @Override
    public void onLoadMoreChinaDynasty(String page, final onFinishedListener listener) {
        NetWorkUtils.getApi().getChinaDynasty(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaDynasty>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();
                    }

                    @Override
                    public void onNext(ChinaDynasty chinaDynasty) {

                        listener.onLoadMoreFinished(chinaDynasty);
                    }
                });
    }
}
