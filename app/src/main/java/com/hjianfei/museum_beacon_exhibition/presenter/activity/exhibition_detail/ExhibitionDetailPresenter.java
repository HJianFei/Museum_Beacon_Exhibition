package com.hjianfei.museum_beacon_exhibition.presenter.activity.exhibition_detail;

import java.util.Map;

/**
 * Created by HJianFei on 2016/9/20.
 */

public interface ExhibitionDetailPresenter {

    void onInitExhibitionDetailData(String detail_url);

    void onSaveCollection(Map<String, Object> map);

    void onDestroy();
}
