package com.hjianfei.museum_beacon_exhibition.model.activity.history_check_detail;

import com.hjianfei.museum_beacon_exhibition.bean.HistoryCheckDetail;

/**
 * Created by HJianFei on 2016/12/9.
 */

public interface HistoryCheckDetailIndicator {

    interface onFinishListener {

        void inFinished(HistoryCheckDetail checkDetail);

//        void onSaveCollectionSuccess(ResultCode resultCode);

        void onError();
    }

    void getHistoryHCheckDetail(String id, onFinishListener listener);
}
