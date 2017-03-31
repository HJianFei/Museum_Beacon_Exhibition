package com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_people;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface ChinaHistoryPeoplePresenter {

    void onInitChinaHistoryPeople(String type, String page, String search_condition);

    void onRefreshChinaHistoryPeople(String type, String page, String search_condition);

    void onLoadMoreChinaHistoryPeople(String type, String page, String search_condition);
}
