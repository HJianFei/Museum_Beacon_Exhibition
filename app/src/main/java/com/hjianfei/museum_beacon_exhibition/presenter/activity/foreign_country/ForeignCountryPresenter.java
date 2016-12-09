package com.hjianfei.museum_beacon_exhibition.presenter.activity.foreign_country;

/**
 * Created by HJianFei on 2016/12/9.
 */

public interface ForeignCountryPresenter {

    void onInit(String country, String type, String page, String search_condition);

    void onRefreshCh(String country, String type, String page, String search_condition);

    void onLoadMore(String country, String type, String page, String search_condition);
}
