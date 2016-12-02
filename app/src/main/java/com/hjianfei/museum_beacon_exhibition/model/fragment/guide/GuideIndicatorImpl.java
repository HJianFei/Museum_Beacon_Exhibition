package com.hjianfei.museum_beacon_exhibition.model.fragment.guide;

import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.hjianfei.museum_beacon_exhibition.bean.StepView;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/11/23.
 */

public class GuideIndicatorImpl implements GuideIndicator {
    @Override
    public void getStepView(String beacon_id, final onGuideFinishedListener listener) {
        NetWorkUtils.getApi().getStepView(beacon_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StepView>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(Constants.TAG, "Throwable:" + e.toString());
                        listener.onError();

                    }

                    @Override
                    public void onNext(StepView stepView) {

                        listener.getStepViewSuccess(stepView);
                    }
                });
    }

    @Override
    public void getBeaconAppreciateByMinor(String minor, final onGuideFinishedListener listener) {
        NetWorkUtils.getApi().getBeaconAppreciateByMinor(minor)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BeaconAppreciate>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(Constants.TAG, e.toString());
                        listener.onError();

                    }

                    @Override
                    public void onNext(BeaconAppreciate beaconAppreciate) {
                        listener.getBeaconAppreciateSuccess(beaconAppreciate);
                    }
                });
    }
}
