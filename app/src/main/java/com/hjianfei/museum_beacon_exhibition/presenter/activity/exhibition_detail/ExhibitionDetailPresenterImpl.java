package com.hjianfei.museum_beacon_exhibition.presenter.activity.exhibition_detail;


import com.hjianfei.museum_beacon_exhibition.bean.ExhibitionDetail;
import com.hjianfei.museum_beacon_exhibition.model.activity.exhibition_detail.ExhibitionDetailIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.exhibition_detail.ExhibitionDetailIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.exhibition_detail.ExhibitionDetailView;

/**
 * Created by HJianFei on 2016/9/20.
 */

public class ExhibitionDetailPresenterImpl implements ExhibitionDetailPresenter, ExhibitionDetailIndicator.onFinishListener {

    private ExhibitionDetailView mExhibitionDetailView;
    private ExhibitionDetailIndicator mExhibitionDetailIndicator;

    public ExhibitionDetailPresenterImpl(ExhibitionDetailView mExhibitionDetailView) {
        this.mExhibitionDetailView = mExhibitionDetailView;
        mExhibitionDetailIndicator = new ExhibitionDetailIndicatorImpl();
    }


    @Override
    public void onInitExhibitionFinished(ExhibitionDetail exhibitionDetail) {
        mExhibitionDetailView.initExhibitionDetailData(exhibitionDetail);

    }

    @Override
    public void onError() {
        mExhibitionDetailView.showError();

    }

    @Override
    public void onInitExhibitionDetailData(String detail_url) {
        mExhibitionDetailIndicator.getExhibitionDetail(detail_url, this);

    }

    @Override
    public void onDestroy() {

    }
}
