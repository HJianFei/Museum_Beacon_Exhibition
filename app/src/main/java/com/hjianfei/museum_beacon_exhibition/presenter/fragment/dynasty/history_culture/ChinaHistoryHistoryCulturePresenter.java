package com.hjianfei.museum_beacon_exhibition.presenter.fragment.dynasty.history_culture;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface ChinaHistoryHistoryCulturePresenter {

    //首次加载数据
    void getInitChinaHistoryHistoryCulture(String type, String page, String search_condition);

    //刷新
    void getRefreshChinaHistoryHistoryCulture(String type, String page, String search_condition);

    //加载更多
    void getLoadChinaHistoryHistoryCulture(String type, String page, String search_condition);
}
