package com.hjianfei.museum_beacon_exhibition.model.activity.museum_detail;

import com.hjianfei.museum_beacon_exhibition.bean.MuseumDetail;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/11/5.
 */

public class MuseumDetailIndicatorImpl implements MuseumDetailIndicator {
    @Override
    public void getMuseumDetailInfo(String museum_name, final onFinishedListener listener) {
        NetWorkUtils.getApi().getMuseumDetail(museum_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MuseumDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(MuseumDetail museumDetail) {
                        listener.onInitMuseumDataFinished(museumDetail);

                    }
                });

    }
}
