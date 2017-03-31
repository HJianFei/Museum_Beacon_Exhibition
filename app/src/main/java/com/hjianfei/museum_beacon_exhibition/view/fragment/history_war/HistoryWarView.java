package com.hjianfei.museum_beacon_exhibition.view.fragment.history_war;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryOldenWar;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface HistoryWarView extends BaseView {

    void onInitFinished(ChinaHistoryOldenWar chinaHistoryOldenWar);

    void onRefreshFinished(ChinaHistoryOldenWar chinaHistoryOldenWar);

    void onLoadMoreFinished(ChinaHistoryOldenWar chinaHistoryOldenWar);


}
