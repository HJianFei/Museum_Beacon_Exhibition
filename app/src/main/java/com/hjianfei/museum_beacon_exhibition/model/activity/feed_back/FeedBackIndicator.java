package com.hjianfei.museum_beacon_exhibition.model.activity.feed_back;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;

import java.util.Map;

/**
 * Created by HJianFei on 2016/11/21.
 */

public interface FeedBackIndicator {


    interface onFinishListener {

        void feedBackInfoFinished(ResultCode resultCode);

        void onError();
    }

    void saveFeedBack(Map<String, Object> map, onFinishListener listener);
}
