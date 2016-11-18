package com.hjianfei.museum_beacon_exhibition.model.activity.exhibition_detail;


import com.hjianfei.museum_beacon_exhibition.bean.ExhibitionDetail;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;

import java.util.Map;

/**
 * Created by HJianFei on 2016/9/20.
 */

public interface ExhibitionDetailIndicator {

    interface onFinishListener {

        void onInitExhibitionFinished(ExhibitionDetail exhibitionDetail);

        void onSaveCollectionSuccess(ResultCode resultCode);

        void onError();
    }

    void getExhibitionDetail(String detail_url, onFinishListener listener);

    void saveCollection(Map<String, Object> map, onFinishListener listener);
}
