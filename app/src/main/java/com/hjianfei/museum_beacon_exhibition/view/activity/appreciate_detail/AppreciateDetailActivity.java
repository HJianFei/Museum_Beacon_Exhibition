package com.hjianfei.museum_beacon_exhibition.view.activity.appreciate_detail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.hjianfei.museum_beacon_exhibition.bean.AppreciateDetail;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.appreciate_detail.AppreciateDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.appreciate_detail.AppreciateDetailPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.SPUtils;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppreciateDetailActivity extends AppCompatActivity implements AppreciateDetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cultural_detail_view_pager)
    RollPagerView culturalDetailViewPager;
    @BindView(R.id.cultural_detail_name)
    TextView culturalDetailName;
    @BindView(R.id.cultural_detail_content)
    TextView culturalDetailContent;
    @BindView(R.id.activity_appreciate_detail)
    LinearLayout activityAppreciateDetail;
    private String cultural_detail_url;
    private String cultural_name;
    private String post_type = "";
    private AppreciateDetailPresenter mAppreciateDetailPresenter;
    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;
    private String[] img_urls;
    private AppreciateDetail appreciate_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appreciate_detail);
        ButterKnife.bind(this);
        cultural_detail_url = getIntent().getStringExtra("cultural_detail_url");
        cultural_name = getIntent().getStringExtra("cultural_name");
        post_type = getIntent().getStringExtra("post_type");
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
                    new ShareAction(AppreciateDetailActivity.this)
                            .withTitle("博物展")
                            .withText(appreciate_detail.getAppreciateDetail().getTitle())
                            .withMedia(new UMImage(AppreciateDetailActivity.this, img_urls[0]))
                            .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                            .setCallback(umShareListener).open();
                } else if (position == 2) {
                    String phone = (String) SPUtils.getParam(AppreciateDetailActivity.this, Constants.PHONE, "");
                    Map<String, Object> map = new HashMap<>();
                    map.put("user_phone", phone);
                    map.put("post_id", appreciate_detail.getAppreciateDetail().getDetail_url());
                    map.put("post_type", post_type);
                    mAppreciateDetailPresenter.onSaveCollection(map);
                }
            }
        });
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
        toolbar.setTitle(cultural_name);
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
        mAppreciateDetailPresenter = new AppreciateDetailPresenterImpl(this);
        mAppreciateDetailPresenter.onInitAppreciateDetailData(cultural_detail_url);

    }

    @Override
    public void initAppreciateDetailData(AppreciateDetail appreciateDetail) {
        appreciate_detail = appreciateDetail;
        culturalDetailName.setText(appreciateDetail.getAppreciateDetail().getTitle());
        culturalDetailContent.setText(appreciateDetail.getAppreciateDetail().getContent());
        String img_url = appreciateDetail.getAppreciateDetail().getImg_url();
        img_url = img_url.substring(1, img_url.length() - 1);
        img_urls = img_url.split(",");
        culturalDetailViewPager.setPlayDelay(3000);
        culturalDetailViewPager.setAdapter(new CommonDetailViewPagerAdapter(img_urls));
        culturalDetailViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));

    }

    @Override
    public void onSaveCollectionSuccess(ResultCode resultCode) {
        if (resultCode.code == 200) {
            ToastUtil.showToast(AppreciateDetailActivity.this, resultCode.msg);
        } else {
            ToastUtil.showToast(AppreciateDetailActivity.this, resultCode.msg);
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

            Toast.makeText(AppreciateDetailActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(AppreciateDetailActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(AppreciateDetailActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
}
