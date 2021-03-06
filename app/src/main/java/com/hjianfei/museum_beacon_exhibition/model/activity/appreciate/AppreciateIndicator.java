package com.hjianfei.museum_beacon_exhibition.model.activity.appreciate;


import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;

import java.util.Map;

/**
 * 创建时间： 2016/9/19.
 * 作者：HJianFei
 * 功能描述：
 */

public interface AppreciateIndicator {
    interface onFinishListener {

        void onInitAppreciateFinished(Appreciates appreciates);

        //刷新成功
        void onRefreshAppreciateSuccess(Appreciates appreciates);

        //加载成功
        void onLoadMoreAppreciateSuccess(Appreciates appreciates);


        void onError();
    }

    void onInitAppreciateByType(String museum_name,String tag, onFinishListener listener, String page,String search_condition);

    void refreshAppreciateByType(String museum_name,String tag, onFinishListener listener, String page,String search_condition);

    void loadMoreAppreciateByType(String museum_name,String tag, onFinishListener listener, String page,String search_condition);

    void updateAppreciateViewCount(Map<String, Object> map);

}
