package com.hjianfei.museum_beacon_exhibition.view.activity.stepview;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.StepView;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailActivity;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StepViewActivity extends BaseActivity {

    @BindView(R.id.step_view_bg)
    ImageView stepViewBg;
    @BindView(R.id.step_view_toolbar)
    Toolbar stepViewToolbar;
    @BindView(R.id.step_view_collapsing)
    CollapsingToolbarLayout stepViewCollapsing;
    @BindView(R.id.step_view_name)
    TextView stepViewName;
    @BindView(R.id.step_view_content)
    TextView stepViewContent;
    private StepView stepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //滑动进入
        getWindow().setEnterTransition(new Slide().setDuration(Constants.DURATION));
        getWindow().setExitTransition(new Slide().setDuration(Constants.DURATION));
        stepView = (StepView) getIntent().getBundleExtra("stepView").getSerializable("stepView");
        setContentView(R.layout.activity_step_view);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(stepViewToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        stepViewToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        stepViewContent.setText(stepView.getStepView().getContent());
        stepViewCollapsing.setTitle(stepView.getStepView().getStep_name());
        stepViewName.setText(stepView.getStepView().getStep_name());
        Glide.with(this).load(stepView.getStepView().getImg_url()).into(stepViewBg);
    }

    @OnClick(R.id.step_view_bg)
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.step_view_bg:
                Intent intent = new Intent(this, PhotoDetailActivity.class);
                intent.putExtra("img_urls", stepView.getStepView().getImg_url());
                intent.putExtra("photo_title", stepView.getStepView().getStep_name());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
        }

    }
}
