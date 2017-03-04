package com.hjianfei.museum_beacon_exhibition.view.fragment.guide;

import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.hjianfei.museum_beacon_exhibition.bean.NotifyResult;
import com.hjianfei.museum_beacon_exhibition.bean.StepView;

/**
 * Created by HJianFei on 2016/12/2.
 */

public interface GuideView {

    void getStepViewSuccess(StepView stepView);

    void getBeaconAppreciateByMinorSuccess(BeaconAppreciate beaconAppreciate);

    void getNotifyFinished(NotifyResult notifyResult);
}
