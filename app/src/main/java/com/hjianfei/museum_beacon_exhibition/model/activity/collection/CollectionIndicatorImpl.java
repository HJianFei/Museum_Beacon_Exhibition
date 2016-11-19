package com.hjianfei.museum_beacon_exhibition.model.activity.collection;

import com.hjianfei.museum_beacon_exhibition.bean.Collection;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/11/18.
 */

public class CollectionIndicatorImpl implements CollectionIndicator {

    @Override
    public void onInitCollectionByType(String phone, String type, String page, final onFinishListener listener) {
        NetWorkUtils.getApi().getCollectionList(phone, type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Collection>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        listener.onError();
                    }

                    @Override
                    public void onNext(Collection collection) {
                        listener.onInitCollectionFinished(collection.getCollections());
                    }
                });
    }

    @Override
    public void refreshCollectionByType(String phone, String type, String page, final onFinishListener listener) {
        NetWorkUtils.getApi().getCollectionList(phone, type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Collection>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        listener.onError();
                    }

                    @Override
                    public void onNext(Collection collection) {
                        listener.onRefreshCollectionSuccess(collection.getCollections());
                    }
                });
    }

    @Override
    public void loadMoreCollectionByType(String phone, String type, String page, final onFinishListener listener) {
        NetWorkUtils.getApi().getCollectionList(phone, type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Collection>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        listener.onError();
                    }

                    @Override
                    public void onNext(Collection collection) {
                        listener.onLoadMoreCollectionSuccess(collection.getCollections());
                    }
                });
    }

    @Override
    public void deleteCollectionByType(Map<String, Object> map, final onFinishListener listener) {
        NetWorkUtils.getApi().deleteCollection(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultCode>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        listener.onError();
                    }

                    @Override
                    public void onNext(ResultCode resultCode) {
                        listener.deleteCollectionSuccess(resultCode);

                    }
                });

    }
}
