package com.hjianfei.museum_beacon_exhibition.model.activity.photo_detail;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;

/**
 * Created by HJianFei on 2016/12/4.
 */

public interface PhotoDetailIndicator {

    interface onFinishedListener {

        void onSaveSuccess(ResultCode resultCode);
    }

    void savePicFromNet(String picPath,onFinishedListener listener);
}
