package com.hjianfei.museum_beacon_exhibition.view.activity.museum_detail;

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
import com.hjianfei.museum_beacon_exhibition.bean.MuseumDetail;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.museum_detail.MuseumDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.museum_detail.MuseumDetailPresenterImpl;
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
                Toast.makeText(MuseumDetailActivity.this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
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
}
