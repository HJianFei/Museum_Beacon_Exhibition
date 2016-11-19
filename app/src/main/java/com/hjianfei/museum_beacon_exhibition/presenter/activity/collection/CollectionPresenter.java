package com.hjianfei.museum_beacon_exhibition.presenter.activity.collection;

import java.util.Map;

/**
 * Created by HJianFei on 2016/11/18.
 */

public interface CollectionPresenter {

    void onInitCollectionData(String phone,String type,String page);

    //下拉刷新页面
    void refreshCollectionData(String phone,String type,String page);

    //上拉加载更多
    void loadMoreCollectionData(String phone,String type,String page);

    void deleteCollection(Map<String, Object> map);

    void onDestroy();
}
