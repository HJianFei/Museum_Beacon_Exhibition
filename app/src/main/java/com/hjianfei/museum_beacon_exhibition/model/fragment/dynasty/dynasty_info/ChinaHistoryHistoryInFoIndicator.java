package com.hjianfei.museum_beacon_exhibition.model.fragment.dynasty.dynasty_info;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHistory;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface ChinaHistoryHistoryInFoIndicator {

    interface onFinishedListener {
        //页面首次数据加载完成
        void onInitChinaHistoryHistoryFinished(ChinaHistoryHistory chinaHistoryHistory);

        //下拉刷新数据加载完成
        void onRefreshChinaHistoryHistoryFinished(ChinaHistoryHistory chinaHistoryHistory);

        //上拉加载更多完成
        void onLoadChinaHistoryHistoryFinished(ChinaHistoryHistory chinaHistoryHistory);

        //数据加载出错
        void onError();

    }

    //首次加载数据
    void getInitChinaHistoryHistory(String type, String page, onFinishedListener listener, String search_condition);

    //刷新
    void getRefreshChinaHistoryHistory(String type, String page, onFinishedListener listener, String search_condition);

    //加载更多
    void getLoadChinaHistoryHistory(String type, String page, onFinishedListener listener, String search_condition);
}
