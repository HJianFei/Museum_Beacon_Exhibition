package com.hjianfei.museum_beacon_exhibition.view.activity.china_history_culture_detail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryCultureDetail;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_culture_detail.ChinaHistoryCultureDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_culture_detail.ChinaHistoryCultureDetailPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ChinaHistoryCultureDetailActivity extends AppCompatActivity implements ChinaHistoryCultureDetailView {

    @BindView(R.id.history_culture_bg)
    ImageView historyCultureBg;
    @BindView(R.id.history_culture_toolbar)
    Toolbar historyCultureToolbar;
    @BindView(R.id.history_culture_collapsing)
    CollapsingToolbarLayout historyCultureCollapsing;
    @BindView(R.id.history_culture_title)
    TextView historyCultureTitle;
    @BindView(R.id.history_culture_content)
    TextView historyCultureContent;
    @BindView(R.id.history_culture_fab)
    FloatingActionButton historyCultureFab;
    @BindView(R.id.history_culture_author)
    TextView historyCultureAuthor;
    @BindView(R.id.history_culture_time)
    TextView historyCultureTime;
    private ChinaHistoryCultureDetailPresenter mPresenter;
    private String title;
    private String author;
    private String time;
    private ChinaHistoryCultureDetail mCultureDetail;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getIntent().getStringExtra("title");
        author = getIntent().getStringExtra("author");
        time = getIntent().getStringExtra("time");
        setContentView(R.layout.activity_china_history_culture_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        historyCultureCollapsing.setTitle(title);
        setSupportActionBar(historyCultureToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        historyCultureToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    private void initData() {
        mPresenter = new ChinaHistoryCultureDetailPresenterImpl(this);
        mPresenter.getChinaHistoryCultureDetail(title);
    }

    @Override
    public void onFinished(ChinaHistoryCultureDetail chinaHistoryCultureDetail) {

        mCultureDetail = chinaHistoryCultureDetail;
        historyCultureAuthor.setText(author);
        historyCultureTime.setText(time);
        Glide.with(this).load(chinaHistoryCultureDetail.getChina_History_Culture_Detail().getImg_url()).into(historyCultureBg);
        historyCultureCollapsing.setTitle(chinaHistoryCultureDetail.getChina_History_Culture_Detail().getTitle());
        historyCultureTitle.setText(chinaHistoryCultureDetail.getChina_History_Culture_Detail().getTitle());
        historyCultureContent.setText(chinaHistoryCultureDetail.getChina_History_Culture_Detail().getContent());

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

    @OnClick({R.id.history_culture_bg, R.id.history_culture_fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.history_culture_bg:
                Intent intent2 = new Intent(ChinaHistoryCultureDetailActivity.this, PhotoDetailActivity.class);
                intent2.putExtra("img_urls", mCultureDetail.getChina_History_Culture_Detail().getImg_url());
                intent2.putExtra("photo_title", mCultureDetail.getChina_History_Culture_Detail().getTitle());
                startActivity(intent2);
                break;
            case R.id.history_culture_fab:
                if (ContextCompat.checkSelfPermission(ChinaHistoryCultureDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ChinaHistoryCultureDetailActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.WRITE_EXTERNAL_CODE);
                } else {
                    doShare();
                }
                break;
        }
    }

    private void doShare() {
        new ShareAction(ChinaHistoryCultureDetailActivity.this)
                .withTitle("博物展")
                .withText(mCultureDetail.getChina_History_Culture_Detail().getTitle())
                .withMedia(new UMImage(ChinaHistoryCultureDetailActivity.this, mCultureDetail.getChina_History_Culture_Detail().getImg_url()))
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

            Toast.makeText(ChinaHistoryCultureDetailActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ChinaHistoryCultureDetailActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                LogUtils.d("throw", "throw:" + t.getMessage());
                ToastUtil.showToast(ChinaHistoryCultureDetailActivity.this, "请允许使用SDCard权限");
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ChinaHistoryCultureDetailActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
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
