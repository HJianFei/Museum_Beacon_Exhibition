package com.hjianfei.museum_beacon_exhibition.presenter.activity.radar;

import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.hjianfei.museum_beacon_exhibition.bean.StepView;
import com.hjianfei.museum_beacon_exhibition.model.activity.radar.RadarIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.radar.RadarIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.radar.RadarView;

/**
 * Created by HJianFei on 2016/11/23.
 */

public class RadarPresenterImpl implements RadarPresenter, RadarIndicator.onRadarFinishedListener {

    private RadarView mRadarView;
    private RadarIndicator mRadarIndicator;

    public RadarPresenterImpl(RadarView mRadarView) {
        this.mRadarView = mRadarView;
        mRadarIndicator = new RadarIndicatorImpl();
    }

    @Override
    public void getStepViewSuccess(StepView stepView) {
        mRadarView.getStepViewSuccess(stepView);
    }

    @Override
    public void getBeaconAppreciateSuccess(BeaconAppreciate beaconAppreciate) {

        mRadarView.getBeaconAppreciateByMinorSuccess(beaconAppreciate);
    }

    @Override
    public void onError() {
        mRadarView.showError();

    }

    @Override
    public void getStepView(String beacon_id) {

        mRadarIndicator.getStepView(beacon_id, this);
    }

    @Override
    public void getBeaconAppreciateByMinor(String minor) {

        mRadarIndicator.getBeaconAppreciateByMinor(minor, this);
    }
}
