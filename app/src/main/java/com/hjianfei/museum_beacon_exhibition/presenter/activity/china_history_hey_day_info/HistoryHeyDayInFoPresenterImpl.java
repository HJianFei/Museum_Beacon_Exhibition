package com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_hey_day_info;

import com.hjianfei.museum_beacon_exhibition.bean.HistoryHeyDayInFo;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_history_dey_day_info.HistoryHeyDayInFoIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.china_history_dey_day_info.HistoryHeyDayInFoIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_hey_day_info.HistoryHeyDayInFoView;

/**
 * Created by HJianFei on 2016/12/9.
 */

public class HistoryHeyDayInFoPresenterImpl implements HistoryHeyDayInFoPresenter, HistoryHeyDayInFoIndicator.onFinishListener {

    private HistoryHeyDayInFoView mView;
    private HistoryHeyDayInFoIndicator mInFoIndicator;

    public HistoryHeyDayInFoPresenterImpl(HistoryHeyDayInFoView mView) {
        this.mView = mView;
        mInFoIndicator = new HistoryHeyDayInFoIndicatorImpl();
    }

    @Override
    public void inFinished(HistoryHeyDayInFo historyHeyDayInFo) {
        if (mView != null) {
            mView.hideDialog();
            mView.onFinished(historyHeyDayInFo);
        }

    }

    @Override
    public void onError() {
        if (mView != null) {
            mView.hideDialog();
            mView.showError();
        }
    }

    @Override
    public void getHistoryHeyDayInFo(String id) {
        if (mView != null) {
//            mView.showDialog();
        }
        mInFoIndicator.getHistoryHeyDayInFo(id, this);
    }
}
