package com.hjianfei.museum_beacon_exhibition.view.activity.history_check;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.Slide;
import android.view.View;

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
import com.hjianfei.museum_beacon_exhibition.bean.HistoryCheck;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.history_check.HistoryCheckPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.history_check.HistoryCheckPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.history_check_detail.HistoryCheckDetailActivity;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class HistoryCheckActivity extends BaseActivity implements HistoryCheckView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.history_check_recyclerView)
    LRecyclerView historyCheckRecyclerView;
    @BindView(R.id.history_check_search)
    FloatingActionButton historyCheckSearch;
    private CommonAdapter<HistoryCheck.ChecksBean> mAdapter;
    private List<HistoryCheck.ChecksBean> hiChecksBeenList = new ArrayList<>();
    private HistoryCheckPresenter mPresenter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private int page = 1;
    private SweetAlertDialog dialog;
    private String search_condition = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Slide().setDuration(Constants.DURATION));
        getWindow().setExitTransition(new Slide().setDuration(Constants.DURATION));
        setContentView(R.layout.activity_history_check);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        toolbar.setTitle("历史盘点");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mAdapter = new CommonAdapter<HistoryCheck.ChecksBean>(this, R.layout.history_check_item, hiChecksBeenList) {
            @Override
            public void setData(ViewHolder holder, HistoryCheck.ChecksBean checksBean) {
                holder.setImageWithUrl(R.id.history_check_item_img, checksBean.getImg_url());
                holder.setText(R.id.history_check_item_time, checksBean.getTime() + "期");
                holder.setText(R.id.history_check_item_title, checksBean.getTitle());
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(this, mAdapter);
        historyCheckRecyclerView.setAdapter(mLRecyclerViewAdapter);
        historyCheckRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyCheckRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        historyCheckRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(HistoryCheckActivity.this, HistoryCheckDetailActivity.class);
                intent.putExtra("id", hiChecksBeenList.get(i).getId() + "");
                intent.putExtra("title", hiChecksBeenList.get(i).getTitle());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(HistoryCheckActivity.this).toBundle());


            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });
        historyCheckRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                page = 1;
                search_condition = "";
                mPresenter.onRefresh(page + "", search_condition);
            }

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(historyCheckRecyclerView);
                if (state == LoadingFooter.State.Loading) {
                    return;
                }
                page++;
                mPresenter.onLoadMore(page + "", search_condition);
            }

            @Override
            public void onScrolled(int i, int i1) {

            }
        });
    }

    private void initData() {
        mPresenter = new HistoryCheckPresenterImpl(this);
        mPresenter.onInit(page + "", search_condition);
    }

    @Override
    public void init(HistoryCheck check) {
        if (check.getCode() == 200) {
            hiChecksBeenList.clear();
            hiChecksBeenList.addAll(check.getChecks());
            historyCheckRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("搜索结果为空");
            dialog.show();
        }
    }

    @Override
    public void refresh(HistoryCheck check) {
        if (check.getCode() == 200) {
            hiChecksBeenList.clear();
            hiChecksBeenList.addAll(check.getChecks());
            historyCheckRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("搜索结果为空");
            dialog.show();
        }
    }

    @Override
    public void loadMore(HistoryCheck check) {
        if (check.getCode() == 200) {
            hiChecksBeenList.addAll(check.getChecks());
            historyCheckRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            historyCheckRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
            dialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("数据加载完啦");
            dialog.show();
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

    @OnClick(R.id.history_check_search)
    public void onClick() {

        new MaterialDialog.Builder(this)
                .title("搜索")
                .titleColor(getResources().getColor(R.color.primary))
                .input("请输入关键字", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        if (!TextUtils.isEmpty(input)) {
                            search_condition = input.toString().trim();
                            page = 1;
                            mPresenter.onInit(page + "", search_condition);
                        }
                    }
                }).show();
    }
}
