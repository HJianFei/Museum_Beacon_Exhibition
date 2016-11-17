package com.hjianfei.museum_beacon_exhibition.model.activity.appreciate;


import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;

import java.util.List;

/**
 * 创建时间： 2016/9/19.
 * 作者：HJianFei
 * 功能描述：
 */

public interface AppreciateIndicator {
    interface onFinishListener {

        void onInitAppreciateFinished(List<Appreciates.AppreciatesBean> appreciatesBeans);

        //刷新成功
        void onRefreshAppreciateSuccess(List<Appreciates.AppreciatesBean> appreciatesBeans);

        //加载成功
        void onLoadMoreAppreciateSuccess(List<Appreciates.AppreciatesBean> appreciatesBeans);


        void onError();
    }

    void onInitAppreciateByType(String tag, onFinishListener listener,String page);

    void refreshAppreciateByType(String tag, onFinishListener listener,String page);

    void loadMoreAppreciateByType(String tag, onFinishListener listener,String page);

}
