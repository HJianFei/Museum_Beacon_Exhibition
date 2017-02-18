package com.hjianfei.museum_beacon_exhibition.view.activity.history_big_thing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
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
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_big_thing_detail.ChinaHistoryBigThingDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryBigThingActivity extends AppCompatActivity {

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
        dynasty_name = getIntent().getStringExtra("dynasty_name");
        chinaHistoryBigThing = (ChinaHistoryBigThing) getIntent().getBundleExtra("bundle").getSerializable("HistoryBigThings");
        setContentView(R.layout.activity_history_big_thing);
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
                intent.putExtra("id", chinaHistoryBigThing.getChina_History_Big_Things().get(i).getId()+"");
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });

    }
}
