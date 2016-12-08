package com.hjianfei.museum_beacon_exhibition.model.activity.china_history_culture_detail;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryCultureDetail;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryCultureDetailIndicatorImpl implements ChinaHistoryCultureDetailIndicator {
    @Override
    public void getHistoryCultureDetailDetail(String title, final onFinishListener listener) {
        NetWorkUtils.getApi().getChinaHistoryCultureDetail(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryCultureDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        listener.onError();
                    }

                    @Override
                    public void onNext(ChinaHistoryCultureDetail chinaHistoryCultureDetail) {

                        listener.inFinished(chinaHistoryCultureDetail);
                    }
                });
    }
}
