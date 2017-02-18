package com.hjianfei.museum_beacon_exhibition.view.activity.museum;

import android.app.ActivityOptions;
import android.content.Intent;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailActivity;
import com.hjianfei.museum_beacon_exhibition.view.fragment.appreciate.AppreciateFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MuseumActivity extends AppCompatActivity {

    @BindView(R.id.museum_img_bg)
    ImageView museumImgBg;
    @BindView(R.id.museum_collapsing)
    CollapsingToolbarLayout museumCollapsing;
    @BindView(R.id.museum_name)
    TextView museumName;
    @BindView(R.id.museum_tabs)
    TabLayout museumTabs;
    @BindView(R.id.museum_viewpager)
    ViewPager museumViewpager;
    private String museum_name;
    private String[] appreciate_type;
    private String[] img_urls;
    private String imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Slide().setDuration(Constants.DURATION));
        getWindow().setExitTransition(new Slide().setDuration(Constants.DURATION));
        museum_name = getIntent().getStringExtra("museum_name");
        String img = getIntent().getStringExtra("img_url");
        String appreciate = getIntent().getStringExtra("appreciate_type");
        String substring = appreciate.substring(1, appreciate.length() - 1);
        imgs = img.substring(1, img.length() - 1);
        appreciate_type = substring.split(",");
        img_urls = imgs.split(",");
        setContentView(R.layout.activity_museum);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        if (appreciate_type.length<4){
            museumTabs.setTabMode(TabLayout.MODE_FIXED);
            museumTabs.setTabGravity(TabLayout.GRAVITY_FILL);
        }
        museumName.setText(museum_name);
        Glide.with(this).load(img_urls[0]).into(museumImgBg);
        museumViewpager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        museumTabs.setupWithViewPager(museumViewpager);
    }

    @OnClick(R.id.museum_img_bg)
    public void onClick() {
        Intent intent2 = new Intent(MuseumActivity.this, PhotoDetailActivity.class);
        intent2.putExtra("img_urls", imgs);
        intent2.putExtra("photo_title",museum_name);
        startActivity(intent2, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }

    class TabsAdapter extends FragmentPagerAdapter {
        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return appreciate_type.length;
        }

        @Override
        public Fragment getItem(int i) {
            return AppreciateFragment.newInstance(museum_name, appreciate_type[i]);

        }

        @Override
        public CharSequence getPageTitle(int position) {

            return appreciate_type[position];
        }
    }
}
