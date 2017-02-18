package com.hjianfei.museum_beacon_exhibition.view.fragment.dynasty.dynasty_culture;


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
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryCulture;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.dynasty.history_culture.ChinaHistoryHistoryCulturePresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.dynasty.history_culture.ChinaHistoryHistoryCulturePresenterImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_culture_detail.ChinaHistoryCultureDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class DynastyCultureFragment extends Fragment implements DynastyCultureView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.china_history_culture_recyclerView)
    LRecyclerView chinaHistoryCultureRecyclerView;
    @BindView(R.id.china_history_culture_search)
    FloatingActionButton chinaHistoryCultureSearch;
    private CommonAdapter<ChinaHistoryCulture.ChinaHistoryCulturesBean> mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private List<ChinaHistoryCulture.ChinaHistoryCulturesBean> chinaHistoryCulturesBeanList = new ArrayList<>();
    private Context mContext;
    private int page = 1;
    private SweetAlertDialog dialog;
    private String search_condition = "";
    private ChinaHistoryHistoryCulturePresenter mPresenter;

    private String dynasty;
    private String mParam2;


    public DynastyCultureFragment() {

    }

    public static DynastyCultureFragment newInstance(String param1, String param2) {
        DynastyCultureFragment fragment = new DynastyCultureFragment();
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
            dynasty = getArguments().getString(ARG_PARAM1);
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
        View view = inflater.inflate(R.layout.fragment_dynasty_culture, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initView() {
        mAdapter = new CommonAdapter<ChinaHistoryCulture.ChinaHistoryCulturesBean>(mContext, R.layout.dynasty_history_info_item, chinaHistoryCulturesBeanList) {
            @Override
            public void setData(ViewHolder holder, ChinaHistoryCulture.ChinaHistoryCulturesBean chinaHistoryCulturesBean) {
                holder.setText(R.id.history_into_title, chinaHistoryCulturesBean.getTitle());
                holder.setText(R.id.history_info_author, chinaHistoryCulturesBean.getAuthor());
                holder.setText(R.id.history_info_type, "朝代：" + chinaHistoryCulturesBean.getType());
                holder.setText(R.id.history_info_time, chinaHistoryCulturesBean.getTime().trim());
                holder.setText(R.id.history_info_views, chinaHistoryCulturesBean.getViews());
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mContext, mAdapter);
        chinaHistoryCultureRecyclerView.setAdapter(mLRecyclerViewAdapter);
        chinaHistoryCultureRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        chinaHistoryCultureRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        chinaHistoryCultureRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(mContext, ChinaHistoryCultureDetailActivity.class);
                intent.putExtra("id", chinaHistoryCulturesBeanList.get(i).getId()+"");
                intent.putExtra("title", chinaHistoryCulturesBeanList.get(i).getTitle());
                intent.putExtra("author", chinaHistoryCulturesBeanList.get(i).getAuthor());
                intent.putExtra("time", chinaHistoryCulturesBeanList.get(i).getTime());
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });
        chinaHistoryCultureRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                page = 1;
                search_condition = "";
                mPresenter.getRefreshChinaHistoryHistoryCulture(dynasty, page + "", search_condition);
            }

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(chinaHistoryCultureRecyclerView);
                if (state == LoadingFooter.State.Loading) {
                    return;
                }
                page++;
                mPresenter.getLoadChinaHistoryHistoryCulture(dynasty, page + "", search_condition);
            }

            @Override
            public void onScrolled(int i, int i1) {

            }
        });
    }

    private void initData() {
        mPresenter = new ChinaHistoryHistoryCulturePresenterImpl(this);
        mPresenter.getInitChinaHistoryHistoryCulture(dynasty, page + "", search_condition);
    }

    @Override
    public void onInitChinaHistoryCultureFinished(ChinaHistoryCulture chinaHistoryCulture) {
        if (chinaHistoryCulture.getCode() == 200) {
            chinaHistoryCulturesBeanList.clear();
            chinaHistoryCulturesBeanList.addAll(chinaHistoryCulture.getChina_History_Cultures());
            chinaHistoryCultureRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("搜索结果为空");
            dialog.show();
        }
    }

    @Override
    public void onRefreshChinaHistoryCultureFinished(ChinaHistoryCulture chinaHistoryCulture) {
        if (chinaHistoryCulture.getCode() == 200) {
            chinaHistoryCulturesBeanList.clear();
            chinaHistoryCulturesBeanList.addAll(chinaHistoryCulture.getChina_History_Cultures());
            chinaHistoryCultureRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("搜索结果为空");
            dialog.show();
        }
    }

    @Override
    public void onLoadMoreChinaHistoryCultureFinished(ChinaHistoryCulture chinaHistoryCulture) {
        if (chinaHistoryCulture.getCode() == 200) {
            chinaHistoryCulturesBeanList.addAll(chinaHistoryCulture.getChina_History_Cultures());
            chinaHistoryCultureRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            chinaHistoryCultureRecyclerView.refreshComplete();
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

    @OnClick(R.id.china_history_culture_search)
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.china_history_culture_search:
                new MaterialDialog.Builder(getActivity())
                        .title("搜索")
                        .titleColor(getResources().getColor(R.color.primary))
                        .input("请输入关键字", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                if (!TextUtils.isEmpty(input)) {
                                    search_condition = input.toString().trim();
                                    page = 1;
                                    mPresenter.getInitChinaHistoryHistoryCulture(dynasty, page + "", search_condition);
                                }
                            }
                        }).show();
                break;
        }
    }
}
