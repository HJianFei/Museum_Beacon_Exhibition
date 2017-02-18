package com.hjianfei.museum_beacon_exhibition.presenter.activity.museum_detail;

import java.util.Map;

/**
 * Created by HJianFei on 2016/11/5.
 */

public interface MuseumDetailPresenter {

    void initMuseumDetailData(String id);

    void onSaveCollection(Map<String, Object> map);
}
