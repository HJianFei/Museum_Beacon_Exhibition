package com.hjianfei.museum_beacon_exhibition.view.fragment.home;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.HomeExhibitionViewPagerAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.HomeViewPagerAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.bean.Exhibitions;
import com.hjianfei.museum_beacon_exhibition.bean.ViewPager;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.home.HomePresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.home.HomePresenterImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.appreciate_detail.AppreciateDetailActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.location.LocationActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.search.SearchActivity;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

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
    @BindView(R.id.home_search)
    LinearLayout homeSearch;
    @BindView(R.id.home_person)
    ImageView homePerson;
    @BindView(R.id.home_location)
    LinearLayout homeLocation;
    @BindView(R.id.home_appreciate_more)
    LinearLayout homeAppreciateMore;
    @BindView(R.id.home_hot_exhibition)
    android.support.v4.view.ViewPager hot_exhibition_view_pager;

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
        mHomePresenter = new HomePresenterImpl(this);
        mHomePresenter.loadHomeViewPager();
        mHomePresenter.loadAppreciateRecyclerView();
        mHomePresenter.loadExhibitionViewPager();

    }

    @OnClick({R.id.home_location, R.id.home_search, R.id.home_person})
    public void onClickListener(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.home_location:
                intent = new Intent(mContext, LocationActivity.class);
                startActivityForResult(intent, Constants.HOME_LOCATION);
                break;
            case R.id.home_search:
                intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.home_person:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initHomeViewPager(ViewPager viewPager) {
//        初始化ViewPager
        homeViewPager.setPlayDelay(3000);
        homeViewPager.setAdapter(new HomeViewPagerAdapter(viewPager.getViewPagers()));
        homeViewPager.setHintView(new ColorPointHintView(mContext, Color.YELLOW, Color.WHITE));

        homeViewPager.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                mIntent = new Intent(mContext, ExhibitionDetailActivity.class);
//                mIntent.putExtra("detail_url", viewPager.getViewPagers().get(position).getDetail_url());
//                mIntent.putExtra("title", viewPager.getViewPagers().get(position).getContent());
//                ActivityOptionsCompat options =
//                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
//                                view.findViewById(R.id.home_view_pager), getString(R.string.transition));
//                ActivityCompat.startActivity(getActivity(), mIntent, options.toBundle());

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
                intent.putExtra("cultural_detail_url", appreciatesBeans.get(position).getDetail_url());
                intent.putExtra("cultural_name", appreciatesBeans.get(position).getContent());
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                view.findViewById(R.id.appreciate_item_image), getString(R.string.transition));
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    @Override
    public void initHotExhibition(Exhibitions exhibitions) {
        homeExhibitionViewPagerAdapter = new HomeExhibitionViewPagerAdapter(exhibitions.getExhibitions(), mContext);
        hot_exhibition_view_pager.setPageMargin(40);
        hot_exhibition_view_pager.setOffscreenPageLimit(5);
        hot_exhibition_view_pager.setAdapter(homeExhibitionViewPagerAdapter);
        hot_exhibition_view_pager.setCurrentItem(1, true);
        hot_exhibition_view_pager.setPageTransformer(true, new ScaleInTransformer(new AlphaPageTransformer(new RotateDownPageTransformer())));
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
}
