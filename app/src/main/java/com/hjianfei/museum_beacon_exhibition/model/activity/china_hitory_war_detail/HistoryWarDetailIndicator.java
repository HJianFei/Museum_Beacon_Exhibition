package com.hjianfei.museum_beacon_exhibition.model.activity.china_hitory_war_detail;

import com.hjianfei.museum_beacon_exhibition.bean.HistoryWarDetail;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface HistoryWarDetailIndicator {

    interface onFinishListener {

        void getHistoryWarDetailFinished(HistoryWarDetail historyWarDetail);

//        void onSaveCollectionSuccess(ResultCode resultCode);

        void onError();
    }

    void getHistoryWarDetail(String detail_url, onFinishListener listener);
}
