package com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_war;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface ChinaHistoryWarPresenter {

    void onInit(String type, String page, String search_condition);

    void onRefreshCh(String type, String page, String search_condition);

    void onLoadMore(String type, String page, String search_condition);
}
