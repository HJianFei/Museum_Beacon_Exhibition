package com.hjianfei.museum_beacon_exhibition.presenter.fragment.guide;

import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.hjianfei.museum_beacon_exhibition.bean.NotifyResult;
import com.hjianfei.museum_beacon_exhibition.bean.StepView;
import com.hjianfei.museum_beacon_exhibition.model.fragment.guide.GuideIndicator;
import com.hjianfei.museum_beacon_exhibition.model.fragment.guide.GuideIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.fragment.guide.GuideView;

/**
 * Created by HJianFei on 2016/11/23.
 */

public class GuidePresenterImpl implements GuidePresenter, GuideIndicator.onGuideFinishedListener {

    private GuideView mGuideView;
    private GuideIndicator mGuideIndicator;

    public GuidePresenterImpl(GuideView mGuideView) {
        this.mGuideView = mGuideView;
        mGuideIndicator = new GuideIndicatorImpl();
    }

    @Override
    public void getStepViewSuccess(StepView stepView) {
        mGuideView.getStepViewSuccess(stepView);
    }

    @Override
    public void getBeaconAppreciateSuccess(BeaconAppreciate beaconAppreciate) {

        mGuideView.getBeaconAppreciateByMinorSuccess(beaconAppreciate);
    }

    @Override
    public void getNotifyFinished(NotifyResult notifyResult) {
        mGuideView.getNotifyFinished(notifyResult);

    }

    @Override
    public void onError() {

    }

    @Override
    public void getStepView(String beacon_id) {

        mGuideIndicator.getStepView(beacon_id, this);
    }

    @Override
    public void getBeaconAppreciateByMinor(String minor) {

        mGuideIndicator.getBeaconAppreciateByMinor(minor, this);
    }

    @Override
    public void getNotify(String notify_id) {
        mGuideIndicator.getNotify(notify_id, this);
    }
}
