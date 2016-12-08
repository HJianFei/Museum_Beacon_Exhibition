package com.hjianfei.museum_beacon_exhibition.model.activity.china_big_thing_detail;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryBigThingDetail;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface ChinaBigThingDetailIndicator {

    interface onFinishListener {

        void getChinaHistoryBigThingDetailFinished(ChinaHistoryBigThingDetail chinaHistoryBigThingDetail);

//        void onSaveCollectionSuccess(ResultCode resultCode);

        void onError();
    }

    void getChinaHistoryBigThingDetail(String title, onFinishListener listener);
}
