package com.hjianfei.museum_beacon_exhibition.model.activity.appreciate;


import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 创建时间： 2016/9/19.
 * 作者：HJianFei
 * 功能描述：
 */

public class AppreciateIndicatorImpl implements AppreciateIndicator {

    @Override
    public void onInitAppreciateByType(String museum_name,String tag, final onFinishListener listener, String page, String search_condition) {
        NetWorkUtils.getApi().getAllAppreciatesByType(museum_name,tag, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Appreciates>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(Appreciates appreciates) {
                        listener.onInitAppreciateFinished(appreciates);


                    }
                });
    }

    @Override
    public void refreshAppreciateByType(String museum_name,String tag, final onFinishListener listener, String page, String search_condition) {
        NetWorkUtils.getApi().getAllAppreciatesByType(museum_name,tag, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Appreciates>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(Appreciates appreciates) {
                        listener.onRefreshAppreciateSuccess(appreciates);


                    }
                });
    }

    @Override
    public void loadMoreAppreciateByType(String museum_name,String tag, final onFinishListener listener, String page, String search_condition) {
        NetWorkUtils.getApi().getAllAppreciatesByType(museum_name,tag, page, search_condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Appreciates>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();

                    }

                    @Override
                    public void onNext(Appreciates appreciates) {
                        listener.onLoadMoreAppreciateSuccess(appreciates);


                    }
                });
    }

    @Override
    public void updateAppreciateViewCount(Map<String, Object> map) {
        NetWorkUtils.getApi().updateAppreciateViewCount(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultCode>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResultCode resultCode) {

                    }
                });
    }
}
