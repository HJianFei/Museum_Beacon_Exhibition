package com.hjianfei.museum_beacon_exhibition.utils;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hjianfei.museum_beacon_exhibition.canstants.Constants;

/**
 * Created by HJianFei on 2016/12/2.
 */

public class StatusBarUtils {

    /**
     * 设置状态栏透明
     *
     * @param activity
     */
    public static void setStatusBarTransparent(FragmentActivity activity) {
        LogUtils.d(Constants.TAG,"hererrrr");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(FragmentActivity activity) {
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            return activity.getResources().getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }

    }
}
