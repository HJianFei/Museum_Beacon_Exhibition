package com.hjianfei.museum_beacon_exhibition.view.activity.china_dynasty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaDynasty;
import com.hjianfei.museum_beacon_exhibition.canstants.Urls;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_dynasty.ChinaDynastyPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_dynasty.ChinaDynastyPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ChinaDynastyActivity extends AppCompatActivity implements ChinaDynastyView {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.china_dynasty)
    LRecyclerView chinaDynasty;
    private CommonAdapter<ChinaDynasty.ChinaDynastiesBean> mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private List<ChinaDynasty.ChinaDynastiesBean> chinaDynastiesBeanList = new ArrayList<>();
    private int page = 1;
    private SweetAlertDialog dialog;
    private ChinaDynastyPresenter mChinaDynastyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_china_dynasty);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        toolbar.setTitle("中国历史");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mAdapter = new CommonAdapter<ChinaDynasty.ChinaDynastiesBean>(this, R.layout.china_dynasty_item, chinaDynastiesBeanList) {
            @Override
            public void setData(ViewHolder holder, ChinaDynasty.ChinaDynastiesBean chinaDynastiesBean) {
                holder.setImageWithUrl(R.id.china_dynasty_image, Urls.API_SERVER + chinaDynastiesBean.getImg_url());

            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(this, mAdapter);
        chinaDynasty.setAdapter(mLRecyclerViewAdapter);
        chinaDynasty.setLayoutManager(new LinearLayoutManager(this));
        chinaDynasty.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        chinaDynasty.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {


            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });
        chinaDynasty.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mChinaDynastyPresenter.onRefreshChinaDynasty(page + "");

            }

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(chinaDynasty);
                if (state == LoadingFooter.State.Loading) {
                    return;
                }
                page++;
                mChinaDynastyPresenter.onLoadMoreChinaDynasty(page + "");
            }

            @Override
            public void onScrolled(int i, int i1) {

            }
        });

    }

    private void initData() {
        mChinaDynastyPresenter = new ChinaDynastyPresenterImpl(this);
        mChinaDynastyPresenter.onInitChinaDynasty(page + "");
    }

    @Override
    public void initChinaDynasty(ChinaDynasty chinaDynastyBean) {
        chinaDynastiesBeanList.clear();
        chinaDynastiesBeanList.addAll(chinaDynastyBean.getChinaDynasties());
        chinaDynasty.refreshComplete();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshChinaDynasty(ChinaDynasty chinaDynastyBean) {
        chinaDynastiesBeanList.clear();
        chinaDynastiesBeanList.addAll(chinaDynastyBean.getChinaDynasties());
        chinaDynasty.refreshComplete();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreChinaDynasty(ChinaDynasty chinaDynastyBean) {
        if (null != chinaDynastyBean) {
            chinaDynastiesBeanList.addAll(chinaDynastyBean.getChinaDynasties());
            chinaDynasty.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            chinaDynasty.refreshComplete();
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
}
