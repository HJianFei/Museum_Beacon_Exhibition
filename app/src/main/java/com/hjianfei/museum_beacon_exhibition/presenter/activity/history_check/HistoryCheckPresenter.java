package com.hjianfei.museum_beacon_exhibition.presenter.activity.history_check;

/**
 * Created by HJianFei on 2016/12/9.
 */

public interface HistoryCheckPresenter {

    void onInit(String page, String search_condition);

    void onRefresh(String page, String search_condition);

    void onLoadMore(String page, String search_condition);
}
