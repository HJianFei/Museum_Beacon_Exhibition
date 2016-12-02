package com.hjianfei.museum_beacon_exhibition.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.bean.Info;

/**
 * Created by HJianFei on 2016/11/14.
 */

public class RadarViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private SparseArray<Info> mDatas;

    public RadarViewPagerAdapter(Context mContext, SparseArray<Info> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final Info info = mDatas.get(position);
        //设置一大堆演示用的数据，麻里麻烦~~
        View view = View.inflate(mContext, R.layout.viewpager_layout, null);
        ImageView ivPortrait = (ImageView) view.findViewById(R.id.iv);
        ImageView ivSex = (ImageView) view.findViewById(R.id.iv_sex);
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        TextView tvDistance = (TextView) view.findViewById(R.id.tv_distance);
        tvName.setText(info.getName());
        tvDistance.setText(info.getDistance() + "km");
        ivPortrait.setImageResource(info.getPortraitId());
        if (info.getSex()) {
            ivSex.setImageResource(R.drawable.girl);
        } else {
            ivSex.setImageResource(R.drawable.boy);
        }
        ivPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "这是 " + info.getName() + " >.<", Toast.LENGTH_SHORT).show();
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
