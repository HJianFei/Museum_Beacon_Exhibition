package com.hjianfei.museum_beacon_exhibition.model.activity.foreign_country;

import com.hjianfei.museum_beacon_exhibition.bean.ForeignHistory;

/**
 * Created by HJianFei on 2016/12/9.
 */

public interface ForeignCountryIndicator {

    interface onFinishedListener {
        void onInitFinished(ForeignHistory foreignHistory);

        void onRefreshFinished(ForeignHistory foreignHistory);

        void onLoadMoreFinished(ForeignHistory foreignHistory);

        void onError();
    }

    void onInit(String country, String type, String page, String search_condition, onFinishedListener listener);

    void onRefreshCh(String country, String type, String page, String search_condition, onFinishedListener listener);

    void onLoadMore(String country, String type, String page, String search_condition, onFinishedListener listener);
}
