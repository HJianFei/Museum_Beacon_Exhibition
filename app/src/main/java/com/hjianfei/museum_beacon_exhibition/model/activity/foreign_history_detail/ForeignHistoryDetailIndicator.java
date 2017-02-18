package com.hjianfei.museum_beacon_exhibition.model.activity.foreign_history_detail;

import com.hjianfei.museum_beacon_exhibition.bean.ForeignHistoryDetail;

/**
 * Created by HJianFei on 2016/12/9.
 */

public interface ForeignHistoryDetailIndicator {

    interface onFinishListener {

        void getForeignCountryDetailFinished(ForeignHistoryDetail foreignHistoryDetail);

//        void onSaveCollectionSuccess(ResultCode resultCode);

        void onError();
    }

    void getForeignCountryDetail(String id, onFinishListener listener);
}
