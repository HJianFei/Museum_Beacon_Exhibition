package com.hjianfei.museum_beacon_exhibition.view.activity.personal;

import android.Manifest;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.Fade;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.any_event.Logout;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.user_info.UserInfoPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.user_info.UserInfoPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.SPUtils;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.utils.widget.CircleImageView;
import com.hjianfei.museum_beacon_exhibition.view.activity.change_password.ChangePasswordActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.change_phone.ChangePhoneActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.collection.CollectionActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.setting.SettingActivity;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.hjianfei.museum_beacon_exhibition.R.id.personal_name;

public class PersonalActivity extends BaseActivity implements PersonalView {

    @BindView(R.id.toolbar)
    Toolbar personalToolbar;
    @BindView(R.id.personal_avatar)
    CircleImageView personalAvatar;
    @BindView(personal_name)
    TextView personalName;
    @BindView(R.id.tv_personal_love)
    TextView tvPersonalLove;
    @BindView(R.id.tv_personal_share)
    TextView tvPersonalShare;
    @BindView(R.id.tv_personal_phone)
    TextView tvPersonalPhone;
    @BindView(R.id.tv_personal_change_password)
    TextView tvPersonalChangePassword;
    @BindView(R.id.tv_personal_setting)
    TextView tvPersonalSetting;
    @BindView(R.id.tv_app_share)
    TextView tvAppShare;
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "user_avatar.png";

    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    // 裁剪后图片的宽(X)和高(Y),80 X 80 的正方形。
    private static int output_X = 90;
    private static int output_Y = 90;

    private UserInfoPresenter mUserInfoPresenter;
    private String user_phone;
    private long startTime;
    private long stopTime;
    private SweetAlertDialog sweetAlertDialog;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        //淡入淡出
        getWindow().setEnterTransition(new Fade().setDuration(Constants.DURATION));
        getWindow().setReturnTransition(new Fade().setDuration(Constants.DURATION));
        user_phone = (String) SPUtils.getParam(PersonalActivity.this, Constants.PHONE, "");

        ButterKnife.bind(this);
        initView();
        EventBus.getDefault().register(this);
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
        mUserInfoPresenter = new UserInfoPresenterImpl(this);

    }

    @Override
    public void onResume() {
        personalName.setText((String) SPUtils.getParam(PersonalActivity.this, Constants.NAME, ""));
        tvPersonalPhone.setText("手机号码：" + SPUtils.getParam(PersonalActivity.this, Constants.PHONE, ""));
        super.onResume();
    }

    @OnClick({R.id.personal_avatar, personal_name,
            R.id.tv_personal_love, R.id.tv_personal_share,
            R.id.tv_personal_phone, R.id.tv_personal_change_password,
            R.id.tv_personal_setting, R.id.tv_app_share})
    public void onClickListener(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.personal_avatar:
                show_Dialog();
                break;
            case personal_name:
                new MaterialDialog.Builder(PersonalActivity.this)
                        .title("修改昵称")
                        .titleColor(getResources().getColor(R.color.primary))
                        .input("请输入昵称", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                if (!TextUtils.isEmpty(input)) {
                                    name = input.toString().trim();
                                    Map<String, Object> map = new HashMap<>();
                                    map.put("user_phone", user_phone);
                                    map.put("name", name);
                                    mUserInfoPresenter.changeNickName(map);
                                    startTime = SystemClock.currentThreadTimeMillis();
                                }
                            }
                        }).show();
                break;
            case R.id.tv_personal_love:
                intent = new Intent(PersonalActivity.this, CollectionActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());


                break;
            case R.id.tv_personal_share:
                ToastUtil.showToast(PersonalActivity.this, "该功能正在开发中...");

                break;
            case R.id.tv_personal_phone:
                intent = new Intent(PersonalActivity.this, ChangePhoneActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

                break;
            case R.id.tv_personal_change_password:
                intent = new Intent(PersonalActivity.this, ChangePasswordActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

                break;
            case R.id.tv_personal_setting:
                intent = new Intent(PersonalActivity.this, SettingActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
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

    private void show_Dialog() {
        View view = getLayoutInflater().inflate(R.layout.photo_choose_dialog, null);
        final Dialog dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.findViewById(R.id.pick_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 从本地相册选取图片作为头像
                choseHeadImageFromGallery();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.tack_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用系统相机拍照
                choseHeadImageFromCameraCapture();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.cancel_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 从系统图库中选择相片
     */
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_PICK);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    /**
     * 调用系统相机拍照获取图片
     */
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 判断存储卡是否可用，存储照片文件
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }

        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
//            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG).show();
                }

                break;

            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);
                }

                break;
        }

        super.onActivityResult(requestCode, resultCode, intent);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, intent);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            personalAvatar.setImageBitmap(photo);
            try {
                saveUserIcon(photo);
                File filePath = new File(Constants.FILE_URI + "/" + IMAGE_FILE_NAME);
                mUserInfoPresenter.changeAvatar(filePath, user_phone);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveUserIcon(Bitmap bitmap) throws IOException {
        File file = new File(Constants.FILE_URI);
        if (!file.exists()) {
            file.mkdirs();
        }
        File dir = new File(Constants.FILE_URI + "/" + IMAGE_FILE_NAME);
        if (!dir.exists()) {
            dir.createNewFile();
        }
        FileOutputStream iStream = new FileOutputStream(dir);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, iStream);
        iStream.close();
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    private void doShare() {
        new ShareAction(PersonalActivity.this)
                .withTitle("史博展")
                .withText("史博展，让沉睡千年的文物‘动’起来")
                .withMedia(new UMImage(this, R.mipmap.logo))
                .withTargetUrl("http://fir.im/fzt8")
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(umShareListener).open();
    }

    @Override
    public void showDialog() {

        sweetAlertDialog = new SweetAlertDialog(PersonalActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("正在修改");
        sweetAlertDialog.show();
    }

    @Override
    public void hideDialog() {
        stopTime = SystemClock.currentThreadTimeMillis();
        if (stopTime - startTime > 500) {
            if (null != sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        } else {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (null != sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
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

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            LogUtils.d("plat", "platform" + platform);

            Toast.makeText(PersonalActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(PersonalActivity.this, "分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                LogUtils.d("throw", "throw:" + t.getMessage());
                ToastUtil.showToast(PersonalActivity.this, "请允许使用SDCard权限");
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(PersonalActivity.this, "分享取消了", Toast.LENGTH_SHORT).show();
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

    @Override
    public void changeAvatarSuccess(ResultCode resultCode) {
        ToastUtil.showToast(PersonalActivity.this, "更新头像成功");
    }

    @Override
    public void changeNameSuccess(ResultCode resultCode) {
        if (resultCode.code == 200) {
            personalName.setText(name);
            SPUtils.setParam(PersonalActivity.this, Constants.NAME, name);
            ToastUtil.showToast(PersonalActivity.this, resultCode.msg);
        } else {
            ToastUtil.showToast(PersonalActivity.this, resultCode.msg);
        }

    }

    @Subscribe
    public void eventMessage(Logout logout) {
        this.finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
