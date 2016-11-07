package com.hjianfei.museum_beacon_exhibition.view.fragment.appreciate;

import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

import java.util.List;

/**
 * Created by HJianFei on 2016/11/7.
 */

public interface AppreciateView extends BaseView {

    void initAppreciateData(List<Appreciates.AppreciatesBean> appreciatesBeans);

    void refreshAppreciateData(List<Appreciates.AppreciatesBean> appreciatesBeans);

    void loadMoreAppreciateData(List<Appreciates.AppreciatesBean> appreciatesBeans);
}
