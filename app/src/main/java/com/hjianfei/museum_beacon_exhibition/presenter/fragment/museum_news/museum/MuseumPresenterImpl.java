package com.hjianfei.museum_beacon_exhibition.presenter.fragment.museum_news.museum;

import com.hjianfei.museum_beacon_exhibition.bean.Museum;
import com.hjianfei.museum_beacon_exhibition.model.fragment.museum_news.museum.MuseumIndicator;
import com.hjianfei.museum_beacon_exhibition.model.fragment.museum_news.museum.MuseumIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.fragment.museum_news.museum.MuseumView;

import java.util.List;
import java.util.Map;

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
    public void initMuseumsData(String type, String page,String search_condition) {
        if (null != mMuseumView) {
//            mMuseumView.showDialog();
        }
        mMuseumIndicator.getInitMuseumsData(type, page, this,search_condition);

    }

    @Override
    public void loadMuseumsMore(String type, String page,String search_condition) {
        if (null != mMuseumView) {
            mMuseumView.showDialog();
        }
        mMuseumIndicator.getLoadMuseumsData(type, page, this,search_condition);

    }

    @Override
    public void refreshMuseumsData(String type, String page,String search_condition) {
        if (null != mMuseumView) {
            mMuseumView.showDialog();
        }
        mMuseumIndicator.getRefreshMuseumsData(type, page, this,search_condition);

    }

    @Override
    public void updateMuseumViewCount(Map<String, Object> map) {
        mMuseumIndicator.updateMuseumViewCount(map);
    }

    @Override
    public void onInitMuseumsFinished(List<Museum.MuseumsBean> museumsBeanList) {
        if (null != mMuseumView) {
            mMuseumView.hideDialog();
        }
        mMuseumView.initMuseumData(museumsBeanList);

    }

    @Override
    public void onRefreshMuseumsFinished(List<Museum.MuseumsBean> museumsBeanList) {
        if (null != mMuseumView) {
            mMuseumView.hideDialog();
        }
        mMuseumView.refreshMuseumData(museumsBeanList);
    }

    @Override
    public void onLoadMuseumsFinished(List<Museum.MuseumsBean> museumsBeanList) {
        if (null != mMuseumView) {
            mMuseumView.hideDialog();
        }
        mMuseumView.loadMoreMuseumData(museumsBeanList);
    }

    @Override
    public void onError() {

    }
}
