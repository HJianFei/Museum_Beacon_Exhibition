package com.hjianfei.museum_beacon_exhibition.model.app_update_info;

import com.hjianfei.museum_beacon_exhibition.bean.UpdateInfo;

/**
 * Created by HJianFei on 2016/11/20.
 */

public interface AppUpdateInfoIndicator {

    interface onFinishListener {

        void getAppUpdateInfoFinished(UpdateInfo updateInfo);

        void onError();
    }
    void getAppUpdateInfo(onFinishListener listener);
}
