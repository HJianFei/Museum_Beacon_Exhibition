package com.hjianfei.museum_beacon_exhibition.view.fragment.museum_news.museum;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
import com.hjianfei.museum_beacon_exhibition.bean.Museum;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.museum_news.museum.MuseumPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.museum_news.museum.MuseumPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MuseumFragment extends Fragment implements MuseumView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.museum_recyclerView)
    LRecyclerView museumRecyclerView;
    private Context mContext;
    private CommonAdapter<Museum.MuseumsBean> mAdapter;
    private List<Museum.MuseumsBean> museumsBeanList = new ArrayList<>();
    private MuseumPresenter mMuseumPresenter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;


    private String mParam1;
    private String mParam2;


    public MuseumFragment() {

    }


    public static MuseumFragment newInstance(String param1, String param2) {
        MuseumFragment fragment = new MuseumFragment();
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
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_museum, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initData() {
        mMuseumPresenter = new MuseumPresenterImpl(this);
        mMuseumPresenter.initMuseumsData();
    }

    private void initView() {
        mAdapter = new CommonAdapter<Museum.MuseumsBean>(mContext, R.layout.museum_recyclerview_item, museumsBeanList) {
            @Override
            public void setData(ViewHolder holder, Museum.MuseumsBean museumsBean) {
                holder.setImageWithUrl(R.id.museum_item_img, museumsBean.getMuseum_img());
                holder.setText(R.id.museum_item_title, museumsBean.getMuseum_title());
                holder.setText(R.id.museum_item_name, museumsBean.getMuseum_name());

            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mContext, mAdapter);
        museumRecyclerView.setAdapter(mLRecyclerViewAdapter);
        museumRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        museumRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        museumRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        museumRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onBottom() {

            }

            @Override
            public void onScrolled(int i, int i1) {

            }
        });


    }

    @Override
    public void initMuseumData(List<Museum.MuseumsBean> museumsBean) {
        museumsBeanList.addAll(museumsBean);
        museumRecyclerView.refreshComplete();
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void loadMoreMuseumData(List<Museum.MuseumsBean> museumsBeanList) {

    }

    @Override
    public void refreshMuseumData(List<Museum.MuseumsBean> museumsBeanList) {

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
