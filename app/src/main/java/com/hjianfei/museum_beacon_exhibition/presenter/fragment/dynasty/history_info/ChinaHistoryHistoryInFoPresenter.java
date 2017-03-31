package com.hjianfei.museum_beacon_exhibition.presenter.fragment.dynasty.history_info;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface ChinaHistoryHistoryInFoPresenter {

    //首次加载数据
    void getInitChinaHistoryHistory(String type, String page, String search_condition);

    //刷新
    void getRefreshChinaHistoryHistory(String type, String page, String search_condition);

    //加载更多
    void getLoadChinaHistoryHistory(String type, String page, String search_condition);
}
