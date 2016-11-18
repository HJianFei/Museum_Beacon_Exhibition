package com.hjianfei.museum_beacon_exhibition.model.activity.museum_detail;

import com.hjianfei.museum_beacon_exhibition.bean.MuseumDetail;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;

import java.util.Map;

/**
 * Created by HJianFei on 2016/11/5.
 */

public interface MuseumDetailIndicator {
    interface onFinishedListener {

        void onInitMuseumDataFinished(MuseumDetail museumDetail);

        void onSaveCollectionSuccess(ResultCode resultCode);

        void onError();
    }

    void getMuseumDetailInfo(String museum_name, onFinishedListener listener);

    void saveCollection(Map<String, Object> map, onFinishedListener listener);
}
