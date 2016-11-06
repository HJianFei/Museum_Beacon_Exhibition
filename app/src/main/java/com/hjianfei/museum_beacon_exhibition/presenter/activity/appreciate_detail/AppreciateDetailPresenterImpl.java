package com.hjianfei.museum_beacon_exhibition.presenter.activity.appreciate_detail;


import com.hjianfei.museum_beacon_exhibition.bean.AppreciateDetail;
import com.hjianfei.museum_beacon_exhibition.model.activity.appreciate_detail.AppreciateDetailIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.appreciate_detail.AppreciateDetailIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.appreciate_detail.AppreciateDetailView;

/**
 * Created by HJianFei on 2016/9/21.
 */

public class AppreciateDetailPresenterImpl implements AppreciateDetailPresenter, AppreciateDetailIndicator.onFinishListener {

    private AppreciateDetailView mAppreciateDetailView;
    private AppreciateDetailIndicator mAppreciateDetailIndicator;

    public AppreciateDetailPresenterImpl(AppreciateDetailView mAppreciateDetailView) {
        this.mAppreciateDetailView = mAppreciateDetailView;
        mAppreciateDetailIndicator = new AppreciateDetailIndicatorImpl();
    }

    @Override
    public void onInitAppreciateDetailData(String detail_url) {
        mAppreciateDetailIndicator.getAppreciateDetail(detail_url, this);
    }

    @Override
    public void onInitAppreciateDetailFinished(AppreciateDetail appreciateDetail) {
        mAppreciateDetailView.initAppreciateDetailData(appreciateDetail);
    }

    @Override
    public void onError() {
        if (null != mAppreciateDetailView) {
            mAppreciateDetailView.showError();
        }

    }

    @Override
    public void onDestroy() {

    }
}
