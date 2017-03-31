package com.hjianfei.museum_beacon_exhibition.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.brtbeacon.sdk.BRTBeacon;
import com.brtbeacon.sdk.BRTBeaconManager;
import com.brtbeacon.sdk.BRTThrowable;
import com.brtbeacon.sdk.callback.BRTBeaconManagerListener;
import com.hjianfei.museum_beacon_exhibition.application.BaseApplication;
import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.hjianfei.museum_beacon_exhibition.bean.NotifyResult;
import com.hjianfei.museum_beacon_exhibition.bean.StepView;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.guide.GuidePresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.guide.GuidePresenterImpl;
import com.hjianfei.museum_beacon_exhibition.receiver.ShowNotificationReceiver;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.view.fragment.guide.GuideView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class BeaconService extends Service implements BRTBeaconManagerListener, GuideView {

    private BRTBeaconManager beaconManager;
    private Set<Integer> brtBeacons = BaseApplication.getInstance().getBrtBeacons();
    private Set<Integer> notifies = BaseApplication.getInstance().getNotify();
    private GuidePresenter mGuidePresenter;
    private List<BeaconAppreciate> beaconAppreciateList = BaseApplication.getInstance().getmBeaconAppreciateList();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d("onResponse", "onCreated");
        mGuidePresenter = new GuidePresenterImpl(this);
        beaconManager = BaseApplication.getInstance().getBRTBeaconManager();
        beaconManager.setBRTBeaconManagerListener(this);
        beaconManager.startRanging();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        LogUtils.d("onResponse", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        beaconManager.stopRanging();
    }

    @Override
    public void onUpdateBeacon(ArrayList<BRTBeacon> arrayList) {

    }

    @Override
    public void onNewBeacon(BRTBeacon brtBeacon) {

        LogUtils.d(Constants.TAG, brtBeacon.getMajor() + "");
        LogUtils.d(Constants.TAG, brtBeacon.getMinor() + "");
        if (brtBeacon.getMajor() == 2001) {//判断大范围的Beacon，一个区域
            mGuidePresenter.getStepView(brtBeacon.getMinor() + "");
        }

        boolean contains = brtBeacons.contains(brtBeacon.getMinor());

        if (!contains) {//不在里面，新的Beacon
            if (brtBeacon.getMajor() == 2002) {//某一件具体的文物
                brtBeacons.add(brtBeacon.getMinor());
                mGuidePresenter.getBeaconAppreciateByMinor(brtBeacon.getMinor() + "");
            }
        }
        boolean contains_notify = notifies.contains(brtBeacon.getMinor());
        if (!contains_notify) {//不在里面，新的Beacon
            if (brtBeacon.getMajor() == 2003) {//通知
                notifies.add(brtBeacon.getMinor());
                mGuidePresenter.getNotify(brtBeacon.getMinor() + "");
            }
        }

    }

    @Override
    public void onGoneBeacon(BRTBeacon brtBeacon) {

    }

    @Override
    public void onError(BRTThrowable brtThrowable) {

    }

    @Override
    public void getStepViewSuccess(StepView stepView) {

        if (stepView.getCode() == 200) {
            BaseApplication.getInstance().setStep(stepView.getStepView().getStep_name());
            EventBus.getDefault().post(stepView);
            Intent intent = new Intent(this, ShowNotificationReceiver.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("stepView", stepView);
            intent.putExtra("stepView", bundle);
            intent.putExtra("type", "stepView");
            sendBroadcast(intent);

        }

    }

    @Override
    public void getBeaconAppreciateByMinorSuccess(BeaconAppreciate beaconAppreciate) {

        if (beaconAppreciate.getCode() == 200) {
            beaconAppreciateList.add(beaconAppreciate);
            EventBus.getDefault().post(beaconAppreciate);
            Intent intent = new Intent(this, ShowNotificationReceiver.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("beaconAppreciate", beaconAppreciate);
            intent.putExtra("guide_detail", bundle);
            intent.putExtra("type", "Appreciate");
            sendBroadcast(intent);

        }
    }

    @Override
    public void getNotifyFinished(NotifyResult notifyResult) {

        if (notifyResult.getCode() == 200) {
            LogUtils.d("onResponse",notifyResult.toString());
            Intent intent = new Intent(this, ShowNotificationReceiver.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("notifyResult", notifyResult);
            intent.putExtra("notifyResult", bundle);
            intent.putExtra("type", "notify");
            sendBroadcast(intent);

        }

    }
}
