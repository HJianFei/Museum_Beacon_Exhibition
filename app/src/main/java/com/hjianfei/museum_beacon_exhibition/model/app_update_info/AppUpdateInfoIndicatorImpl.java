package com.hjianfei.museum_beacon_exhibition.model.app_update_info;

import com.hjianfei.museum_beacon_exhibition.bean.UpdateInfo;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/11/20.
 */

public class AppUpdateInfoIndicatorImpl implements AppUpdateInfoIndicator {
    @Override
    public void getAppUpdateInfo(final onFinishListener listener) {
        NetWorkUtils.getApi().getAppUpdateInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        listener.onError();
                    }

                    @Override
                    public void onNext(UpdateInfo updateInfo) {
                        listener.getAppUpdateInfoFinished(updateInfo);

                    }
                });
    }
}
