package com.hjianfei.museum_beacon_exhibition.view.fragment.dynasty.dynasty_culture;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryCulture;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

/**
 * Created by HJianFei on 2016/12/8.
 */

public interface DynastyCultureView extends BaseView{

    void onInitChinaHistoryCultureFinished(ChinaHistoryCulture chinaHistoryCulture);

    void onRefreshChinaHistoryCultureFinished(ChinaHistoryCulture chinaHistoryCulture);

    void onLoadMoreChinaHistoryCultureFinished(ChinaHistoryCulture chinaHistoryCulture);
}
