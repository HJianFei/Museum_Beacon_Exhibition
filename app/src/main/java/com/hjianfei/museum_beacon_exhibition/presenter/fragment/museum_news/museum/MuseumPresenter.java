package com.hjianfei.museum_beacon_exhibition.presenter.fragment.museum_news.museum;

/**
 * Created by HJianFei on 2016/11/5.
 */

public interface MuseumPresenter {

    //页面可见，首次加载
    void initMuseumsData();

    //上拉加载更多
    void loadMuseumsMore();

    //下拉刷新
    void refreshMuseumsData();
}
