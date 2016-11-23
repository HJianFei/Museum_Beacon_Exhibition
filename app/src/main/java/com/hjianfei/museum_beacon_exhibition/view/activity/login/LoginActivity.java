package com.hjianfei.museum_beacon_exhibition.view.activity.login;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.LoginResult;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.login.LoginPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.login.LoginPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.SPUtils;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.view.activity.main.MainActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.register.RegisterActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.splash.SplashActivity;
import com.stephentuso.welcome.WelcomeScreenHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class LoginActivity extends AppCompatActivity implements LoginView {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_go)
    Button btGo;
    @BindView(R.id.cv)
    CardView cv;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private WelcomeScreenHelper welcomeScreen;
    private LoginPresenter mLoginPresenter;
    private String user_name;
    private String user_password;
    private SweetAlertDialog dialog;
    private long startTime;
    private long stopTime;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phone = (String) SPUtils.getParam(this, Constants.PHONE, "");
        if (!phone.equals("") && null != phone) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        welcomeScreen = new WelcomeScreenHelper(this, SplashActivity.class);
        welcomeScreen.show(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.WRITE_EXTERNAL_CODE);
        }
    }

    @OnClick({R.id.bt_go, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;
            case R.id.bt_go:
                user_name = etUsername.getText().toString().trim();
                user_password = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(user_name)) {
                    ToastUtil.showToast(LoginActivity.this, "用户名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(user_password)) {
                    ToastUtil.showToast(LoginActivity.this, "密码不能为空");
                    return;
                }
                mLoginPresenter = new LoginPresenterImpl(this);
                Map<String, Object> map = new HashMap<>();
                map.put("phone", user_name);
                map.put("password", user_password);
                mLoginPresenter.loginUser(map);
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeScreen.onSaveInstanceState(outState);
    }

    @Override
    public void loginSuccess(final LoginResult loginResult) {
        SPUtils.setParam(LoginActivity.this, Constants.PHONE, loginResult.getUser().getUser_phone());
        SPUtils.setParam(LoginActivity.this, Constants.NAME, loginResult.getUser().getUser_name());
        stopTime = SystemClock.currentThreadTimeMillis();
        if (stopTime - startTime > 500) {
            if (loginResult.getStatus().equals("0")) {
                ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                Intent i2 = new Intent(this, MainActivity.class);
                startActivity(i2, oc2.toBundle());
                ToastUtil.showToast(LoginActivity.this, loginResult.getMsg());
                this.finish();

            } else if (loginResult.getStatus().equals("1")) {
                ToastUtil.showToast(LoginActivity.this, loginResult.getMsg());
            } else if (loginResult.getStatus().equals("2")) {
                ToastUtil.showToast(LoginActivity.this, loginResult.getMsg());
            }
        } else {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (loginResult.getStatus().equals("0")) {
                        ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this);
                        Intent i2 = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i2, oc2.toBundle());
                        ToastUtil.showToast(LoginActivity.this, loginResult.getMsg());
                        LoginActivity.this.finish();

                    } else if (loginResult.getStatus().equals("1")) {
                        ToastUtil.showToast(LoginActivity.this, loginResult.getMsg());
                    } else if (loginResult.getStatus().equals("2")) {
                        ToastUtil.showToast(LoginActivity.this, loginResult.getMsg());
                    }
                }
            }, 500);
        }
    }

    @Override
    public void showDialog() {
        dialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText("正在登录");
        dialog.show();
        startTime = SystemClock.currentThreadTimeMillis();
    }

    @Override
    public void hideDialog() {
        stopTime = SystemClock.currentThreadTimeMillis();
        if (stopTime - startTime > 500) {
            if (null != dialog) {
                dialog.dismiss();
            }
        } else {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (null != dialog) {
                        dialog.dismiss();
                    }
                }
            }, 500);
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case Constants.WRITE_EXTERNAL_CODE:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    ToastUtil.showToast(this, "为了更好的用户体验，请允许使用SDCard权限");
                }
                break;
        }
    }
}
