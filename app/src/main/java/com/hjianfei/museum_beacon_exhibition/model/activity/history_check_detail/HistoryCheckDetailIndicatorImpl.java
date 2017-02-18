package com.hjianfei.museum_beacon_exhibition.model.activity.history_check_detail;

import com.hjianfei.museum_beacon_exhibition.bean.HistoryCheckDetail;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/9.
 */

public class HistoryCheckDetailIndicatorImpl implements HistoryCheckDetailIndicator {
    @Override
    public void getHistoryHCheckDetail(String id, final onFinishListener listener) {
        NetWorkUtils.getApi().getHistoryCheckDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HistoryCheckDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(HistoryCheckDetail historyCheckDetail) {
                        listener.inFinished(historyCheckDetail);

                    }
                });
    }
}
