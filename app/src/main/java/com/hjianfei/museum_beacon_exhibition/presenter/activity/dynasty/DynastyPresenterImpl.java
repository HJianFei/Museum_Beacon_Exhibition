package com.hjianfei.museum_beacon_exhibition.presenter.activity.dynasty;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryBigThing;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryPeople;
import com.hjianfei.museum_beacon_exhibition.model.activity.dynasty.DynastyIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.dynasty.DynastyIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.dynasty.DynastyView;

/**
 * Created by HJianFei on 2016/12/7.
 */

public class DynastyPresenterImpl implements DynastyPresenter, DynastyIndicator.onFinishedListener {

    private DynastyIndicator mDynastyIndicator;
    private DynastyView mDynastyView;

    public DynastyPresenterImpl(DynastyView mDynastyView) {
        this.mDynastyView = mDynastyView;
        mDynastyIndicator = new DynastyIndicatorImpl();
    }

    @Override
    public void getDynastyBigThings(String type, String page) {
        if (mDynastyView != null) {
            mDynastyView.showDialog();
        }
        mDynastyIndicator.getChinaHistoryBigThings(type, page, this);


    }

    @Override
    public void getChinaHistoryPeopleByRandom(String type) {
        mDynastyIndicator.getChinaHistoryPeopleByRandom(type,this);

    }

    @Override
    public void onChinaHistoryBigThingFinished(ChinaHistoryBigThing chinaHistoryBigThing) {
        if (mDynastyView != null) {
            mDynastyView.hideDialog();
        }
        mDynastyView.initDynastyBigThing(chinaHistoryBigThing);
    }

    @Override
    public void onChinaHistoryPeopleByRandomFinished(ChinaHistoryPeople chinaHistoryPeople) {
        mDynastyView.initChinaHistoryPeople(chinaHistoryPeople);

    }

    @Override
    public void onError() {

    }
}
