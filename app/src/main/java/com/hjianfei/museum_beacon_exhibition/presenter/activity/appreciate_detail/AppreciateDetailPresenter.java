package com.hjianfei.museum_beacon_exhibition.presenter.activity.appreciate_detail;

import java.util.Map;

/**
 * Created by HJianFei on 2016/9/21.
 */

public interface AppreciateDetailPresenter {

    void onInitAppreciateDetailData(String id);

    void onSaveCollection(Map<String, Object> map);

    void onDestroy();
}
