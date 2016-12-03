package com.hjianfei.museum_beacon_exhibition.model.base;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;

import java.util.Map;

/**
 * Created by HJianFei on 2016/12/3.
 */

public interface BaseIndicator {

    interface onFinishListener {
        void onSaveCollectionSuccess(ResultCode resultCode);

        void onError();
    }

    void saveCollection(Map<String, Object> map, onFinishListener listener);
}
