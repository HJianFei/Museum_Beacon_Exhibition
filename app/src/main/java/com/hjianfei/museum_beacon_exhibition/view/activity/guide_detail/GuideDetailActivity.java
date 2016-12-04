package com.hjianfei.museum_beacon_exhibition.view.activity.guide_detail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.canstants.Urls;
import com.hjianfei.museum_beacon_exhibition.presenter.base.BasePresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.base.BasePresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.ImageVideoThumbUtils;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.SPUtils;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.utils.widget.CustomVideoView;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hjianfei.museum_beacon_exhibition.R.id.video_item;


public class GuideDetailActivity extends AppCompatActivity implements GuideDetailView {


    @BindView(R.id.guide_video_view)
    CustomVideoView guideVideoView;
    @BindView(R.id.rl_vv)
    RelativeLayout rlVv;
    @BindView(R.id.guide_detail_title)
    TextView guideDetailTitle;
    @BindView(R.id.guide_detail_content)
    TextView guideDetailContent;
    @BindView(R.id.guide_detail_card_view)
    CardView guideDetailCardView;
    @BindView(R.id.guide_detail_scroll_view)
    ScrollView guide_detail_scroll_view;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.video_item)
    ImageView videoItem;
    private BeaconAppreciate beaconAppreciate;
    private Bitmap thumbnail;
    private BasePresenter mBasePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beaconAppreciate = (BeaconAppreciate) getIntent().getBundleExtra("guide_detail").getSerializable("beaconAppreciate");
        setContentView(R.layout.activity_guide_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mBasePresenter = new BasePresenterImpl(this);
        toolbar.setTitle(beaconAppreciate.getBeaconAppreciate().getTitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        guideDetailTitle.setText(beaconAppreciate.getBeaconAppreciate().getTitle());
        guideDetailContent.setText(beaconAppreciate.getBeaconAppreciate().getContent());
        Uri uri = Uri.parse(Urls.API_SERVER + beaconAppreciate.getBeaconAppreciate().getVideo_url());

        guideVideoView.setMediaController(new MediaController(this));
        guideVideoView.setVideoURI(uri);
        // 播放在线视频
        guideVideoView.setVideoPath(uri.toString());
        guideVideoView.requestFocus();
        guideVideoView.setPlayPauseListener(playPauseListener());
        guideVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoItem.setVisibility(View.VISIBLE);

            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                thumbnail = ImageVideoThumbUtils.createVideoThumbnail(Urls.API_SERVER + beaconAppreciate.getBeaconAppreciate().getVideo_url(), 320);

            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.beacon_appreciate_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.guide_detail_image:
                Intent intent = new Intent(this, PhotoDetailActivity.class);
                intent.putExtra("img_urls", beaconAppreciate.getBeaconAppreciate().getImg_url());
                intent.putExtra("photo_title", beaconAppreciate.getBeaconAppreciate().getTitle());
                startActivity(intent);
                break;
            case R.id.guide_detail_share:
                if (ContextCompat.checkSelfPermission(GuideDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(GuideDetailActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.WRITE_EXTERNAL_CODE);
                } else {
                    doShare();
                }
                break;
            case R.id.guide_detail_love:
                String phone = (String) SPUtils.getParam(GuideDetailActivity.this, Constants.PHONE, "");
                Map<String, Object> map = new HashMap<>();
                map.put("user_phone", phone);
                map.put("post_id", beaconAppreciate.getBeaconAppreciate().getTitle());
                map.put("post_type", "文物鉴赏");
                map.put("img_url", beaconAppreciate.getBeaconAppreciate().getImg_url());
                map.put("detail_url", beaconAppreciate.getBeaconAppreciate().getMinor());
                mBasePresenter.onSaveCollection(map);
                break;
        }
        return true;

    }

    @NonNull
    private CustomVideoView.PlayPauseListener playPauseListener() {
        return new CustomVideoView.PlayPauseListener() {
            @Override
            public void onPlay() {
                videoItem.setVisibility(View.GONE);
            }

            @Override
            public void onPause() {
                videoItem.setVisibility(View.VISIBLE);

            }
        };
    }

    @OnClick(video_item)
    public void onClickListener(View v) {
        switch (v.getId()) {
            case video_item:
                videoItem.setVisibility(View.GONE);
                guideVideoView.start();
                break;
        }
    }

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void doShare() {
        UMVideo video = new UMVideo(Urls.API_SERVER + beaconAppreciate.getBeaconAppreciate().getVideo_url());
//        video.setTitle(beaconAppreciate.getBeaconAppreciate().getTitle());//视频的标题
//        video.setDescription("my description");//视频的描述
        video.setThumb(new UMImage(GuideDetailActivity.this, thumbnail));
        new ShareAction(GuideDetailActivity.this)
                .withTitle(beaconAppreciate.getBeaconAppreciate().getTitle())
                .withText(beaconAppreciate.getBeaconAppreciate().getContent())
                .withMedia(video)
                .withTargetUrl(Urls.API_SERVER + beaconAppreciate.getBeaconAppreciate().getVideo_url())
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(umShareListener).open();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            LogUtils.d("plat", "platform" + platform);

            Toast.makeText(GuideDetailActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(GuideDetailActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                LogUtils.d("throw", "throw:" + t.getMessage());
                ToastUtil.showToast(GuideDetailActivity.this, "请允许使用SDCard权限");
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(GuideDetailActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onSaveCollectionSuccess(ResultCode resultCode) {

        ToastUtil.showToast(GuideDetailActivity.this, resultCode.msg);
    }
}
