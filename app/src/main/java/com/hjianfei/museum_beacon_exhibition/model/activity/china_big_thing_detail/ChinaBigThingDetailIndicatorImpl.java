package com.hjianfei.museum_beacon_exhibition.model.activity.china_big_thing_detail;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryBigThingDetail;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaBigThingDetailIndicatorImpl implements ChinaBigThingDetailIndicator {
    @Override
    public void getChinaHistoryBigThingDetail(String title, final onFinishListener listener) {
        NetWorkUtils.getApi().getChinaHistoryBigThingDetail(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryBigThingDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(Constants.TAG, e.toString());
                        listener.onError();

                    }

                    @Override
                    public void onNext(ChinaHistoryBigThingDetail chinaHistoryBigThingDetail) {
                        listener.getChinaHistoryBigThingDetailFinished(chinaHistoryBigThingDetail);

                    }
                });
    }
}
