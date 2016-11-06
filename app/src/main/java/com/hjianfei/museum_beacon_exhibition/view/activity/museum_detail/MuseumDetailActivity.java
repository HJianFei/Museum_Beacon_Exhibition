package com.hjianfei.museum_beacon_exhibition.view.activity.museum_detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.CommonDetailViewPagerAdapter;
import com.hjianfei.museum_beacon_exhibition.bean.MuseumDetail;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.museum_detail.MuseumDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.museum_detail.MuseumDetailPresenterImpl;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MuseumDetailActivity extends AppCompatActivity implements MuseumDetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.museum_detail_view_pager)
    RollPagerView museumDetailViewPager;
    @BindView(R.id.museum_detail_name)
    TextView museumDetailName;
    @BindView(R.id.museum_detail_content)
    TextView museumDetailContent;
    private MuseumDetailPresenter mMuseumDetailPresenter;
    private String museum_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_detail);
        museum_name = getIntent().getStringExtra("museum_name");
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initView() {

        toolbar.setTitle(museum_name);
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
        mMuseumDetailPresenter = new MuseumDetailPresenterImpl(this);
        mMuseumDetailPresenter.initMuseumDetailData(museum_name);

    }

    @Override
    public void initMuseumDetailData(MuseumDetail museumDetail) {
        museumDetailName.setText(museumDetail.getMuseum_Detail().getMuseum_detail_name());
        museumDetailContent.setText(museumDetail.getMuseum_Detail().getMuseum_detail_content());
        String img_url = museumDetail.getMuseum_Detail().getMuseum_detail_imgs();
        String[] img_urls = img_url.split(",");
        museumDetailViewPager.setPlayDelay(3000);
        museumDetailViewPager.setAdapter(new CommonDetailViewPagerAdapter(img_urls));
        museumDetailViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));


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
