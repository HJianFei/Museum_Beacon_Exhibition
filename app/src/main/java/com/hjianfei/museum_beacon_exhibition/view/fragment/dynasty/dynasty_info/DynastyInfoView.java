package com.hjianfei.museum_beacon_exhibition.view.fragment.dynasty.dynasty_info;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHistory;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface DynastyInfoView extends BaseView {

    void onInitChinaHistoryHistoryFinished(ChinaHistoryHistory chinaHistoryHistory);

    void onRefreshChinaHistoryHistoryFinished(ChinaHistoryHistory chinaHistoryHistory);

    void onLoadMoreChinaHistoryHistoryFinished(ChinaHistoryHistory chinaHistoryHistory);
}
