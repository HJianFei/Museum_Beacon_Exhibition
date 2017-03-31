package com.hjianfei.museum_beacon_exhibition.model.activity.appreciate_detail;


import com.hjianfei.museum_beacon_exhibition.bean.AppreciateDetail;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;

import java.util.Map;

/**
 * Created by HJianFei on 2016/9/21.
 */

public interface AppreciateDetailIndicator {

    interface onFinishListener {

        void onInitAppreciateDetailFinished(AppreciateDetail appreciateDetail);

        void onSaveCollectionSuccess(ResultCode resultCode);

        void onError();
    }

    void getAppreciateDetail(String id, onFinishListener listener);

    void saveCollection(Map<String, Object> map,onFinishListener listener);
}
