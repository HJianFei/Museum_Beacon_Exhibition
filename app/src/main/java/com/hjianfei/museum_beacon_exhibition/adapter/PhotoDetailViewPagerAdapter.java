package com.hjianfei.museum_beacon_exhibition.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hjianfei.museum_beacon_exhibition.R;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

/**
 * Created by HJianFei on 2016/12/3.
 */

public class PhotoDetailViewPagerAdapter extends StaticPagerAdapter {

    private String[] imgUrl;

    public PhotoDetailViewPagerAdapter(String[] imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView item_iv = new ImageView(container.getContext());
        Glide.with(container.getContext())
                .load(imgUrl[position].trim())
                .placeholder(R.drawable.photo)
                .error(R.drawable.photo)
                .into(item_iv);
        return item_iv;
    }

    @Override
    public int getCount() {
        return imgUrl.length;
    }
}
