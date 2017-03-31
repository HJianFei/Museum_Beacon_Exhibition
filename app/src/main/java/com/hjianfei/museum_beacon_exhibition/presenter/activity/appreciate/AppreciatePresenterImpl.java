package com.hjianfei.museum_beacon_exhibition.presenter.activity.appreciate;


import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.model.activity.appreciate.AppreciateIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.appreciate.AppreciateIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.fragment.appreciate.AppreciateView;
import java.util.Map;

/**
 * 创建时间： 2016/9/19.
 * 作者：HJianFei
 * 功能描述：
 */

public class AppreciatePresenterImpl implements AppreciatePresenter, AppreciateIndicator.onFinishListener {

    private AppreciateView mAppreciateView;
    private AppreciateIndicator mAppreciateIndicator;

    public AppreciatePresenterImpl(AppreciateView appreciateView) {
        this.mAppreciateView = appreciateView;
        mAppreciateIndicator = new AppreciateIndicatorImpl();
    }


    @Override
    public void onInitAppreciateFinished(Appreciates appreciates) {
        if (null != mAppreciateView) {
            mAppreciateView.hideDialog();
        }
        mAppreciateView.initAppreciateData(appreciates);

    }

    @Override
    public void onRefreshAppreciateSuccess(Appreciates appreciates) {
        if (null != mAppreciateView) {
            mAppreciateView.hideDialog();
        }
        mAppreciateView.refreshAppreciateData(appreciates);
    }

    @Override
    public void onLoadMoreAppreciateSuccess(Appreciates appreciates) {
        if (null != mAppreciateView) {
            mAppreciateView.hideDialog();
        }
        mAppreciateView.loadMoreAppreciateData(appreciates);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onInitAppreciateData(String museum_name,String tag, String page, String search_condition) {
//        if (null != mAppreciateView) {
//            mAppreciateView.showDialog();
//        }
        mAppreciateIndicator.onInitAppreciateByType(museum_name,tag, this, page, search_condition);

    }

    @Override
    public void refreshAppreciatesData(String museum_name,String tag, String page, String search_condition) {
        if (null != mAppreciateView) {
            mAppreciateView.showDialog();
        }
        mAppreciateIndicator.refreshAppreciateByType(museum_name,tag, this, page, search_condition);

    }

    @Override
    public void loadMoreAppreciatesData(String museum_name,String tag, String page, String search_condition) {
        if (null != mAppreciateView) {
            mAppreciateView.showDialog();
        }
        mAppreciateIndicator.loadMoreAppreciateByType(museum_name,tag, this, page, search_condition);

    }

    @Override
    public void updateAppreciateViewCount(Map<String, Object> map) {
        mAppreciateIndicator.updateAppreciateViewCount(map);
    }

    @Override
    public void onDestroy() {

    }
}
