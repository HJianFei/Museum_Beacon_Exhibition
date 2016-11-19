package com.hjianfei.museum_beacon_exhibition.presenter.activity.collection;

import com.hjianfei.museum_beacon_exhibition.bean.Collection;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.model.activity.collection.CollectionIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.collection.CollectionIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.fragment.collection.CollectionView;

import java.util.List;
import java.util.Map;

/**
 * Created by HJianFei on 2016/11/18.
 */

public class CollectionPresenterImpl implements CollectionPresenter, CollectionIndicator.onFinishListener {

    private CollectionView mCollectionView;
    private CollectionIndicator mCollectionIndicator;

    public CollectionPresenterImpl(CollectionView mCollectionView) {
        this.mCollectionView = mCollectionView;
        mCollectionIndicator = new CollectionIndicatorImpl();
    }

    @Override
    public void onInitCollectionFinished(List<Collection.CollectionsBean> collectionsBeanList) {
        if (null != mCollectionView) {
            mCollectionView.hideDialog();
        }
        mCollectionView.initCollectionData(collectionsBeanList);

    }

    @Override
    public void onRefreshCollectionSuccess(List<Collection.CollectionsBean> collectionsBeanList) {
        if (null != mCollectionView) {
            mCollectionView.hideDialog();
        }
        mCollectionView.refreshCollectionData(collectionsBeanList);
    }

    @Override
    public void onLoadMoreCollectionSuccess(List<Collection.CollectionsBean> collectionsBeanList) {
        if (null != mCollectionView) {
            mCollectionView.hideDialog();
        }
        mCollectionView.loadMoreCollectionData(collectionsBeanList);
    }

    @Override
    public void deleteCollectionSuccess(ResultCode resultCode) {
        mCollectionView.onDeleteCollectionSuccess(resultCode);
    }

    @Override
    public void onError() {
        if (null != mCollectionView) {
            mCollectionView.hideDialog();
        }

    }

    @Override
    public void onInitCollectionData(String phone, String type, String page) {
        if (null != mCollectionView) {
            mCollectionView.showDialog();
        }
        mCollectionIndicator.onInitCollectionByType(phone, type, page, this);
    }

    @Override
    public void refreshCollectionData(String phone, String type, String page) {
        if (null != mCollectionView) {
            mCollectionView.showDialog();
        }
        mCollectionIndicator.refreshCollectionByType(phone, type, page, this);
    }

    @Override
    public void loadMoreCollectionData(String phone, String type, String page) {
        if (null != mCollectionView) {
            mCollectionView.showDialog();
        }
        mCollectionIndicator.loadMoreCollectionByType(phone, type, page, this);
    }

    @Override
    public void deleteCollection(Map<String, Object> map) {
        mCollectionIndicator.deleteCollectionByType(map, this);
    }

    @Override
    public void onDestroy() {

    }
}
