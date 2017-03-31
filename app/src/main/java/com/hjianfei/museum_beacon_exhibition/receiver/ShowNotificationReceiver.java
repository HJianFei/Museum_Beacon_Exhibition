package com.hjianfei.museum_beacon_exhibition.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.hjianfei.museum_beacon_exhibition.bean.NotifyResult;
import com.hjianfei.museum_beacon_exhibition.bean.StepView;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Administrator on 2017/3/4 0004.
 */

public class ShowNotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getStringExtra("type").equals("Appreciate")) {
            BeaconAppreciate beaconAppreciate = (BeaconAppreciate) intent.getBundleExtra("guide_detail").getSerializable("beaconAppreciate");
            Intent broadcastIntent = new Intent(context, NotificationReceiver.class);
            broadcastIntent.putExtra("type", "Appreciate");
            Bundle bundle = new Bundle();
            bundle.putSerializable("beaconAppreciate", beaconAppreciate);
            broadcastIntent.putExtra("guide_detail", bundle);
            PendingIntent contentIntent = PendingIntent.getBroadcast(context, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
            mBuilder.setContentTitle("文物介绍")//设置通知栏标题
                    .setContentText(beaconAppreciate.getBeaconAppreciate().getTitle()) //设置通知栏显示内容
                    .setContentIntent(contentIntent)
                    .setTicker(beaconAppreciate.getBeaconAppreciate().getTitle()) //通知首次出现在通知栏，带上升动画效果的
                    .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                    .setPriority(1000) //设置该通知优先级
                    .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                    .setOngoing(false)//ture，设置他为一个正在进行的通知。
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setSmallIcon(R.drawable.ic_launcher);//设置通知小ICON
            mNotificationManager.notify(2, mBuilder.build());
        } else if (intent.getStringExtra("type").equals("stepView")) {

            StepView stepView = (StepView) intent.getBundleExtra("stepView").getSerializable("stepView");
            Intent broadcastIntent = new Intent(context, NotificationReceiver.class);
            broadcastIntent.putExtra("type", "stepView");
            Bundle bundle = new Bundle();
            bundle.putSerializable("stepView", stepView);
            broadcastIntent.putExtra("stepView", bundle);
            PendingIntent contentIntent = PendingIntent.getBroadcast(context, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
            mBuilder.setContentTitle("位置提醒")//设置通知栏标题
                    .setContentText("你已进入" + stepView.getStepView().getStep_name() + "啦") //设置通知栏显示内容
                    .setContentIntent(contentIntent)
                    .setTicker("你已进入" + stepView.getStepView().getStep_name() + "啦") //通知首次出现在通知栏，带上升动画效果的
                    .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                    .setPriority(1000) //设置该通知优先级
                    .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                    .setOngoing(false)//ture，设置他为一个正在进行的通知。
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setSmallIcon(R.drawable.ic_launcher);//设置通知小ICON
            mNotificationManager.notify(1, mBuilder.build());

        } else if (intent.getStringExtra("type").equals("notify")) {
            NotifyResult notifyResult = (NotifyResult) intent.getBundleExtra("notifyResult").getSerializable("notifyResult");
            Intent broadcastIntent = new Intent(context, NotificationReceiver.class);
            broadcastIntent.putExtra("type", "notify");
            Bundle bundle = new Bundle();
            bundle.putSerializable("notifyResult", notifyResult);
            broadcastIntent.putExtra("notifyResult", bundle);
            PendingIntent contentIntent = PendingIntent.getBroadcast(context, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
            mBuilder.setContentTitle(notifyResult.getNotify().getType())//设置通知栏标题
                    .setContentText(notifyResult.getNotify().getNotify_title()) //设置通知栏显示内容
                    .setContentIntent(contentIntent)
                    .setTicker(notifyResult.getNotify().getNotify_title()) //通知首次出现在通知栏，带上升动画效果的
                    .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                    .setPriority(1000) //设置该通知优先级
                    .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                    .setOngoing(false)//ture，设置他为一个正在进行的通知。
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setSmallIcon(R.drawable.ic_launcher);//设置通知小ICON
            mNotificationManager.notify(1, mBuilder.build());

        }
    }
}
