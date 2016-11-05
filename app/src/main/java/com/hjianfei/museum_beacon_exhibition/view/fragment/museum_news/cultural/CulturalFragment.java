package com.hjianfei.museum_beacon_exhibition.view.fragment.museum_news.cultural;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.HeaderSpanSizeLookup;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewUtils;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.museum_news.cultrual.CulturalPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.museum_news.cultrual.CulturalPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CulturalFragment extends Fragment implements CulturalView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.cultural_recyclerView)
    LRecyclerView culturalRecyclerView;
    private CommonAdapter<Appreciates.AppreciatesBean> mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private List<Appreciates.AppreciatesBean> appreciatesBeanList = new ArrayList<>();
    private CulturalPresenter mCulturalPresenter;

    private String mParam1;
    private String mParam2;
    private Context mContext;


    public CulturalFragment() {

    }


    public static CulturalFragment newInstance(String param1, String param2) {
        CulturalFragment fragment = new CulturalFragment();
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
        this.mContext = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cultural, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initData() {
        mCulturalPresenter = new CulturalPresenterImpl(this);
        mCulturalPresenter.initAppreciatesData();

    }

    private void initView() {
        mAdapter = new CommonAdapter<Appreciates.AppreciatesBean>(mContext, R.layout.cultural_recyclerview_item, appreciatesBeanList) {
            @Override
            public void setData(ViewHolder holder, Appreciates.AppreciatesBean appreciatesBean) {
                System.out.println(appreciatesBean.getContent());
                holder.setImageWithUrl(R.id.appreciate_item_image, appreciatesBean.getImg_url());
                holder.setText(R.id.appreciate_item_title, appreciatesBean.getContent());

            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mContext, mAdapter);
        View head_view = View.inflate(mContext, R.layout.cultural_head_view, null);
        culturalRecyclerView.setAdapter(mLRecyclerViewAdapter);
        //setLayoutManager
        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        manager.setSpanSizeLookup(new HeaderSpanSizeLookup((LRecyclerViewAdapter) culturalRecyclerView.getAdapter(), manager.getSpanCount()));
        culturalRecyclerView.setLayoutManager(manager);
        culturalRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        culturalRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        RecyclerViewUtils.setHeaderView(culturalRecyclerView, head_view);
        culturalRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
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
    public void initCulturalData(List<Appreciates.AppreciatesBean> appreciatesBeans) {
        appreciatesBeanList.addAll(appreciatesBeans);
        culturalRecyclerView.refreshComplete();
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void loadMoreCulturalData(List<Appreciates.AppreciatesBean> appreciatesBeans) {

    }

    @Override
    public void refreshCulturalData(List<Appreciates.AppreciatesBean> appreciatesBeans) {

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
