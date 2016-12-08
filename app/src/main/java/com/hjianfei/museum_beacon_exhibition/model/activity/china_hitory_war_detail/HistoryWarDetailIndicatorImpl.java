package com.hjianfei.museum_beacon_exhibition.model.activity.china_hitory_war_detail;

import com.hjianfei.museum_beacon_exhibition.bean.HistoryWarDetail;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class HistoryWarDetailIndicatorImpl implements HistoryWarDetailIndicator {
    @Override
    public void getHistoryWarDetail(String detail_url, final onFinishListener listener) {
        NetWorkUtils.getApi().getHistoryOldenWarDetail(detail_url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HistoryWarDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(HistoryWarDetail historyWarDetail) {
                        listener.getHistoryWarDetailFinished(historyWarDetail);

                    }
                });
    }
}
