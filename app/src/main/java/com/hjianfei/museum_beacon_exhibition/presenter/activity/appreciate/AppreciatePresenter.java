package com.hjianfei.museum_beacon_exhibition.presenter.activity.appreciate;

/**
 * 创建时间： 2016/9/19.
 * 作者：HJianFei
 * 功能描述：
 */

public interface AppreciatePresenter {

    void onInitAppreciateData(String tag);

    //下拉刷新页面
    void refreshAppreciatesData(String tag);

    //上拉加载更多
    void loadMoreAppreciatesData(String tag);

    void onDestroy();

}
