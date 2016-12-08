package com.hjianfei.museum_beacon_exhibition.view.activity.china_history_war_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.HistoryWarDetail;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_war_detail.HistoryWarDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_war_detail.HistoryWarDetailPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryWarDetailActivity extends AppCompatActivity implements HistoryWarDetailView, AppBarLayout.OnOffsetChangedListener {

    private static final int PERCENTAGE_TO_SHOW_IMAGE = 20;
    private int mMaxScrollSize;
    private boolean mIsImageHidden;
    @BindView(R.id.history_war_detail_toolbar)
    Toolbar historyWarDetailToolbar;
    @BindView(R.id.history_war_detail_bg_img)
    ImageView historyWarDetailBgImg;
    @BindView(R.id.history_war_detail_collapsing)
    CollapsingToolbarLayout historyWarDetailCollapsing;
    @BindView(R.id.history_war_detail_appbar)
    AppBarLayout historyWarDetailAppbar;
    @BindView(R.id.history_war_detail_army)
    TextView historyWarDetailArmy;
    @BindView(R.id.history_war_detail_name)
    TextView historyWarDetailName;
    @BindView(R.id.history_war_detail_result)
    TextView historyWarDetailResult;
    @BindView(R.id.history_war_detail_time)
    TextView historyWarDetailTime;
    @BindView(R.id.history_war_detail_address)
    TextView historyWarDetailAddress;
    @BindView(R.id.history_war_detail_figure)
    TextView historyWarDetailFigure;
    @BindView(R.id.history_war_detail_content)
    TextView historyWarDetailContent;
    @BindView(R.id.history_war_detail_fab)
    FloatingActionButton historyWarDetailFab;
    private HistoryWarDetailPresenter mPresenter;
    private String detail_url;
    private String img_url;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detail_url = getIntent().getStringExtra("detail_url");
        img_url = getIntent().getStringExtra("img_url");
        title = getIntent().getStringExtra("title");
        setContentView(R.layout.activity_history_war_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        setSupportActionBar(historyWarDetailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        historyWarDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        historyWarDetailAppbar.addOnOffsetChangedListener(this);
        Glide.with(this).load(img_url).into(historyWarDetailBgImg);
    }

    private void initData() {
        mPresenter = new HistoryWarDetailPresenterImpl(this);
        mPresenter.getHistoryWarDetail(detail_url);
    }

    @Override
    public void onFinished(HistoryWarDetail historyWarDetail) {
        historyWarDetailCollapsing.setTitle(title);
        historyWarDetailName.setText(historyWarDetail.getChina_History_Olden_War_Detail().getName());
        historyWarDetailArmy.setText(historyWarDetail.getChina_History_Olden_War_Detail().getArmy());
        historyWarDetailResult.setText(historyWarDetail.getChina_History_Olden_War_Detail().getResult());
        historyWarDetailTime.setText(historyWarDetail.getChina_History_Olden_War_Detail().getTime());
        historyWarDetailAddress.setText(historyWarDetail.getChina_History_Olden_War_Detail().getAddress());
        historyWarDetailFigure.setText(historyWarDetail.getChina_History_Olden_War_Detail().getFigure());
        historyWarDetailContent.setText(historyWarDetail.getChina_History_Olden_War_Detail().getWar_detail());
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

    @OnClick({R.id.history_war_detail_bg_img, R.id.history_war_detail_fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.history_war_detail_bg_img:
                Intent intent2 = new Intent(HistoryWarDetailActivity.this, PhotoDetailActivity.class);
                intent2.putExtra("img_urls", img_url);
                intent2.putExtra("photo_title", title);
                startActivity(intent2);
                break;
            case R.id.history_war_detail_fab:
                break;
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int currentScrollPercentage = (Math.abs(verticalOffset)) * 100
                / mMaxScrollSize;

        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
            if (!mIsImageHidden) {
                mIsImageHidden = true;

                ViewCompat.animate(historyWarDetailFab).scaleY(0).scaleX(0).start();
            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            if (mIsImageHidden) {
                mIsImageHidden = false;
                ViewCompat.animate(historyWarDetailFab).scaleY(1).scaleX(1).start();
            }
        }
    }
}
