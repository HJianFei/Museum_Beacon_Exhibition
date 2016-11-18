package com.hjianfei.museum_beacon_exhibition.presenter.fragment.museum_news.museum;

import java.util.Map;

/**
 * Created by HJianFei on 2016/11/5.
 */

public interface MuseumPresenter {

    //页面可见，首次加载
    void initMuseumsData(String type,String page);

    //上拉加载更多
    void loadMuseumsMore(String type,String page);

    //下拉刷新
    void refreshMuseumsData(String type,String page);

    void updateMuseumViewCount(Map<String, Object> map);
}
