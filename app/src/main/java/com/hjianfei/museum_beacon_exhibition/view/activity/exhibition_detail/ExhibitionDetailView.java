package com.hjianfei.museum_beacon_exhibition.view.activity.exhibition_detail;

import com.hjianfei.museum_beacon_exhibition.bean.ExhibitionDetail;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

/**
 * Created by HJianFei on 2016/11/6.
 */

public interface ExhibitionDetailView extends BaseView {

    void initExhibitionDetailData(ExhibitionDetail exhibitionDetail);

    void onSaveCollectionSuccess(ResultCode resultCode);
}
