package com.hjianfei.museum_beacon_exhibition.presenter.activity.history_check_detail;

import com.hjianfei.museum_beacon_exhibition.bean.HistoryCheckDetail;
import com.hjianfei.museum_beacon_exhibition.model.activity.history_check_detail.HistoryCheckDetailIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.history_check_detail.HistoryCheckDetailIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.history_check_detail.HistoryCheckDetailView;

/**
 * Created by HJianFei on 2016/12/9.
 */

public class HistoryCheckDetailPresenterImpl implements HistoryCheckDetailPresenter, HistoryCheckDetailIndicator.onFinishListener {


    private HistoryCheckDetailView mView;
    private HistoryCheckDetailIndicator mIndicator;

    public HistoryCheckDetailPresenterImpl(HistoryCheckDetailView mView) {
        this.mView = mView;
        mIndicator = new HistoryCheckDetailIndicatorImpl();
    }

    @Override
    public void inFinished(HistoryCheckDetail checkDetail) {
        if (mView != null) {
            mView.hideDialog();
            mView.onFinished(checkDetail);
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
    public void getHistoryHCheckDetail(String id) {
        if (mView != null) {
            mView.showDialog();
            mIndicator.getHistoryHCheckDetail(id,this);
        }

    }
}
