package com.hjianfei.museum_beacon_exhibition.presenter.activity.feed_back;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.model.activity.feed_back.FeedBackIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.feed_back.FeedBackIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.feedback.FeedBackView;

import java.util.Map;

/**
 * Created by HJianFei on 2016/11/21.
 */

public class FeedBackPresenterImpl implements FeedBackPresenter, FeedBackIndicator.onFinishListener {

    private FeedBackView mFeedBackView;
    private FeedBackIndicator mFeedBackIndicator;

    public FeedBackPresenterImpl(FeedBackView mFeedBackView) {
        this.mFeedBackView = mFeedBackView;
        mFeedBackIndicator = new FeedBackIndicatorImpl();
    }

    @Override
    public void feedBackInfoFinished(ResultCode resultCode) {
        if (null != mFeedBackView) {
            mFeedBackView.hideDialog();
        }
        mFeedBackView.onFeedBackSuccess(resultCode);

    }

    @Override
    public void onError() {
        mFeedBackView.showError();

    }

    @Override
    public void saveFeedBack(Map<String, Object> map) {
        if (null != mFeedBackView) {
            mFeedBackView.showDialog();
        }
        mFeedBackIndicator.saveFeedBack(map, this);
    }

    @Override
    public void onDestroy() {

    }
}
