package com.hjianfei.museum_beacon_exhibition.presenter.activity.museum_detail;

import com.hjianfei.museum_beacon_exhibition.bean.MuseumDetail;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.model.activity.museum_detail.MuseumDetailIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.museum_detail.MuseumDetailIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.museum_detail.MuseumDetailView;

import java.util.Map;

/**
 * Created by HJianFei on 2016/11/5.
 */

public class MuseumDetailPresenterImpl implements MuseumDetailPresenter, MuseumDetailIndicator.onFinishedListener {

    private MuseumDetailView mMuseumDetailView;
    private MuseumDetailIndicator mMuseumDetailIndicator;

    public MuseumDetailPresenterImpl(MuseumDetailView mMuseumDetailView) {
        this.mMuseumDetailView = mMuseumDetailView;
        mMuseumDetailIndicator = new MuseumDetailIndicatorImpl();
    }

    @Override
    public void initMuseumDetailData(String id) {
        mMuseumDetailIndicator.getMuseumDetailInfo(id, this);

    }

    @Override
    public void onSaveCollection(Map<String, Object> map) {
        mMuseumDetailIndicator.saveCollection(map, this);
    }

    @Override
    public void onInitMuseumDataFinished(MuseumDetail museumDetail) {
        mMuseumDetailView.initMuseumDetailData(museumDetail);

    }

    @Override
    public void onSaveCollectionSuccess(ResultCode resultCode) {
        mMuseumDetailView.onSaveCollectionSuccess(resultCode);
    }

    @Override
    public void onError() {
        mMuseumDetailView.showError();

    }
}
