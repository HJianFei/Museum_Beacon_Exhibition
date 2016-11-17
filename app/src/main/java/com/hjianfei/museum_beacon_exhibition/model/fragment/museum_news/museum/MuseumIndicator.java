package com.hjianfei.museum_beacon_exhibition.model.fragment.museum_news.museum;

import com.hjianfei.museum_beacon_exhibition.bean.Museum;

import java.util.List;

/**
 * Created by HJianFei on 2016/11/5.
 */

public interface MuseumIndicator {

    interface onFinishedListener {
        //页面首次数据加载完成
        void onInitMuseumsFinished(List<Museum.MuseumsBean> museumsBeanList);

        //下拉刷新数据加载完成
        void onRefreshMuseumsFinished(List<Museum.MuseumsBean> museumsBeanList);

        //上拉加载更多完成
        void onLoadMuseumsFinished(List<Museum.MuseumsBean> museumsBeanList);

        //数据加载出错
        void onError();

    }

    //首次加载数据
    void getInitMuseumsData(String type,String page,onFinishedListener listener);

    //刷新
    void getRefreshMuseumsData(String type,String page,onFinishedListener listener);

    //加载更多
    void getLoadMuseumsData(String type,String page,onFinishedListener listener);
}
