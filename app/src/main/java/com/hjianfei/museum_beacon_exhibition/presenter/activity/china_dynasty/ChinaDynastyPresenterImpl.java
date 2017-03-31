package com.hjianfei.museum_beacon_exhibition.presenter.activity.china_dynasty;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaDynasty;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_dynasty.ChinaDynastyIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_dynasty.ChinaDynastyIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_dynasty.ChinaDynastyView;

/**
 * Created by HJianFei on 2016/12/7.
 */

public class ChinaDynastyPresenterImpl implements ChinaDynastyPresenter, ChinaDynastyIndicator.onFinishedListener {

    private ChinaDynastyView mChinaDynastyView;
    private ChinaDynastyIndicator mChinaDynastyIndicator;

    public ChinaDynastyPresenterImpl(ChinaDynastyView mChinaDynastyView) {
        this.mChinaDynastyView = mChinaDynastyView;
        mChinaDynastyIndicator = new ChinaDynastyIndicatorImpl();
    }

    @Override
    public void onInitFinished(ChinaDynasty chinaDynasty) {
        if (null != mChinaDynastyView) {
            mChinaDynastyView.hideDialog();
        }
        mChinaDynastyView.initChinaDynasty(chinaDynasty);

    }

    @Override
    public void onRefreshFinished(ChinaDynasty chinaDynasty) {
        if (null != mChinaDynastyView) {
            mChinaDynastyView.hideDialog();
        }
        mChinaDynastyView.refreshChinaDynasty(chinaDynasty);
    }

    @Override
    public void onLoadMoreFinished(ChinaDynasty chinaDynasty) {
        if (null != mChinaDynastyView) {
            mChinaDynastyView.hideDialog();
        }
        mChinaDynastyView.loadMoreChinaDynasty(chinaDynasty);
    }

    @Override
    public void onError() {
        if (null != mChinaDynastyView) {
            mChinaDynastyView.hideDialog();
        }
        mChinaDynastyView.showDialog();
    }

    @Override
    public void onInitChinaDynasty(String page) {
        if (null != mChinaDynastyView) {
//            mChinaDynastyView.showDialog();
        }
        mChinaDynastyIndicator.onInitChinaDynasty(page, this);

    }

    @Override
    public void onRefreshChinaDynasty(String page) {
        if (null != mChinaDynastyView) {
            mChinaDynastyView.showDialog();
        }
        mChinaDynastyIndicator.onRefreshChinaDynasty(page, this);
    }

    @Override
    public void onLoadMoreChinaDynasty(String page) {
        if (null != mChinaDynastyView) {
            mChinaDynastyView.showDialog();
        }
        mChinaDynastyIndicator.onLoadMoreChinaDynasty(page, this);
    }
}
