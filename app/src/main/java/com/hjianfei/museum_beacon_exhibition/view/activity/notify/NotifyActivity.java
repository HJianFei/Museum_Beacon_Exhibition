package com.hjianfei.museum_beacon_exhibition.view.activity.notify;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.NotifyResult;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailActivity;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class NotifyActivity extends BaseActivity {

    @BindView(R.id.notify_bg)
    ImageView notifyBg;
    @BindView(R.id.notify_toolbar)
    Toolbar notifyToolbar;
    @BindView(R.id.notify_collapsing)
    CollapsingToolbarLayout notifyCollapsing;
    @BindView(R.id.notify_name)
    TextView notifyName;
    @BindView(R.id.notify_content)
    TextView notifyContent;
    private NotifyResult notifyResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notify);
        //淡入淡出
        getWindow().setEnterTransition(new Fade().setDuration(Constants.DURATION));
        getWindow().setReturnTransition(new Fade().setDuration(Constants.DURATION));
        notifyResult = (NotifyResult) getIntent().getBundleExtra("notifyResult").getSerializable("notifyResult");


        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(notifyToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        notifyToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        notifyContent.setText(notifyResult.getNotify().getNotify_content());
        notifyCollapsing.setTitle(notifyResult.getNotify().getNotify_title());
        notifyName.setText(notifyResult.getNotify().getNotify_title());
        Glide.with(this)
                .load(notifyResult.getNotify().getNotify_img_url())
                .placeholder(R.drawable.photo)
                .error(R.drawable.photo)
                .into(notifyBg);

    }

    @OnClick(R.id.notify_bg)
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.notify_bg:
                Intent intent = new Intent(this, PhotoDetailActivity.class);
                intent.putExtra("img_urls", notifyResult.getNotify().getNotify_img_url());
                intent.putExtra("photo_title", notifyResult.getNotify().getNotify_title());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
        }

    }
}
