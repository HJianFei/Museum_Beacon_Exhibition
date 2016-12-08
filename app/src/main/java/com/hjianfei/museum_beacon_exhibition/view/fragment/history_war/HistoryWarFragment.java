package com.hjianfei.museum_beacon_exhibition.view.fragment.history_war;


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
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryOldenWar;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_war.ChinaHistoryWarPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_war.ChinaHistoryWarPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_war_detail.HistoryWarDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class HistoryWarFragment extends Fragment implements HistoryWarView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.history_war_recyclerView)
    LRecyclerView historyWarRecyclerView;
    @BindView(R.id.history_war_search)
    FloatingActionButton historyWarSearch;

    private String type;
    private String mParam2;
    private Context mContext;
    private CommonAdapter<ChinaHistoryOldenWar.ChinaHistoryOldenWarsBean> mAdapter;
    private List<ChinaHistoryOldenWar.ChinaHistoryOldenWarsBean> chinaHistoryOldenWarsBeanList = new ArrayList<>();
    private ChinaHistoryWarPresenter mPresenter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private SweetAlertDialog dialog;
    private String search_condition = "";
    private int page = 1;


    public HistoryWarFragment() {
    }


    public static HistoryWarFragment newInstance(String param1, String param2) {
        HistoryWarFragment fragment = new HistoryWarFragment();
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
            type = getArguments().getString(ARG_PARAM1);
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
        View view = inflater.inflate(R.layout.fragment_history_war, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initView() {

        mAdapter = new CommonAdapter<ChinaHistoryOldenWar.ChinaHistoryOldenWarsBean>(mContext, R.layout.history_war_item, chinaHistoryOldenWarsBeanList) {
            @Override
            public void setData(ViewHolder holder, ChinaHistoryOldenWar.ChinaHistoryOldenWarsBean chinaHistoryOldenWarsBean) {
                holder.setImageWithUrl(R.id.history_war_image_item, chinaHistoryOldenWarsBean.getImg_url());
                holder.setText(R.id.history_war_title_item, chinaHistoryOldenWarsBean.getName());
                holder.setText(R.id.history_war_desc_item, chinaHistoryOldenWarsBean.getDescription());
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mContext, mAdapter);
        historyWarRecyclerView.setAdapter(mLRecyclerViewAdapter);
        historyWarRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        historyWarRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        historyWarRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

                Intent intent = new Intent(mContext, HistoryWarDetailActivity.class);
                intent.putExtra("detail_url", chinaHistoryOldenWarsBeanList.get(i).getDetail_url());
                intent.putExtra("img_url", chinaHistoryOldenWarsBeanList.get(i).getImg_url());
                intent.putExtra("title", chinaHistoryOldenWarsBeanList.get(i).getName());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });
        historyWarRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                page = 1;
                search_condition = "";
                mPresenter.onRefreshCh(type, page + "", search_condition);
            }

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(historyWarRecyclerView);
                if (state == LoadingFooter.State.Loading) {
                    return;
                }
                page++;
                mPresenter.onLoadMore(type, page + "", search_condition);
            }

            @Override
            public void onScrolled(int i, int i1) {

            }
        });

    }

    private void initData() {
        mPresenter = new ChinaHistoryWarPresenterImpl(this);
        mPresenter.onInit(type, page + "", search_condition);
    }

    @Override
    public void onInitFinished(ChinaHistoryOldenWar chinaHistoryOldenWar) {
        if (chinaHistoryOldenWar.getCode() == 200) {
            chinaHistoryOldenWarsBeanList.clear();
            chinaHistoryOldenWarsBeanList.addAll(chinaHistoryOldenWar.getChina_History_Olden_Wars());
            historyWarRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("搜索结果为空");
            dialog.show();
        }
    }

    @Override
    public void onRefreshFinished(ChinaHistoryOldenWar chinaHistoryOldenWar) {
        if (chinaHistoryOldenWar.getCode() == 200) {
            chinaHistoryOldenWarsBeanList.clear();
            chinaHistoryOldenWarsBeanList.addAll(chinaHistoryOldenWar.getChina_History_Olden_Wars());
            historyWarRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("搜索结果为空");
            dialog.show();
        }
    }

    @Override
    public void onLoadMoreFinished(ChinaHistoryOldenWar chinaHistoryOldenWar) {
        if (chinaHistoryOldenWar.getCode() == 200) {
            chinaHistoryOldenWarsBeanList.addAll(chinaHistoryOldenWar.getChina_History_Olden_Wars());
            historyWarRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            historyWarRecyclerView.refreshComplete();
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

    @OnClick(R.id.history_war_search)
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.history_war_search:
                new MaterialDialog.Builder(getActivity())
                        .title("搜索")
                        .titleColor(getResources().getColor(R.color.primary))
                        .input("请输入关键字", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                if (!TextUtils.isEmpty(input)) {
                                    search_condition = input.toString().trim();
                                    page = 1;
                                    mPresenter.onInit(type, page + "", search_condition);
                                }
                            }
                        }).show();
                break;

        }
    }
}
