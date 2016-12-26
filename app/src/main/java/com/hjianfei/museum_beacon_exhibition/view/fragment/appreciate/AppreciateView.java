package com.hjianfei.museum_beacon_exhibition.view.fragment.appreciate;

import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

/**
 * Created by HJianFei on 2016/11/7.
 */

public interface AppreciateView extends BaseView {

    void initAppreciateData(Appreciates appreciates);

    void refreshAppreciateData(Appreciates appreciates);

    void loadMoreAppreciateData(Appreciates appreciates);
}
