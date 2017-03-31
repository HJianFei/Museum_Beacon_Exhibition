package com.hjianfei.museum_beacon_exhibition.presenter.activity.feed_back;

import java.util.Map;

/**
 * Created by HJianFei on 2016/11/21.
 */

public interface FeedBackPresenter {

    void saveFeedBack(Map<String, Object> map);

    void onDestroy();
}
