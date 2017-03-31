package com.hjianfei.museum_beacon_exhibition.model.activity.foreign_history_detail;

import com.hjianfei.museum_beacon_exhibition.bean.ForeignHistoryDetail;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/9.
 */

public class ForeignHistoryDetailIndicatorImpl implements ForeignHistoryDetailIndicator {
    @Override
    public void getForeignCountryDetail(String id, final onFinishListener listener) {
        NetWorkUtils.getApi().getForeignHistoryDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ForeignHistoryDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();
                        LogUtils.d(Constants.TAG, e.toString());

                    }

                    @Override
                    public void onNext(ForeignHistoryDetail foreignHistoryDetail) {

                        listener.getForeignCountryDetailFinished(foreignHistoryDetail);

                    }
                });
    }

}
