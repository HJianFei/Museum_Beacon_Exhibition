package com.hjianfei.museum_beacon_exhibition.view.activity.radar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.brtbeacon.sdk.BRTBeacon;
import com.brtbeacon.sdk.BRTBeaconManager;
import com.brtbeacon.sdk.BRTThrowable;
import com.brtbeacon.sdk.callback.BRTBeaconManagerListener;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.RadarViewPagerAdapter;
import com.hjianfei.museum_beacon_exhibition.application.BaseApplication;
import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.hjianfei.museum_beacon_exhibition.bean.Info;
import com.hjianfei.museum_beacon_exhibition.bean.StepView;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.radar.RadarPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.radar.RadarPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.widget.radar_custom_view.FixedSpeedScroller;
import com.hjianfei.museum_beacon_exhibition.utils.widget.radar_custom_view.RadarView;
import com.hjianfei.museum_beacon_exhibition.utils.widget.radar_custom_view.RadarViewGroup;
import com.hjianfei.museum_beacon_exhibition.utils.widget.radar_custom_view.RadarViewPager;
import com.hjianfei.museum_beacon_exhibition.utils.widget.radar_custom_view.ZoomOutPageTransformer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadarActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadarViewGroup.IRadarClickListener, BRTBeaconManagerListener, com.hjianfei.museum_beacon_exhibition.view.activity.radar.RadarView {

    @BindView(R.id.step_view)
    HorizontalStepView stepView;
    @BindView(R.id.id_scan_circle)
    RadarView idScanCircle;
    @BindView(R.id.radar)
    RadarViewGroup radar;
    @BindView(R.id.vp)
    RadarViewPager vp;
    @BindView(R.id.ry_container)
    RelativeLayout ryContainer;
    private BRTBeaconManager beaconManager;
    private Set<BRTBeacon> brtBeacons = BaseApplication.getInstance().getBrtBeacons();

    private int[] mImgs = {R.drawable.len};
    private String[] mNames = {"A"};
    private int mPosition;
    private FixedSpeedScroller scroller;
    private SparseArray<Info> mDatas = new SparseArray<>();
    private RadarPresenter mRadarPresenter;
    private List<String> step = BaseApplication.getInstance().getStep();
    private List<Integer> step_type = BaseApplication.getInstance().getStep_type();
    private int minor;
    private RadarViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_radar);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        beaconManager = BaseApplication.getInstance().getBRTBeaconManager();
        beaconManager.setBRTBeaconManagerListener(this);
        mRadarPresenter = new RadarPresenterImpl(this);
        if (step != null && step_type != null) {
            initStepView(step, step_type);
        }
    }

    private void initViewPager() {
        /**
         * 将Viewpager所在容器的事件分发交给ViewPager
         */
        ryContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return vp.dispatchTouchEvent(event);
            }
        });
        mAdapter = new RadarViewPagerAdapter(RadarActivity.this, mDatas);
        vp.setAdapter(mAdapter);
        //设置缓存数为展示的数目
        vp.setOffscreenPageLimit(mDatas.size());
        vp.setPageMargin(2);
        //设置切换动画
        vp.setPageTransformer(true, new ZoomOutPageTransformer());
        vp.addOnPageChangeListener(this);
        setViewPagerSpeed(300);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                radar.setDatas(mDatas);
            }
        }, 1500);
        radar.setiRadarClickListener(this);
    }

    private void initData() {
        for (int i = 0; i < mImgs.length; i++) {
            Info info = new Info();
            info.setPortraitId(mImgs[i]);
            info.setAge(((int) Math.random() * 25 + 16) + "岁");
            info.setName(mNames[(int) (Math.random() * mNames.length)]);
            info.setSex(i % 3 == 0 ? false : true);
            info.setDistance(Math.round((Math.random() * 10) * 100) / 100);
            mDatas.put(i, info);

        }
    }

    private void initStepView(List<String> step, List<Integer> step_type) {
        List<StepBean> stepsBeanList = new ArrayList<>();
        StepBean stepBean;
        for (int j = 0; j < step.size(); j++) {
            stepBean = new StepBean(step.get(j), step_type.get(j));
            stepsBeanList.add(stepBean);
        }
        stepView.setStepViewTexts(stepsBeanList)
                .setTextSize(16)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, R.color.primary_dark))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.primary_dark))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, R.color.primary_dark))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.primary_dark))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.step_view_success))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.step_view_unsuccess))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.step_view_here));//设置StepsViewIndicator AttentionIcon
    }

    /**
     * 设置ViewPager切换速度
     *
     * @param duration
     */
    private void setViewPagerSpeed(int duration) {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            scroller = new FixedSpeedScroller(RadarActivity.this, new AccelerateInterpolator());
            field.set(vp, scroller);
            scroller.setmDuration(duration);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mPosition = position;
    }

    @Override
    public void onPageSelected(int position) {
        radar.setCurrentShowItem(position);
        //当手指左滑速度大于2000时viewpager右滑（注意是item+2）
        if (vp.getSpeed() < -1800) {

            vp.setCurrentItem(mPosition + 2);
            vp.setSpeed(0);
        } else if (vp.getSpeed() > 1800 && mPosition > 0) {
            //当手指右滑速度大于2000时viewpager左滑（注意item-1即可）
            vp.setCurrentItem(mPosition - 1);
            vp.setSpeed(0);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onRadarItemClick(int position) {
        vp.setCurrentItem(position);
    }

    @Override
    public void onUpdateBeacon(ArrayList<BRTBeacon> arrayList) {

    }

    @Override
    public void onNewBeacon(BRTBeacon brtBeacon) {
        boolean contains = brtBeacons.contains(brtBeacon);
        if (!contains) {
            if (brtBeacon.getMajor() == 2001) {
                step_type.clear();
                step.clear();
                minor = brtBeacon.getMinor();
                mRadarPresenter.getStepView(brtBeacon.getMajor() + "");
            } else if (brtBeacon.getMajor() == 2002) {
                brtBeacons.add(brtBeacon);
                LogUtils.d(Constants.TAG, brtBeacon.getMinor() + "");
                mRadarPresenter.getBeaconAppreciateByMinor(brtBeacon.getMinor() + "");
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
    public void onResume() {
        beaconManager.startRanging();
        super.onResume();
    }

    @Override
    public void onPause() {
        beaconManager.stopRanging();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getStepViewSuccess(StepView stepView) {
        if (stepView.getCode() == 200) {
            String step_name = stepView.getStepView().getStep_name();
            step_name = step_name.substring(1, step_name.length() - 1);
            String[] step_tmp = step_name.split(",");
            for (int i = 0; i < step_tmp.length; i++) {
                step.add(step_tmp[i]);
                step_type.add(-1);
            }

            for (int i = 0; i < minor; i++) {
                step_type.set(i, 1);
            }
            step_type.set(minor, 0);
            initStepView(step, step_type);
        }


    }

    @Override
    public void getBeaconAppreciateByMinorSuccess(BeaconAppreciate beaconAppreciate) {
        LogUtils.d(Constants.TAG, beaconAppreciate.toString());
        if (mDatas.size() == 0) {
//        initData();
            Info info = new Info();
            info.setPortraitId(mImgs[0]);
            info.setAge(((int) Math.random() * 25 + 16) + "岁");
            info.setName(mNames[(int) (Math.random() * mNames.length)]);
            info.setSex(0 % 3 == 0 ? false : true);
            info.setDistance(Math.round((Math.random() * 10) * 100) / 100);
            mDatas.put(0, info);
            initViewPager();
        }else{
            LogUtils.d(Constants.TAG,"哈哈哈");
            Info info = new Info();
            info.setPortraitId(mImgs[0]);
            info.setAge(((int) Math.random() * 25 + 16) + "岁");
            info.setName(mNames[(int) (Math.random() * mNames.length)]);
            info.setSex(0 % 3 == 0 ? false : true);
            info.setDistance(Math.round((Math.random() * 10) * 100) / 100);
            mDatas.put(1, info);
            mAdapter.notifyDataSetChanged();
//            initViewPager();

        }
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }
}
