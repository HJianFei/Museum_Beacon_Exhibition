package com.hjianfei.museum_beacon_exhibition.view.activity.china_history_hey_day;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHeyDay;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_hey_day.ChinaHistoryHeyDayPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_hey_day.ChinaHistoryHeyDayPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_hey_day_info.HistoryHeyDayInFoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ChinaHistoryHeyDayActivity extends AppCompatActivity implements ChinaHistoryHeyDayView {

    @BindView(R.id.history_hey_day_recyclerView)
    LRecyclerView historyHeyDayRecyclerView;
    @BindView(R.id.history_hey_day_search)
    FloatingActionButton historyHeyDaySearch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private CommonAdapter<ChinaHistoryHeyDay.ChinaHistoryHeyDaysBean> mAdapter;
    private List<ChinaHistoryHeyDay.ChinaHistoryHeyDaysBean> chinaHistoryHeyDaysBeanList = new ArrayList<>();
    private ChinaHistoryHeyDayPresenter mPresenter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private int page = 1;
    private SweetAlertDialog dialog;
    private String search_condition = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_china_history_hey_day);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initView() {
        toolbar.setTitle("历史盛世");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mAdapter = new CommonAdapter<ChinaHistoryHeyDay.ChinaHistoryHeyDaysBean>(this, R.layout.china_history_hey_day_item, chinaHistoryHeyDaysBeanList) {
            @Override
            public void setData(ViewHolder holder, ChinaHistoryHeyDay.ChinaHistoryHeyDaysBean chinaHistoryHeyDaysBean) {
                holder.setImageWithUrl(R.id.history_hey_day_item_img, chinaHistoryHeyDaysBean.getImg_url());
                holder.setText(R.id.history_hey_day_item_title, chinaHistoryHeyDaysBean.getTitle());
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(this, mAdapter);
        historyHeyDayRecyclerView.setAdapter(mLRecyclerViewAdapter);
        historyHeyDayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyHeyDayRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        historyHeyDayRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

                Intent intent = new Intent(ChinaHistoryHeyDayActivity.this, HistoryHeyDayInFoActivity.class);
                intent.putExtra("title", chinaHistoryHeyDaysBeanList.get(i).getTitle());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });
        historyHeyDayRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                page = 1;
                search_condition = "";
                mPresenter.onRefreshChinaHistoryHeyDay(page + "", search_condition);
            }

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(historyHeyDayRecyclerView);
                if (state == LoadingFooter.State.Loading) {
                    return;
                }
                page++;
                mPresenter.onLoadMoreChinaHistoryHeyDay(page + "", search_condition);
            }

            @Override
            public void onScrolled(int i, int i1) {

            }
        });
    }

    private void initData() {
        mPresenter = new ChinaHistoryHeyDayPresenterImpl(this);
        mPresenter.onInitChinaHistoryHeyDay(page + "", search_condition);
    }

    @Override
    public void initChinaHistoryHeyDay(ChinaHistoryHeyDay chinaHistoryHeyDay) {
        if (chinaHistoryHeyDay.getCode() == 200) {
            chinaHistoryHeyDaysBeanList.clear();
            chinaHistoryHeyDaysBeanList.addAll(chinaHistoryHeyDay.getChina_History_Hey_Days());
            historyHeyDayRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("搜索结果为空");
            dialog.show();
        }
    }

    @Override
    public void refreshChinaHistoryHeyDay(ChinaHistoryHeyDay chinaHistoryHeyDay) {
        if (chinaHistoryHeyDay.getCode() == 200) {
            chinaHistoryHeyDaysBeanList.clear();
            chinaHistoryHeyDaysBeanList.addAll(chinaHistoryHeyDay.getChina_History_Hey_Days());
            historyHeyDayRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("搜索结果为空");
            dialog.show();
        }
    }

    @Override
    public void loadMoreChinaHistoryHeyDay(ChinaHistoryHeyDay chinaHistoryHeyDay) {
        if (chinaHistoryHeyDay.getCode() == 200) {
            chinaHistoryHeyDaysBeanList.addAll(chinaHistoryHeyDay.getChina_History_Hey_Days());
            historyHeyDayRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            historyHeyDayRecyclerView.refreshComplete();
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

    @OnClick(R.id.history_hey_day_search)
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
                            mPresenter.onInitChinaHistoryHeyDay(page + "", search_condition);
                        }
                    }
                }).show();
    }
}
