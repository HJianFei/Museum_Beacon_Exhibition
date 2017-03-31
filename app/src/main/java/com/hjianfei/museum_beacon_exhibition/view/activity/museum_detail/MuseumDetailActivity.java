package com.hjianfei.museum_beacon_exhibition.view.activity.museum_detail;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.CommonDetailViewPagerAdapter;
import com.hjianfei.museum_beacon_exhibition.bean.MuseumDetail;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.museum_detail.MuseumDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.museum_detail.MuseumDetailPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.view.activity.museum.MuseumActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailActivity;
import com.hjianfei.museum_beacon_exhibition.view.base.BaseActivity;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MuseumDetailActivity extends BaseActivity implements MuseumDetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.museum_detail_view_pager)
    RollPagerView museumDetailViewPager;
    @BindView(R.id.museum_detail_name)
    TextView museumDetailName;
    @BindView(R.id.museum_detail_content)
    TextView museumDetailContent;
    @BindView(R.id.museum_detail_enter)
    LinearLayout museumDetailEnter;
    private MuseumDetailPresenter mMuseumDetailPresenter;
    private String id = "";
    private String museum_name = "";
    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;
    private MuseumDetail museum_detail;
    private String post_type = "";
    private String[] img_urls;
    private String appreciate_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Slide().setDuration(Constants.DURATION));
        getWindow().setExitTransition(new Slide().setDuration(Constants.DURATION));
        setContentView(R.layout.activity_museum_detail);
        id = getIntent().getStringExtra("id");
        museum_name = getIntent().getStringExtra("museum_name");
        post_type = getIntent().getStringExtra("post_type");
        appreciate_type = getIntent().getStringExtra("appreciate_type");
        ButterKnife.bind(this);
        initData();
        initView();
        initMenuFragment();

    }

    private void initMenuFragment() {
        fragmentManager = getSupportFragmentManager();
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.height_menuBar));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(View clickedView, int position) {
                if (position == 1) {
                    if (ContextCompat.checkSelfPermission(MuseumDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MuseumDetailActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.WRITE_EXTERNAL_CODE);
                    } else {
                        doShare();
                    }
                } else if (position == 2) {
//                    String phone = (String) SPUtils.getParam(MuseumDetailActivity.this, Constants.PHONE, "");
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("user_phone", phone);
//                    map.put("post_id", museum_detail.getMuseum_Detail().getMuseum_detail_name());
//                    map.put("post_type", post_type);
//                    map.put("img_url", img_urls[0]);
//                    map.put("id", museum_detail.getMuseum_Detail().getId()+"");
//                    mMuseumDetailPresenter.onSaveCollection(map);

                }
            }
        });
    }

    private void doShare() {
        new ShareAction(MuseumDetailActivity.this)
                .withTitle("博物展")
                .withText(museum_detail.getMuseum_Detail().getMuseum_detail_name())
                .withMedia(new UMImage(MuseumDetailActivity.this, museum_detail.getMuseum_Detail().getMuseum_detail_imgs()))
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(umShareListener).open();
    }

    private List<MenuObject> getMenuObjects() {
        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.menu_close);

        MenuObject send = new MenuObject("分享");
        send.setResource(R.drawable.menu_share);

//        MenuObject like = new MenuObject("收藏");
//        like.setResource(R.drawable.menu_love);

        menuObjects.add(close);
        menuObjects.add(send);
//        menuObjects.add(like);
        return menuObjects;
    }

    private void initView() {

        toolbar.setTitle(museum_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initData() {
        mMuseumDetailPresenter = new MuseumDetailPresenterImpl(this);
        mMuseumDetailPresenter.initMuseumDetailData(id);

    }

    @Override
    public void initMuseumDetailData(final MuseumDetail museumDetail) {
        museum_detail = museumDetail;
        museumDetailName.setText(museumDetail.getMuseum_Detail().getMuseum_detail_name());
        museumDetailContent.setText(museumDetail.getMuseum_Detail().getMuseum_detail_content());
        String img_url = museumDetail.getMuseum_Detail().getMuseum_detail_imgs();
        if (img_url.startsWith("[") && img_url.endsWith("]")) {
            img_url = img_url.substring(1, img_url.length() - 1);
        }
        img_urls = img_url.split(",");
        museumDetailViewPager.setPlayDelay(3000);
        museumDetailViewPager.setAdapter(new CommonDetailViewPagerAdapter(img_urls));
        museumDetailViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));
        final String finalImg_url = img_url;
        museumDetailViewPager.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MuseumDetailActivity.this, PhotoDetailActivity.class);
                intent.putExtra("img_urls", finalImg_url);
                intent.putExtra("photo_title", museumDetail.getMuseum_Detail().getMuseum_detail_name());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MuseumDetailActivity.this).toBundle());

            }
        });


    }

    @Override
    public void onSaveCollectionSuccess(ResultCode resultCode) {
        if (resultCode.code == 200) {
            ToastUtil.showToast(MuseumDetailActivity.this, resultCode.msg);
        } else {
            ToastUtil.showToast(MuseumDetailActivity.this, resultCode.msg);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.appreciate_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);

            Toast.makeText(MuseumDetailActivity.this, "分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MuseumDetailActivity.this, "分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MuseumDetailActivity.this, "分享取消了", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.museum_detail_enter)
    public void onClick() {
        Intent intent = new Intent(this, MuseumActivity.class);
        intent.putExtra("museum_name", museum_name);
        intent.putExtra("img_url", museum_detail.getMuseum_Detail().getMuseum_detail_imgs());
        intent.putExtra("appreciate_type", appreciate_type);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
