package com.hjianfei.museum_beacon_exhibition.model.fragment.dynasty.dynasty_culture;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryCulture;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface ChinaHistoryHistoryCultureIndicator {

    interface onFinishedListener {
        //页面首次数据加载完成
        void onInitChinaHistoryHistoryCultureFinished(ChinaHistoryCulture chinaHistoryCulture);

        //下拉刷新数据加载完成
        void onRefreshChinaHistoryHistoryCultureFinished(ChinaHistoryCulture chinaHistoryCulture);

        //上拉加载更多完成
        void onLoadChinaHistoryHistoryCultureFinished(ChinaHistoryCulture chinaHistoryCulture);

        //数据加载出错
        void onError();

    }

    //首次加载数据
    void getInitChinaHistoryCulture(String type, String page, onFinishedListener listener, String search_condition);

    //刷新
    void getRefreshChinaHistoryCulture(String type, String page, onFinishedListener listener, String search_condition);

    //加载更多
    void getLoadChinaHistoryCulture(String type, String page, onFinishedListener listener, String search_condition);
}
