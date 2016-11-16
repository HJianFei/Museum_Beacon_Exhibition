package com.hjianfei.museum_beacon_exhibition.view.activity.register;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseView;

/**
 * Created by HJianFei on 2016/11/16.
 */

public interface RegisterView extends BaseView {

    /**
     * 用户注册
     *
     * @param resultCode
     */
    void registerUserFinished(ResultCode resultCode);
}
