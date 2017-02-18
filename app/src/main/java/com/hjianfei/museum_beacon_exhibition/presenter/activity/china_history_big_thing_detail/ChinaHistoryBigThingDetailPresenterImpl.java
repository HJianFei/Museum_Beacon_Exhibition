package com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_big_thing_detail;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryBigThingDetail;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_big_thing_detail.ChinaBigThingDetailIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_big_thing_detail.ChinaBigThingDetailIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_big_thing_detail.ChinaHistoryBigThingDetailView;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryBigThingDetailPresenterImpl implements ChinaHistoryBigThingDetailPresenter, ChinaBigThingDetailIndicator.onFinishListener {

    private ChinaHistoryBigThingDetailView mDetailView;
    private ChinaBigThingDetailIndicator mIndicator;

    public ChinaHistoryBigThingDetailPresenterImpl(ChinaHistoryBigThingDetailView mDetailView) {
        this.mDetailView = mDetailView;
        mIndicator = new ChinaBigThingDetailIndicatorImpl();
    }

    @Override
    public void getChinaHistoryBigThingDetailFinished(ChinaHistoryBigThingDetail chinaHistoryBigThingDetail) {

        if (mDetailView != null) {
            mDetailView.hideDialog();
        }
        mDetailView.getChinaHistoryBigThingDetailFinished(chinaHistoryBigThingDetail);
    }

    @Override
    public void onError() {
        if (mDetailView != null) {
            mDetailView.hideDialog();
        }
        mDetailView.showError();
    }

    @Override
    public void getChinaHistoryBigThingDetail(String id) {

        if (mDetailView != null) {
            mDetailView.showDialog();
        }
        mIndicator.getChinaHistoryBigThingDetail(id, this);
    }
}
