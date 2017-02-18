package com.hjianfei.museum_beacon_exhibition.presenter.activity.foreign_country;

import com.hjianfei.museum_beacon_exhibition.bean.ForeignHistory;
import com.hjianfei.museum_beacon_exhibition.model.activity.foreign_country.ForeignCountryIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.foreign_country.ForeignCountryIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.fragment.foreign_country.ForeignCountryView;

/**
 * Created by HJianFei on 2016/12/9.
 */

public class ForeignCountryPresenterImpl implements ForeignCountryPresenter, ForeignCountryIndicator.onFinishedListener {

    private ForeignCountryView mView;
    private ForeignCountryIndicator mIndicator;

    public ForeignCountryPresenterImpl(ForeignCountryView mView) {
        this.mView = mView;
        mIndicator = new ForeignCountryIndicatorImpl();
    }

    @Override
    public void onInitFinished(ForeignHistory foreignHistory) {
        if (mView != null) {
            mView.hideDialog();
            mView.onInitFinished(foreignHistory);
        }
    }

    @Override
    public void onRefreshFinished(ForeignHistory foreignHistory) {
        if (mView != null) {
            mView.hideDialog();
            mView.onRefreshFinished(foreignHistory);
        }
    }

    @Override
    public void onLoadMoreFinished(ForeignHistory foreignHistory) {
        if (mView != null) {
            mView.hideDialog();
            mView.onLoadMoreFinished(foreignHistory);
        }
    }

    @Override
    public void onError() {
        if (mView != null) {
            mView.hideDialog();
            mView.showError();
        }

    }

    @Override
    public void onInit(String country, String type, String page, String search_condition) {
        if (mView != null) {
//            mView.showDialog();
            mIndicator.onInit(country, type, page, search_condition, this);
        }
    }

    @Override
    public void onRefreshCh(String country, String type, String page, String search_condition) {
        if (mView != null) {
            mView.showDialog();
            mIndicator.onRefreshCh(country, type, page, search_condition, this);
        }
    }

    @Override
    public void onLoadMore(String country, String type, String page, String search_condition) {
        if (mView != null) {
            mView.showDialog();
            mIndicator.onLoadMore(country, type, page, search_condition, this);
        }
    }
}
