package com.hjianfei.museum_beacon_exhibition.presenter.activity.history_check;

import com.hjianfei.museum_beacon_exhibition.bean.HistoryCheck;
import com.hjianfei.museum_beacon_exhibition.model.activity.history_check.HistoryCheckIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.history_check.HistoryCheckIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.history_check.HistoryCheckView;

/**
 * Created by HJianFei on 2016/12/9.
 */

public class HistoryCheckPresenterImpl implements HistoryCheckPresenter, HistoryCheckIndicator.onFinishedListener {

    private HistoryCheckView mView;
    private HistoryCheckIndicator mIndicator;

    public HistoryCheckPresenterImpl(HistoryCheckView mView) {
        this.mView = mView;
        mIndicator = new HistoryCheckIndicatorImpl();
    }

    @Override
    public void onInitFinished(HistoryCheck check) {
        if (mView != null) {
            mView.hideDialog();
            mView.init(check);
        }

    }

    @Override
    public void onRefreshFinished(HistoryCheck check) {
        if (mView != null) {
            mView.hideDialog();
            mView.refresh(check);
        }
    }

    @Override
    public void onLoadMoreFinished(HistoryCheck check) {
        if (mView != null) {
            mView.hideDialog();
            mView.loadMore(check);
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
    public void onInit(String page, String search_condition) {
        if (mView != null) {
            mView.showDialog();
            mIndicator.onInit(page, search_condition, this);
        }
    }

    @Override
    public void onRefresh(String page, String search_condition) {
        if (mView != null) {
            mView.showDialog();
            mIndicator.onRefresh(page, search_condition, this);
        }
    }

    @Override
    public void onLoadMore(String page, String search_condition) {
        if (mView != null) {
            mView.showDialog();
            mIndicator.onLoadMore(page, search_condition, this);
        }
    }
}
