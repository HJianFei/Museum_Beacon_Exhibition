package com.hjianfei.museum_beacon_exhibition.presenter.activity.appreciate;

import java.util.Map;

/**
 * 创建时间： 2016/9/19.
 * 作者：HJianFei
 * 功能描述：
 */

public interface AppreciatePresenter {

    void onInitAppreciateData(String museum_name,String tag, String page,String search_condition);

    //下拉刷新页面
    void refreshAppreciatesData(String museum_name,String tag, String page,String search_condition);

    //上拉加载更多
    void loadMoreAppreciatesData(String museum_name,String tag, String page,String search_condition);

    void updateAppreciateViewCount(Map<String, Object> map);

    void onDestroy();

}
