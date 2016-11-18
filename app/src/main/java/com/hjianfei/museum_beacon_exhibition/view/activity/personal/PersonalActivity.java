package com.hjianfei.museum_beacon_exhibition.view.activity.personal;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.utils.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

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
