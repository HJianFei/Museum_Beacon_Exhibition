package com.hjianfei.museum_beacon_exhibition.model.activity.china_history_war;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryOldenWar;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface ChinaHistoryWarIndicator {

    interface onFinishedListener {
        void onInitFinished(ChinaHistoryOldenWar chinaHistoryOldenWar);

        void onRefreshFinished(ChinaHistoryOldenWar chinaHistoryOldenWar);

        void onLoadMoreFinished(ChinaHistoryOldenWar chinaHistoryOldenWar);

        void onError();
    }

    void onInit(String type, String page, String search_condition, onFinishedListener listener);

    void onRefreshCh(String type, String page, String search_condition, onFinishedListener listener);

    void onLoadMore(String type, String page, String search_condition, onFinishedListener listener);
}
