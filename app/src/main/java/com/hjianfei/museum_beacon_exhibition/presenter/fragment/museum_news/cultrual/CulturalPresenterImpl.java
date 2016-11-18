package com.hjianfei.museum_beacon_exhibition.presenter.fragment.museum_news.cultrual;

import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.model.fragment.museum_news.cultrual.CulturalIndicator;
import com.hjianfei.museum_beacon_exhibition.model.fragment.museum_news.cultrual.CulturalIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.fragment.museum_news.cultural.CulturalView;

import java.util.List;
import java.util.Map;

/**
 * Created by HJianFei on 2016/11/5.
 */

public class CulturalPresenterImpl implements CulturalPresenter, CulturalIndicator.onFinishedListener {

    private CulturalView mCulturalView;
    private CulturalIndicator mCulturalIndicator;

    public CulturalPresenterImpl(CulturalView mCulturalView) {
        this.mCulturalView = mCulturalView;
        mCulturalIndicator = new CulturalIndicatorImpl();
    }

    @Override
    public void initAppreciatesData(String tag, String page) {
        if (null != mCulturalView) {
            mCulturalView.showDialog();
        }
        mCulturalIndicator.getInitAppreciatesData(tag, page, this);

    }

    @Override
    public void loadAppreciatesMore(String tag, String page) {
        if (null != mCulturalView) {
            mCulturalView.showDialog();
        }
        mCulturalIndicator.getLoadAppreciatesData(tag, page, this);

    }

    @Override
    public void refreshAppreciatesData(String tag, String page) {
        if (null != mCulturalView) {
            mCulturalView.showDialog();
        }
        mCulturalIndicator.getRefreshAppreciatesData(tag, page, this);

    }

    @Override
    public void updateAppreciateViewCount(Map<String, Object> map) {
        mCulturalIndicator.updateAppreciateViewCount(map);
    }


    @Override
    public void onInitAppreciatesFinished(List<Appreciates.AppreciatesBean> appreciatesBeans) {
        if (null != mCulturalView) {
            mCulturalView.hideDialog();
        }
        mCulturalView.initCulturalData(appreciatesBeans);
    }

    @Override
    public void onRefreshAppreciatesFinished(List<Appreciates.AppreciatesBean> appreciatesBeans) {
        if (null != mCulturalView) {
            mCulturalView.hideDialog();
        }
        mCulturalView.refreshCulturalData(appreciatesBeans);
    }

    @Override
    public void onLoadAppreciatesFinished(List<Appreciates.AppreciatesBean> appreciatesBeans) {
        if (null != mCulturalView) {
            mCulturalView.hideDialog();
        }
        mCulturalView.loadMoreCulturalData(appreciatesBeans);
    }

    @Override
    public void onError() {
        mCulturalView.showError();

    }
}
