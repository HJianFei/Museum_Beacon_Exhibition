package com.hjianfei.museum_beacon_exhibition.model.activity.collection;

import com.hjianfei.museum_beacon_exhibition.bean.Collection;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;

import java.util.List;
import java.util.Map;

/**
 * Created by HJianFei on 2016/11/18.
 */

public interface CollectionIndicator {

    interface onFinishListener {

        void onInitCollectionFinished(List<Collection.CollectionsBean> collectionsBeanList);

        //刷新成功
        void onRefreshCollectionSuccess(List<Collection.CollectionsBean> collectionsBeanList);

        //加载成功
        void onLoadMoreCollectionSuccess(List<Collection.CollectionsBean> collectionsBeanList);

        void deleteCollectionSuccess(ResultCode resultCode);

        void onError();
    }

    void onInitCollectionByType(String phone,String type,String page, onFinishListener listener);

    void refreshCollectionByType(String phone,String type,String page, onFinishListener listener);

    void loadMoreCollectionByType(String phone,String type,String page, onFinishListener listener);

    void deleteCollectionByType(Map<String, Object> map,onFinishListener listener);
}
