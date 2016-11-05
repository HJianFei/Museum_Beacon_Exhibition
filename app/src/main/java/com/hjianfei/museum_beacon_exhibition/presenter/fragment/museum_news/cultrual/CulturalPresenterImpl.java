package com.hjianfei.museum_beacon_exhibition.presenter.fragment.museum_news.cultrual;

import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.model.fragment.museum_news.cultrual.CulturalIndicator;
import com.hjianfei.museum_beacon_exhibition.model.fragment.museum_news.cultrual.CulturalIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.fragment.museum_news.cultural.CulturalView;

import java.util.List;

/**
 * Created by HJianFei on 2016/11/5.
 */

public class CulturalPresenterImpl implements CulturalPresenter, CulturalIndicator.onFinishedListener {

    private CulturalView mCulturalView;
    private CulturalIndicator mCultrualIndicator;

    public CulturalPresenterImpl(CulturalView mCulturalView) {
        this.mCulturalView = mCulturalView;
        mCultrualIndicator = new CulturalIndicatorImpl();
    }

    @Override
    public void initAppreciatesData() {
        mCultrualIndicator.getInitAppreciatesData(this);

    }

    @Override
    public void loadAppreciatesMore() {
        mCultrualIndicator.getLoadAppreciatesData(this);

    }

    @Override
    public void refreshAppreciatesData() {
        mCultrualIndicator.getRefreshAppreciatesData(this);

    }


    @Override
    public void onInitAppreciatesFinished(List<Appreciates.AppreciatesBean> appreciatesBeans) {
        mCulturalView.initCulturalData(appreciatesBeans);
    }

    @Override
    public void onRefreshAppreciatesFinished(List<Appreciates.AppreciatesBean> appreciatesBeans) {
        mCulturalView.refreshCulturalData(appreciatesBeans);
    }

    @Override
    public void onLoadAppreciatesFinished(List<Appreciates.AppreciatesBean> appreciatesBeans) {
        mCulturalView.refreshCulturalData(appreciatesBeans);
    }

    @Override
    public void onError() {
        mCulturalView.showError();

    }
}
