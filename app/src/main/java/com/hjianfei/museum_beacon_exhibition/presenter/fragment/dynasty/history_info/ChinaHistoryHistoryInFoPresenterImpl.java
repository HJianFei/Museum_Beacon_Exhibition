package com.hjianfei.museum_beacon_exhibition.presenter.fragment.dynasty.history_info;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHistory;
import com.hjianfei.museum_beacon_exhibition.model.fragment.dynasty.dynasty_info.ChinaHistoryHistoryInFoIndicator;
import com.hjianfei.museum_beacon_exhibition.model.fragment.dynasty.dynasty_info.ChinaHistoryHistoryInFoIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.fragment.dynasty.dynasty_info.DynastyInfoView;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryHistoryInFoPresenterImpl implements ChinaHistoryHistoryInFoPresenter, ChinaHistoryHistoryInFoIndicator.onFinishedListener {

    private DynastyInfoView mDynastyInfoView;
    private ChinaHistoryHistoryInFoIndicator mInFoIndicator;

    public ChinaHistoryHistoryInFoPresenterImpl(DynastyInfoView mDynastyInfoView) {
        this.mDynastyInfoView = mDynastyInfoView;
        mInFoIndicator = new ChinaHistoryHistoryInFoIndicatorImpl();
    }

    @Override
    public void onInitChinaHistoryHistoryFinished(ChinaHistoryHistory chinaHistoryHistory) {
        mDynastyInfoView.onInitChinaHistoryHistoryFinished(chinaHistoryHistory);

    }

    @Override
    public void onRefreshChinaHistoryHistoryFinished(ChinaHistoryHistory chinaHistoryHistory) {
        mDynastyInfoView.onRefreshChinaHistoryHistoryFinished(chinaHistoryHistory);
    }

    @Override
    public void onLoadChinaHistoryHistoryFinished(ChinaHistoryHistory chinaHistoryHistory) {
        mDynastyInfoView.onLoadMoreChinaHistoryHistoryFinished(chinaHistoryHistory);
    }

    @Override
    public void onError() {

    }

    @Override
    public void getInitChinaHistoryHistory(String type, String page, String search_condition) {
        mInFoIndicator.getInitChinaHistoryHistory(type, page, this, search_condition);
    }

    @Override
    public void getRefreshChinaHistoryHistory(String type, String page, String search_condition) {
        mInFoIndicator.getRefreshChinaHistoryHistory(type, page, this, search_condition);
    }

    @Override
    public void getLoadChinaHistoryHistory(String type, String page, String search_condition) {
        mInFoIndicator.getLoadChinaHistoryHistory(type, page, this, search_condition);
    }
}
