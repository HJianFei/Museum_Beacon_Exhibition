package com.hjianfei.museum_beacon_exhibition.view.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.utils.widget.CircleImageView;
import com.hjianfei.museum_beacon_exhibition.view.activity.collection.CollectionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalActivity extends AppCompatActivity implements PersonalView {

    @BindView(R.id.back_drop)
    ImageView backDrop;
    @BindView(R.id.personal_toolbar)
    Toolbar personalToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
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
    @BindView(R.id.personal_bg)
    CoordinatorLayout personalBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(personalToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        personalToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @OnClick({R.id.back_drop, R.id.personal_avatar_bg,
            R.id.personal_avatar, R.id.personal_name,
            R.id.tv_personal_love, R.id.tv_personal_share,
            R.id.tv_personal_change, R.id.tv_personal_change_password,
            R.id.tv_personal_setting, R.id.tv_app_share})
    public void onClickListener(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.back_drop:
                break;
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
                break;
            case R.id.tv_personal_setting:
                break;
            case R.id.tv_app_share:
                break;
        }
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
}
