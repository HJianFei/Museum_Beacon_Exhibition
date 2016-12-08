package com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_people;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryPeople;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_hisroty_people.ChinaHistoryPeopleIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_hisroty_people.ChinaHistoryPeopleIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_people.ChinaHistoryPeopleView;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryPeoplePresenterImpl implements ChinaHistoryPeoplePresenter, ChinaHistoryPeopleIndicator.onFinishedListener {

    private ChinaHistoryPeopleView mView;
    private ChinaHistoryPeopleIndicator mIndicator;

    public ChinaHistoryPeoplePresenterImpl(ChinaHistoryPeopleView mView) {
        this.mView = mView;
        mIndicator = new ChinaHistoryPeopleIndicatorImpl();
    }

    @Override
    public void onInitFinished(ChinaHistoryPeople chinaHistoryPeople) {
        if (mView != null) {
            mView.hideDialog();
        }
        mView.onInitFinished(chinaHistoryPeople);

    }

    @Override
    public void onRefreshFinished(ChinaHistoryPeople chinaHistoryPeople) {
        if (mView != null) {
            mView.hideDialog();
        }
        mView.onRefreshFinished(chinaHistoryPeople);
    }

    @Override
    public void onLoadMoreFinished(ChinaHistoryPeople chinaHistoryPeople) {
        if (mView != null) {
            mView.hideDialog();
        }
        mView.onLoadMoreFinished(chinaHistoryPeople);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onInitChinaHistoryPeople(String type, String page, String search_condition) {
        if (mView != null) {
            mView.showDialog();
        }
        mIndicator.onInitChinaHistoryPeople(type, page, search_condition, this);
    }

    @Override
    public void onRefreshChinaHistoryPeople(String type, String page, String search_condition) {
        if (mView != null) {
            mView.showDialog();
        }
        mIndicator.onRefreshChinaHistoryPeople(type, page, search_condition, this);
    }

    @Override
    public void onLoadMoreChinaHistoryPeople(String type, String page, String search_condition) {
        if (mView != null) {
            mView.showDialog();
        }
        mIndicator.onLoadMoreChinaHistoryPeople(type, page, search_condition, this);
    }
}
