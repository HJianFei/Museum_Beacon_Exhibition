package com.hjianfei.museum_beacon_exhibition.model.fragment.museum_news.cultrual;

import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/11/5.
 */

public class CulturalIndicatorImpl implements CulturalIndicator {
    @Override
    public void getInitAppreciatesData(final onFinishedListener listener) {
        NetWorkUtils.getApi().getAllAppreciatesByType("珍品鉴赏")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Appreciates>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Appreciates appreciates) {
                        listener.onInitAppreciatesFinished(appreciates.getAppreciates());


                    }
                });

    }

    @Override
    public void getRefreshAppreciatesData(onFinishedListener listener) {

    }

    @Override
    public void getLoadAppreciatesData(onFinishedListener listener) {

    }
}
