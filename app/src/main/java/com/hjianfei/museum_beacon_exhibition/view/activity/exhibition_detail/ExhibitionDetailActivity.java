package com.hjianfei.museum_beacon_exhibition.view.activity.exhibition_detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.CommonDetailViewPagerAdapter;
import com.hjianfei.museum_beacon_exhibition.bean.ExhibitionDetail;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.exhibition_detail.ExhibitionDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.exhibition_detail.ExhibitionDetailPresenterImpl;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExhibitionDetailActivity extends AppCompatActivity implements ExhibitionDetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.exhibition_detail_view_pager)
    RollPagerView exhibitionDetailViewPager;
    @BindView(R.id.exhibition_detail_name)
    TextView exhibitionDetailName;
    @BindView(R.id.exhibition_detail_time)
    TextView exhibitionDetailTime;
    @BindView(R.id.exhibition_detail_address)
    TextView exhibitionDetailAddress;
    @BindView(R.id.exhibition_detail_content)
    TextView exhibitionDetailContent;
    private String exhibition_detail_url;
    private String exhibition_title;
    private ExhibitionDetailPresenter mExhibitionDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibition_detail);
        ButterKnife.bind(this);
        exhibition_detail_url = getIntent().getStringExtra("exhibition_detail_url");
        exhibition_title = getIntent().getStringExtra("exhibition_title");
        initData();
        initView();
    }

    private void initView() {
        toolbar.setTitle(exhibition_title);
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
        mExhibitionDetailPresenter = new ExhibitionDetailPresenterImpl(this);
        mExhibitionDetailPresenter.onInitExhibitionDetailData(exhibition_detail_url);

    }

    @Override
    public void initExhibitionDetailData(ExhibitionDetail exhibitionDetail) {
        exhibitionDetailName.setText(exhibitionDetail.getExhibitionDetail().getTitle());
        exhibitionDetailTime.setText(exhibitionDetail.getExhibitionDetail().getShow_time());
        exhibitionDetailAddress.setText(exhibitionDetail.getExhibitionDetail().getAddress());
        exhibitionDetailContent.setText(exhibitionDetail.getExhibitionDetail().getContent());
        String img_url = exhibitionDetail.getExhibitionDetail().getImg_url();
//        img_url = img_url.substring(1, img_url.length() - 1);
        String[] img_urls = img_url.split(",");
        exhibitionDetailViewPager.setPlayDelay(3000);
        exhibitionDetailViewPager.setAdapter(new CommonDetailViewPagerAdapter(img_urls));
        exhibitionDetailViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));

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
