package com.hjianfei.museum_beacon_exhibition.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.Exhibitions;

import java.util.List;

/**
 * Created by HJianFei on 2016/11/3.
 */

public class HomeExhibitionViewPagerAdapter extends PagerAdapter {

    private List<Exhibitions.ExhibitionsBean> exhibitionsList;
    private Context mContext;

    public HomeExhibitionViewPagerAdapter(List<Exhibitions.ExhibitionsBean> exhibitionsList, Context mContext) {
        this.exhibitionsList = exhibitionsList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return exhibitionsList.size();
    }
    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = View.inflate(mContext, R.layout.hot_exhibition_item, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_home_item);
        TextView hot_item_title = (TextView) view.findViewById(R.id.hot_item_title);
        TextView hot_item_time = (TextView) view.findViewById(R.id.hot_item_time);
        hot_item_title.setText(exhibitionsList.get(position).getContent());
        hot_item_time.setText(exhibitionsList.get(position).getDetail_url());
        Glide.with(container.getContext())
                .load(exhibitionsList.get(position).getImg_url())
//                .placeholder(R.drawable.img4)
//                .error(R.drawable.img1)
                .into(iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                T.showShort(container.getContext(), "initHomeRecommend：" + position);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
