package com.hjianfei.museum_beacon_exhibition.view.activity.appreciate_detail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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
import com.hjianfei.museum_beacon_exhibition.bean.AppreciateDetail;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.appreciate_detail.AppreciateDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.appreciate_detail.AppreciateDetailPresenterImpl;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.List;

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
    private String cultural_detail_url;
    private String cultural_name;
    private AppreciateDetailPresenter mAppreciateDetailPresenter;
    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appreciate_detail);
        ButterKnife.bind(this);
        cultural_detail_url = getIntent().getStringExtra("cultural_detail_url");
        cultural_name = getIntent().getStringExtra("cultural_name");
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
                Toast.makeText(AppreciateDetailActivity.this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<MenuObject> getMenuObjects() {
        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_close);

        MenuObject send = new MenuObject("Send message");
        send.setResource(R.drawable.icn_1);

        MenuObject like = new MenuObject("Like profile");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.icn_2);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject("Add to friends");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.icn_3));
        addFr.setDrawable(bd);

        MenuObject addFav = new MenuObject("Add to favorites");
        addFav.setResource(R.drawable.icn_4);

        MenuObject block = new MenuObject("Block user");
        block.setResource(R.drawable.icn_5);

        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        menuObjects.add(block);
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
        culturalDetailName.setText(appreciateDetail.getAppreciateDetail().getTitle());
        culturalDetailContent.setText(appreciateDetail.getAppreciateDetail().getContent());
        String img_url = appreciateDetail.getAppreciateDetail().getImg_url();
        String[] img_urls = img_url.split(",");
        culturalDetailViewPager.setPlayDelay(3000);
        culturalDetailViewPager.setAdapter(new CommonDetailViewPagerAdapter(img_urls));
        culturalDetailViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));

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
//    @OnClick(R.id.share_icon)
//    public void onClickListener(View v) {
//        switch (v.getId()) {
//            case R.id.share_icon:
//                ShareSDK.initSDK(this);
//                OnekeyShare oks = new OnekeyShare();
////关闭sso授权
//                oks.disableSSOWhenAuthorize();
//
//// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
//                oks.setTitle("标题");
//// titleUrl是标题的网络链接，QQ和QQ空间等使用
//                oks.setTitleUrl("http://sharesdk.cn");
//// text是分享文本，所有平台都需要这个字段
//                oks.setText("我是分享文本");
//// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
////oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//// url仅在微信（包括好友和朋友圈）中使用
//                oks.setUrl("http://sharesdk.cn");
//// comment是我对这条分享的评论，仅在人人网和QQ空间使用
//                oks.setComment("我是测试评论文本");
//// site是分享此内容的网站名称，仅在QQ空间使用
//                oks.setSite(getString(R.string.app_name));
//// siteUrl是分享此内容的网站地址，仅在QQ空间使用
//                oks.setSiteUrl("http://sharesdk.cn");
//
//// 启动分享GUI
//                oks.show(this);
//                break;
//        }
//
//    }
}
