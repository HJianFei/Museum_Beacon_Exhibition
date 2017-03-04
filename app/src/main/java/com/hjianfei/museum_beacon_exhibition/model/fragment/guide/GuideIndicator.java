package com.hjianfei.museum_beacon_exhibition.model.fragment.guide;

import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.hjianfei.museum_beacon_exhibition.bean.NotifyResult;
import com.hjianfei.museum_beacon_exhibition.bean.StepView;

/**
 * Created by HJianFei on 2016/11/23.
 */

public interface GuideIndicator {

    interface onGuideFinishedListener {

        void getStepViewSuccess(StepView stepView);

        void getBeaconAppreciateSuccess(BeaconAppreciate beaconAppreciate);

        void getNotifyFinished(NotifyResult notifyResult);

        void onError();
    }

    void getStepView(String beacon_id, onGuideFinishedListener listener);

    void getBeaconAppreciateByMinor(String minor, onGuideFinishedListener listener);

    void getNotify(String notify_id, onGuideFinishedListener listener);
}
