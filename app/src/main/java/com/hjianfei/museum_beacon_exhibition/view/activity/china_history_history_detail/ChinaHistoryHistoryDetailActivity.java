package com.hjianfei.museum_beacon_exhibition.view.activity.china_history_history_detail;

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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHistoryDetail;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_history_detail.ChinaHistoryHistoryDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.china_history_history_detail.ChinaHistoryHistoryDetailPresenterImpl;
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

public class ChinaHistoryHistoryDetailActivity extends AppCompatActivity implements ChinaHistoryHistoryDetailView {

    @BindView(R.id.history_history_bg)
    ImageView historyHistoryBg;
    @BindView(R.id.history_history_toolbar)
    Toolbar historyHistoryToolbar;
    @BindView(R.id.history_history_collapsing)
    CollapsingToolbarLayout historyHistoryCollapsing;
    @BindView(R.id.history_history_title)
    TextView historyHistoryTitle;
    @BindView(R.id.history_history_author)
    TextView historyHistoryAuthor;
    @BindView(R.id.history_history_time)
    TextView historyHistoryTime;
    @BindView(R.id.history_history_content)
    TextView historyHistoryContent;
    @BindView(R.id.history_history_fab)
    FloatingActionButton historyHistoryFab;
    private ChinaHistoryHistoryDetailPresenter mPresenter;
    private String id;
    private String title;
    private String author;
    private String time;
    private ChinaHistoryHistoryDetail mHistoryDetail;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //滑动进入
        getWindow().setEnterTransition(new Slide().setDuration(Constants.DURATION));
        getWindow().setExitTransition(new Slide().setDuration(Constants.DURATION));
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        author = getIntent().getStringExtra("author");
        time = getIntent().getStringExtra("time");
        setContentView(R.layout.activity_china_history_history_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        historyHistoryCollapsing.setTitle(title);
        setSupportActionBar(historyHistoryToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        historyHistoryToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initData() {
        mPresenter = new ChinaHistoryHistoryDetailPresenterImpl(this);
        mPresenter.getChinaHistoryHistoryDetail(id);
    }

    @Override
    public void onFinished(ChinaHistoryHistoryDetail chinaHistoryHistoryDetail) {

        mHistoryDetail = chinaHistoryHistoryDetail;
        historyHistoryAuthor.setText(author);
        historyHistoryTime.setText(time);
        Glide.with(this).load(chinaHistoryHistoryDetail.getChina_History_History_Detail().getImg_url()).into(historyHistoryBg);
        historyHistoryTitle.setText(chinaHistoryHistoryDetail.getChina_History_History_Detail().getTitle());
        historyHistoryContent.setText(chinaHistoryHistoryDetail.getChina_History_History_Detail().getContent());
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

    @OnClick({R.id.history_history_bg, R.id.history_history_fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.history_history_bg:
                Intent intent2 = new Intent(ChinaHistoryHistoryDetailActivity.this, PhotoDetailActivity.class);
                intent2.putExtra("img_urls", mHistoryDetail.getChina_History_History_Detail().getImg_url());
                intent2.putExtra("photo_title", mHistoryDetail.getChina_History_History_Detail().getTitle());
                startActivity(intent2, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

                break;
            case R.id.history_history_fab:
                if (ContextCompat.checkSelfPermission(ChinaHistoryHistoryDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ChinaHistoryHistoryDetailActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.WRITE_EXTERNAL_CODE);
                } else {
                    doShare();
                }
                break;
        }
    }
    private void doShare() {
        new ShareAction(ChinaHistoryHistoryDetailActivity.this)
                .withTitle("博物展")
                .withText(mHistoryDetail.getChina_History_History_Detail().getTitle())
                .withMedia(new UMImage(ChinaHistoryHistoryDetailActivity.this, mHistoryDetail.getChina_History_History_Detail().getImg_url()))
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

            Toast.makeText(ChinaHistoryHistoryDetailActivity.this, "分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ChinaHistoryHistoryDetailActivity.this, "分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                LogUtils.d("throw", "throw:" + t.getMessage());
                ToastUtil.showToast(ChinaHistoryHistoryDetailActivity.this, "请允许使用SDCard权限");
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ChinaHistoryHistoryDetailActivity.this,"分享取消了", Toast.LENGTH_SHORT).show();
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
