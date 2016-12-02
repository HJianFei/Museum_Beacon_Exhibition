package com.hjianfei.museum_beacon_exhibition.view.activity.radar;

import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.hjianfei.museum_beacon_exhibition.bean.StepView;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

/**
 * Created by HJianFei on 2016/11/23.
 */

public interface RadarView extends BaseView {

    void getStepViewSuccess(StepView stepView);

    void getBeaconAppreciateByMinorSuccess(BeaconAppreciate beaconAppreciate);
}
