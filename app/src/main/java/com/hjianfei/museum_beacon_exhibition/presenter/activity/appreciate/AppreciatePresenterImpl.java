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
        if (null != mAppreciateView) {
            mAppreciateView.hideDialog();
        }
        mAppreciateView.initAppreciateData(appreciatesBeans);

    }

    @Override
    public void onRefreshAppreciateSuccess(List<Appreciates.AppreciatesBean> appreciatesBeans) {
        if (null != mAppreciateView) {
            mAppreciateView.hideDialog();
        }
        mAppreciateView.refreshAppreciateData(appreciatesBeans);
    }

    @Override
    public void onLoadMoreAppreciateSuccess(List<Appreciates.AppreciatesBean> appreciatesBeans) {
        if (null != mAppreciateView) {
            mAppreciateView.hideDialog();
        }
        mAppreciateView.loadMoreAppreciateData(appreciatesBeans);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onInitAppreciateData(String tag, String page) {
        if (null != mAppreciateView) {
            mAppreciateView.showDialog();
        }
        mAppreciateIndicator.onInitAppreciateByType(tag, this, page);

    }

    @Override
    public void refreshAppreciatesData(String tag, String page) {
        if (null != mAppreciateView) {
            mAppreciateView.showDialog();
        }
        mAppreciateIndicator.refreshAppreciateByType(tag, this, page);

    }

    @Override
    public void loadMoreAppreciatesData(String tag, String page) {
        if (null != mAppreciateView) {
            mAppreciateView.showDialog();
        }
        mAppreciateIndicator.loadMoreAppreciateByType(tag, this, page);

    }

    @Override
    public void onDestroy() {

    }
}
