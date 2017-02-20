package com.hjianfei.museum_beacon_exhibition.view.activity.change_password;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.user_info.UserInfoPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.user_info.UserInfoPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.SPUtils;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ChangePasswordActivity extends BaseActivity implements ChangePasswordView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ed_old_password)
    EditText edOldPassword;
    @BindView(R.id.ed_new_password)
    EditText edNewPassword;
    @BindView(R.id.ed_new_password_repeat)
    EditText edNewPasswordRepeat;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.activity_change_password)
    LinearLayout activityChangePassword;
    private UserInfoPresenter mUserInfoPresenter;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //淡入淡出
        getWindow().setEnterTransition(new Slide().setDuration(Constants.DURATION));
        getWindow().setExitTransition(new Slide().setDuration(Constants.DURATION));
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle("修改密码");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @OnClick(R.id.btn_ok)
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                if (TextUtils.isEmpty(edOldPassword.getText().toString().trim())) {
                    ToastUtil.showToast(ChangePasswordActivity.this, "旧密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(edNewPassword.getText().toString().trim()) || TextUtils.isEmpty(edNewPassword.getText().toString().trim())) {
                    ToastUtil.showToast(ChangePasswordActivity.this, "新密码不能为空");
                    return;
                }
                if (!edNewPassword.getText().toString().trim().equals(edNewPasswordRepeat.getText().toString().trim())) {
                    ToastUtil.showToast(ChangePasswordActivity.this, "密码输入不一致");
                    return;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("phone", SPUtils.getParam(ChangePasswordActivity.this, Constants.PHONE, ""));
                map.put("old_password", edOldPassword.getText().toString().trim());
                map.put("new_password", edNewPassword.getText().toString().trim());
                mUserInfoPresenter = new UserInfoPresenterImpl(this);
                mUserInfoPresenter.changePassword(map);
                break;
        }
    }

    @Override
    public void changePasswordSuccess(ResultCode resultCode) {
        ToastUtil.showToast(ChangePasswordActivity.this, resultCode.msg);
        this.finish();
    }

    @Override
    public void showDialog() {
        dialog = new SweetAlertDialog(ChangePasswordActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText("正在修改");
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
}
