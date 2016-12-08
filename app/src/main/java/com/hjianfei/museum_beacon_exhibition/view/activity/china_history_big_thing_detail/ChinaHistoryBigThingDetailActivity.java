package com.hjianfei.museum_beacon_exhibition.view.activity.china_history_big_thing_detail;

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
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryBigThingDetail;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_big_thing_detail.ChinaHistoryBigThingDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_big_thing_detail.ChinaHistoryBigThingDetailPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChinaHistoryBigThingDetailActivity extends AppCompatActivity implements ChinaHistoryBigThingDetailView {

    @BindView(R.id.big_thing_bg)
    ImageView bigThingBg;
    @BindView(R.id.big_thing_toolbar)
    Toolbar bigThingToolbar;
    @BindView(R.id.big_thing_collapsing)
    CollapsingToolbarLayout bigThingCollapsing;
    @BindView(R.id.big_thing_title)
    TextView bigThingTitle;
    @BindView(R.id.big_thing_author)
    TextView bigThingAuthor;
    @BindView(R.id.big_thing_time)
    TextView bigThingTime;
    //    @BindView(R.id.big_thing_view)
//    TextView bigThingView;
    @BindView(R.id.big_thing_content)
    TextView big_thing_content;
    @BindView(R.id.big_thing_fab)
    FloatingActionButton bigThingFab;
    private ChinaHistoryBigThingDetailPresenter mPresenter;
    private String big_thing_title;
    private ChinaHistoryBigThingDetail mBigThingDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        big_thing_title = getIntent().getStringExtra("big_thing_title");
        setContentView(R.layout.activity_china_history_big_thing_detail);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        mPresenter = new ChinaHistoryBigThingDetailPresenterImpl(this);
        mPresenter.getChinaHistoryBigThingDetail(big_thing_title);
    }

    private void initView() {
        bigThingCollapsing.setTitle(big_thing_title);
        setSupportActionBar(bigThingToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bigThingToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void getChinaHistoryBigThingDetailFinished(ChinaHistoryBigThingDetail chinaHistoryBigThingDetail) {

        mBigThingDetail = chinaHistoryBigThingDetail;
        big_thing_content.setText(chinaHistoryBigThingDetail.getChina_History_Big_Thing_Detail().getContent());
        Glide.with(this).load(chinaHistoryBigThingDetail.getChina_History_Big_Thing_Detail().getImg_url()).into(bigThingBg);
        bigThingTitle.setText(chinaHistoryBigThingDetail.getChina_History_Big_Thing_Detail().getTitle());
        bigThingAuthor.setText("作者：" + chinaHistoryBigThingDetail.getChina_History_Big_Thing_Detail().getAuthor());
        bigThingTime.setText("时间：" + chinaHistoryBigThingDetail.getChina_History_Big_Thing_Detail().getTime());
//        bigThingView.setText(chinaHistoryBigThingDetail.getChina_History_Big_Thing_Detail().getViews());

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

    @OnClick({R.id.big_thing_fab, R.id.big_thing_bg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.big_thing_bg:
                Intent intent2 = new Intent(ChinaHistoryBigThingDetailActivity.this, PhotoDetailActivity.class);
                intent2.putExtra("img_urls", mBigThingDetail.getChina_History_Big_Thing_Detail().getImg_url());
                intent2.putExtra("photo_title", mBigThingDetail.getChina_History_Big_Thing_Detail().getTitle());
                startActivity(intent2);
                break;
            case R.id.big_thing_fab:
                break;
        }
    }
}
