package com.hjianfei.museum_beacon_exhibition.model.activity.history_check;

import com.hjianfei.museum_beacon_exhibition.bean.HistoryCheck;

/**
 * Created by HJianFei on 2016/12/9.
 */

public interface HistoryCheckIndicator {


    interface onFinishedListener {
        void onInitFinished(HistoryCheck check);

        void onRefreshFinished(HistoryCheck check);

        void onLoadMoreFinished(HistoryCheck check);

        void onError();
    }

    void onInit(String page, String search_condition, onFinishedListener listener);

    void onRefresh(String page, String search_condition, onFinishedListener listener);

    void onLoadMore(String page, String search_condition, onFinishedListener listener);

}
