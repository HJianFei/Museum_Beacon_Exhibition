package com.hjianfei.museum_beacon_exhibition.model.activity.radar;

import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.hjianfei.museum_beacon_exhibition.bean.StepView;

/**
 * Created by HJianFei on 2016/11/23.
 */

public interface RadarIndicator {

    interface onRadarFinishedListener {

        void getStepViewSuccess(StepView stepView);

        void getBeaconAppreciateSuccess(BeaconAppreciate beaconAppreciate);

        void onError();
    }

    void getStepView(String beacon_id, onRadarFinishedListener listener);

    void getBeaconAppreciateByMinor(String minor, onRadarFinishedListener listener);
}
