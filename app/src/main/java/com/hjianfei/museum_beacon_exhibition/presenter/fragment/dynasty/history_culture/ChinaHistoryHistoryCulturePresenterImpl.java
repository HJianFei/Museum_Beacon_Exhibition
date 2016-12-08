package com.hjianfei.museum_beacon_exhibition.presenter.fragment.dynasty.history_culture;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryCulture;
import com.hjianfei.museum_beacon_exhibition.model.fragment.dynasty.dynasty_culture.ChinaHistoryHistoryCultureIndicator;
import com.hjianfei.museum_beacon_exhibition.model.fragment.dynasty.dynasty_culture.ChinaHistoryHistoryCultureIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.fragment.dynasty.dynasty_culture.DynastyCultureView;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryHistoryCulturePresenterImpl implements ChinaHistoryHistoryCulturePresenter, ChinaHistoryHistoryCultureIndicator.onFinishedListener {

    private DynastyCultureView mDynastyCultureView;
    private ChinaHistoryHistoryCultureIndicator mCultureIndicator;

    public ChinaHistoryHistoryCulturePresenterImpl(DynastyCultureView mDynastyCultureView) {
        this.mDynastyCultureView = mDynastyCultureView;
        mCultureIndicator = new ChinaHistoryHistoryCultureIndicatorImpl();
    }


    @Override
    public void onInitChinaHistoryHistoryCultureFinished(ChinaHistoryCulture chinaHistoryCulture) {
        mDynastyCultureView.onInitChinaHistoryCultureFinished(chinaHistoryCulture);
    }

    @Override
    public void onRefreshChinaHistoryHistoryCultureFinished(ChinaHistoryCulture chinaHistoryCulture) {
        mDynastyCultureView.onRefreshChinaHistoryCultureFinished(chinaHistoryCulture);
    }

    @Override
    public void onLoadChinaHistoryHistoryCultureFinished(ChinaHistoryCulture chinaHistoryCulture) {
        mDynastyCultureView.onLoadMoreChinaHistoryCultureFinished(chinaHistoryCulture);
    }

    @Override
    public void onError() {

    }

    @Override
    public void getInitChinaHistoryHistoryCulture(String type, String page, String search_condition) {
        mCultureIndicator.getInitChinaHistoryCulture(type, page, this, search_condition);
    }

    @Override
    public void getRefreshChinaHistoryHistoryCulture(String type, String page, String search_condition) {
        mCultureIndicator.getRefreshChinaHistoryCulture(type, page, this, search_condition);
    }

    @Override
    public void getLoadChinaHistoryHistoryCulture(String type, String page, String search_condition) {
        mCultureIndicator.getLoadChinaHistoryCulture(type, page, this, search_condition);
    }
}
