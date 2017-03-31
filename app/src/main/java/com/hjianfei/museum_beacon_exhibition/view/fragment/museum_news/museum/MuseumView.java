package com.hjianfei.museum_beacon_exhibition.view.fragment.museum_news.museum;

import com.hjianfei.museum_beacon_exhibition.bean.Museum;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

import java.util.List;

/**
 * Created by HJianFei on 2016/11/5.
 */

public interface MuseumView extends BaseView {

    //页面可见时，首次加载博物馆数据
    void initMuseumData(List<Museum.MuseumsBean> museumsBeanList);

    //页面上拉加载更多
    void loadMoreMuseumData(List<Museum.MuseumsBean> museumsBeanList);

    //页面下拉刷新
    void refreshMuseumData(List<Museum.MuseumsBean> museumsBeanList);

}
