package com.hjianfei.museum_beacon_exhibition.model.activity.appreciate;


import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 创建时间： 2016/9/19.
 * 作者：HJianFei
 * 功能描述：
 */

public class AppreciateIndicatorImpl implements AppreciateIndicator {

    @Override
    public void onInitAppreciateByType(String tag, final onFinishListener listener) {
        NetWorkUtils.getApi().getAllAppreciatesByType(tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Appreciates>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(Appreciates appreciates) {
                        listener.onInitAppreciateFinished(appreciates.getAppreciates());


                    }
                });
    }

    @Override
    public void refreshAppreciateByType(String tag, onFinishListener listener) {

    }

    @Override
    public void loadMoreAppreciateByType(String tag, onFinishListener listener) {

    }
}
