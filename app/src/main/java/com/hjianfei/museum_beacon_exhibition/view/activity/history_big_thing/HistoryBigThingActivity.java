package com.hjianfei.museum_beacon_exhibition.view.activity.history_big_thing;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
<<<<<<< HEAD
import android.transition.Fade;
=======
import android.transition.Slide;
>>>>>>> tmp
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewUtils;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryBigThing;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_big_thing_detail.ChinaHistoryBigThingDetailActivity;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryBigThingActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.big_thing_recyclerView)
    LRecyclerView bigThingRecyclerView;
    private ChinaHistoryBigThing chinaHistoryBigThing;
    private CommonAdapter<ChinaHistoryBigThing.ChinaHistoryBigThingsBean> mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private String dynasty_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_history_big_thing);
        //淡入淡出
        getWindow().setEnterTransition(new Fade().setDuration(Constants.DURATION));
        getWindow().setReturnTransition(new Fade().setDuration(Constants.DURATION));
        dynasty_name = getIntent().getStringExtra("dynasty_name");
        chinaHistoryBigThing = (ChinaHistoryBigThing) getIntent().getBundleExtra("bundle").getSerializable("HistoryBigThings");

=======
        //淡入淡出
        getWindow().setEnterTransition(new Slide().setDuration(Constants.DURATION));
        getWindow().setExitTransition(new Slide().setDuration(Constants.DURATION));
        dynasty_name = getIntent().getStringExtra("dynasty_name");
        chinaHistoryBigThing = (ChinaHistoryBigThing) getIntent().getBundleExtra("bundle").getSerializable("HistoryBigThings");
        setContentView(R.layout.activity_history_big_thing);
>>>>>>> tmp
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle(dynasty_name + "大事件");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mAdapter = new CommonAdapter<ChinaHistoryBigThing.ChinaHistoryBigThingsBean>(this, R.layout.dynasty_big_thing_item, chinaHistoryBigThing.getChina_History_Big_Things()) {
            @Override
            public void setData(ViewHolder holder, ChinaHistoryBigThing.ChinaHistoryBigThingsBean chinaHistoryBigThingsBean) {
                holder.setText(R.id.dynasty_big_thing_title, chinaHistoryBigThingsBean.getTitle());
                holder.setText(R.id.dynasty_big_thing_type, "——朝代：" + chinaHistoryBigThingsBean.getType());
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(this, mAdapter);
        View head_view = View.inflate(this, R.layout.cultural_head_view, null);
        bigThingRecyclerView.setAdapter(mLRecyclerViewAdapter);
        bigThingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bigThingRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        bigThingRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        bigThingRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        RecyclerViewUtils.setHeaderView(bigThingRecyclerView, head_view);
        mLRecyclerViewAdapter.setPullRefreshEnabled(false);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(HistoryBigThingActivity.this, ChinaHistoryBigThingDetailActivity.class);
                intent.putExtra("big_thing_title", chinaHistoryBigThing.getChina_History_Big_Things().get(i).getTitle());
                intent.putExtra("id", chinaHistoryBigThing.getChina_History_Big_Things().get(i).getId() + "");
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(HistoryBigThingActivity.this).toBundle());

            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });

    }
}
