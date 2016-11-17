package com.hjianfei.museum_beacon_exhibition.view.activity.museum_detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.TextView;
import android.widget.Toast;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.CommonDetailViewPagerAdapter;
import com.hjianfei.museum_beacon_exhibition.bean.MuseumDetail;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.museum_detail.MuseumDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.museum_detail.MuseumDetailPresenterImpl;
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

public class MuseumDetailActivity extends AppCompatActivity implements MuseumDetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.museum_detail_view_pager)
    RollPagerView museumDetailViewPager;
    @BindView(R.id.museum_detail_name)
    TextView museumDetailName;
    @BindView(R.id.museum_detail_content)
    TextView museumDetailContent;
    private MuseumDetailPresenter mMuseumDetailPresenter;
    private String museum_name;
    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;
    private MuseumDetail museum_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_detail);
        museum_name = getIntent().getStringExtra("museum_name");
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
                new ShareAction(MuseumDetailActivity.this)
                        .withTitle("博物展")
                        .withText(museum_detail.getMuseum_Detail().getMuseum_detail_name())
                        .withMedia(new UMImage(MuseumDetailActivity.this, museum_detail.getMuseum_Detail().getMuseum_detail_imgs()))
                        .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                        .setCallback(umShareListener).open();
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
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.menu_love);
        like.setBitmap(b);
        MenuObject addFav = new MenuObject("点赞");
        addFav.setResource(R.drawable.menu_favorites);

        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFav);
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
        mMuseumDetailPresenter.initMuseumDetailData(museum_name);

    }

    @Override
    public void initMuseumDetailData(MuseumDetail museumDetail) {
        museum_detail = museumDetail;
        museumDetailName.setText(museumDetail.getMuseum_Detail().getMuseum_detail_name());
        museumDetailContent.setText(museumDetail.getMuseum_Detail().getMuseum_detail_content());
        String img_url = museumDetail.getMuseum_Detail().getMuseum_detail_imgs();
        String[] img_urls = img_url.split(",");
        museumDetailViewPager.setPlayDelay(3000);
        museumDetailViewPager.setAdapter(new CommonDetailViewPagerAdapter(img_urls));
        museumDetailViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));


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

            Toast.makeText(MuseumDetailActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MuseumDetailActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MuseumDetailActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
}
