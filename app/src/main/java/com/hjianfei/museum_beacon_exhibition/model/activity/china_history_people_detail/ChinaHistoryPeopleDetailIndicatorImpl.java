package com.hjianfei.museum_beacon_exhibition.model.activity.china_history_people_detail;

import com.hjianfei.museum_beacon_exhibition.bean.HistoryPeopleDetail;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryPeopleDetailIndicatorImpl implements ChinaHistoryPeopleDetailIndicator {
    @Override
    public void getHistoryPeopleDetail(String detail_url, final onFinishListener listener) {
        NetWorkUtils.getApi().getChinaHistoryPeopleDetail(detail_url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HistoryPeopleDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(Constants.TAG, e.toString());
                        listener.onError();

                    }

                    @Override
                    public void onNext(HistoryPeopleDetail historyPeopleDetail) {
                        listener.getHistoryPeopleDetailFinished(historyPeopleDetail);

                    }
                });
    }
}
