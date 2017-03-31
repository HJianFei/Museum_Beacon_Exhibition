package com.hjianfei.museum_beacon_exhibition.view.activity.main;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.transition.Fade;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.any_event.Logout;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.service.BeaconService;
import com.hjianfei.museum_beacon_exhibition.utils.StatusBarUtils;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseActivity;
import com.hjianfei.museum_beacon_exhibition.view.fragment.guide.GuideFragment;
import com.hjianfei.museum_beacon_exhibition.view.fragment.home.HomeFragment;
import com.hjianfei.museum_beacon_exhibition.view.fragment.museum_news.MuseumNewsFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tab_home_rb)
    RadioButton tabHomeRb;
    @BindView(R.id.tab_home_guide)
    RadioButton tabHomeGuide;
    @BindView(R.id.tab_home_museum_news)
    RadioButton tabHomeMuseumNews;
    @BindView(R.id.radio_group_bottom)
    RadioGroup radioGroupBottom;
    @BindView(R.id.fragment_content)
    FrameLayout fragmentContent;
    @BindView(R.id.iv_home_guide)
    ImageView ivHomeGuide;
    private GuideFragment guideFragment;
    private HomeFragment homeFragment;
    private MuseumNewsFragment museumNewsFragment;
    private String notify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //淡入淡出
        getWindow().setEnterTransition(new Fade().setDuration(Constants.DURATION));
        getWindow().setReturnTransition(new Fade().setDuration(Constants.DURATION));
        StatusBarUtils.setStatusBarTransparent(this);

        ButterKnife.bind(this);
        //设置默认显示的fragment==home
        notify = getIntent().getStringExtra("notify");
        if (notify.equals("notify")) {
            selectStyle(R.id.tab_home_guide);
        } else {
            selectStyle(R.id.tab_home_rb);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, Constants.BAIDU_READ_PHONE_STATE);
        }
        //检测手机蓝牙是否开启
        checkBlueToothIsOpen();
        EventBus.getDefault().register(this);
    }

    /**
     * 检测手机蓝牙是否开启
     */
    private void checkBlueToothIsOpen() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(mIntent, Constants.REQUEST_ENABLE_BT);

        } else {
            Intent intent = new Intent(this, BeaconService.class);
            startService(intent);
//            time.start();
        }
    }

    private void selectStyle(int id) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (id) {
            case R.id.tab_home_rb:
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance("homeFragment", "homeFragment");
                    ft.replace(R.id.fragment_content, homeFragment);
                } else {
                    ft.replace(R.id.fragment_content, homeFragment);
                }
                break;
            case R.id.tab_home_guide:
                if (guideFragment == null) {
                    guideFragment = GuideFragment.newInstance("guideFragment", "guideFragment");
                    ft.replace(R.id.fragment_content, guideFragment);
                } else {
                    ft.replace(R.id.fragment_content, guideFragment);
                }
                break;
            case R.id.tab_home_museum_news:
                if (museumNewsFragment == null) {
                    museumNewsFragment = MuseumNewsFragment.newInstance("museumNewsFragment", "museumNewsFragment");
                    ft.replace(R.id.fragment_content, museumNewsFragment);
                } else {
                    ft.replace(R.id.fragment_content, museumNewsFragment);
                }
                break;
        }
        ft.commit();

    }

    @OnClick({R.id.tab_home_rb, R.id.tab_home_guide, R.id.tab_home_museum_news})
    public void OnClickListener(View view) {
        //切换fragment
        selectStyle(view.getId());

    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //两秒之内按返回键就会退出
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                moveTaskToBack(true);
//                finish();
//                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "使用此功能需要打开蓝牙", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, BeaconService.class);
                startService(intent);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case Constants.BAIDU_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(this, BeaconService.class);
                    startService(intent);
                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
                } else {
                    // 没有获取到权限，做特殊处理
                    ToastUtil.showToast(this, "定位权限未授予");
                }
                break;
            default:
                break;
        }
    }

    @Subscribe
    public void eventMessage(Logout logout) {
        this.finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
