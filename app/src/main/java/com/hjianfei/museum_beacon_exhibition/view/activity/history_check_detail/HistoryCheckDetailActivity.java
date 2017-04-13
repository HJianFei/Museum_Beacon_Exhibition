package com.hjianfei.museum_beacon_exhibition.view.activity.history_check_detail;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.HistoryCheckDetail;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.history_check_detail.HistoryCheckDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.history_check_detail.HistoryCheckDetailPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailActivity;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class HistoryCheckDetailActivity extends BaseActivity implements HistoryCheckDetailView {

    @BindView(R.id.history_check_detail_bg)
    ImageView historyCheckDetailBg;
    @BindView(R.id.history_check_detail_toolbar)
    Toolbar historyCheckDetailToolbar;
    @BindView(R.id.history_check_detail_collapsing)
    CollapsingToolbarLayout historyCheckDetailCollapsing;
    @BindView(R.id.history_check_detail_title)
    TextView historyCheckDetailTitle;
    @BindView(R.id.history_check_detail_content)
    TextView historyCheckDetailContent;
    @BindView(R.id.history_check_detail_fab)
    FloatingActionButton historyCheckDetailFab;
    private HistoryCheckDetailPresenter mPresenter;
    private String id;
    private String title;
    private HistoryCheckDetail mCheckDetail;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_check_detail);
        //淡入淡出
        getWindow().setEnterTransition(new Fade().setDuration(Constants.DURATION));
        getWindow().setReturnTransition(new Fade().setDuration(Constants.DURATION));
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");

        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        historyCheckDetailCollapsing.setTitle(title);
        setSupportActionBar(historyCheckDetailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        historyCheckDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initData() {
        mPresenter = new HistoryCheckDetailPresenterImpl(this);
        mPresenter.getHistoryHCheckDetail(id);
    }

    @Override
    public void onFinished(HistoryCheckDetail historyCheckDetail) {
        mCheckDetail = historyCheckDetail;
        historyCheckDetailTitle.setText(historyCheckDetail.getCheck_Detail().getTitle());
        historyCheckDetailContent.setText(historyCheckDetail.getCheck_Detail().getDetail());
        Glide.with(this)
                .load(historyCheckDetail.getCheck_Detail().getImg_url())
                .placeholder(R.drawable.photo)
                .error(R.drawable.photo)
                .into(historyCheckDetailBg);



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

    @OnClick({R.id.history_check_detail_bg, R.id.history_check_detail_fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.history_check_detail_bg:
                Intent intent2 = new Intent(HistoryCheckDetailActivity.this, PhotoDetailActivity.class);
                intent2.putExtra("img_urls", mCheckDetail.getCheck_Detail().getImg_url());
                intent2.putExtra("photo_title", title);
                startActivity(intent2, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.history_check_detail_fab:
                if (ContextCompat.checkSelfPermission(HistoryCheckDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(HistoryCheckDetailActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.WRITE_EXTERNAL_CODE);
                } else {
                    doShare();
                }
                break;
        }
    }

    private void doShare() {
        new ShareAction(HistoryCheckDetailActivity.this)
                .withTitle("博物展")
                .withText(mCheckDetail.getCheck_Detail().getTitle())
                .withMedia(new UMImage(HistoryCheckDetailActivity.this, mCheckDetail.getCheck_Detail().getImg_url()))
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(umShareListener).open();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            LogUtils.d("plat", "platform" + platform);

            Toast.makeText(HistoryCheckDetailActivity.this, "分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(HistoryCheckDetailActivity.this, "分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                LogUtils.d("throw", "throw:" + t.getMessage());
                ToastUtil.showToast(HistoryCheckDetailActivity.this, "请允许使用SDCard权限");
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(HistoryCheckDetailActivity.this, " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case Constants.WRITE_EXTERNAL_CODE:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    ToastUtil.showToast(this, "请允许使用SDCard权限");
                } else {
                    doShare();
                }
                break;
        }
    }


}
