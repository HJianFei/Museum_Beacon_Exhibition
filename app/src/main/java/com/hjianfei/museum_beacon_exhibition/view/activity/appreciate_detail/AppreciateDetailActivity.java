package com.hjianfei.museum_beacon_exhibition.view.activity.appreciate_detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.CommonDetailViewPagerAdapter;
import com.hjianfei.museum_beacon_exhibition.bean.AppreciateDetail;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.appreciate_detail.AppreciateDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.appreciate_detail.AppreciateDetailPresenterImpl;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppreciateDetailActivity extends AppCompatActivity implements AppreciateDetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cultural_detail_view_pager)
    RollPagerView culturalDetailViewPager;
    @BindView(R.id.cultural_detail_name)
    TextView culturalDetailName;
    @BindView(R.id.cultural_detail_content)
    TextView culturalDetailContent;
    private String cultural_detail_url;
    private String cultural_name;
    private AppreciateDetailPresenter mAppreciateDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appreciate_detail);
        ButterKnife.bind(this);
        cultural_detail_url = getIntent().getStringExtra("cultural_detail_url");
        cultural_name = getIntent().getStringExtra("cultural_name");
        initData();
        initView();
    }

    private void initView() {
        toolbar.setTitle(cultural_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initData() {
        mAppreciateDetailPresenter = new AppreciateDetailPresenterImpl(this);
        mAppreciateDetailPresenter.onInitAppreciateDetailData(cultural_detail_url);

    }

    @Override
    public void initAppreciateDetailData(AppreciateDetail appreciateDetail) {
        culturalDetailName.setText(appreciateDetail.getAppreciateDetail().getTitle());
        culturalDetailContent.setText(appreciateDetail.getAppreciateDetail().getContent());
        String img_url = appreciateDetail.getAppreciateDetail().getImg_url();
        String[] img_urls = img_url.split(",");
        culturalDetailViewPager.setPlayDelay(3000);
        culturalDetailViewPager.setAdapter(new CommonDetailViewPagerAdapter(img_urls));
        culturalDetailViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));

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
}
