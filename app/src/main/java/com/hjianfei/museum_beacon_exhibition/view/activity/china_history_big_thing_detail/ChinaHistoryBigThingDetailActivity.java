package com.hjianfei.museum_beacon_exhibition.view.activity.china_history_big_thing_detail;

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
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryBigThingDetail;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_big_thing_detail.ChinaHistoryBigThingDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_big_thing_detail.ChinaHistoryBigThingDetailPresenterImpl;
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

public class ChinaHistoryBigThingDetailActivity extends BaseActivity implements ChinaHistoryBigThingDetailView {

    @BindView(R.id.big_thing_bg)
    ImageView bigThingBg;
    @BindView(R.id.big_thing_toolbar)
    Toolbar bigThingToolbar;
    @BindView(R.id.big_thing_collapsing)
    CollapsingToolbarLayout bigThingCollapsing;
    @BindView(R.id.big_thing_title)
    TextView bigThingTitle;
    @BindView(R.id.big_thing_author)
    TextView bigThingAuthor;
    @BindView(R.id.big_thing_time)
    TextView bigThingTime;
    //    @BindView(R.id.big_thing_view)
//    TextView bigThingView;
    @BindView(R.id.big_thing_content)
    TextView big_thing_content;
    @BindView(R.id.big_thing_fab)
    FloatingActionButton bigThingFab;
    private ChinaHistoryBigThingDetailPresenter mPresenter;
    private String big_thing_title;
    private ChinaHistoryBigThingDetail mBigThingDetail;
    private SweetAlertDialog dialog;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_china_history_big_thing_detail);
        //淡入淡出
        getWindow().setEnterTransition(new Fade().setDuration(Constants.DURATION));
        getWindow().setReturnTransition(new Fade().setDuration(Constants.DURATION));
        big_thing_title = getIntent().getStringExtra("big_thing_title");
        id = getIntent().getStringExtra("id");

        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        mPresenter = new ChinaHistoryBigThingDetailPresenterImpl(this);
        mPresenter.getChinaHistoryBigThingDetail(id);
    }

    private void initView() {
        bigThingCollapsing.setTitle(big_thing_title);
        setSupportActionBar(bigThingToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bigThingToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void getChinaHistoryBigThingDetailFinished(ChinaHistoryBigThingDetail chinaHistoryBigThingDetail) {

        mBigThingDetail = chinaHistoryBigThingDetail;
        big_thing_content.setText(chinaHistoryBigThingDetail.getChina_History_Big_Thing_Detail().getContent());
        Glide.with(this).load(chinaHistoryBigThingDetail.getChina_History_Big_Thing_Detail().getImg_url()).placeholder(R.drawable.photo)
                .error(R.drawable.photo).into(bigThingBg);
        bigThingTitle.setText(chinaHistoryBigThingDetail.getChina_History_Big_Thing_Detail().getTitle());
        bigThingAuthor.setText("作者：" + chinaHistoryBigThingDetail.getChina_History_Big_Thing_Detail().getAuthor());
        bigThingTime.setText("时间：" + chinaHistoryBigThingDetail.getChina_History_Big_Thing_Detail().getTime());
//        bigThingView.setText(chinaHistoryBigThingDetail.getChina_History_Big_Thing_Detail().getViews());

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

    @OnClick({R.id.big_thing_fab, R.id.big_thing_bg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.big_thing_bg:
                Intent intent2 = new Intent(ChinaHistoryBigThingDetailActivity.this, PhotoDetailActivity.class);
                intent2.putExtra("img_urls", mBigThingDetail.getChina_History_Big_Thing_Detail().getImg_url());
                intent2.putExtra("photo_title", mBigThingDetail.getChina_History_Big_Thing_Detail().getTitle());
                startActivity(intent2, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

                break;
            case R.id.big_thing_fab:
                if (ContextCompat.checkSelfPermission(ChinaHistoryBigThingDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ChinaHistoryBigThingDetailActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.WRITE_EXTERNAL_CODE);
                } else {
                    doShare();
                }
                break;
        }
    }

    private void doShare() {
        new ShareAction(ChinaHistoryBigThingDetailActivity.this)
                .withTitle("博物展")
                .withText(mBigThingDetail.getChina_History_Big_Thing_Detail().getTitle())
                .withMedia(new UMImage(ChinaHistoryBigThingDetailActivity.this, mBigThingDetail.getChina_History_Big_Thing_Detail().getImg_url()))
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

            Toast.makeText(ChinaHistoryBigThingDetailActivity.this, "分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ChinaHistoryBigThingDetailActivity.this, "分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                LogUtils.d("throw", "throw:" + t.getMessage());
                ToastUtil.showToast(ChinaHistoryBigThingDetailActivity.this, "请允许使用SDCard权限");
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ChinaHistoryBigThingDetailActivity.this, "分享取消了", Toast.LENGTH_SHORT).show();
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


















