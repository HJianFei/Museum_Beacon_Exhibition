package com.hjianfei.museum_beacon_exhibition.model.activity.dynasty;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryBigThing;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryPeople;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/7.
 */

public class DynastyIndicatorImpl implements DynastyIndicator {
    @Override
    public void getChinaHistoryBigThings(String type, String page, final onFinishedListener listener) {
        NetWorkUtils.getApi().getChinaHistoryBigThings(type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryBigThing>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();
                        LogUtils.d(Constants.TAG, e.toString());
                    }

                    @Override
                    public void onNext(ChinaHistoryBigThing chinaHistoryBigThing) {

                        listener.onChinaHistoryBigThingFinished(chinaHistoryBigThing);
                    }
                });
    }

    @Override
    public void getChinaHistoryPeopleByRandom(String type, final onFinishedListener listener) {
        NetWorkUtils.getApi().getChinaHistoryPeopleByRandom(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChinaHistoryPeople>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(Constants.TAG, e.toString());
                        listener.onError();

                    }

                    @Override
                    public void onNext(ChinaHistoryPeople chinaHistoryPeople) {

                        listener.onChinaHistoryPeopleByRandomFinished(chinaHistoryPeople);
                    }
                });
    }
}
