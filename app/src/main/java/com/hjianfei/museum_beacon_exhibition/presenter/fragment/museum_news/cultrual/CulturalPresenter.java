package com.hjianfei.museum_beacon_exhibition.presenter.fragment.museum_news.cultrual;

/**
 * Created by HJianFei on 2016/11/5.
 */

public interface CulturalPresenter {
    //页面可见，首次加载
    void initAppreciatesData(String tag, String page);

    //上拉加载更多
    void loadAppreciatesMore(String tag, String page);

    //下拉刷新
    void refreshAppreciatesData(String tag, String page);

}
