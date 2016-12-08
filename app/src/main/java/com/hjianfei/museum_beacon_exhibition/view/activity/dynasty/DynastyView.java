package com.hjianfei.museum_beacon_exhibition.view.activity.dynasty;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryBigThing;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryPeople;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

/**
 * Created by HJianFei on 2016/12/7.
 */

public interface DynastyView extends BaseView {

    void initDynastyBigThing(ChinaHistoryBigThing chinaHistoryBigThing);

    void initChinaHistoryPeople(ChinaHistoryPeople chinaHistoryPeople);
}
