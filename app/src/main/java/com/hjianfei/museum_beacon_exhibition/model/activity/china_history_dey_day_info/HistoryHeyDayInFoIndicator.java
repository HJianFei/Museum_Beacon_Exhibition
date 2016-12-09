package com.hjianfei.museum_beacon_exhibition.model.activity.china_history_dey_day_info;

import com.hjianfei.museum_beacon_exhibition.bean.HistoryHeyDayInFo;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface HistoryHeyDayInFoIndicator {

    interface onFinishListener {

        void inFinished(HistoryHeyDayInFo historyHeyDayInFo);

//        void onSaveCollectionSuccess(ResultCode resultCode);

        void onError();
    }

    void getHistoryHeyDayInFo(String title, onFinishListener listener);
}
