package com.hjianfei.museum_beacon_exhibition.model.fragment.home;


import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.bean.Exhibitions;
import com.hjianfei.museum_beacon_exhibition.bean.ViewPager;

/**
 * Created by HJianFei on 2016/11/3.
 */

public interface HomeIndicator {

    interface onFinishedListener {
        void OnViewPagerFinished(ViewPager viewPager);

        void onAppreciateRecyclerViewFinished(Appreciates appreciates);

        void onHotExhibitionFinished(Exhibitions exhibitions);

        void OnError();
    }

    void getViewPagerData(onFinishedListener listener);

    void getAppreciateRecyclerView(onFinishedListener listener);

    void getHotExhibition(onFinishedListener listener);
}
