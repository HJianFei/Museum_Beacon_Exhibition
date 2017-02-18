package com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_people_detail;

import com.hjianfei.museum_beacon_exhibition.bean.HistoryPeopleDetail;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_history_people_detail.ChinaHistoryPeopleDetailIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_history_people_detail.ChinaHistoryPeopleDetailIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_people_detail.ChinaHistoryPeopleDetailView;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryPeopleDetailPresenterImpl implements ChinaHistoryPeopleDetailPresenter, ChinaHistoryPeopleDetailIndicator.onFinishListener {

    private ChinaHistoryPeopleDetailView mView;
    private ChinaHistoryPeopleDetailIndicator mIndicator;

    public ChinaHistoryPeopleDetailPresenterImpl(ChinaHistoryPeopleDetailView mView) {
        this.mView = mView;
        mIndicator = new ChinaHistoryPeopleDetailIndicatorImpl();
    }

    @Override
    public void getHistoryPeopleDetailFinished(HistoryPeopleDetail historyPeopleDetail) {
        if (null != mView) {
            mView.hideDialog();
        }
        mView.onFinished(historyPeopleDetail);

    }

    @Override
    public void onError() {
        if (null != mView) {
            mView.hideDialog();
        }
        mView.showError();

    }


    @Override
    public void getHistoryPeopleDetail(String id) {
        if (null != mView) {
            mView.showDialog();
        }
        mIndicator.getHistoryPeopleDetail(id, this);
    }
}
