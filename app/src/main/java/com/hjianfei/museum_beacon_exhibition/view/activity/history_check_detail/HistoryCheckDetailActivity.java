package com.hjianfei.museum_beacon_exhibition.view.activity.history_check_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.HistoryCheckDetail;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.history_check_detail.HistoryCheckDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.history_check_detail.HistoryCheckDetailPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class HistoryCheckDetailActivity extends AppCompatActivity implements HistoryCheckDetailView {

    @BindView(R.id.history_check_detail_bg)
    ImageView historyCheckDetailBg;
    @BindView(R.id.history_check_detail_toolbar)
    Toolbar historyCheckDetailToolbar;
    @BindView(R.id.history_check_detail_collapsing)
    CollapsingToolbarLayout historyCheckDetailCollapsing;
    @BindView(R.id.history_check_detail_title)
    TextView historyCheckDetailTitle;
    @BindView(R.id.history_check_detail_content)
    TextView historyCheckDetailContent;
    @BindView(R.id.history_check_detail_fab)
    FloatingActionButton historyCheckDetailFab;
    private HistoryCheckDetailPresenter mPresenter;
    private String title;
    private HistoryCheckDetail mCheckDetail;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getIntent().getStringExtra("title");
        setContentView(R.layout.activity_history_check_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        historyCheckDetailCollapsing.setTitle(title);
        setSupportActionBar(historyCheckDetailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        historyCheckDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initData() {
        mPresenter = new HistoryCheckDetailPresenterImpl(this);
        mPresenter.getHistoryHCheckDetail(title);
    }

    @Override
    public void onFinished(HistoryCheckDetail historyCheckDetail) {
        mCheckDetail = historyCheckDetail;
        historyCheckDetailTitle.setText(historyCheckDetail.getCheck_Detail().getTitle());
        historyCheckDetailContent.setText(historyCheckDetail.getCheck_Detail().getDetail());
        Glide.with(this).load(historyCheckDetail.getCheck_Detail().getImg_url()).into(historyCheckDetailBg);


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

    @OnClick({R.id.history_check_detail_bg, R.id.history_check_detail_fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.history_check_detail_bg:
                Intent intent2 = new Intent(HistoryCheckDetailActivity.this, PhotoDetailActivity.class);
                intent2.putExtra("img_urls", mCheckDetail.getCheck_Detail().getImg_url());
                intent2.putExtra("photo_title", title);
                startActivity(intent2);
                break;
            case R.id.history_check_detail_fab:
                break;
        }
    }
}
