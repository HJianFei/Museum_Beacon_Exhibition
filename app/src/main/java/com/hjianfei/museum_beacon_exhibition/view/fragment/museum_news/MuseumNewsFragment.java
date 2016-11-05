package com.hjianfei.museum_beacon_exhibition.view.fragment.museum_news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.view.fragment.museum_news.cultural.CulturalFragment;
import com.hjianfei.museum_beacon_exhibition.view.fragment.museum_news.museum.MuseumFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MuseumNewsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.sliding_tabs)
    TabLayout slidingTabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;


    private String mParam1;
    private String mParam2;


    public MuseumNewsFragment() {
    }

    public static MuseumNewsFragment newInstance(String param1, String param2) {
        MuseumNewsFragment fragment = new MuseumNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_museum_news, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(MuseumFragment.newInstance("博物馆", "博物馆"), "博物馆");
        adapter.addFragment(CulturalFragment.newInstance("文物", "文物"), "文物");
        viewpager.setAdapter(adapter);
        slidingTabs.setLayoutMode(TabLayout.MODE_SCROLLABLE);
        slidingTabs.addTab(slidingTabs.newTab().setText("博物馆"));
        slidingTabs.addTab(slidingTabs.newTab().setText("文物"));
        slidingTabs.setupWithViewPager(viewpager);

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
