package com.hjianfei.museum_beacon_exhibition.view.activity.history_check;

import com.hjianfei.museum_beacon_exhibition.bean.HistoryCheck;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

/**
 * Created by HJianFei on 2016/12/9.
 */

public interface HistoryCheckView extends BaseView {

    void init(HistoryCheck check);

    void refresh(HistoryCheck check);

    void loadMore(HistoryCheck check);
}
