package com.hjianfei.museum_beacon_exhibition.model.activity.china_history_culture_detail;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryCultureDetail;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface ChinaHistoryCultureDetailIndicator {

    interface onFinishListener {

        void inFinished(ChinaHistoryCultureDetail chinaHistoryCultureDetail);

//        void onSaveCollectionSuccess(ResultCode resultCode);

        void onError();
    }

    void getHistoryCultureDetailDetail(String title, onFinishListener listener);
}
