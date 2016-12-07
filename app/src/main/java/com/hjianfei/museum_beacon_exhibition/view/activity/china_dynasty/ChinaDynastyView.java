package com.hjianfei.museum_beacon_exhibition.view.activity.china_dynasty;

import com.hjianfei.museum_beacon_exhibition.bean.ChinaDynasty;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

/**
 * Created by HJianFei on 2016/12/7.
 */

public interface ChinaDynastyView extends BaseView {

    void initChinaDynasty(ChinaDynasty chinaDynasty);

    void refreshChinaDynasty(ChinaDynasty chinaDynasty);

    void loadMoreChinaDynasty(ChinaDynasty chinaDynasty);
}
