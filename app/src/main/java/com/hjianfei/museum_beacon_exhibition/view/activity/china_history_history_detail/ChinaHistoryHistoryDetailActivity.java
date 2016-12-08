package com.hjianfei.museum_beacon_exhibition.view.activity.china_history_history_detail;

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
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHistoryDetail;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_history_detail.ChinaHistoryHistoryDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_history_detail.ChinaHistoryHistoryDetailPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChinaHistoryHistoryDetailActivity extends AppCompatActivity implements ChinaHistoryHistoryDetailView {

    @BindView(R.id.history_history_bg)
    ImageView historyHistoryBg;
    @BindView(R.id.history_history_toolbar)
    Toolbar historyHistoryToolbar;
    @BindView(R.id.history_history_collapsing)
    CollapsingToolbarLayout historyHistoryCollapsing;
    @BindView(R.id.history_history_title)
    TextView historyHistoryTitle;
    @BindView(R.id.history_history_author)
    TextView historyHistoryAuthor;
    @BindView(R.id.history_history_time)
    TextView historyHistoryTime;
    @BindView(R.id.history_history_content)
    TextView historyHistoryContent;
    @BindView(R.id.history_history_fab)
    FloatingActionButton historyHistoryFab;
    private ChinaHistoryHistoryDetailPresenter mPresenter;
    private String title;
    private String author;
    private String time;
    private ChinaHistoryHistoryDetail mHistoryDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getIntent().getStringExtra("title");
        author = getIntent().getStringExtra("author");
        time = getIntent().getStringExtra("time");
        setContentView(R.layout.activity_china_history_history_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        historyHistoryCollapsing.setTitle(title);
        setSupportActionBar(historyHistoryToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        historyHistoryToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initData() {
        mPresenter = new ChinaHistoryHistoryDetailPresenterImpl(this);
        mPresenter.getChinaHistoryHistoryDetail(title);
    }

    @Override
    public void onFinished(ChinaHistoryHistoryDetail chinaHistoryHistoryDetail) {

        mHistoryDetail = chinaHistoryHistoryDetail;
        historyHistoryAuthor.setText(author);
        historyHistoryTime.setText(time);
        Glide.with(this).load(chinaHistoryHistoryDetail.getChina_History_History_Detail().getImg_url()).into(historyHistoryBg);
        historyHistoryTitle.setText(chinaHistoryHistoryDetail.getChina_History_History_Detail().getTitle());
        historyHistoryContent.setText(chinaHistoryHistoryDetail.getChina_History_History_Detail().getContent());
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @OnClick({R.id.history_history_bg, R.id.history_history_fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.history_history_bg:
                Intent intent2 = new Intent(ChinaHistoryHistoryDetailActivity.this, PhotoDetailActivity.class);
                intent2.putExtra("img_urls", mHistoryDetail.getChina_History_History_Detail().getImg_url());
                intent2.putExtra("photo_title", mHistoryDetail.getChina_History_History_Detail().getTitle());
                startActivity(intent2);
                break;
            case R.id.history_history_fab:
                break;
        }
    }
}
