package com.hjianfei.museum_beacon_exhibition.model.activity.china_history_history_detail;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHistoryDetail;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface ChinaHistoryHistoryDetailIndicator {

    interface onFinishListener {

        void inFinished(ChinaHistoryHistoryDetail chinaHistoryHistoryDetail);

//        void onSaveCollectionSuccess(ResultCode resultCode);

        void onError();
    }

    void getHistoryHistoryDetailDetail(String title, onFinishListener listener);
}
