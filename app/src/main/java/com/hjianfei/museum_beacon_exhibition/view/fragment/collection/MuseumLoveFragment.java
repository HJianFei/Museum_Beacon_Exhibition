package com.hjianfei.museum_beacon_exhibition.view.fragment.collection;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
import com.hjianfei.museum_beacon_exhibition.bean.Collection;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.collection.CollectionPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.activity.collection.CollectionPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.SPUtils;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.view.activity.museum_detail.MuseumDetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MuseumLoveFragment extends Fragment implements CollectionView {

    @BindView(R.id.cultural_love_recyclerview)
    LRecyclerView culturalLoveRecyclerview;

    private CommonAdapter<Collection.CollectionsBean> mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private List<Collection.CollectionsBean> collectionsBeanList = new ArrayList<>();
    private CollectionPresenter mCollectionPresenter;
    private int page = 1;
    private int index = 0;
    private SweetAlertDialog dialog;

    private String user_phone = "";

    private static final String TYPE = "博物馆";

    private Context mContext;

    public MuseumLoveFragment() {

    }

    public static MuseumLoveFragment newInstance() {
        MuseumLoveFragment fragment = new MuseumLoveFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_museum_love, container, false);
        ButterKnife.bind(this, view);
        user_phone = (String) SPUtils.getParam(mContext, Constants.PHONE, "");
        initData();
        initView();
        return view;
    }

    private void initView() {
        mAdapter = new CommonAdapter<Collection.CollectionsBean>(mContext, R.layout.collection_item, collectionsBeanList) {
            @Override
            public void setData(ViewHolder holder, Collection.CollectionsBean collectionsBean) {
                holder.setImageWithUrl(R.id.cultural_item_iv, collectionsBean.getImg_url());
                holder.setText(R.id.cultural_title_tv, collectionsBean.getPost_id());
                holder.setText(R.id.cultural_time_tv, "收藏时间：" + collectionsBean.getCreated_time());


            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mContext, mAdapter);

        culturalLoveRecyclerview.setAdapter(mLRecyclerViewAdapter);
        //setLayoutManager
        culturalLoveRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        culturalLoveRecyclerview.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(mContext, MuseumDetailActivity.class);
                intent.putExtra("museum_name", collectionsBeanList.get(i).getPost_id());
                intent.putExtra("post_type", TYPE);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int i) {
                Map<String, Object> map = new HashMap<>();
                map.put("user_phone", user_phone);
                map.put("post_id", collectionsBeanList.get(i).getPost_id());
                index = i;
                mCollectionPresenter.deleteCollection(map);

            }
        });
        culturalLoveRecyclerview.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mCollectionPresenter.refreshCollectionData(user_phone, TYPE, page + "");
            }

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(culturalLoveRecyclerview);
                if (state == LoadingFooter.State.Loading) {
                    return;
                }
                page++;
                mCollectionPresenter.loadMoreCollectionData(user_phone, TYPE, page + "");
            }

            @Override
            public void onScrolled(int i, int i1) {

            }
        });
    }

    private void initData() {
        mCollectionPresenter = new CollectionPresenterImpl(this);
        mCollectionPresenter.onInitCollectionData(user_phone, TYPE, page + "");
    }

    @Override
    public void initCollectionData(List<Collection.CollectionsBean> collectionsBeans) {
        if (null != collectionsBeans) {
            collectionsBeanList.clear();
            collectionsBeanList.addAll(collectionsBeans);
            culturalLoveRecyclerview.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("数据为空");
            dialog.show();
        }
    }

    @Override
    public void refreshCollectionData(List<Collection.CollectionsBean> collectionsBeans) {
        if (null != collectionsBeans) {
            collectionsBeanList.clear();
            collectionsBeanList.addAll(collectionsBeans);
            culturalLoveRecyclerview.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("数据为空");
            dialog.show();
        }
    }

    @Override
    public void loadMoreCollectionData(List<Collection.CollectionsBean> collectionsBeans) {
        if (null != collectionsBeans) {
            collectionsBeanList.addAll(collectionsBeans);
            culturalLoveRecyclerview.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            culturalLoveRecyclerview.refreshComplete();
            mAdapter.notifyDataSetChanged();
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("数据加载完啦");
            dialog.show();
        }
    }

    @Override
    public void onDeleteCollectionSuccess(ResultCode resultCode) {
        if (resultCode.code == 200) {
            collectionsBeanList.remove(index);
            mAdapter.notifyDataSetChanged();
        }
        ToastUtil.showToast(mContext, resultCode.msg);
    }

    @Override
    public void showDialog() {
        dialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText("加载中");
        dialog.show();
    }

    @Override
    public void hideDialog() {
        if (null != dialog) {
            dialog.dismiss();
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }
}
