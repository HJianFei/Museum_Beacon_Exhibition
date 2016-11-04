package com.hjianfei.museum_beacon_exhibition.adapter.common;

import android.view.View;
import android.view.ViewGroup;

/**
 * description:
 * author: HJianFei
 * date: 2016/9/19 14:04
 * update: 2016/9/19
*/
public interface OnItemClickListener<T>
{
    void onItemClick(ViewGroup parent, View view, T t, int position);
    boolean onItemLongClick(ViewGroup parent, View view, T t, int position);
}