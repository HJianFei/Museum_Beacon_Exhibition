package com.hjianfei.museum_beacon_exhibition.view.base;

/**
 * Created by HJianFei on 2016/11/2.
 */

public interface BaseView {
    //显示进度条
    void showDialog();

    //隐藏进度条
    void hideDialog();

    //显示加载错误
    void showError();

    //显示数据为空
    void showEmpty();
}
