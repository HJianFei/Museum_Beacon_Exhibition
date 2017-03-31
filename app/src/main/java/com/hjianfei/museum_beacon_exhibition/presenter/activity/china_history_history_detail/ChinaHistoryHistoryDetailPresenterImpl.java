package com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_history_detail;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHistoryDetail;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_history_history_detail.ChinaHistoryHistoryDetailIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_history_history_detail.ChinaHistoryHistoryDetailIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_history_detail.ChinaHistoryHistoryDetailView;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryHistoryDetailPresenterImpl implements ChinaHistoryHistoryDetailPresenter, ChinaHistoryHistoryDetailIndicator.onFinishListener {

    private ChinaHistoryHistoryDetailView mView;
    private ChinaHistoryHistoryDetailIndicator mIndicator;

    public ChinaHistoryHistoryDetailPresenterImpl(ChinaHistoryHistoryDetailView mView) {
        this.mView = mView;
        mIndicator = new ChinaHistoryHistoryDetailIndicatorImpl();
    }

    @Override
    public void inFinished(ChinaHistoryHistoryDetail chinaHistoryHistoryDetail) {
        if (null != mView) {
            mView.hideDialog();
        }
        mView.onFinished(chinaHistoryHistoryDetail);
    }

    @Override
    public void onError() {
        if (null != mView) {
            mView.hideDialog();
        }
        mView.showDialog();
    }

    @Override
    public void getChinaHistoryHistoryDetail(String id) {
        if (null != mView) {
//            mView.showDialog();
        }
        mIndicator.getHistoryHistoryDetailDetail(id, this);
    }
}
