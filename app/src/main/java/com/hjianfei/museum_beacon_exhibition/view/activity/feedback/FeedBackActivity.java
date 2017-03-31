package com.hjianfei.museum_beacon_exhibition.view.activity.feedback;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.feed_back.FeedBackPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.feed_back.FeedBackPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.SPUtils;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class FeedBackActivity extends BaseActivity implements FeedBackView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ed_feed_back)
    EditText edFeedBack;
    private FeedBackPresenter mFeedBackPresenter;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        //淡入淡出
        getWindow().setEnterTransition(new Fade().setDuration(Constants.DURATION));
        getWindow().setReturnTransition(new Fade().setDuration(Constants.DURATION));

        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle("意见与反馈");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.feed_back_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_btn_send:
                mFeedBackPresenter = new FeedBackPresenterImpl(this);
                if (!TextUtils.isEmpty(edFeedBack.getText().toString().trim())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("phone", SPUtils.getParam(FeedBackActivity.this, Constants.PHONE, ""));
                    map.put("content", edFeedBack.getText().toString().trim());
                    mFeedBackPresenter.saveFeedBack(map);
                } else {
                    ToastUtil.showToast(FeedBackActivity.this, "输入内容不能为空");
                }

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFeedBackSuccess(ResultCode resultCode) {
        ToastUtil.showToast(FeedBackActivity.this, resultCode.msg);

    }

    @Override
    public void showDialog() {
        dialog = new SweetAlertDialog(FeedBackActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText("意见反馈中");
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
}
