package com.hjianfei.museum_beacon_exhibition.model.activity.appreciate_detail;


import com.hjianfei.museum_beacon_exhibition.bean.AppreciateDetail;

/**
 * Created by HJianFei on 2016/9/21.
 */

public interface AppreciateDetailIndicator {

    interface onFinishListener {

        void onInitAppreciateDetailFinished(AppreciateDetail appreciateDetail);

        void onError();
    }

    void getAppreciateDetail(String detail_url, onFinishListener listener);
}
