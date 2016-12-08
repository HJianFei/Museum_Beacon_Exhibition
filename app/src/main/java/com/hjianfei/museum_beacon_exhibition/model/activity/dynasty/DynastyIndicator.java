package com.hjianfei.museum_beacon_exhibition.model.activity.dynasty;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryBigThing;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryPeople;

/**
 * Created by HJianFei on 2016/12/7.
 */

public interface DynastyIndicator {

    interface onFinishedListener {

        void onChinaHistoryBigThingFinished(ChinaHistoryBigThing chinaHistoryBigThing);

        void onChinaHistoryPeopleByRandomFinished(ChinaHistoryPeople chinaHistoryPeople);

        void onError();
    }

    void getChinaHistoryBigThings(String type,String page,onFinishedListener listener);


    void getChinaHistoryPeopleByRandom(String type,onFinishedListener listener);

}
