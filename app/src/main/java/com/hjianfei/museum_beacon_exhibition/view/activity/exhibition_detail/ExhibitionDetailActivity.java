package com.hjianfei.museum_beacon_exhibition.view.activity.exhibition_detail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import com.hjianfei.museum_beacon_exhibition.bean.ExhibitionDetail;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.exhibition_detail.ExhibitionDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.exhibition_detail.ExhibitionDetailPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
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
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibition_detail);
        ButterKnife.bind(this);
        exhibition_detail_url = getIntent().getStringExtra("exhibition_detail_url");
        exhibition_title = getIntent().getStringExtra("exhibition_title");
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
                    new ShareAction(ExhibitionDetailActivity.this)
                            .withTitle("博物展")
                            .withText(exhibition_detail.getExhibitionDetail().getTitle())
                            .withMedia(new UMImage(ExhibitionDetailActivity.this, img_urls[0]))
                            .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                            .setCallback(umShareListener).open();
                } else if (position == 2) {
                    ToastUtil.showToast(ExhibitionDetailActivity.this, "收藏");

                } else if (position == 3) {
                    ToastUtil.showToast(ExhibitionDetailActivity.this, "点赞");
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
}
