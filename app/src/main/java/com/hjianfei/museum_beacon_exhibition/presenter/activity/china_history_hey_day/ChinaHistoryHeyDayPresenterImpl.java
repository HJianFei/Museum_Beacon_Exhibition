package com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_hey_day;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHeyDay;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_history_hey_day.HistoryHeyDayIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_history_hey_day.HistoryHeyDayIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_hey_day.ChinaHistoryHeyDayView;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryHeyDayPresenterImpl implements ChinaHistoryHeyDayPresenter, HistoryHeyDayIndicator.onFinishedListener {

    private ChinaHistoryHeyDayView mView;
    private HistoryHeyDayIndicator mIndicator;

    public ChinaHistoryHeyDayPresenterImpl(ChinaHistoryHeyDayView mView) {
        this.mView = mView;
        mIndicator = new HistoryHeyDayIndicatorImpl();
    }

    @Override
    public void onInitFinished(ChinaHistoryHeyDay chinaHistoryHeyDay) {
        if (mView != null) {
            mView.hideDialog();
            mView.initChinaHistoryHeyDay(chinaHistoryHeyDay);
        }

    }

    @Override
    public void onRefreshFinished(ChinaHistoryHeyDay chinaHistoryHeyDay) {
        if (mView != null) {
            mView.hideDialog();
            mView.refreshChinaHistoryHeyDay(chinaHistoryHeyDay);
        }
    }

    @Override
    public void onLoadMoreFinished(ChinaHistoryHeyDay chinaHistoryHeyDay) {
        if (mView != null) {
            mView.hideDialog();
            mView.loadMoreChinaHistoryHeyDay(chinaHistoryHeyDay);
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
    public void onInitChinaHistoryHeyDay(String page, String search_condition) {
        if (mView != null) {
            mView.showDialog();
            mIndicator.onInitChinaHistoryHeyDay(page, search_condition, this);
        }
    }

    @Override
    public void onRefreshChinaHistoryHeyDay(String page, String search_condition) {
        if (mView != null) {
            mView.showDialog();
            mIndicator.onRefreshChinaHistoryHeyDay(page, search_condition, this);
        }
    }

    @Override
    public void onLoadMoreChinaHistoryHeyDay(String page, String search_condition) {
        if (mView != null) {
            mView.showDialog();
            mIndicator.onLoadMoreChinaHistoryHeyDay(page, search_condition, this);
        }
    }
}
