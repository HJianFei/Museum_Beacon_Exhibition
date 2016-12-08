package com.hjianfei.museum_beacon_exhibition.view.activity.china_history_culture_detail;

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
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryCultureDetail;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_culture_detail.ChinaHistoryCultureDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_culture_detail.ChinaHistoryCultureDetailPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChinaHistoryCultureDetailActivity extends AppCompatActivity implements ChinaHistoryCultureDetailView {

    @BindView(R.id.history_culture_bg)
    ImageView historyCultureBg;
    @BindView(R.id.history_culture_toolbar)
    Toolbar historyCultureToolbar;
    @BindView(R.id.history_culture_collapsing)
    CollapsingToolbarLayout historyCultureCollapsing;
    @BindView(R.id.history_culture_title)
    TextView historyCultureTitle;
    @BindView(R.id.history_culture_content)
    TextView historyCultureContent;
    @BindView(R.id.history_culture_fab)
    FloatingActionButton historyCultureFab;
    @BindView(R.id.history_culture_author)
    TextView historyCultureAuthor;
    @BindView(R.id.history_culture_time)
    TextView historyCultureTime;
    private ChinaHistoryCultureDetailPresenter mPresenter;
    private String title;
    private String author;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getIntent().getStringExtra("title");
        author = getIntent().getStringExtra("author");
        time = getIntent().getStringExtra("time");
        setContentView(R.layout.activity_china_history_culture_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        historyCultureCollapsing.setTitle(title);
        setSupportActionBar(historyCultureToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        historyCultureToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    private void initData() {
        mPresenter = new ChinaHistoryCultureDetailPresenterImpl(this);
        mPresenter.getChinaHistoryCultureDetail(title);
    }

    @Override
    public void onFinished(ChinaHistoryCultureDetail chinaHistoryCultureDetail) {
        historyCultureAuthor.setText(author);
        historyCultureTime.setText(time);
        Glide.with(this).load(chinaHistoryCultureDetail.getChina_History_Culture_Detail().getImg_url()).into(historyCultureBg);
        historyCultureCollapsing.setTitle(chinaHistoryCultureDetail.getChina_History_Culture_Detail().getTitle());
        historyCultureTitle.setText(chinaHistoryCultureDetail.getChina_History_Culture_Detail().getTitle());
        historyCultureContent.setText(chinaHistoryCultureDetail.getChina_History_Culture_Detail().getContent());

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

    @OnClick({R.id.history_culture_bg, R.id.history_culture_fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.history_culture_bg:
                break;
            case R.id.history_culture_fab:
                break;
        }
    }
}
