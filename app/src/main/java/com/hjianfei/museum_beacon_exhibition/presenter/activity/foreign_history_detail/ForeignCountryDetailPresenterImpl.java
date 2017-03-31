package com.hjianfei.museum_beacon_exhibition.presenter.activity.foreign_history_detail;

import com.hjianfei.museum_beacon_exhibition.bean.ForeignHistoryDetail;
import com.hjianfei.museum_beacon_exhibition.model.activity.foreign_history_detail.ForeignHistoryDetailIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.foreign_history_detail.ForeignHistoryDetailIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.foreign_history_detail.ForeignCountryDetailView;

/**
 * Created by HJianFei on 2016/12/9.
 */

public class ForeignCountryDetailPresenterImpl implements ForeignCountryDetailPresenter, ForeignHistoryDetailIndicator.onFinishListener {

    private ForeignCountryDetailView mView;
    private ForeignHistoryDetailIndicator mIndicator;

    public ForeignCountryDetailPresenterImpl(ForeignCountryDetailView mView) {
        this.mView = mView;
        mIndicator = new ForeignHistoryDetailIndicatorImpl();
    }

    @Override
    public void getForeignCountryDetailFinished(ForeignHistoryDetail foreignHistoryDetail) {
        if (mView != null) {
            mView.hideDialog();
            mView.onFinished(foreignHistoryDetail);
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
    public void getForeignCountryDetail(String id) {
        if (mView != null) {
//            mView.showDialog();
        }
        mIndicator.getForeignCountryDetail(id, this);
    }
}
