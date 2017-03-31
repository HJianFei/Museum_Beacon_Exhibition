package com.hjianfei.museum_beacon_exhibition.view.fragment.collection;

import com.hjianfei.museum_beacon_exhibition.bean.Collection;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

import java.util.List;

/**
 * Created by HJianFei on 2016/11/18.
 */

public interface CollectionView extends BaseView {

    void initCollectionData(List<Collection.CollectionsBean> collectionsBeanList);

    void refreshCollectionData(List<Collection.CollectionsBean> collectionsBeanList);

    void loadMoreCollectionData(List<Collection.CollectionsBean> collectionsBeanList);

    void onDeleteCollectionSuccess(ResultCode resultCode);
}
