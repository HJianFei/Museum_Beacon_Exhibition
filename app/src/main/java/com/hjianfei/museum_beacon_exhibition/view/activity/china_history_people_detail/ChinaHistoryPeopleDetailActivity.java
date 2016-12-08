package com.hjianfei.museum_beacon_exhibition.view.activity.china_history_people_detail;

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
import com.hjianfei.museum_beacon_exhibition.bean.HistoryPeopleDetail;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_people_detail.ChinaHistoryPeopleDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_people_detail.ChinaHistoryPeopleDetailPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ChinaHistoryPeopleDetailActivity extends AppCompatActivity implements ChinaHistoryPeopleDetailView {

    @BindView(R.id.history_people_bg)
    ImageView historyPeopleBg;
    @BindView(R.id.history_people_toolbar)
    Toolbar historyPeopleToolbar;
    @BindView(R.id.history_people_collapsing)
    CollapsingToolbarLayout historyPeopleCollapsing;
    @BindView(R.id.history_people_name)
    TextView historyPeopleName;
    @BindView(R.id.history_people_content)
    TextView historyPeopleContent;
    @BindView(R.id.history_people_fab)
    FloatingActionButton historyPeopleFab;
    private ChinaHistoryPeopleDetailPresenter mPeopleDetailPresenter;
    private String detail_url;
    private String img_url;
    private HistoryPeopleDetail mHistoryPeopleDetail;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detail_url = getIntent().getStringExtra("detail_url");
        img_url = getIntent().getStringExtra("img_url");
        setContentView(R.layout.activity_china_history_people_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        mPeopleDetailPresenter = new ChinaHistoryPeopleDetailPresenterImpl(this);
        mPeopleDetailPresenter.getHistoryPeopleDetail(detail_url);
    }

    private void initView() {
        setSupportActionBar(historyPeopleToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        historyPeopleToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Glide.with(this).load(img_url).into(historyPeopleBg);

    }

    @Override
    public void onFinished(HistoryPeopleDetail historyPeopleDetail) {
        mHistoryPeopleDetail = historyPeopleDetail;
        historyPeopleContent.setText(historyPeopleDetail.getChina_History_People_Detail().getContent());
        historyPeopleCollapsing.setTitle(historyPeopleDetail.getChina_History_People_Detail().getName());
        historyPeopleName.setText(historyPeopleDetail.getChina_History_People_Detail().getName());
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

    @OnClick({R.id.history_people_bg, R.id.history_people_fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.history_people_bg:

                Intent intent2 = new Intent(ChinaHistoryPeopleDetailActivity.this, PhotoDetailActivity.class);
                intent2.putExtra("img_urls", img_url);
                intent2.putExtra("photo_title", mHistoryPeopleDetail.getChina_History_People_Detail().getName());
                startActivity(intent2);
                break;
            case R.id.history_people_fab:
                break;
        }
    }
}
