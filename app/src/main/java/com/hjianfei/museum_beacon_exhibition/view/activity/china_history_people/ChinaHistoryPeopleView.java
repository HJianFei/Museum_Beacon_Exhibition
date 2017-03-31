package com.hjianfei.museum_beacon_exhibition.view.activity.china_history_people;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryPeople;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface ChinaHistoryPeopleView extends BaseView {

    void onInitFinished(ChinaHistoryPeople chinaHistoryPeople);

    void onRefreshFinished(ChinaHistoryPeople chinaHistoryPeople);

    void onLoadMoreFinished(ChinaHistoryPeople chinaHistoryPeople);
}
