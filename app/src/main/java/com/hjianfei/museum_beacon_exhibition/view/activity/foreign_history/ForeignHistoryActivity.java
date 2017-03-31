package com.hjianfei.museum_beacon_exhibition.view.activity.foreign_history;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewUtils;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.view.activity.foreign_country_history.ForeignCountryActivity;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForeignHistoryActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.foreign_history_recyclerView)
    LRecyclerView foreignHistoryRecyclerView;

    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private List<String> country = new ArrayList<>();
    private CommonAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreign_history);
        //淡入淡出
        getWindow().setEnterTransition(new Fade().setDuration(Constants.DURATION));
        getWindow().setReturnTransition(new Fade().setDuration(Constants.DURATION));

        ButterKnife.bind(this);
        country.add("美国历史");
        country.add("英国历史");
        country.add("日本历史");
        country.add("韩国历史");
        country.add("朝鲜历史");
//        country.add("其他国家历史");
        initView();
    }

    private void initView() {
        toolbar.setTitle("外国历史");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mAdapter = new CommonAdapter<String>(this, R.layout.dynasty_big_thing_item, country) {
            @Override
            public void setData(ViewHolder holder, String s) {
                holder.setText(R.id.dynasty_big_thing_title, s);
                holder.setText(R.id.dynasty_big_thing_type, "作者:彼岸花开");
            }
        };

        mLRecyclerViewAdapter = new LRecyclerViewAdapter(this, mAdapter);
        View head_view = View.inflate(this, R.layout.cultural_head_view, null);
        foreignHistoryRecyclerView.setAdapter(mLRecyclerViewAdapter);
        foreignHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        foreignHistoryRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        foreignHistoryRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        foreignHistoryRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        RecyclerViewUtils.setHeaderView(foreignHistoryRecyclerView, head_view);
        mLRecyclerViewAdapter.setPullRefreshEnabled(false);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(ForeignHistoryActivity.this, ForeignCountryActivity.class);
                intent.putExtra("country", country.get(i));
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ForeignHistoryActivity.this).toBundle());


            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });
    }
}
