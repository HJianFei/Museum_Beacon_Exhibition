package com.hjianfei.museum_beacon_exhibition.view.fragment.appreciate.appreciation_of_treasures;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.HeaderSpanSizeLookup;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.appreciate.AppreciatePresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.appreciate.AppreciatePresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.view.activity.appreciate_detail.AppreciateDetailActivity;
import com.hjianfei.museum_beacon_exhibition.view.fragment.appreciate.AppreciateView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.wangyuwei.flipshare.FlipShareView;
import me.wangyuwei.flipshare.ShareItem;


public class Appreciation_Of_TreasuresFragment extends Fragment implements AppreciateView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.appreciate_of_treasures_recyclerView)
    LRecyclerView appreciateOfTreasuresRecyclerView;
    @BindView(R.id.appreciate_of_treasures_search)
    FloatingActionButton appreciateOfTreasuresSearch;
    private CommonAdapter<Appreciates.AppreciatesBean> mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private List<Appreciates.AppreciatesBean> appreciatesBeanList = new ArrayList<>();
    private AppreciatePresenter mAppreciatePresenter;
    private Context mContext;

    private String mParam1;
    private String mParam2;


    public Appreciation_Of_TreasuresFragment() {
    }

    public static Appreciation_Of_TreasuresFragment newInstance(String param1, String param2) {
        Appreciation_Of_TreasuresFragment fragment = new Appreciation_Of_TreasuresFragment();
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
        View view = inflater.inflate(R.layout.fragment_appreciation__of__treasures, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initView() {
        mAdapter = new CommonAdapter<Appreciates.AppreciatesBean>(mContext, R.layout.cultural_recyclerview_item, appreciatesBeanList) {
            @Override
            public void setData(ViewHolder holder, Appreciates.AppreciatesBean appreciatesBean) {
                holder.setImageWithUrl(R.id.appreciate_item_image, appreciatesBean.getImg_url());
                holder.setText(R.id.appreciate_item_title, appreciatesBean.getContent());

            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mContext, mAdapter);
        appreciateOfTreasuresRecyclerView.setAdapter(mLRecyclerViewAdapter);
        //setLayoutManager
        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        manager.setSpanSizeLookup(new HeaderSpanSizeLookup((LRecyclerViewAdapter) appreciateOfTreasuresRecyclerView.getAdapter(), manager.getSpanCount()));
        appreciateOfTreasuresRecyclerView.setLayoutManager(manager);
        appreciateOfTreasuresRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        appreciateOfTreasuresRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(getActivity(), AppreciateDetailActivity.class);
                intent.putExtra("cultural_detail_url", appreciatesBeanList.get(i).getDetail_url());
                intent.putExtra("cultural_name", appreciatesBeanList.get(i).getContent());
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                view.findViewById(R.id.appreciate_item_image), getString(R.string.transition));
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });
        appreciateOfTreasuresRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
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

    private void initData() {
        mAppreciatePresenter = new AppreciatePresenterImpl(this);
        mAppreciatePresenter.onInitAppreciateData("珍品鉴赏");
    }

    @Override
    public void initAppreciateData(List<Appreciates.AppreciatesBean> appreciatesBeans) {
        appreciatesBeanList.addAll(appreciatesBeans);
        appreciateOfTreasuresRecyclerView.refreshComplete();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshAppreciateData(List<Appreciates.AppreciatesBean> appreciatesBeans) {

    }

    @Override
    public void loadMoreAppreciateData(List<Appreciates.AppreciatesBean> appreciatesBeans) {

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

    @OnClick(R.id.appreciate_of_treasures_search)
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.appreciate_of_treasures_search:
                FlipShareView flipShareView = new FlipShareView.Builder(getActivity(), appreciateOfTreasuresSearch)
                        .addItem(new ShareItem("刷新", Color.WHITE, 0xff43549C))
                        .addItem(new ShareItem("搜索", Color.WHITE, 0xff4999F0))
                        .addItem(new ShareItem("国内", Color.WHITE, 0xffD9392D))
                        .addItem(new ShareItem("国外", Color.WHITE, 0xff57708A))
                        .setAnimType(FlipShareView.TYPE_HORIZONTAL)
                        .create();
                flipShareView.setOnFlipClickListener(new FlipShareView.OnFlipClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        ToastUtil.showToast(mContext, position + "");
                    }

                    @Override
                    public void dismiss() {

                    }
                });
                break;
        }

    }
}
