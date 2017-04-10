package com.hjianfei.museum_beacon_exhibition.view.activity.foreign_history_detail;

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
<<<<<<< HEAD
import android.transition.Fade;
=======
import android.transition.Slide;
>>>>>>> tmp
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.ForeignHistoryDetail;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.foreign_history_detail.ForeignCountryDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.foreign_history_detail.ForeignCountryDetailPresenterImpl;
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

public class ForeignCountryDetailActivity extends BaseActivity implements ForeignCountryDetailView, AppBarLayout.OnOffsetChangedListener {


    private static final int PERCENTAGE_TO_SHOW_IMAGE = 20;
    private int mMaxScrollSize;
    private boolean mIsImageHidden;
    @BindView(R.id.foreign_country_detail_toolbar)
    Toolbar foreignCountryDetailToolbar;
    @BindView(R.id.foreign_country_detail_bg_img)
    ImageView foreignCountryDetailBgImg;
    @BindView(R.id.foreign_country_detail_collapsing)
    CollapsingToolbarLayout foreignCountryDetailCollapsing;
    @BindView(R.id.foreign_country_detail_title)
    TextView foreignCountryDetailTitle;
    @BindView(R.id.foreign_country_detail_author)
    TextView foreignCountryDetailAuthor;
    @BindView(R.id.foreign_country_detail_time)
    TextView foreignCountryDetailTime;
    @BindView(R.id.foreign_country_detail_content)
    TextView foreignCountryDetailContent;
    @BindView(R.id.foreign_country_detail_fab)
    FloatingActionButton foreignCountryDetailFab;
    @BindView(R.id.foreign_country_detail_appbar)
    AppBarLayout foreignCountryDetailAppbar;
    private ForeignCountryDetailPresenter mPresenter;
    private String id;
    private String title;
    private SweetAlertDialog dialog;
    private String img_url;
    private ForeignHistoryDetail foreignDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_foreign_country_detail);
        //淡入淡出
        getWindow().setEnterTransition(new Fade().setDuration(Constants.DURATION));
        getWindow().setReturnTransition(new Fade().setDuration(Constants.DURATION));
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");

=======
        //滑动进入
        getWindow().setEnterTransition(new Slide().setDuration(Constants.DURATION));
        getWindow().setExitTransition(new Slide().setDuration(Constants.DURATION));
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        setContentView(R.layout.activity_foreign_country_detail);
>>>>>>> tmp
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        mPresenter = new ForeignCountryDetailPresenterImpl(this);
        mPresenter.getForeignCountryDetail(id);
    }

    private void initView() {
        setSupportActionBar(foreignCountryDetailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        foreignCountryDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        foreignCountryDetailAppbar.addOnOffsetChangedListener(this);
    }

    @Override
    public void onFinished(ForeignHistoryDetail foreignHistoryDetail) {
        foreignDetail = foreignHistoryDetail;
        foreignCountryDetailCollapsing.setTitle(title);
        foreignCountryDetailTitle.setText(foreignHistoryDetail.getForeign_History_Detail().getTitle());
        foreignCountryDetailAuthor.setText("作者：" + foreignHistoryDetail.getForeign_History_Detail().getAuthor());
        foreignCountryDetailTime.setText("时间：" + foreignHistoryDetail.getForeign_History_Detail().getTime());
        foreignCountryDetailContent.setText(foreignHistoryDetail.getForeign_History_Detail().getDetail());
<<<<<<< HEAD
        Glide.with(this).load(foreignHistoryDetail.getForeign_History_Detail().getImg_url()).placeholder(R.drawable.photo)
                .error(R.drawable.photo).into(foreignCountryDetailBgImg);
=======
        Glide.with(this).load(foreignHistoryDetail.getForeign_History_Detail().getImg_url()).into(foreignCountryDetailBgImg);
>>>>>>> tmp
        img_url = foreignHistoryDetail.getForeign_History_Detail().getImg_url();
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

    @OnClick({R.id.foreign_country_detail_bg_img, R.id.foreign_country_detail_fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.foreign_country_detail_bg_img:

                Intent intent2 = new Intent(ForeignCountryDetailActivity.this, PhotoDetailActivity.class);
                intent2.putExtra("img_urls", img_url);
                intent2.putExtra("photo_title", title);
                startActivity(intent2, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.foreign_country_detail_fab:
                if (ContextCompat.checkSelfPermission(ForeignCountryDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ForeignCountryDetailActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.WRITE_EXTERNAL_CODE);
                } else {
                    doShare();
                }
                break;
        }
    }

    private void doShare() {
        new ShareAction(ForeignCountryDetailActivity.this)
                .withTitle("博物展")
                .withText(foreignDetail.getForeign_History_Detail().getTitle())
                .withMedia(new UMImage(ForeignCountryDetailActivity.this, img_url))
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

            Toast.makeText(ForeignCountryDetailActivity.this, "分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ForeignCountryDetailActivity.this, "分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                LogUtils.d("throw", "throw:" + t.getMessage());
                ToastUtil.showToast(ForeignCountryDetailActivity.this, "请允许使用SDCard权限");
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ForeignCountryDetailActivity.this, "分享取消了", Toast.LENGTH_SHORT).show();
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

                ViewCompat.animate(foreignCountryDetailFab).scaleY(0).scaleX(0).start();
            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            if (mIsImageHidden) {
                mIsImageHidden = false;
                ViewCompat.animate(foreignCountryDetailFab).scaleY(1).scaleX(1).start();
            }
        }
    }
}
