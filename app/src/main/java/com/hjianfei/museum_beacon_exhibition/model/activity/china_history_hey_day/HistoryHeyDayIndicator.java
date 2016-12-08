package com.hjianfei.museum_beacon_exhibition.model.activity.china_history_hey_day;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHeyDay;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface HistoryHeyDayIndicator {

    interface onFinishedListener {
        void onInitFinished(ChinaHistoryHeyDay chinaHistoryHeyDay);

        void onRefreshFinished(ChinaHistoryHeyDay chinaHistoryHeyDay);

        void onLoadMoreFinished(ChinaHistoryHeyDay chinaHistoryHeyDay);

        void onError();
    }

    void onInitChinaHistoryHeyDay(String page, String search_condition, onFinishedListener listener);

    void onRefreshChinaHistoryHeyDay(String page, String search_condition, onFinishedListener listener);

    void onLoadMoreChinaHistoryHeyDay(String page, String search_condition, onFinishedListener listener);
}
