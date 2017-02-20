package com.hjianfei.museum_beacon_exhibition.view.activity.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.bean.SecurityResultCode;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.register.RegisterPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.register.RegisterPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.utils.ValidatorUtils;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


public class RegisterActivity extends BaseActivity implements RegisterView {
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.cv_add)
    CardView cvAdd;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_repeatpassword)
    EditText etRepeatpassword;
    @BindView(R.id.btn_register)
    Button btn_register;
    @BindView(R.id.security_code)
    EditText securityCode;
    @BindView(R.id.register_activity_layout)
    RelativeLayout registerActivityLayout;
    @BindView(R.id.frame_layout)
    FrameLayout frame_layout;
    private String user_phone;
    private String security_code;
    private String password;
    private String repeat_password;
    private TimeCount time;
    private RegisterPresenter mRegisterPresenter;
    private SweetAlertDialog dialog;
    private long startTime;
    private long stopTime;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (null != dialog) {
                dialog.dismiss();
            }
            String data = (String) msg.obj;
            data = data.substring(21);
            Gson gson = new Gson();
            SecurityResultCode resultCode = gson.fromJson(data, SecurityResultCode.class);
            ToastUtil.showToast(RegisterActivity.this, resultCode.getDetail());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }
        SMSSDK.registerEventHandler(eh); //注册短信回调
        time = new TimeCount(60000, 1000);
        mRegisterPresenter = new RegisterPresenterImpl(this);
    }

    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @Override
    public void onBackPressed() {
        animateRevealClose();
    }

    @OnClick({R.id.btn_get_code, R.id.fab, R.id.btn_register})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.fab:
                animateRevealClose();
                break;
            case R.id.btn_get_code://获取短信验证码
                user_phone = etUsername.getText().toString().trim();
                boolean mobile = ValidatorUtils.isMobile(user_phone);
                if (mobile) {
                    dialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    dialog.setTitleText("验证码获取中");
                    dialog.show();
                    //请求获取短信验证码，在监听中返回
                    SMSSDK.getVerificationCode(Constants.MOBILE, user_phone);
                } else {
                    ToastUtil.showToast(RegisterActivity.this, "手机号码格式不正确");
                }
                break;
            case R.id.btn_register:
                security_code = securityCode.getText().toString();
                user_phone = etUsername.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                repeat_password = etRepeatpassword.getText().toString().trim();
                if (TextUtils.isEmpty(security_code)) {
                    ToastUtil.showToast(RegisterActivity.this, "验证码不能为空");
                    return;
                } else if (!password.equals(repeat_password)) {
                    ToastUtil.showToast(RegisterActivity.this, "两次密码输入不一致");
                    return;
                } else {
                    SMSSDK.registerEventHandler(eh); //注册短信回调
                    //提交验证码，检验验证码输入输入是否正确
                    SMSSDK.submitVerificationCode(Constants.MOBILE, user_phone, security_code);
                    dialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    dialog.setTitleText("注册中");
                    dialog.show();
                    startTime = SystemClock.currentThreadTimeMillis();
                }
                break;
        }

    }

    /**
     * 短信验证回调事件接收器
     */
    EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码成功
                    LogUtils.d("onResponse", "提交验证码成功");
                    Map<String, Object> map = new HashMap<>();
                    map.put("user_phone", user_phone);
                    map.put("user_password", password);
                    mRegisterPresenter.registerUser(map);

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) { //获取验证码成功

                    dialog.dismiss();
                    LogUtils.d("onResponse", "获取验证码成功");
                    time.start();

                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) { //返回支持发送验证码的国家列表

                    LogUtils.d("onResponse", data.toString());

                }
            } else {
                Message message = Message.obtain();
                message.obj = data.toString();
                handler.sendMessage(message);
                LogUtils.d(Constants.TAG, data.toString());
            }
        }
    };

    @Override
    protected void onDestroy() {
        SMSSDK.unregisterEventHandler(eh);
        super.onDestroy();
    }

    @Override
    public void registerUserFinished(ResultCode resultCode) {
        LogUtils.d(Constants.TAG, resultCode.toString());
        if (resultCode.code == 200) {
            ToastUtil.showToast(RegisterActivity.this, resultCode.msg);
            animateRevealClose();
        } else {
            ToastUtil.showToast(RegisterActivity.this, resultCode.msg);
        }

    }

    @Override
    public void showDialog() {

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
        if (null != dialog) {
            dialog.dismiss();
            ToastUtil.showToast(RegisterActivity.this, "网络出错，稍后再试");
        }

    }

    @Override
    public void showEmpty() {

    }


    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btnGetCode.setClickable(false);
            btnGetCode.setText(millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            btnGetCode.setText("重新获取");
            btnGetCode.setClickable(true);

        }
    }
}
