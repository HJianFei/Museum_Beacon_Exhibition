package com.hjianfei.museum_beacon_exhibition.view.activity.foreign_country_history;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
<<<<<<< HEAD
import android.transition.Fade;
=======
import android.transition.Slide;
>>>>>>> tmp
import android.widget.ImageView;
import android.widget.TextView;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseActivity;
import com.hjianfei.museum_beacon_exhibition.view.fragment.foreign_country.ForeignCountryFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForeignCountryActivity extends BaseActivity {

    @BindView(R.id.foreign_country_img_bg)
    ImageView foreignCountryImgBg;
    @BindView(R.id.foreign_country_tabs)
    TabLayout foreignCountryTabs;
    @BindView(R.id.foreign_country_viewpager)
    ViewPager foreignCountryViewpager;
    @BindView(R.id.foreign_country_country)
    TextView foreignCountryCountry;
    private String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_foreign_country);
        //淡入淡出
        getWindow().setEnterTransition(new Fade().setDuration(Constants.DURATION));
        getWindow().setReturnTransition(new Fade().setDuration(Constants.DURATION));
        country = getIntent().getStringExtra("country");

=======

        //滑动进入
        getWindow().setEnterTransition(new Slide().setDuration(Constants.DURATION));
        getWindow().setExitTransition(new Slide().setDuration(Constants.DURATION));
        country = getIntent().getStringExtra("country");
        setContentView(R.layout.activity_foreign_country);
>>>>>>> tmp
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        foreignCountryCountry.setText(country);
        foreignCountryViewpager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        foreignCountryTabs.setupWithViewPager(foreignCountryViewpager);
    }

    @OnClick(R.id.foreign_country_img_bg)
    public void onClick() {
    }

    class TabsAdapter extends FragmentPagerAdapter {
        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return ForeignCountryFragment.newInstance(country, "历史资料");
                case 1:
                    return ForeignCountryFragment.newInstance(country, "人物故事");
                case 2:
                    return ForeignCountryFragment.newInstance(country, "历史文化");
                case 3:
                    return ForeignCountryFragment.newInstance(country, "历史科技");
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "历史资料";
                case 1:
                    return "人物故事";
                case 2:
                    return "国家文化";
                case 3:
                    return "国家科技";
            }
            return "";
        }
    }
}
