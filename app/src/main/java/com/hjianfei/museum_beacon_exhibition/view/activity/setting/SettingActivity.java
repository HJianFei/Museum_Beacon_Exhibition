package com.hjianfei.museum_beacon_exhibition.view.activity.setting;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.any_event.Logout;
import com.hjianfei.museum_beacon_exhibition.bean.UpdateInfo;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.app_update_info.AppUpdateInfoPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.app_update_info.AppUpdateInfoPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.DeviceUtils;
import com.hjianfei.museum_beacon_exhibition.utils.SPUtils;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.utils.fileload.DownLoadService;
import com.hjianfei.museum_beacon_exhibition.view.activity.about_me.AboutMeActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.feedback.FeedBackActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.login.LoginActivity;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class SettingActivity extends BaseActivity implements SettingView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.version_update)
    TextView versionUpdate;
    @BindView(R.id.clear_cache)
    TextView clearCache;
    @BindView(R.id.help_and_feedback)
    TextView helpAndFeedback;
    @BindView(R.id.about_me)
    TextView aboutMe;
    @BindView(R.id.btn_exit)
    Button btnExit;
    @BindView(R.id.activity_setting)
    LinearLayout activitySetting;
    private SweetAlertDialog dialog;
    private AppUpdateInfoPresenter mAppUpdateInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //滑动进入
        getWindow().setEnterTransition(new Slide().setDuration(Constants.DURATION));
        getWindow().setExitTransition(new Slide().setDuration(Constants.DURATION));
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @OnClick({R.id.version_update, R.id.clear_cache, R.id.help_and_feedback, R.id.about_me, R.id.btn_exit})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.version_update:
                mAppUpdateInfoPresenter = new AppUpdateInfoPresenterImpl(this);
                mAppUpdateInfoPresenter.getAppUpdateInfo();
                break;
            case R.id.clear_cache:
                dialog = new SweetAlertDialog(SettingActivity.this, SweetAlertDialog.WARNING_TYPE);
                dialog.setTitleText("确定清除缓存？");
                dialog.setConfirmText("确定");
                dialog.showCancelButton(true);
                dialog.setCancelText("取消");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        if (null != dialog) {
                            dialog.dismiss();
                        }
                        ToastUtil.showToast(SettingActivity.this, "缓存清除成功");

                    }
                });

                dialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        if (null != dialog) {
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
                break;
            case R.id.help_and_feedback:
                Intent intent = new Intent(SettingActivity.this, FeedBackActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.about_me:
                Intent intent1 = new Intent(SettingActivity.this, AboutMeActivity.class);
                startActivity(intent1, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.btn_exit:
                dialog = new SweetAlertDialog(SettingActivity.this, SweetAlertDialog.WARNING_TYPE);
                dialog.setTitleText("确定退出登录？");
                dialog.setConfirmText("确定");
                dialog.showCancelButton(true);
                dialog.setCancelText("取消");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        SPUtils.setParam(SettingActivity.this, Constants.PHONE, "");
                        SPUtils.setParam(SettingActivity.this, Constants.NAME, "");
                        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SettingActivity.this).toBundle());
                        EventBus.getDefault().post(new Logout());
                        finish();

                    }
                });

                dialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        if (null != dialog) {
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
                break;
        }
    }

    @Override
    public void showDialog() {
        dialog = new SweetAlertDialog(SettingActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText("正在检测新版本");
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

    @Override
    public void getUpdateInfoFinished(UpdateInfo updateInfo) {
        int mVersion_code = DeviceUtils.getVersionCode(SettingActivity.this);// 当前的版本号
        int nVersion_code = Integer.parseInt(updateInfo.getAppUpdateInfo().getCurVersion());
        if (mVersion_code < nVersion_code) {
            // 显示提示对话
            showNoticeDialog(updateInfo);
        } else {

            ToastUtil.showToast(SettingActivity.this, "已经是最新版本");

        }
    }

    private void showNoticeDialog(final UpdateInfo updateInfo) {

        dialog = new SweetAlertDialog(SettingActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        dialog.setTitleText("发现新版本");
        dialog.setContentText("最新版本号：" + updateInfo.getAppUpdateInfo().getCurVersion() + "\n" + "更新内容：" + updateInfo.getAppUpdateInfo().getDescription() + "\n" + "最低兼容版本：" + updateInfo.getAppUpdateInfo().getMinVersion());
        dialog.setCustomImage(R.drawable.icon_update);
        dialog.showCancelButton(true);
        dialog.setConfirmText("马上更新");
        dialog.setCancelText("以后更新");
        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                System.out.println("here");
                if (null != dialog) {
                    dialog.dismiss();
                }
                Intent intent = new Intent(SettingActivity.this, DownLoadService.class);
                intent.putExtra("app_name", updateInfo.getAppUpdateInfo().getAppURL());
                startService(intent);
            }
        });
        dialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                if (null != dialog) {
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
}
