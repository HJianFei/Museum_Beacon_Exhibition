package com.hjianfei.museum_beacon_exhibition.view.activity.china_history_people_detail;

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
import com.hjianfei.museum_beacon_exhibition.bean.HistoryPeopleDetail;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_people_detail.ChinaHistoryPeopleDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_people_detail.ChinaHistoryPeopleDetailPresenterImpl;
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

public class ChinaHistoryPeopleDetailActivity extends AppCompatActivity implements ChinaHistoryPeopleDetailView {

    @BindView(R.id.history_people_bg)
    ImageView historyPeopleBg;
    @BindView(R.id.history_people_toolbar)
    Toolbar historyPeopleToolbar;
    @BindView(R.id.history_people_collapsing)
    CollapsingToolbarLayout historyPeopleCollapsing;
    @BindView(R.id.history_people_name)
    TextView historyPeopleName;
    @BindView(R.id.history_people_content)
    TextView historyPeopleContent;
    @BindView(R.id.history_people_fab)
    FloatingActionButton historyPeopleFab;
    private ChinaHistoryPeopleDetailPresenter mPeopleDetailPresenter;
    private String id;
    private String img_url;
    private HistoryPeopleDetail mHistoryPeopleDetail;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra("id");
        img_url = getIntent().getStringExtra("img_url");
        setContentView(R.layout.activity_china_history_people_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        mPeopleDetailPresenter = new ChinaHistoryPeopleDetailPresenterImpl(this);
        mPeopleDetailPresenter.getHistoryPeopleDetail(id);
    }

    private void initView() {
        setSupportActionBar(historyPeopleToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        historyPeopleToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Glide.with(this).load(img_url).into(historyPeopleBg);

    }

    @Override
    public void onFinished(HistoryPeopleDetail historyPeopleDetail) {
        mHistoryPeopleDetail = historyPeopleDetail;
        historyPeopleContent.setText(historyPeopleDetail.getChina_History_People_Detail().getContent());
        historyPeopleCollapsing.setTitle(historyPeopleDetail.getChina_History_People_Detail().getName());
        historyPeopleName.setText(historyPeopleDetail.getChina_History_People_Detail().getName());
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

    @OnClick({R.id.history_people_bg, R.id.history_people_fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.history_people_bg:

                Intent intent2 = new Intent(ChinaHistoryPeopleDetailActivity.this, PhotoDetailActivity.class);
                intent2.putExtra("img_urls", img_url);
                intent2.putExtra("photo_title", mHistoryPeopleDetail.getChina_History_People_Detail().getName());
                startActivity(intent2);
                break;
            case R.id.history_people_fab:

                if (ContextCompat.checkSelfPermission(ChinaHistoryPeopleDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ChinaHistoryPeopleDetailActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.WRITE_EXTERNAL_CODE);
                } else {
                    doShare();
                }
                break;
        }
    }

    private void doShare() {
        new ShareAction(ChinaHistoryPeopleDetailActivity.this)
                .withTitle("博物展")
                .withText(mHistoryPeopleDetail.getChina_History_People_Detail().getName())
                .withMedia(new UMImage(ChinaHistoryPeopleDetailActivity.this, img_url))
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

            Toast.makeText(ChinaHistoryPeopleDetailActivity.this, "分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ChinaHistoryPeopleDetailActivity.this, "分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                LogUtils.d("throw", "throw:" + t.getMessage());
                ToastUtil.showToast(ChinaHistoryPeopleDetailActivity.this, "请允许使用SDCard权限");
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ChinaHistoryPeopleDetailActivity.this, "分享取消了", Toast.LENGTH_SHORT).show();
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
