package com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_hey_day;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface ChinaHistoryHeyDayPresenter {

    void onInitChinaHistoryHeyDay(String page, String search_condition);

    void onRefreshChinaHistoryHeyDay(String page, String search_condition);

    void onLoadMoreChinaHistoryHeyDay(String page, String search_condition);
}
