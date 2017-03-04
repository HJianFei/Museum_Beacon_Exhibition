package com.hjianfei.museum_beacon_exhibition.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.hjianfei.museum_beacon_exhibition.bean.NotifyResult;
import com.hjianfei.museum_beacon_exhibition.bean.StepView;
import com.hjianfei.museum_beacon_exhibition.utils.SystemUtils;
import com.hjianfei.museum_beacon_exhibition.view.activity.guide_detail.GuideDetailActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.main.MainActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.notify.NotifyActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.stepview.StepViewActivity;

/**
 * Created by Administrator on 2017/3/4 0004.
 */

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        //判断app进程是否存活
        if (SystemUtils.isAppAlive(context, "com.hjianfei.museum_beacon_exhibition")) {

            Intent mainIntent = new Intent(context, MainActivity.class);
            mainIntent.putExtra("notify", "notify");
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

            if (intent.getStringExtra("type").equals("Appreciate")) {
                BeaconAppreciate beaconAppreciate = (BeaconAppreciate) intent.getBundleExtra("guide_detail").getSerializable("beaconAppreciate");
                Intent detailIntent = new Intent(context, GuideDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("beaconAppreciate", beaconAppreciate);
                detailIntent.putExtra("guide_detail", bundle);
                Intent[] intents = {mainIntent, detailIntent};
                context.startActivities(intents);
            } else if (intent.getStringExtra("type").equals("stepView")) {
                StepView stepView = (StepView) intent.getBundleExtra("stepView").getSerializable("stepView");
                Intent detailIntent = new Intent(context, StepViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("stepView", stepView);
                detailIntent.putExtra("stepView", bundle);
                Intent[] intents = {mainIntent, detailIntent};
                context.startActivities(intents);
            } else if (intent.getStringExtra("type").equals("notify")) {
                NotifyResult stepView = (NotifyResult) intent.getBundleExtra("notifyResult").getSerializable("notifyResult");
                Intent detailIntent = new Intent(context, NotifyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("notifyResult", stepView);
                detailIntent.putExtra("notifyResult", bundle);
                Intent[] intents = {mainIntent, detailIntent};
                context.startActivities(intents);
            }

        }
    }
}
