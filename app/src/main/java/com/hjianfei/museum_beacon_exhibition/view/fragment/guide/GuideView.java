package com.hjianfei.museum_beacon_exhibition.view.fragment.guide;

import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.hjianfei.museum_beacon_exhibition.bean.StepView;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

/**
 * Created by HJianFei on 2016/12/2.
 */

public interface GuideView extends BaseView {

    void getStepViewSuccess(StepView stepView);

    void getBeaconAppreciateByMinorSuccess(BeaconAppreciate beaconAppreciate);
}
