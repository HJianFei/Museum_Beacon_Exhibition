package com.hjianfei.museum_beacon_exhibition.model.activity.china_history_history_detail;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHistoryDetail;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryHistoryDetailIndicatorImpl implements ChinaHistoryHistoryDetailIndicator {
    @Override
    public void getHistoryHistoryDetailDetail(String id, final onFinishListener listener) {
        NetWorkUtils.getApi().getChinaHistoryHistoryDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryHistoryDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        listener.onError();
                    }

                    @Override
                    public void onNext(ChinaHistoryHistoryDetail chinaHistoryCultureDetail) {

                        listener.inFinished(chinaHistoryCultureDetail);
                    }
                });
    }
}
