package com.hjianfei.museum_beacon_exhibition.model.fragment.museum_news.cultrual;

import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;

import java.util.List;

/**
 * Created by HJianFei on 2016/11/5.
 */

public interface CulturalIndicator {

    interface onFinishedListener {
        //页面首次数据加载完成
        void onInitAppreciatesFinished(List<Appreciates.AppreciatesBean> appreciatesBeans);

        //下拉刷新数据加载完成
        void onRefreshAppreciatesFinished(List<Appreciates.AppreciatesBean> appreciatesBeans);

        //上拉加载更多完成
        void onLoadAppreciatesFinished(List<Appreciates.AppreciatesBean> appreciatesBeans);

        //数据加载出错
        void onError();

    }

    //首次加载数据
    void getInitAppreciatesData(onFinishedListener listener);

    //刷新
    void getRefreshAppreciatesData(onFinishedListener listener);

    //加载更多
    void getLoadAppreciatesData(onFinishedListener listener);
}
