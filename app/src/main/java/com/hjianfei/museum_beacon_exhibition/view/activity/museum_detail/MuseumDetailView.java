package com.hjianfei.museum_beacon_exhibition.view.activity.museum_detail;

import com.hjianfei.museum_beacon_exhibition.bean.MuseumDetail;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

/**
 * Created by HJianFei on 2016/11/5.
 */

public interface MuseumDetailView extends BaseView {

    void initMuseumDetailData(MuseumDetail museumDetail);

    void onSaveCollectionSuccess(ResultCode resultCode);
}
