package com.hjianfei.museum_beacon_exhibition.view.activity.exhibition_detail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.CommonDetailViewPagerAdapter;
import com.hjianfei.museum_beacon_exhibition.bean.ExhibitionDetail;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.exhibition_detail.ExhibitionDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.exhibition_detail.ExhibitionDetailPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.SPUtils;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailActivity;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExhibitionDetailActivity extends AppCompatActivity implements ExhibitionDetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.exhibition_detail_view_pager)
    RollPagerView exhibitionDetailViewPager;
    @BindView(R.id.exhibition_detail_name)
    TextView exhibitionDetailName;
    @BindView(R.id.exhibition_detail_time)
    TextView exhibitionDetailTime;
    @BindView(R.id.exhibition_detail_address)
    TextView exhibitionDetailAddress;
    @BindView(R.id.exhibition_detail_content)
    TextView exhibitionDetailContent;
    private String exhibition_detail_url;
    private String exhibition_title;
    private ExhibitionDetailPresenter mExhibitionDetailPresenter;
    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;
    private ExhibitionDetail exhibition_detail;
    private String[] img_urls;
    private String post_type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibition_detail);
        ButterKnife.bind(this);
        exhibition_detail_url = getIntent().getStringExtra("exhibition_detail_url");
        exhibition_title = getIntent().getStringExtra("exhibition_title");
        post_type = getIntent().getStringExtra("post_type");
        initData();
        initView();
        initMenuFragment();
    }

    private void initMenuFragment() {
        fragmentManager = getSupportFragmentManager();
        final MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.height_menuBar));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(View clickedView, int position) {
                if (position == 1) {
                    if (ContextCompat.checkSelfPermission(ExhibitionDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ExhibitionDetailActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.WRITE_EXTERNAL_CODE);
                    } else {
                        doShare();
                    }

                } else if (position == 2) {
                    String phone = (String) SPUtils.getParam(ExhibitionDetailActivity.this, Constants.PHONE, "");
                    Map<String, Object> map = new HashMap<>();
                    map.put("user_phone", phone);
                    map.put("post_id", exhibition_detail.getExhibitionDetail().getTitle());
                    map.put("post_type", post_type);
                    map.put("img_url", img_urls[0]);
                    map.put("detail_url", exhibition_detail.getExhibitionDetail().getDetail_url());
                    mExhibitionDetailPresenter.onSaveCollection(map);
                }

            }
        });
    }

    private void doShare() {
        new ShareAction(ExhibitionDetailActivity.this)
                .withTitle("博物展")
                .withText(exhibition_detail.getExhibitionDetail().getTitle())
                .withMedia(new UMImage(ExhibitionDetailActivity.this, img_urls[0]))
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(umShareListener).open();
    }

    private List<MenuObject> getMenuObjects() {
        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.menu_close);

        MenuObject send = new MenuObject("分享");
        send.setResource(R.drawable.menu_share);

        MenuObject like = new MenuObject("收藏");
        like.setResource(R.drawable.menu_love);

        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        return menuObjects;
    }

    private void initView() {
        toolbar.setTitle(exhibition_title);
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
        mExhibitionDetailPresenter = new ExhibitionDetailPresenterImpl(this);
        mExhibitionDetailPresenter.onInitExhibitionDetailData(exhibition_detail_url);

    }

    @Override
    public void initExhibitionDetailData(ExhibitionDetail exhibitionDetail) {
        exhibition_detail = exhibitionDetail;
        exhibitionDetailName.setText(exhibitionDetail.getExhibitionDetail().getTitle());
        exhibitionDetailTime.setText(exhibitionDetail.getExhibitionDetail().getShow_time());
        exhibitionDetailAddress.setText(exhibitionDetail.getExhibitionDetail().getAddress());
        exhibitionDetailContent.setText(exhibitionDetail.getExhibitionDetail().getContent());
        String img_url = exhibitionDetail.getExhibitionDetail().getImg_url();
        img_url = img_url.substring(1, img_url.length() - 1);
        img_urls = img_url.split(",");
        exhibitionDetailViewPager.setPlayDelay(3000);
        exhibitionDetailViewPager.setAdapter(new CommonDetailViewPagerAdapter(img_urls));
        exhibitionDetailViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));

        final String finalImg_url = img_url;
        exhibitionDetailViewPager.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ExhibitionDetailActivity.this, PhotoDetailActivity.class);
                intent.putExtra("img_urls", finalImg_url);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSaveCollectionSuccess(ResultCode resultCode) {
        if (resultCode.code == 200) {
            ToastUtil.showToast(ExhibitionDetailActivity.this, resultCode.msg);
        } else {
            ToastUtil.showToast(ExhibitionDetailActivity.this, resultCode.msg);
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

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            LogUtils.d(Constants.TAG, "platform" + platform);

            Toast.makeText(ExhibitionDetailActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ExhibitionDetailActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                LogUtils.d(Constants.TAG, "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ExhibitionDetailActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
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
}
