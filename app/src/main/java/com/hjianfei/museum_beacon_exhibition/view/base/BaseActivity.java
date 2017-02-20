package com.hjianfei.museum_beacon_exhibition.view.base;

import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2017/2/19.
 */

public class BaseActivity extends AppCompatActivity {

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
