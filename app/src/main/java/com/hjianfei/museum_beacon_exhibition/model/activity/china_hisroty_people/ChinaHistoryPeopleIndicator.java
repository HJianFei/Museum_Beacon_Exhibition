package com.hjianfei.museum_beacon_exhibition.model.activity.china_hisroty_people;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryPeople;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface ChinaHistoryPeopleIndicator {

    interface onFinishedListener {
        void onInitFinished(ChinaHistoryPeople chinaHistoryPeople);

        void onRefreshFinished(ChinaHistoryPeople chinaHistoryPeople);

        void onLoadMoreFinished(ChinaHistoryPeople chinaHistoryPeople);

        void onError();
    }

    void onInitChinaHistoryPeople(String type, String page, String search_condition, onFinishedListener listener);

    void onRefreshChinaHistoryPeople(String type, String page, String search_condition, onFinishedListener listener);

    void onLoadMoreChinaHistoryPeople(String type, String page, String search_condition, onFinishedListener listener);
}
