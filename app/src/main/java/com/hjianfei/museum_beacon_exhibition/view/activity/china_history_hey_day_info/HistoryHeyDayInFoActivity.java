package com.hjianfei.museum_beacon_exhibition.view.activity.china_history_hey_day_info;

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
import com.hjianfei.museum_beacon_exhibition.bean.HistoryHeyDayInFo;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_hey_day_info.HistoryHeyDayInFoPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_hey_day_info.HistoryHeyDayInFoPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class HistoryHeyDayInFoActivity extends AppCompatActivity implements HistoryHeyDayInFoView {


    @BindView(R.id.hey_day_bg_img)
    ImageView heyDayBgImg;
    @BindView(R.id.hey_day_toolbar)
    Toolbar heyDayToolbar;
    @BindView(R.id.hey_day_collapsing)
    CollapsingToolbarLayout heyDayCollapsing;
    @BindView(R.id.hey_day_detail)
    TextView heyDayDetail;
    @BindView(R.id.hey_day_fab)
    FloatingActionButton heyDayFab;
    private HistoryHeyDayInFoPresenter mPresenter;
    private String title;
    private HistoryHeyDayInFo mHistoryHeyDayInFo;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getIntent().getStringExtra("title");
        setContentView(R.layout.activity_istory_hey_day_in_fo);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        heyDayCollapsing.setTitle(title);
        setSupportActionBar(heyDayToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        heyDayToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initData() {
        mPresenter = new HistoryHeyDayInFoPresenterImpl(this);
        mPresenter.getHistoryHeyDayInFo(title);
    }

    @Override
    public void onFinished(HistoryHeyDayInFo historyHeyDayInFo) {
        mHistoryHeyDayInFo = historyHeyDayInFo;
        heyDayDetail.setText(historyHeyDayInFo.getChina_History_Hey_Day_InFo().getDetail());
        Glide.with(this).load(historyHeyDayInFo.getChina_History_Hey_Day_InFo().getImg_url()).into(heyDayBgImg);

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

    @OnClick({R.id.hey_day_bg_img, R.id.hey_day_fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hey_day_bg_img:
                Intent intent2 = new Intent(HistoryHeyDayInFoActivity.this, PhotoDetailActivity.class);
                intent2.putExtra("img_urls", mHistoryHeyDayInFo.getChina_History_Hey_Day_InFo().getImg_url());
                intent2.putExtra("photo_title", title);
                startActivity(intent2);
                break;
            case R.id.hey_day_fab:
                break;
        }
    }
}
