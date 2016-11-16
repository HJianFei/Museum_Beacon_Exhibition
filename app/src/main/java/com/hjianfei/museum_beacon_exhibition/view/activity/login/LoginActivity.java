package com.hjianfei.museum_beacon_exhibition.view.activity.login;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.LoginResult;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.login.LoginPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.login.LoginPresenterImpl;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        welcomeScreen = new WelcomeScreenHelper(this, SplashActivity.class);
        welcomeScreen.show(savedInstanceState);
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
    public void loginSuccess(LoginResult loginResult) {
        if (loginResult.getStatus().equals("0")) {
            Explode explode = new Explode();
            explode.setDuration(500);
            getWindow().setExitTransition(explode);
            getWindow().setEnterTransition(explode);
            ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
            Intent i2 = new Intent(this, MainActivity.class);
            startActivity(i2, oc2.toBundle());
            ToastUtil.showToast(LoginActivity.this,loginResult.getMsg());
            this.finish();

        } else if (loginResult.getStatus().equals("1")) {
            ToastUtil.showToast(LoginActivity.this, loginResult.getMsg());
        } else if (loginResult.getStatus().equals("2")) {
            ToastUtil.showToast(LoginActivity.this, loginResult.getMsg());
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
