package com.hjianfei.museum_beacon_exhibition.view.activity.china_history_war;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.view.fragment.history_war.HistoryWarFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChinaHistoryWarActivity extends AppCompatActivity {

    @BindView(R.id.history_war_img_bg)
    ImageView historyWarImgBg;
    @BindView(R.id.history_war_collapsing)
    CollapsingToolbarLayout historyWarCollapsing;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.history_war_title_container)
    LinearLayout historyWarTitleContainer;
    @BindView(R.id.history_war_tabs)
    TabLayout historyWarTabs;
    @BindView(R.id.history_war_viewpager)
    ViewPager historyWarViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Slide().setDuration(Constants.DURATION));
        getWindow().setExitTransition(new Slide().setDuration(Constants.DURATION));
        setContentView(R.layout.activity_china_history_war);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        historyWarViewpager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        historyWarTabs.setupWithViewPager(historyWarViewpager);
    }

    class TabsAdapter extends FragmentPagerAdapter {
        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return HistoryWarFragment.newInstance("古代战争", "古代战争");
                case 1:
                    return HistoryWarFragment.newInstance("近代战争", "近代战争");
                case 2:
                    return HistoryWarFragment.newInstance("外国战争", "外国战争");
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "古代战争";
                case 1:
                    return "近代战争";
                case 2:
                    return "外国战争";
            }
            return "";
        }
    }
}
