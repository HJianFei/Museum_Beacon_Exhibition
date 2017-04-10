package com.hjianfei.museum_beacon_exhibition.view.activity.dynasty;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
<<<<<<< HEAD
import android.transition.Fade;
=======
import android.transition.Slide;
>>>>>>> tmp
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.OnItemClickListener;
import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryBigThing;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryPeople;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.canstants.Urls;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.dynasty.DynastyPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.dynasty.DynastyPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_big_thing_detail.ChinaHistoryBigThingDetailActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_people.ChinaHistoryPeopleActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_people_detail.ChinaHistoryPeopleDetailActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.history_big_thing.HistoryBigThingActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailActivity;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseActivity;
import com.hjianfei.museum_beacon_exhibition.view.fragment.dynasty.dynasty_culture.DynastyCultureFragment;
import com.hjianfei.museum_beacon_exhibition.view.fragment.dynasty.dynasty_info.DynastyInfoFragment;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class DynastyActivity extends BaseActivity implements DynastyView {


    @BindView(R.id.dynasty_bg_img)
    ImageView dynastyBgImg;
    @BindView(R.id.dynasty_big_thing_more)
    TextView dynastyBigThingMore;
    @BindView(R.id.dynasty_big_thing)
    MarqueeView dynastyBigThing;
    @BindView(R.id.dynasty_people_more)
    TextView dynastyPeopleMore;
    @BindView(R.id.dynasty_people_recyclerView)
    RecyclerView dynastyPeopleRecyclerView;
    @BindView(R.id.dynasty_tabs)
    TabLayout dynastyTabs;
    @BindView(R.id.dynasty_viewpager)
    ViewPager dynastyViewpager;
    private String dynasty_img_url;
    private String dynasty_name;
    private SweetAlertDialog dialog;
    private ChinaHistoryBigThing chinaHistoryBigThingBean = new ChinaHistoryBigThing();
    private DynastyPresenter mDynastyPresenter;
    private int page = 1;
    private CommonAdapter<ChinaHistoryPeople.ChinaHistoryPeoplesBean> mAdapter;
    private List<ChinaHistoryPeople.ChinaHistoryPeoplesBean> chinaHistoryPeoplesBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_dynasty);
        //淡入淡出
        getWindow().setEnterTransition(new Fade().setDuration(Constants.DURATION));
        getWindow().setReturnTransition(new Fade().setDuration(Constants.DURATION));

=======
        //淡入淡出
        getWindow().setEnterTransition(new Slide().setDuration(Constants.DURATION));
        getWindow().setExitTransition(new Slide().setDuration(Constants.DURATION));
        setContentView(R.layout.activity_dynasty);
>>>>>>> tmp
        dynasty_name = getIntent().getStringExtra("dynasty_name");
        dynasty_img_url = getIntent().getStringExtra("dynasty_img_url");
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
<<<<<<< HEAD
        Glide.with(this).load(Urls.API_SERVER + dynasty_img_url).placeholder(R.drawable.photo)
                .error(R.drawable.photo).into(dynastyBgImg);
=======
        Glide.with(this).load(Urls.API_SERVER + dynasty_img_url).into(dynastyBgImg);
>>>>>>> tmp
        mAdapter = new CommonAdapter<ChinaHistoryPeople.ChinaHistoryPeoplesBean>(this, R.layout.dynasty_people_item, chinaHistoryPeoplesBeanList) {
            @Override
            public void setData(ViewHolder holder, ChinaHistoryPeople.ChinaHistoryPeoplesBean chinaHistoryPeoplesBean) {

                holder.setImageWithUrl(R.id.dynasty_people_image, chinaHistoryPeoplesBean.getImg_url());
                holder.setText(R.id.dynasty_people_name, chinaHistoryPeoplesBean.getName());
                holder.setText(R.id.dynasty_people_view, chinaHistoryPeoplesBean.getViews() + "");
            }
        };
        dynastyPeopleRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        dynastyPeopleRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {

                Intent intent = new Intent(DynastyActivity.this, ChinaHistoryPeopleDetailActivity.class);
                intent.putExtra("id", chinaHistoryPeoplesBeanList.get(position).getId() + "");
                intent.putExtra("img_url", chinaHistoryPeoplesBeanList.get(position).getImg_url());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(DynastyActivity.this).toBundle());
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

        dynastyViewpager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        dynastyTabs.setupWithViewPager(dynastyViewpager);
    }


    private void initData() {
        mDynastyPresenter = new DynastyPresenterImpl(this);
        mDynastyPresenter.getDynastyBigThings(dynasty_name, page + "");
        mDynastyPresenter.getChinaHistoryPeopleByRandom(dynasty_name);
    }

    @Override
    public void initDynastyBigThing(final ChinaHistoryBigThing chinaHistoryBigThing) {

        chinaHistoryBigThingBean = chinaHistoryBigThing;
        final List<String> info = new ArrayList<>();
        for (ChinaHistoryBigThing.ChinaHistoryBigThingsBean s : chinaHistoryBigThing.getChina_History_Big_Things()) {
            info.add(s.getTitle());
        }
        dynastyBigThing.startWithList(info);
        dynastyBigThing.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Intent intent = new Intent(DynastyActivity.this, ChinaHistoryBigThingDetailActivity.class);
                intent.putExtra("big_thing_title", info.get(position));
                intent.putExtra("id", chinaHistoryBigThing.getChina_History_Big_Things().get(position).getId() + "");
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(DynastyActivity.this).toBundle());

            }
        });
    }

    @Override
    public void initChinaHistoryPeople(ChinaHistoryPeople chinaHistoryPeople) {
        if (chinaHistoryPeople != null) {
            chinaHistoryPeoplesBeanList.addAll(chinaHistoryPeople.getChina_History_Peoples());
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void showDialog() {
        dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText("加载中");
        dialog.show();
    }

    @Override
    public void hideDialog() {
        if (null != dialog) {
            dialog.dismiss();
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @OnClick({R.id.dynasty_big_thing_more, R.id.dynasty_people_more, R.id.dynasty_bg_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dynasty_big_thing_more:
                Intent intent = new Intent(DynastyActivity.this, HistoryBigThingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("HistoryBigThings", chinaHistoryBigThingBean);
                intent.putExtra("bundle", bundle);
                intent.putExtra("dynasty_name", dynasty_name);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.dynasty_people_more:
                Intent intents = new Intent(DynastyActivity.this, ChinaHistoryPeopleActivity.class);
                intents.putExtra("dynasty_name", dynasty_name);
                startActivity(intents, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.dynasty_bg_img:
                Intent intent2 = new Intent(DynastyActivity.this, PhotoDetailActivity.class);
                intent2.putExtra("img_urls", Urls.API_SERVER + dynasty_img_url);
                intent2.putExtra("photo_title", dynasty_name + "历史");
                startActivity(intent2, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
        }
    }


    class TabsAdapter extends FragmentPagerAdapter {
        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return DynastyInfoFragment.newInstance(dynasty_name, "");
                case 1:
                    return DynastyCultureFragment.newInstance(dynasty_name, "");
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "历史资料";
                case 1:
                    return "历史文化";
            }
            return "";
        }
    }
}
