package com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_war;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryOldenWar;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_history_war.ChinaHistoryWarIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_history_war.ChinaHistoryWarIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.fragment.history_war.HistoryWarView;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryWarPresenterImpl implements ChinaHistoryWarPresenter, ChinaHistoryWarIndicator.onFinishedListener {

    private HistoryWarView mView;
    private ChinaHistoryWarIndicator mIndicator;

    public ChinaHistoryWarPresenterImpl(HistoryWarView mView) {
        this.mView = mView;
        mIndicator = new ChinaHistoryWarIndicatorImpl();
    }

    @Override
    public void onInitFinished(ChinaHistoryOldenWar chinaHistoryOldenWar) {
        if (mView != null) {
            mView.hideDialog();
        }
        mView.onInitFinished(chinaHistoryOldenWar);

    }

    @Override
    public void onRefreshFinished(ChinaHistoryOldenWar chinaHistoryOldenWar) {
        if (mView != null) {
            mView.hideDialog();
        }
        mView.onRefreshFinished(chinaHistoryOldenWar);
    }

    @Override
    public void onLoadMoreFinished(ChinaHistoryOldenWar chinaHistoryOldenWar) {
        if (mView != null) {
            mView.hideDialog();
        }
        mView.onLoadMoreFinished(chinaHistoryOldenWar);
    }

    @Override
    public void onError() {
        if (mView != null) {
            mView.hideDialog();
        }
        mView.showError();
    }

    @Override
    public void onInit(String type, String page, String search_condition) {
        if (mView != null) {
            mView.showDialog();
        }
        mIndicator.onInit(type, page, search_condition, this);
    }

    @Override
    public void onRefreshCh(String type, String page, String search_condition) {
        if (mView != null) {
            mView.showDialog();
        }
        mIndicator.onRefreshCh(type, page, search_condition, this);
    }

    @Override
    public void onLoadMore(String type, String page, String search_condition) {
        if (mView != null) {
            mView.showDialog();
        }
        mIndicator.onLoadMore(type, page, search_condition, this);
    }
}
