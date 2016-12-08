package com.hjianfei.museum_beacon_exhibition.view.fragment.dynasty.dynasty_info;


import android.content.Context;
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
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHistory;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.dynasty.history_info.ChinaHistoryHistoryInFoPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.dynasty.history_info.ChinaHistoryHistoryInFoPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class DynastyInfoFragment extends Fragment implements DynastyInfoView {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.china_history_info_recyclerView)
    LRecyclerView chinaHistoryInfoRecyclerView;
    @BindView(R.id.china_history_info_search)
    FloatingActionButton chinaHistoryInfoSearch;
    private CommonAdapter<ChinaHistoryHistory.ChinaHistoryHistoriesBean> mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private List<ChinaHistoryHistory.ChinaHistoryHistoriesBean> chinaHistoryHistoriesBeanList = new ArrayList<>();
    private Context mContext;
    private int page = 1;
    private SweetAlertDialog dialog;
    private String search_condition = "";
    private ChinaHistoryHistoryInFoPresenter mPresenter;

    private String dynasty;
    private String mParam2;


    public DynastyInfoFragment() {

    }

    public static DynastyInfoFragment newInstance(String param1, String param2) {
        DynastyInfoFragment fragment = new DynastyInfoFragment();
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

        View view = inflater.inflate(R.layout.fragment_dynasty_info, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initView() {

        mAdapter = new CommonAdapter<ChinaHistoryHistory.ChinaHistoryHistoriesBean>(mContext, R.layout.dynasty_history_info_item, chinaHistoryHistoriesBeanList) {
            @Override
            public void setData(ViewHolder holder, ChinaHistoryHistory.ChinaHistoryHistoriesBean chinaHistoryHistoriesBean) {

                holder.setText(R.id.history_into_title,chinaHistoryHistoriesBean.getTitle());
                holder.setText(R.id.history_info_author,chinaHistoryHistoriesBean.getAuthor());
                holder.setText(R.id.history_info_type,"朝代："+chinaHistoryHistoriesBean.getType());
                holder.setText(R.id.history_info_time,chinaHistoryHistoriesBean.getTime().trim());
                holder.setText(R.id.history_info_views,chinaHistoryHistoriesBean.getViews());
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mContext, mAdapter);
        chinaHistoryInfoRecyclerView.setAdapter(mLRecyclerViewAdapter);
        chinaHistoryInfoRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        chinaHistoryInfoRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        chinaHistoryInfoRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });
        chinaHistoryInfoRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                page = 1;
                search_condition = "";
                mPresenter.getRefreshChinaHistoryHistory(dynasty, page + "", search_condition);
            }

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(chinaHistoryInfoRecyclerView);
                if (state == LoadingFooter.State.Loading) {
                    return;
                }
                page++;
                mPresenter.getLoadChinaHistoryHistory(dynasty, page + "", search_condition);
            }

            @Override
            public void onScrolled(int i, int i1) {

            }
        });
    }

    private void initData() {

        mPresenter = new ChinaHistoryHistoryInFoPresenterImpl(this);
        mPresenter.getInitChinaHistoryHistory(dynasty, page + "", search_condition);
    }

    @Override
    public void onInitChinaHistoryHistoryFinished(ChinaHistoryHistory chinaHistoryHistory) {
        if (chinaHistoryHistory.getCode()==200) {
            chinaHistoryHistoriesBeanList.clear();
            chinaHistoryHistoriesBeanList.addAll(chinaHistoryHistory.getChina_History_Histories());
            chinaHistoryInfoRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("搜索结果为空");
            dialog.show();
        }
    }

    @Override
    public void onRefreshChinaHistoryHistoryFinished(ChinaHistoryHistory chinaHistoryHistory) {
        if (chinaHistoryHistory.getCode()==200) {
            chinaHistoryHistoriesBeanList.clear();
            chinaHistoryHistoriesBeanList.addAll(chinaHistoryHistory.getChina_History_Histories());
            chinaHistoryInfoRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("搜索结果为空");
            dialog.show();
        }
    }

    @Override
    public void onLoadMoreChinaHistoryHistoryFinished(ChinaHistoryHistory chinaHistoryHistory) {
        if (chinaHistoryHistory.getCode()==200) {
            chinaHistoryHistoriesBeanList.addAll(chinaHistoryHistory.getChina_History_Histories());
            chinaHistoryInfoRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            chinaHistoryInfoRecyclerView.refreshComplete();
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
    @OnClick(R.id.china_history_info_search)
    public void onClickListener(View v){
        switch (v.getId()){
            case R.id.china_history_info_search:
                new MaterialDialog.Builder(getActivity())
                        .title("搜索")
                        .titleColor(getResources().getColor(R.color.primary))
                        .input("请输入关键字", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                if (!TextUtils.isEmpty(input)) {
                                    search_condition = input.toString().trim();
                                    page = 1;
                                    mPresenter.getInitChinaHistoryHistory(dynasty, page + "", search_condition);
                                }
                            }
                        }).show();
                break;
        }
    }
}
