package com.hjianfei.museum_beacon_exhibition.model.activity.china_dynasty;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaDynasty;

/**
 * Created by HJianFei on 2016/12/7.
 */

public interface ChinaDynastyIndicator {

    interface onFinishedListener{
        void onInitFinished(ChinaDynasty chinaDynasty);

        void onRefreshFinished(ChinaDynasty chinaDynasty);

        void onLoadMoreFinished(ChinaDynasty chinaDynasty);

        void onError();
    }
    void onInitChinaDynasty(String page,onFinishedListener listener);

    void onRefreshChinaDynasty(String page,onFinishedListener listener);

    void onLoadMoreChinaDynasty(String page,onFinishedListener listener);
}
