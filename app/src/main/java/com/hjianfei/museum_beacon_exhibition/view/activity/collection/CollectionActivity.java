package com.hjianfei.museum_beacon_exhibition.view.activity.collection;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseActivity;
import com.hjianfei.museum_beacon_exhibition.view.fragment.collection.CulturalLoveFragment;
import com.hjianfei.museum_beacon_exhibition.view.fragment.collection.HotExhibitionFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CollectionActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collection_tabs)
    TabLayout collectionTabs;
    @BindView(R.id.collection_viewpager)
    ViewPager collectionViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_collection);
        //淡入淡出
        getWindow().setEnterTransition(new Fade().setDuration(Constants.DURATION));
        getWindow().setReturnTransition(new Fade().setDuration(Constants.DURATION));


        ButterKnife.bind(this);
        initView();
        initViewPager();
    }

    private void initViewPager() {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(CulturalLoveFragment.newInstance(), "文物鉴赏");
//        adapter.addFragment(MuseumLoveFragment.newInstance(), "博物馆");
        adapter.addFragment(HotExhibitionFragment.newInstance(), "热门展览");
        collectionViewpager.setAdapter(adapter);
        collectionTabs.setLayoutMode(TabLayout.MODE_SCROLLABLE);
        collectionTabs.addTab(collectionTabs.newTab().setText("文物鉴赏"));
//        collectionTabs.addTab(collectionTabs.newTab().setText("博物馆"));
        collectionTabs.addTab(collectionTabs.newTab().setText("热门展览"));
        collectionTabs.setupWithViewPager(collectionViewpager);
    }

    private void initView() {
        toolbar.setTitle("我的收藏");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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
