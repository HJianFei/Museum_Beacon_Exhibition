package com.hjianfei.museum_beacon_exhibition.view.fragment.foreign_country;

import com.hjianfei.museum_beacon_exhibition.bean.ForeignHistory;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

/**
 * Created by HJianFei on 2016/12/9.
 */

public interface ForeignCountryView extends BaseView {

    void onInitFinished(ForeignHistory foreignHistory);

    void onRefreshFinished(ForeignHistory foreignHistory);

    void onLoadMoreFinished(ForeignHistory foreignHistory);
}
