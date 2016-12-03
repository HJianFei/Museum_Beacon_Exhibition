package com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.PhotoDetailViewPagerAdapter;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.TextHintView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.photo_detail)
    RollPagerView photoDetail;
    private String[] img_urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        img_urls = getIntent().getStringExtra("img_urls").split(",");
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
        //        初始化ViewPager
        photoDetail.setPlayDelay(3000);
        photoDetail.setAdapter(new PhotoDetailViewPagerAdapter(img_urls));
        photoDetail.setHintView(new TextHintView(PhotoDetailActivity.this));

        photoDetail.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                mIntent = new Intent(mContext, ExhibitionDetailActivity.class);
//                mIntent.putExtra("detail_url", viewPager.getViewPagers().get(position).getDetail_url());
//                mIntent.putExtra("title", viewPager.getViewPagers().get(position).getContent());
//                ActivityOptionsCompat options =
//                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
//                                view.findViewById(R.id.home_view_pager), getString(R.string.transition));
//                ActivityCompat.startActivity(getActivity(), mIntent, options.toBundle());

            }
        });
    }
}
