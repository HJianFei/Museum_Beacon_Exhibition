package com.hjianfei.museum_beacon_exhibition.view.activity.foreign_history_detail;

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
import com.hjianfei.museum_beacon_exhibition.bean.ForeignHistoryDetail;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.foreign_history_detail.ForeignCountryDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.foreign_history_detail.ForeignCountryDetailPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ForeignCountryDetailActivity extends AppCompatActivity implements ForeignCountryDetailView, AppBarLayout.OnOffsetChangedListener {


    private static final int PERCENTAGE_TO_SHOW_IMAGE = 20;
    private int mMaxScrollSize;
    private boolean mIsImageHidden;
    @BindView(R.id.foreign_country_detail_toolbar)
    Toolbar foreignCountryDetailToolbar;
    @BindView(R.id.foreign_country_detail_bg_img)
    ImageView foreignCountryDetailBgImg;
    @BindView(R.id.foreign_country_detail_collapsing)
    CollapsingToolbarLayout foreignCountryDetailCollapsing;
    @BindView(R.id.foreign_country_detail_title)
    TextView foreignCountryDetailTitle;
    @BindView(R.id.foreign_country_detail_author)
    TextView foreignCountryDetailAuthor;
    @BindView(R.id.foreign_country_detail_time)
    TextView foreignCountryDetailTime;
    @BindView(R.id.foreign_country_detail_content)
    TextView foreignCountryDetailContent;
    @BindView(R.id.foreign_country_detail_fab)
    FloatingActionButton foreignCountryDetailFab;
    @BindView(R.id.foreign_country_detail_appbar)
    AppBarLayout foreignCountryDetailAppbar;
    private ForeignCountryDetailPresenter mPresenter;
    private String title;
    private SweetAlertDialog dialog;
    private String img_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getIntent().getStringExtra("title");
        setContentView(R.layout.activity_foreign_country_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        mPresenter = new ForeignCountryDetailPresenterImpl(this);
        mPresenter.getForeignCountryDetail(title);
    }

    private void initView() {
        setSupportActionBar(foreignCountryDetailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        foreignCountryDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        foreignCountryDetailAppbar.addOnOffsetChangedListener(this);
    }

    @Override
    public void onFinished(ForeignHistoryDetail foreignHistoryDetail) {
        foreignCountryDetailCollapsing.setTitle(title);
        foreignCountryDetailTitle.setText(foreignHistoryDetail.getForeign_History_Detail().getTitle());
        foreignCountryDetailAuthor.setText("作者："+foreignHistoryDetail.getForeign_History_Detail().getAuthor());
        foreignCountryDetailTime.setText("时间："+foreignHistoryDetail.getForeign_History_Detail().getTime());
        foreignCountryDetailContent.setText(foreignHistoryDetail.getForeign_History_Detail().getDetail());
        Glide.with(this).load(foreignHistoryDetail.getForeign_History_Detail().getImg_url()).into(foreignCountryDetailBgImg);
        img_url = foreignHistoryDetail.getForeign_History_Detail().getImg_url();
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

    @OnClick({R.id.foreign_country_detail_bg_img, R.id.foreign_country_detail_fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.foreign_country_detail_bg_img:

                Intent intent2 = new Intent(ForeignCountryDetailActivity.this, PhotoDetailActivity.class);
                intent2.putExtra("img_urls", img_url);
                intent2.putExtra("photo_title", title);
                startActivity(intent2);
                break;
            case R.id.foreign_country_detail_fab:
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

                ViewCompat.animate(foreignCountryDetailFab).scaleY(0).scaleX(0).start();
            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            if (mIsImageHidden) {
                mIsImageHidden = false;
                ViewCompat.animate(foreignCountryDetailFab).scaleY(1).scaleX(1).start();
            }
        }
    }
}
