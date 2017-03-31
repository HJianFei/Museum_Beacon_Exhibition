package com.hjianfei.museum_beacon_exhibition.presenter.base;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.model.base.BaseIndicator;
import com.hjianfei.museum_beacon_exhibition.model.base.BaseIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.guide_detail.GuideDetailView;

import java.util.Map;

/**
 * Created by HJianFei on 2016/12/3.
 */

public class BasePresenterImpl implements BasePresenter, BaseIndicator.onFinishListener {

    private GuideDetailView mGuideDetailView;
    private BaseIndicator mBaseIndicator;

    public BasePresenterImpl(GuideDetailView mGuideDetailView) {
        this.mGuideDetailView = mGuideDetailView;
        mBaseIndicator = new BaseIndicatorImpl();
    }

    @Override
    public void onSaveCollectionSuccess(ResultCode resultCode) {
        if (null != mGuideDetailView) {
            mGuideDetailView.onSaveCollectionSuccess(resultCode);
        }


    }

    @Override
    public void onError() {

    }

    @Override
    public void onSaveCollection(Map<String, Object> map) {

        mBaseIndicator.saveCollection(map, this);
    }
}
