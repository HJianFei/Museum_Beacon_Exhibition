package com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_culture_detail;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryCultureDetail;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_history_culture_detail.ChinaHistoryCultureDetailIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_history_culture_detail.ChinaHistoryCultureDetailIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_culture_detail.ChinaHistoryCultureDetailView;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryCultureDetailPresenterImpl implements ChinaHistoryCultureDetailPresenter, ChinaHistoryCultureDetailIndicator.onFinishListener {

    private ChinaHistoryCultureDetailView mView;
    private ChinaHistoryCultureDetailIndicator mIndicator;

    public ChinaHistoryCultureDetailPresenterImpl(ChinaHistoryCultureDetailView mView) {
        this.mView = mView;
        mIndicator = new ChinaHistoryCultureDetailIndicatorImpl();
    }

    @Override
    public void inFinished(ChinaHistoryCultureDetail chinaHistoryCultureDetail) {
        if (null != mView) {
            mView.hideDialog();
        }
        mView.onFinished(chinaHistoryCultureDetail);
    }

    @Override
    public void onError() {
        if (null != mView) {
            mView.hideDialog();
        }
        mView.showDialog();
    }

    @Override
    public void getChinaHistoryCultureDetail(String id) {
        if (null != mView) {
//            mView.showDialog();
        }
        mIndicator.getHistoryCultureDetailDetail(id, this);
    }
}
