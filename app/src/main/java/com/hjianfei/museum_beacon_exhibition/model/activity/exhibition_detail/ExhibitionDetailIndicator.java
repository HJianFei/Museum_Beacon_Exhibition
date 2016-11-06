package com.hjianfei.museum_beacon_exhibition.model.activity.exhibition_detail;


import com.hjianfei.museum_beacon_exhibition.bean.ExhibitionDetail;

/**
 * Created by HJianFei on 2016/9/20.
 */

public interface ExhibitionDetailIndicator {

    interface onFinishListener {

        void onInitExhibitionFinished(ExhibitionDetail exhibitionDetail);

        void onError();
    }

    void getExhibitionDetail(String detail_url, onFinishListener listener);
}
