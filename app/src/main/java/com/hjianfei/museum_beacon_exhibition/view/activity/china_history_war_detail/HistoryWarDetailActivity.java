package com.hjianfei.museum_beacon_exhibition.view.activity.china_history_war_detail;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.HistoryWarDetail;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_war_detail.HistoryWarDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_war_detail.HistoryWarDetailPresenterImpl;
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


public class HistoryWarDetailActivity extends BaseActivity implements HistoryWarDetailView, AppBarLayout.OnOffsetChangedListener {

    private static final int PERCENTAGE_TO_SHOW_IMAGE = 20;
    private int mMaxScrollSize;
    private boolean mIsImageHidden;
    @BindView(R.id.history_war_detail_toolbar)
    Toolbar historyWarDetailToolbar;
    @BindView(R.id.history_war_detail_bg_img)
    ImageView historyWarDetailBgImg;
    @BindView(R.id.history_war_detail_collapsing)
    CollapsingToolbarLayout historyWarDetailCollapsing;
    @BindView(R.id.history_war_detail_appbar)
    AppBarLayout historyWarDetailAppbar;
    @BindView(R.id.history_war_detail_army)
    TextView historyWarDetailArmy;
    @BindView(R.id.history_war_detail_name)
    TextView historyWarDetailName;
    @BindView(R.id.history_war_detail_result)
    TextView historyWarDetailResult;
    @BindView(R.id.history_war_detail_time)
    TextView historyWarDetailTime;
    @BindView(R.id.history_war_detail_address)
    TextView historyWarDetailAddress;
    @BindView(R.id.history_war_detail_figure)
    TextView historyWarDetailFigure;
    @BindView(R.id.history_war_detail_content)
    TextView historyWarDetailContent;
    @BindView(R.id.history_war_detail_fab)
    FloatingActionButton historyWarDetailFab;
    private HistoryWarDetailPresenter mPresenter;
    private String id;
    private String img_url;
    private String title;
    private SweetAlertDialog dialog;
    private HistoryWarDetail historyDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_war_detail);
        //淡入淡出
        getWindow().setEnterTransition(new Fade().setDuration(Constants.DURATION));
        getWindow().setReturnTransition(new Fade().setDuration(Constants.DURATION));
        id = getIntent().getStringExtra("id");
        img_url = getIntent().getStringExtra("img_url");
        title = getIntent().getStringExtra("title");


        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        setSupportActionBar(historyWarDetailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        historyWarDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        historyWarDetailAppbar.addOnOffsetChangedListener(this);
        Glide.with(this).load(img_url).placeholder(R.drawable.photo)
                .error(R.drawable.photo).into(historyWarDetailBgImg);

    }

    private void initData() {
        mPresenter = new HistoryWarDetailPresenterImpl(this);
        mPresenter.getHistoryWarDetail(id);
    }

    @Override
    public void onFinished(HistoryWarDetail historyWarDetail) {
        historyDetail = historyWarDetail;
        historyWarDetailCollapsing.setTitle(title);
        historyWarDetailName.setText(historyWarDetail.getChina_History_Olden_War_Detail().getName());
        historyWarDetailArmy.setText(historyWarDetail.getChina_History_Olden_War_Detail().getArmy());
        historyWarDetailResult.setText(historyWarDetail.getChina_History_Olden_War_Detail().getResult());
        historyWarDetailTime.setText(historyWarDetail.getChina_History_Olden_War_Detail().getTime());
        historyWarDetailAddress.setText(historyWarDetail.getChina_History_Olden_War_Detail().getAddress());
        historyWarDetailFigure.setText(historyWarDetail.getChina_History_Olden_War_Detail().getFigure());
        historyWarDetailContent.setText(historyWarDetail.getChina_History_Olden_War_Detail().getWar_detail());
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

    @OnClick({R.id.history_war_detail_bg_img, R.id.history_war_detail_fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.history_war_detail_bg_img:
                Intent intent2 = new Intent(HistoryWarDetailActivity.this, PhotoDetailActivity.class);
                intent2.putExtra("img_urls", img_url);
                intent2.putExtra("photo_title", title);
                startActivity(intent2, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.history_war_detail_fab:
                if (ContextCompat.checkSelfPermission(HistoryWarDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(HistoryWarDetailActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.WRITE_EXTERNAL_CODE);
                } else {
                    doShare();
                }
                break;
        }
    }

    private void doShare() {
        new ShareAction(HistoryWarDetailActivity.this)
                .withTitle("博物展")
                .withText(historyDetail.getChina_History_Olden_War_Detail().getName())
                .withMedia(new UMImage(HistoryWarDetailActivity.this, img_url))
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

            Toast.makeText(HistoryWarDetailActivity.this, "分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(HistoryWarDetailActivity.this, "分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                LogUtils.d("throw", "throw:" + t.getMessage());
                ToastUtil.showToast(HistoryWarDetailActivity.this, "请允许使用SDCard权限");
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(HistoryWarDetailActivity.this, "分享取消了", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int currentScrollPercentage = (Math.abs(verticalOffset)) * 100
                / mMaxScrollSize;

        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
            if (!mIsImageHidden) {
                mIsImageHidden = true;

                ViewCompat.animate(historyWarDetailFab).scaleY(0).scaleX(0).start();
            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            if (mIsImageHidden) {
                mIsImageHidden = false;
                ViewCompat.animate(historyWarDetailFab).scaleY(1).scaleX(1).start();
            }
        }
    }
}
