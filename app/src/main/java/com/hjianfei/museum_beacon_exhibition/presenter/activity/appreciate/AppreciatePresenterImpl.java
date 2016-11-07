package com.hjianfei.museum_beacon_exhibition.presenter.activity.appreciate;


import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.model.activity.appreciate.AppreciateIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.appreciate.AppreciateIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.fragment.appreciate.AppreciateView;

import java.util.List;

/**
 * 创建时间： 2016/9/19.
 * 作者：HJianFei
 * 功能描述：
 */

public class AppreciatePresenterImpl implements AppreciatePresenter, AppreciateIndicator.onFinishListener {

    private AppreciateView mAppreciateView;
    private AppreciateIndicator mAppreciateIndicator;

    public AppreciatePresenterImpl(AppreciateView appreciateView) {
        this.mAppreciateView = appreciateView;
        mAppreciateIndicator = new AppreciateIndicatorImpl();
    }


    @Override
    public void onInitAppreciateFinished(List<Appreciates.AppreciatesBean> appreciatesBeans) {
        mAppreciateView.initAppreciateData(appreciatesBeans);

    }

    @Override
    public void onRefreshAppreciateSuccess(List<Appreciates.AppreciatesBean> appreciatesBeans) {
        mAppreciateView.refreshAppreciateData(appreciatesBeans);
    }

    @Override
    public void onLoadMoreAppreciateSuccess(List<Appreciates.AppreciatesBean> appreciatesBeans) {
        mAppreciateView.loadMoreAppreciateData(appreciatesBeans);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onInitAppreciateData(String tag) {
        mAppreciateIndicator.onInitAppreciateByType(tag, this);

    }

    @Override
    public void refreshAppreciatesData(String tag) {
        mAppreciateIndicator.refreshAppreciateByType(tag, this);

    }

    @Override
    public void loadMoreAppreciatesData(String tag) {
        mAppreciateIndicator.loadMoreAppreciateByType(tag, this);

    }

    @Override
    public void onDestroy() {

    }
}
