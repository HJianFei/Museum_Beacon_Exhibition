package com.hjianfei.museum_beacon_exhibition.model.activity.china_history_dey_day_info;

import com.hjianfei.museum_beacon_exhibition.bean.HistoryHeyDayInFo;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class HistoryHeyDayInFoIndicatorImpl implements HistoryHeyDayInFoIndicator {
    @Override
    public void getHistoryHeyDayInFo(String title, final onFinishListener listener) {
        NetWorkUtils.getApi().getHistoryHeyDayInFo(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HistoryHeyDayInFo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        listener.onError();
                    }

                    @Override
                    public void onNext(HistoryHeyDayInFo historyHeyDayInFo) {
                        listener.inFinished(historyHeyDayInFo);

                    }
                });
    }
}
