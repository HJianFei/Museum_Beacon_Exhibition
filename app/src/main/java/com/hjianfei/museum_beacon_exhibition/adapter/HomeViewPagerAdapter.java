package com.hjianfei.museum_beacon_exhibition.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hjianfei.museum_beacon_exhibition.bean.ViewPager;
import com.hjianfei.museum_beacon_exhibition.canstants.Urls;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.List;

/**
 * 创建时间： 2016/9/9.
 * 作者：HJianFei
 * 功能描述：
 */

public class HomeViewPagerAdapter extends StaticPagerAdapter {
    private List<ViewPager.ViewPagersBean> mViewPagers;

    public HomeViewPagerAdapter(List<ViewPager.ViewPagersBean> viewPagers) {
        this.mViewPagers = viewPagers;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView item_iv = new ImageView(container.getContext());
        item_iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(container.getContext())
                .load(Urls.API_SERVER + mViewPagers.get(position).getImg_url())
//                .placeholder(R.drawable.photo)
//                .error(R.drawable.photo)
                .into(item_iv);
        return item_iv;
    }

    @Override
    public int getCount() {
        return mViewPagers.size();
    }
}
