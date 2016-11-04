package com.hjianfei.museum_beacon_exhibition.view.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.search_tip)
    EditText searchTip;
    @BindView(R.id.btn_search)
    TextView btnSearch;
    @BindView(R.id.activity_search)
    LinearLayout activitySearch;
    @BindView(R.id.search_toolbar)
    Toolbar searchToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(searchToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        searchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @OnClick(R.id.btn_search)
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                if (!TextUtils.isEmpty(searchTip.getText().toString().toString())) {
                    ToastUtil.showToast(this, searchTip.getText().toString().trim());
                } else {
                    Snackbar.make(activitySearch, "搜索条件不能为空", Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
