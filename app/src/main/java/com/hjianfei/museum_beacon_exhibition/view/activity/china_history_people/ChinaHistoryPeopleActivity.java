package com.hjianfei.museum_beacon_exhibition.view.activity.china_history_people;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.HeaderSpanSizeLookup;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryPeople;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_people.ChinaHistoryPeoplePresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_people.ChinaHistoryPeoplePresenterImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_people_detail.ChinaHistoryPeopleDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ChinaHistoryPeopleActivity extends AppCompatActivity implements ChinaHistoryPeopleView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.history_people_recyclerView)
    LRecyclerView historyPeopleRecyclerView;
    @BindView(R.id.history_people_search)
    FloatingActionButton historyPeopleSearch;
    private CommonAdapter<ChinaHistoryPeople.ChinaHistoryPeoplesBean> mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private List<ChinaHistoryPeople.ChinaHistoryPeoplesBean> chinaHistoryPeoplesBeanList = new ArrayList<>();
    private int page = 1;
    private SweetAlertDialog dialog;
    private String search_condition = "";
    private ChinaHistoryPeoplePresenter mPeoplePresenter;
    private String dynasty_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dynasty_name = getIntent().getStringExtra("dynasty_name");
        setContentView(R.layout.activity_china_history_people);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        toolbar.setTitle(dynasty_name + "人物");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mAdapter = new CommonAdapter<ChinaHistoryPeople.ChinaHistoryPeoplesBean>(this, R.layout.history_people_item, chinaHistoryPeoplesBeanList) {
            @Override
            public void setData(ViewHolder holder, ChinaHistoryPeople.ChinaHistoryPeoplesBean chinaHistoryPeoplesBean) {

                holder.setImageWithUrl(R.id.dynasty_people_image, chinaHistoryPeoplesBean.getImg_url());
                holder.setText(R.id.dynasty_people_name, chinaHistoryPeoplesBean.getName());
                holder.setText(R.id.dynasty_people_view, chinaHistoryPeoplesBean.getViews() + "");
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(this, mAdapter);
        historyPeopleRecyclerView.setAdapter(mLRecyclerViewAdapter);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new HeaderSpanSizeLookup((LRecyclerViewAdapter) historyPeopleRecyclerView.getAdapter(), manager.getSpanCount()));
        historyPeopleRecyclerView.setLayoutManager(manager);
        historyPeopleRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        historyPeopleRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(ChinaHistoryPeopleActivity.this, ChinaHistoryPeopleDetailActivity.class);
                intent.putExtra("id", chinaHistoryPeoplesBeanList.get(i).getId()+"");
                intent.putExtra("img_url", chinaHistoryPeoplesBeanList.get(i).getImg_url());
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });
        historyPeopleRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                page = 1;
                search_condition = "";
                mPeoplePresenter.onRefreshChinaHistoryPeople(dynasty_name, page + "", search_condition);
            }

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(historyPeopleRecyclerView);
                if (state == LoadingFooter.State.Loading) {
                    return;
                }
                page++;
                mPeoplePresenter.onLoadMoreChinaHistoryPeople(dynasty_name, page + "", search_condition);
            }

            @Override
            public void onScrolled(int i, int i1) {

            }
        });


    }

    private void initData() {
        mPeoplePresenter = new ChinaHistoryPeoplePresenterImpl(this);
        mPeoplePresenter.onInitChinaHistoryPeople(dynasty_name, page + "", search_condition);
    }

    @Override
    public void onInitFinished(ChinaHistoryPeople chinaHistoryPeople) {
        if (chinaHistoryPeople.getCode() == 200) {
            chinaHistoryPeoplesBeanList.clear();
            chinaHistoryPeoplesBeanList.addAll(chinaHistoryPeople.getChina_History_Peoples());
            historyPeopleRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("搜索结果为空");
            dialog.show();
        }
    }

    @Override
    public void onRefreshFinished(ChinaHistoryPeople chinaHistoryPeople) {
        if (chinaHistoryPeople.getCode() == 200) {
            chinaHistoryPeoplesBeanList.clear();
            chinaHistoryPeoplesBeanList.addAll(chinaHistoryPeople.getChina_History_Peoples());
            historyPeopleRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("搜索结果为空");
            dialog.show();
        }
    }

    @Override
    public void onLoadMoreFinished(ChinaHistoryPeople chinaHistoryPeople) {
        if (chinaHistoryPeople.getCode() == 200) {
            chinaHistoryPeoplesBeanList.addAll(chinaHistoryPeople.getChina_History_Peoples());
            historyPeopleRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            historyPeopleRecyclerView.refreshComplete();
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

    @OnClick(R.id.history_people_search)
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.history_people_search:
                new MaterialDialog.Builder(this)
                        .title("搜索")
                        .titleColor(getResources().getColor(R.color.primary))
                        .input("请输入关键字", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                if (!TextUtils.isEmpty(input)) {
                                    search_condition = input.toString().trim();
                                    page = 1;
                                    mPeoplePresenter.onInitChinaHistoryPeople(dynasty_name, page + "", search_condition);
                                }
                            }
                        }).show();
                break;
        }
    }

}
