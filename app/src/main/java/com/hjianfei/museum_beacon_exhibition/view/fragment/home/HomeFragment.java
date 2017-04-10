package com.hjianfei.museum_beacon_exhibition.view.fragment.home;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.HomeExhibitionViewPagerAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.HomeViewPagerAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
<<<<<<< HEAD
import com.hjianfei.museum_beacon_exhibition.any_event.LocationResult;
=======
>>>>>>> tmp
import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.bean.Exhibitions;
import com.hjianfei.museum_beacon_exhibition.bean.ViewPager;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.home.HomePresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.home.HomePresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.StatusBarUtils;
import com.hjianfei.museum_beacon_exhibition.utils.widget.MetroImageView;
import com.hjianfei.museum_beacon_exhibition.view.activity.about_me.AboutMeActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.appreciate_detail.AppreciateDetailActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.location.LocationActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.museum.MuseumActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.personal.PersonalActivity;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

<<<<<<< HEAD
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

=======
>>>>>>> tmp
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeFragment extends Fragment implements HomeView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.home_view_pager)
    RollPagerView homeViewPager;
    @BindView(R.id.appreciate_recyclerView)
    RecyclerView appreciateRecyclerView;
    @BindView(R.id.status_bar)
    ImageView status_bar;
    @BindView(R.id.home_person)
    ImageView homePerson;
    @BindView(R.id.home_local_city)
    TextView home_local_city;
    @BindView(R.id.home_location)
    LinearLayout homeLocation;
    @BindView(R.id.home_appreciate_more)
    LinearLayout homeAppreciateMore;
    @BindView(R.id.hot_exhibition_more)
    MetroImageView hot_exhibition_more;
    @BindView(R.id.home_hot_exhibition)
    android.support.v4.view.ViewPager hot_exhibition_view_pager;
    @BindView(R.id.home_no_exhibition)
    TextView homeNoExhibition;

    private CommonAdapter<Appreciates.AppreciatesBean> mAdapter;
    private HomeExhibitionViewPagerAdapter homeExhibitionViewPagerAdapter;

    private HomePresenter mHomePresenter;
    private Context mContext;

    private String mParam1;
    private String mParam2;


    public HomeFragment() {

    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
<<<<<<< HEAD
        EventBus.getDefault().register(this);
=======
>>>>>>> tmp
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void initView() {
        ViewGroup.LayoutParams params = status_bar.getLayoutParams();
        params.height = StatusBarUtils.getStatusBarHeight(getActivity());
        status_bar.setLayoutParams(params);
        mHomePresenter = new HomePresenterImpl(this);
        mHomePresenter.loadHomeViewPager();
        mHomePresenter.loadAppreciateRecyclerView();
        mHomePresenter.loadExhibitionViewPager(home_local_city.getText().toString());

    }

    @OnClick({R.id.home_location, R.id.home_person, R.id.home_appreciate_more, R.id.hot_exhibition_more})
    public void onClickListener(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.home_location:
                intent = new Intent(mContext, LocationActivity.class);
                startActivityForResult(intent, Constants.HOME_LOCATION);
//                startActivityForResult(intent, Constants.HOME_LOCATION, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
            case R.id.home_person:
                intent = new Intent(mContext, PersonalActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
            case R.id.home_appreciate_more:
                intent = new Intent(mContext, MuseumActivity.class);
                intent.putExtra("museum_name", "广东省博物馆");
                intent.putExtra("img_url", "[http://www.chezhan168.com/userfiles/image/20151221/21133056edf7f86b0f0984.jpg]");
                intent.putExtra("appreciate_type", "[青花瓷之约,珍品鉴赏,自然标本,专题鉴赏]");
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
            case R.id.hot_exhibition_more:
                break;
        }
    }

<<<<<<< HEAD
    //    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode==RESULT_CODE) {
//            if (requestCode == Constants.HOME_LOCATION) {
//                String result = data.getStringExtra("city");
//                LogUtils.d("onResponse",result);
//                home_local_city.setText(result);
//                mHomePresenter.loadExhibitionViewPager(result);
//            }
//        }
//    }
=======
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Constants.HOME_RESULT_CODE) {
            if (requestCode == Constants.HOME_LOCATION) {
                String result = data.getStringExtra("city");
                home_local_city.setText(result);
                mHomePresenter.loadExhibitionViewPager(result);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
>>>>>>> tmp

    @Override
    public void initHomeViewPager(ViewPager viewPager) {
//        初始化ViewPager
        homeViewPager.setPlayDelay(3000);
        homeViewPager.setAdapter(new HomeViewPagerAdapter(viewPager.getViewPagers()));
        homeViewPager.setHintView(new ColorPointHintView(mContext, Color.YELLOW, Color.WHITE));

        homeViewPager.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent;
                if (position == 0) {
                    intent = new Intent(mContext, AboutMeActivity.class);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                } else if (position == 1) {
//                    intent = new Intent(mContext, MuseumDetailActivity.class);
//                    intent.putExtra("id", "");
//                    intent.putExtra("museum_name", "广东省博物馆");
//                    intent.putExtra("post_type", "博物馆");
//                    intent.putExtra("appreciate_type","[青花瓷之约,珍品鉴赏,自然标本,专题鉴赏]");
//                    startActivity(intent);
                }


            }
        });
    }

    @Override
    public void initAppreciateRecyclerView(final List<Appreciates.AppreciatesBean> appreciatesBeans) {

        mAdapter = new CommonAdapter<Appreciates.AppreciatesBean>(mContext, R.layout.appreciate_recycler_view_item, appreciatesBeans) {
            @Override
            public void setData(ViewHolder holder, Appreciates.AppreciatesBean appreciatesBean) {
                holder.setText(R.id.appreciate_item_title, appreciatesBean.getContent());
                holder.setImageWithUrl(R.id.appreciate_item_image, appreciatesBean.getImg_url());
            }
        };
        appreciateRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        appreciateRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new com.hjianfei.museum_beacon_exhibition.adapter.common.OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Intent intent = new Intent(getActivity(), AppreciateDetailActivity.class);
                intent.putExtra("id", appreciatesBeans.get(position).getId() + "");
                intent.putExtra("cultural_name", appreciatesBeans.get(position).getContent());
                intent.putExtra("post_type", "文物鉴赏");
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());

            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    @Override
    public void initHotExhibition(Exhibitions exhibitions) {
        if (exhibitions.getCode() == 200) {
            homeNoExhibition.setVisibility(View.GONE);
            hot_exhibition_view_pager.setVisibility(View.VISIBLE);
            homeExhibitionViewPagerAdapter = new HomeExhibitionViewPagerAdapter(exhibitions.getExhibitions(), getActivity());
            hot_exhibition_view_pager.setPageMargin(40);
            hot_exhibition_view_pager.setOffscreenPageLimit(5);
            hot_exhibition_view_pager.setAdapter(homeExhibitionViewPagerAdapter);
            if (exhibitions.getExhibitions().size() > 2) {
                hot_exhibition_view_pager.setCurrentItem(1, true);
            }
            hot_exhibition_view_pager.setPageTransformer(true, new ScaleInTransformer(new AlphaPageTransformer()));
        } else {
            hot_exhibition_view_pager.setVisibility(View.GONE);
            homeNoExhibition.setVisibility(View.VISIBLE);

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
<<<<<<< HEAD

    @Subscribe
    public void eventMessage(LocationResult locationResult) {
        home_local_city.setText(locationResult.getCity_name());
        mHomePresenter.loadExhibitionViewPager(locationResult.getCity_name());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
=======
>>>>>>> tmp
}
