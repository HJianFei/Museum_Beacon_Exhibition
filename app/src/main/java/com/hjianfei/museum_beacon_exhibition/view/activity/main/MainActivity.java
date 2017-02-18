package com.hjianfei.museum_beacon_exhibition.view.activity.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.utils.StatusBarUtils;
import com.hjianfei.museum_beacon_exhibition.view.fragment.guide.GuideFragment;
import com.hjianfei.museum_beacon_exhibition.view.fragment.home.HomeFragment;
import com.hjianfei.museum_beacon_exhibition.view.fragment.museum_news.MuseumNewsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //淡入淡出
        getWindow().setEnterTransition(new Slide().setDuration(Constants.DURATION));
        getWindow().setExitTransition(new Slide().setDuration(Constants.DURATION));
        StatusBarUtils.setStatusBarTransparent(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        Explode explode = new Explode();
//        explode.setDuration(500);
//        getWindow().setExitTransition(explode);
//        getWindow().setEnterTransition(explode);
        //设置默认显示的fragment==home
        selectStyle(R.id.tab_home_rb);
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
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
