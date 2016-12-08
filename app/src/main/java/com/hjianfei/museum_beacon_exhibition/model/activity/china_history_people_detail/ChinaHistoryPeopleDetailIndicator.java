package com.hjianfei.museum_beacon_exhibition.model.activity.china_history_people_detail;

import com.hjianfei.museum_beacon_exhibition.bean.HistoryPeopleDetail;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface ChinaHistoryPeopleDetailIndicator {

    interface onFinishListener {

        void getHistoryPeopleDetailFinished(HistoryPeopleDetail historyPeopleDetail);

//        void onSaveCollectionSuccess(ResultCode resultCode);

        void onError();
    }

    void getHistoryPeopleDetail(String detail_url, onFinishListener listener);
}
