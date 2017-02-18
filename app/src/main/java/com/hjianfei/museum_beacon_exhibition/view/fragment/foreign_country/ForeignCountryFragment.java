package com.hjianfei.museum_beacon_exhibition.view.fragment.foreign_country;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
import com.hjianfei.museum_beacon_exhibition.bean.ForeignHistory;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.foreign_country.ForeignCountryPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.foreign_country.ForeignCountryPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.foreign_history_detail.ForeignCountryDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class ForeignCountryFragment extends Fragment implements ForeignCountryView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.foreign_country_recyclerView)
    LRecyclerView foreignCountryRecyclerView;
    @BindView(R.id.foreign_country_search)
    FloatingActionButton foreignCountrySearch;
    private Context mContext;
    private CommonAdapter<ForeignHistory.ForeignHistoriesBean> mAdapter;
    private List<ForeignHistory.ForeignHistoriesBean> foreignHistoriesBeanList = new ArrayList<>();
    private ForeignCountryPresenter mPresenter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private SweetAlertDialog dialog;
    private String search_condition = "";
    private int page = 1;
    private String country;
    private String type;


    public ForeignCountryFragment() {

    }

    public static ForeignCountryFragment newInstance(String param1, String param2) {
        ForeignCountryFragment fragment = new ForeignCountryFragment();
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
            country = getArguments().getString(ARG_PARAM1);
            type = getArguments().getString(ARG_PARAM2);
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

        View view = inflater.inflate(R.layout.fragment_foreign_country, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initView() {
        mAdapter = new CommonAdapter<ForeignHistory.ForeignHistoriesBean>(mContext, R.layout.history_war_item, foreignHistoriesBeanList) {
            @Override
            public void setData(ViewHolder holder, ForeignHistory.ForeignHistoriesBean foreignHistoriesBean) {
                holder.setImageWithUrl(R.id.history_war_image_item, foreignHistoriesBean.getImg_url());
                holder.setText(R.id.history_war_title_item, foreignHistoriesBean.getTitle());
                holder.setText(R.id.history_war_desc_item, foreignHistoriesBean.getDescription());
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mContext, mAdapter);
        foreignCountryRecyclerView.setAdapter(mLRecyclerViewAdapter);
        foreignCountryRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        foreignCountryRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        foreignCountryRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

                Intent intent = new Intent(mContext, ForeignCountryDetailActivity.class);
                intent.putExtra("id", foreignHistoriesBeanList.get(i).getId() + "");
                intent.putExtra("title", foreignHistoriesBeanList.get(i).getTitle());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());

            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });
        foreignCountryRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                page = 1;
                search_condition = "";
                mPresenter.onRefreshCh(country, type, page + "", search_condition);
            }

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(foreignCountryRecyclerView);
                if (state == LoadingFooter.State.Loading) {
                    return;
                }
                page++;
                mPresenter.onLoadMore(country, type, page + "", search_condition);
            }

            @Override
            public void onScrolled(int i, int i1) {

            }
        });
    }

    private void initData() {
        mPresenter = new ForeignCountryPresenterImpl(this);
        mPresenter.onInit(country, type, page + "", search_condition);
    }

    @Override
    public void onInitFinished(ForeignHistory foreignHistory) {
        if (foreignHistory.getCode() == 200) {
            foreignHistoriesBeanList.clear();
            foreignHistoriesBeanList.addAll(foreignHistory.getForeign_Histories());
            foreignCountryRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("搜索结果为空");
            dialog.show();
        }
    }

    @Override
    public void onRefreshFinished(ForeignHistory foreignHistory) {
        if (foreignHistory.getCode() == 200) {
            foreignHistoriesBeanList.clear();
            foreignHistoriesBeanList.addAll(foreignHistory.getForeign_Histories());
            foreignCountryRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("搜索结果为空");
            dialog.show();
        }
    }

    @Override
    public void onLoadMoreFinished(ForeignHistory foreignHistory) {
        if (foreignHistory.getCode() == 200) {
            foreignHistoriesBeanList.addAll(foreignHistory.getForeign_Histories());
            foreignCountryRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            foreignCountryRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("数据加载完啦");
            dialog.show();
        }
    }

    @Override
    public void showDialog() {
        dialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
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

    @OnClick(R.id.foreign_country_search)
    public void onClick() {
        new MaterialDialog.Builder(getActivity())
                .title("搜索")
                .titleColor(getResources().getColor(R.color.primary))
                .input("请输入关键字", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        if (!TextUtils.isEmpty(input)) {
                            search_condition = input.toString().trim();
                            page = 1;
                            mPresenter.onInit(country, type, page + "", search_condition);
                        }
                    }
                }).show();
    }
}
