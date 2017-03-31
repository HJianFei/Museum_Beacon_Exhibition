package com.hjianfei.museum_beacon_exhibition.presenter.fragment.home;

import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.bean.Exhibitions;
import com.hjianfei.museum_beacon_exhibition.bean.ViewPager;
import com.hjianfei.museum_beacon_exhibition.model.fragment.home.HomeIndicator;
import com.hjianfei.museum_beacon_exhibition.model.fragment.home.HomeIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.fragment.home.HomeView;

/**
 * Created by HJianFei on 2016/11/3.
 */

public class HomePresenterImpl implements HomePresenter, HomeIndicator.onFinishedListener {

    private HomeView mHomeView;
    private HomeIndicator mHomeIndicator;

    public HomePresenterImpl(HomeView mHomeView) {
        this.mHomeView = mHomeView;
        mHomeIndicator = new HomeIndicatorImpl();
    }

    @Override
    public void loadHomeViewPager() {
        mHomeIndicator.getViewPagerData(this);

    }

    @Override
    public void loadAppreciateRecyclerView() {
        mHomeIndicator.getAppreciateRecyclerView(this);

    }

    @Override
    public void loadExhibitionViewPager(String type) {
        mHomeIndicator.getHotExhibition(type,this);
    }

    @Override
    public void OnViewPagerFinished(ViewPager viewPager) {
        mHomeView.initHomeViewPager(viewPager);

    }

    @Override
    public void onAppreciateRecyclerViewFinished(Appreciates appreciates) {
        mHomeView.initAppreciateRecyclerView(appreciates.getAppreciates());
    }

    @Override
    public void onHotExhibitionFinished(Exhibitions exhibitions) {
        mHomeView.initHotExhibition(exhibitions);
    }

    @Override
    public void OnError() {

    }
}
