package com.hjianfei.museum_beacon_exhibition.view.fragment.museum_news.cultural;

import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

import java.util.List;

/**
 * Created by HJianFei on 2016/11/5.
 */

public interface CulturalView extends BaseView {
    //页面可见时，首次加载文物数据
    void initCulturalData(List<Appreciates.AppreciatesBean> appreciatesBeans);

    //页面上拉加载更多
    void loadMoreCulturalData(List<Appreciates.AppreciatesBean> appreciatesBeans);

    //页面下拉刷新
    void refreshCulturalData(List<Appreciates.AppreciatesBean> appreciatesBeans);
}
