package com.hjianfei.museum_beacon_exhibition.presenter.fragment.museum_news.museum;

import com.hjianfei.museum_beacon_exhibition.bean.Museum;
import com.hjianfei.museum_beacon_exhibition.model.fragment.museum_news.museum.MuseumIndicator;
import com.hjianfei.museum_beacon_exhibition.model.fragment.museum_news.museum.MuseumIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.fragment.museum_news.museum.MuseumView;

import java.util.List;

/**
 * Created by HJianFei on 2016/11/5.
 */

public class MuseumPresenterImpl implements MuseumPresenter, MuseumIndicator.onFinishedListener {

    private MuseumView mMuseumView;
    private MuseumIndicator mMuseumIndicator;

    public MuseumPresenterImpl(MuseumView mMuseumView) {
        this.mMuseumView = mMuseumView;
        mMuseumIndicator = new MuseumIndicatorImpl();
    }

    @Override
    public void initMuseumsData() {
        mMuseumIndicator.getInitMuseumsData(this);

    }

    @Override
    public void loadMuseumsMore() {
        mMuseumIndicator.getLoadMuseumsData(this);

    }

    @Override
    public void refreshMuseumsData() {
        mMuseumIndicator.getRefreshMuseumsData(this);

    }

    @Override
    public void onInitMuseumsFinished(List<Museum.MuseumsBean> museumsBeanList) {
        mMuseumView.initMuseumData(museumsBeanList);

    }

    @Override
    public void onRefreshMuseumsFinished(List<Museum.MuseumsBean> museumsBeanList) {
        mMuseumView.refreshMuseumData(museumsBeanList);
    }

    @Override
    public void onLoadMuseumsFinished(List<Museum.MuseumsBean> museumsBeanList) {
        mMuseumView.loadMoreMuseumData(museumsBeanList);
    }

    @Override
    public void onError() {

    }
}
