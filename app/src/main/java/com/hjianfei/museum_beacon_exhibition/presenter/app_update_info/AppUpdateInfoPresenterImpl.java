package com.hjianfei.museum_beacon_exhibition.presenter.app_update_info;

import com.hjianfei.museum_beacon_exhibition.bean.UpdateInfo;
import com.hjianfei.museum_beacon_exhibition.model.app_update_info.AppUpdateInfoIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.model.app_update_info.AppUpdateInfoIndicator;
import com.hjianfei.museum_beacon_exhibition.view.activity.setting.SettingView;

/**
 * Created by HJianFei on 2016/11/20.
 */

public class AppUpdateInfoPresenterImpl implements AppUpdateInfoPresenter, AppUpdateInfoIndicator.onFinishListener {

    private SettingView mSettingView;
    private AppUpdateInfoIndicator mAppUpdateInfoIndicator;

    public AppUpdateInfoPresenterImpl(SettingView mSettingView) {
        this.mSettingView = mSettingView;
        mAppUpdateInfoIndicator = new AppUpdateInfoIndicatorImpl();
    }

    @Override
    public void getAppUpdateInfo() {
        if (mSettingView != null) {
            mSettingView.showDialog();
        }
        mAppUpdateInfoIndicator.getAppUpdateInfo(this);


    }

    @Override
    public void getAppUpdateInfoFinished(UpdateInfo updateInfo) {
        if (mSettingView != null) {
            mSettingView.hideDialog();
        }
        mSettingView.getUpdateInfoFinished(updateInfo);

    }

    @Override
    public void onError() {

    }
}
