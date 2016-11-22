package com.hjianfei.museum_beacon_exhibition.view.activity.change_phone;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.bean.SecurityResultCode;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.user_info.UserInfoPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.user_info.UserInfoPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.SPUtils;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.utils.ValidatorUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static com.hjianfei.museum_beacon_exhibition.R.id.old_phone;

public class ChangePhoneActivity extends AppCompatActivity implements ChangePhoneView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(old_phone)
    TextView oldPhone;
    @BindView(R.id.new_phone)
    EditText newPhone;
    @BindView(R.id.security_code)
    EditText securityCode;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.btn_change_ok)
    Button btnChangeOk;
    @BindView(R.id.register_activity_layout)
    RelativeLayout registerActivityLayout;
    private String user_phone;
    private String security_code;
    private TimeCount time;
    private SweetAlertDialog dialog;
    private long startTime;
    private long stopTime;
    private UserInfoPresenter mUserInfoPresenter;
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
            ToastUtil.showToast(ChangePhoneActivity.this, resultCode.getDetail());
        }
    };
    private String old_user_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        old_user_phone = (String) SPUtils.getParam(ChangePhoneActivity.this, Constants.PHONE, "");
        setContentView(R.layout.activity_change_phone);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle("更换手机号码");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        SMSSDK.registerEventHandler(eh); //注册短信回调
        time = new TimeCount(60000, 1000);
        mUserInfoPresenter = new UserInfoPresenterImpl(this);
        oldPhone.setText(old_user_phone);
    }

    @OnClick({R.id.btn_change_ok, R.id.btn_get_code})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.btn_change_ok:
                security_code = securityCode.getText().toString();
                user_phone = newPhone.getText().toString().trim();
                if (TextUtils.isEmpty(security_code)) {
                    ToastUtil.showToast(ChangePhoneActivity.this, "验证码不能为空");
                    return;
                } else {
                    SMSSDK.registerEventHandler(eh); //注册短信回调
                    //提交验证码，检验验证码输入输入是否正确
                    SMSSDK.submitVerificationCode(Constants.MOBILE, user_phone, security_code);
                    dialog = new SweetAlertDialog(ChangePhoneActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    dialog.setTitleText("正在更换");
                    dialog.show();
                    startTime = SystemClock.currentThreadTimeMillis();
                }

                break;
            case R.id.btn_get_code:
                user_phone = newPhone.getText().toString().trim();
                boolean mobile = ValidatorUtils.isMobile(user_phone);
                if (mobile) {
                    dialog = new SweetAlertDialog(ChangePhoneActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    dialog.setTitleText("正在获取验证码");
                    dialog.show();
                    //请求获取短信验证码，在监听中返回
                    SMSSDK.getVerificationCode(Constants.MOBILE, user_phone);
                } else {
                    ToastUtil.showToast(ChangePhoneActivity.this, "手机号码格式不正确");
                }
                break;
        }
    }

    @Override
    public void changePhoneSuccess(ResultCode resultCode) {
        SPUtils.setParam(ChangePhoneActivity.this, Constants.PHONE, user_phone);
        ToastUtil.showToast(ChangePhoneActivity.this, resultCode.msg);
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

    }

    @Override
    public void showEmpty() {

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
                    map.put("new_phone", user_phone);
                    map.put("old_phone", old_user_phone);
                    mUserInfoPresenter.changePhone(map);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) { //获取验证码成功

                    dialog.dismiss();
                    LogUtils.d("onResponse", "获取验证码成功");
                    time.start();

                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) { //返回支持发送验证码的国家列表

                    LogUtils.d("onResponse", data.toString());

                }
            } else {
                LogUtils.d(Constants.TAG, data.toString());
                Message message = Message.obtain();
                message.obj = data.toString();
                handler.sendMessage(message);

            }
        }
    };

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

    @Override
    protected void onDestroy() {
        SMSSDK.unregisterEventHandler(eh);
        super.onDestroy();
    }
}
