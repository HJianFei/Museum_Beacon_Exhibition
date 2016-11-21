package com.hjianfei.museum_beacon_exhibition.view.activity.personal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.utils.widget.CircleImageView;
import com.hjianfei.museum_beacon_exhibition.view.activity.change_password.ChangePasswordActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.setting.SettingActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.collection.CollectionActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalActivity extends AppCompatActivity implements PersonalView {

    @BindView(R.id.toolbar)
    Toolbar personalToolbar;
    @BindView(R.id.personal_avatar)
    CircleImageView personalAvatar;
    @BindView(R.id.personal_name)
    TextView personalName;
    @BindView(R.id.personal_avatar_bg)
    LinearLayout personalAvatarBg;
    @BindView(R.id.tv_personal_love)
    TextView tvPersonalLove;
    @BindView(R.id.tv_personal_share)
    TextView tvPersonalShare;
    @BindView(R.id.tv_personal_change)
    TextView tvPersonalChange;
    @BindView(R.id.tv_personal_change_password)
    TextView tvPersonalChangePassword;
    @BindView(R.id.tv_personal_setting)
    TextView tvPersonalSetting;
    @BindView(R.id.tv_app_share)
    TextView tvAppShare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        personalToolbar.setTitle("我的");
        setSupportActionBar(personalToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        personalToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @OnClick({R.id.personal_avatar_bg,
            R.id.personal_avatar, R.id.personal_name,
            R.id.tv_personal_love, R.id.tv_personal_share,
            R.id.tv_personal_change, R.id.tv_personal_change_password,
            R.id.tv_personal_setting, R.id.tv_app_share})
    public void onClickListener(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.personal_avatar_bg:
                break;
            case R.id.personal_avatar:
                break;
            case R.id.personal_name:
                break;
            case R.id.tv_personal_love:
                intent = new Intent(PersonalActivity.this, CollectionActivity.class);
                startActivity(intent);

                break;
            case R.id.tv_personal_share:

                break;
            case R.id.tv_personal_change:
                break;
            case R.id.tv_personal_change_password:
                intent = new Intent(PersonalActivity.this, ChangePasswordActivity.class);
                startActivity(intent);

                break;
            case R.id.tv_personal_setting:
                intent = new Intent(PersonalActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_app_share:
                if (ContextCompat.checkSelfPermission(PersonalActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PersonalActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.WRITE_EXTERNAL_CODE);
                } else {
                    doShare();
                }
                break;
        }
    }

    private void doShare() {
        new ShareAction(PersonalActivity.this)
                .withTitle("博物展")
                .withText("博物展，让沉睡千年的文物‘动’起来")
                .withMedia(new UMImage(this, R.mipmap.logo))
                .withTargetUrl("http://fir.im/fzt8")
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(umShareListener).open();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            LogUtils.d("plat", "platform" + platform);

            Toast.makeText(PersonalActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(PersonalActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                LogUtils.d("throw", "throw:" + t.getMessage());
                ToastUtil.showToast(PersonalActivity.this, "请允许使用SDCard权限");
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(PersonalActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
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
