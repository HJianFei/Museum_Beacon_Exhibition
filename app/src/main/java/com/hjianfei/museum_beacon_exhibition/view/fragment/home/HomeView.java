package com.hjianfei.museum_beacon_exhibition.view.fragment.home;


import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.bean.Exhibitions;
import com.hjianfei.museum_beacon_exhibition.bean.ViewPager;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

import java.util.List;

/**
 * Created by HJianFei on 2016/11/3.
 */

public interface HomeView extends BaseView {

    //初始化数据(ViewPager)
    void initHomeViewPager(ViewPager viewPager);

    //初始化文物鉴赏appreciate_recyclerview
    void initAppreciateRecyclerView(List<Appreciates.AppreciatesBean> appreciatesBeans);

    //初始化展览预告
    void initHotExhibition(Exhibitions exhibitions);

}
