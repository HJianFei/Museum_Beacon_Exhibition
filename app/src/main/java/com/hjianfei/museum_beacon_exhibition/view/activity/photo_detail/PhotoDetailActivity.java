package com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.PhotoDetailViewPagerAdapter;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.photo_detail.PhotoDetailPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.photo_detail.PhotoDetailPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.TextHintView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class PhotoDetailActivity extends AppCompatActivity implements PhotoDetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.photo_detail)
    RollPagerView photoDetail;
    @BindView(R.id.photo_title)
    TextView photo_title;
    private String[] img_urls;
    private PhotoDetailPresenter mpPhotoDetailPresenter;
    private SweetAlertDialog dialog;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        img_urls = getIntent().getStringExtra("img_urls").split(",");
        title = getIntent().getStringExtra("photo_title");
        setContentView(R.layout.activity_photo_detail);
        ButterKnife.bind(this);
        initToolBar();
        initPhotoView();
    }

    private void initToolBar() {
        toolbar.setTitle("图片详情");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initPhotoView() {
        mpPhotoDetailPresenter = new PhotoDetailPresenterImpl(this);
        photo_title.setText(title);
        //        初始化ViewPager
        photoDetail.setPlayDelay(3000);
        photoDetail.setAdapter(new PhotoDetailViewPagerAdapter(img_urls));
        photoDetail.setHintView(new TextHintView(PhotoDetailActivity.this));

        photoDetail.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                dialog = new SweetAlertDialog(PhotoDetailActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                dialog.setCustomImage(R.drawable.guide_save_pic);
                dialog.setTitleText("保存图片?");
                dialog.showCancelButton(true);
                dialog.setCancelText("取消");
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mpPhotoDetailPresenter.savePicFromNet(img_urls[position]);
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void onSavePicSuccess(ResultCode resultCode) {

        ToastUtil.showToast(PhotoDetailActivity.this, resultCode.msg);
    }
}
