package com.hjianfei.museum_beacon_exhibition.view.activity.appreciate;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.view.fragment.appreciate.appreciation_of_treasures.Appreciation_Of_TreasuresFragment;
import com.hjianfei.museum_beacon_exhibition.view.fragment.appreciate.blue_and_white_china.Blue_And_White_ChinaFragment;
import com.hjianfei.museum_beacon_exhibition.view.fragment.appreciate.natural_specimen.Natural_SpecimenFragment;
import com.hjianfei.museum_beacon_exhibition.view.fragment.appreciate.thematic_appreciation.Thematic_appreciationFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppreciateActivity extends AppCompatActivity {

    @BindView(R.id.iv_appreciate_bg)
    ImageView ivAppreciateBg;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.sliding_tabs)
    TabLayout slidingTabs;
    @BindView(R.id.appreciate_view_pager)
    ViewPager appreciateViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appreciate);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        collapsingToolbar.setTitle("文物鉴赏");
        ivAppreciateBg.setImageResource(R.drawable.banner3);
        setupViewPager(appreciateViewPager);
        slidingTabs.addTab(slidingTabs.newTab().setText("青花瓷之约"));
        slidingTabs.addTab(slidingTabs.newTab().setText("珍品鉴赏"));
        slidingTabs.addTab(slidingTabs.newTab().setText("自然标本"));
        slidingTabs.addTab(slidingTabs.newTab().setText("专题鉴赏"));
        slidingTabs.setupWithViewPager(appreciateViewPager);
    }

    private void setupViewPager(ViewPager appreciateViewPager) {

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(Blue_And_White_ChinaFragment.newInstance("青花瓷之约", "青花瓷之约"), "青花瓷之约");
        adapter.addFragment(Appreciation_Of_TreasuresFragment.newInstance("珍品鉴赏", "珍品鉴赏"), "珍品鉴赏");
        adapter.addFragment(Natural_SpecimenFragment.newInstance("自然标本", "自然标本"), "自然标本");
        adapter.addFragment(Thematic_appreciationFragment.newInstance("专题鉴赏", "专题鉴赏"), "专题鉴赏");
        appreciateViewPager.setAdapter(adapter);

    }

    static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
