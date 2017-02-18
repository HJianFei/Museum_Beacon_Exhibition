package com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_war_detail;

import com.hjianfei.museum_beacon_exhibition.bean.HistoryWarDetail;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_hitory_war_detail.HistoryWarDetailIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_hitory_war_detail.HistoryWarDetailIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_war_detail.HistoryWarDetailView;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class HistoryWarDetailPresenterImpl implements HistoryWarDetailPresenter, HistoryWarDetailIndicator.onFinishListener {

    private HistoryWarDetailView mView;
    private HistoryWarDetailIndicator mIndicator;

    public HistoryWarDetailPresenterImpl(HistoryWarDetailView mView) {
        this.mView = mView;
        mIndicator = new HistoryWarDetailIndicatorImpl();
    }

    @Override
    public void getHistoryWarDetailFinished(HistoryWarDetail historyWarDetail) {
        if (mView != null) {
            mView.hideDialog();
            mView.onFinished(historyWarDetail);
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
    public void getHistoryWarDetail(String id) {
        if (mView != null) {
            mView.showDialog();
            mIndicator.getHistoryWarDetail(id, this);
        }
    }
}
