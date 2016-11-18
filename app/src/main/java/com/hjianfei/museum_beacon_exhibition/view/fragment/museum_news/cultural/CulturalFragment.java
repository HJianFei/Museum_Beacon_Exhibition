package com.hjianfei.museum_beacon_exhibition.view.fragment.museum_news.cultural;


import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.HeaderSpanSizeLookup;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.util.RecyclerViewUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.museum_news.cultrual.CulturalPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.museum_news.cultrual.CulturalPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.appreciate_detail.AppreciateDetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import me.wangyuwei.flipshare.FlipShareView;
import me.wangyuwei.flipshare.ShareItem;


public class CulturalFragment extends Fragment implements CulturalView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.cultural_recyclerView)
    LRecyclerView culturalRecyclerView;
    @BindView(R.id.cultural_search)
    FloatingActionButton culturalSearch;
    private CommonAdapter<Appreciates.AppreciatesBean> mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private List<Appreciates.AppreciatesBean> appreciatesBeanList = new ArrayList<>();
    private CulturalPresenter mCulturalPresenter;

    private String mParam1;
    private String mParam2;
    private Context mContext;
    private int page = 1;
    private static final String TYPE = "专题鉴赏";
    private SweetAlertDialog dialog;
    private long startTime;
    private long stopTime;
    private String search_condition = "";


    public CulturalFragment() {

    }


    public static CulturalFragment newInstance(String param1, String param2) {
        CulturalFragment fragment = new CulturalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onAttach(Context context) {
        this.mContext = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cultural, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initData() {
        mCulturalPresenter = new CulturalPresenterImpl(this);
        mCulturalPresenter.initAppreciatesData(TYPE, page + "", search_condition);

    }

    private void initView() {
        mAdapter = new CommonAdapter<Appreciates.AppreciatesBean>(mContext, R.layout.cultural_recyclerview_item, appreciatesBeanList) {
            @Override
            public void setData(ViewHolder holder, Appreciates.AppreciatesBean appreciatesBean) {
                holder.setImageWithUrl(R.id.appreciate_item_image, appreciatesBean.getImg_url());
                holder.setText(R.id.appreciate_item_title, appreciatesBean.getContent());

            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mContext, mAdapter);
        View head_view = View.inflate(mContext, R.layout.cultural_head_view, null);
        culturalRecyclerView.setAdapter(mLRecyclerViewAdapter);
        //setLayoutManager
        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        manager.setSpanSizeLookup(new HeaderSpanSizeLookup((LRecyclerViewAdapter) culturalRecyclerView.getAdapter(), manager.getSpanCount()));
        culturalRecyclerView.setLayoutManager(manager);
        culturalRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        culturalRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        RecyclerViewUtils.setHeaderView(culturalRecyclerView, head_view);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(getActivity(), AppreciateDetailActivity.class);
                intent.putExtra("cultural_detail_url", appreciatesBeanList.get(i).getDetail_url());
                intent.putExtra("cultural_name", appreciatesBeanList.get(i).getContent());
                intent.putExtra("post_type", TYPE);
                Map<String, Object> map = new HashMap<>();
                map.put("id", appreciatesBeanList.get(i).getId());
                map.put("view_count", appreciatesBeanList.get(i).getView_count() + 1);
                mCulturalPresenter.updateAppreciateViewCount(map);
                intent.putExtra("post_type", TYPE);
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                view.findViewById(R.id.appreciate_item_image), getString(R.string.transition));
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });
        culturalRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                page = 1;
                search_condition = "";
                mCulturalPresenter.refreshAppreciatesData(TYPE, page + "", search_condition);
            }

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(culturalRecyclerView);
                if (state == LoadingFooter.State.Loading) {
                    return;
                }
                page++;
                mCulturalPresenter.loadAppreciatesMore(TYPE, page + "", search_condition);
            }

            @Override
            public void onScrolled(int i, int i1) {

            }
        });
    }

    @Override
    public void initCulturalData(List<Appreciates.AppreciatesBean> appreciatesBeans) {
        if (null != appreciatesBeans) {
            appreciatesBeanList.clear();
            appreciatesBeanList.addAll(appreciatesBeans);
            culturalRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("搜索结果为空");
            dialog.show();
        }

    }

    @Override
    public void loadMoreCulturalData(List<Appreciates.AppreciatesBean> appreciatesBeans) {
        if (null != appreciatesBeans) {
            appreciatesBeanList.addAll(appreciatesBeans);
            culturalRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            culturalRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("数据加载完啦");
            dialog.show();
        }
    }

    @Override
    public void refreshCulturalData(List<Appreciates.AppreciatesBean> appreciatesBeans) {
        if (null != appreciatesBeans) {
            appreciatesBeanList.clear();
            appreciatesBeanList.addAll(appreciatesBeans);
            culturalRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("搜索结果为空");
            dialog.show();
        }
    }


    @Override
    public void showDialog() {
        dialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText("加载中");
        dialog.show();
        startTime = SystemClock.currentThreadTimeMillis();
    }

    @Override
    public void hideDialog() {
//        stopTime = SystemClock.currentThreadTimeMillis();
//        if (stopTime - startTime > 500) {
        if (null != dialog) {
            dialog.dismiss();
        }
//        } else {
//            new Handler().postDelayed(new Runnable() {
//                public void run() {
//                    if (null != dialog) {
//                        dialog.dismiss();
//                    }
//                }
//            }, 500);
//        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @OnClick(R.id.cultural_search)
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.cultural_search:
                FlipShareView flipShareView = new FlipShareView.Builder(getActivity(), culturalSearch)
                        .addItem(new ShareItem("刷新", Color.WHITE, 0xfffbc402, BitmapFactory.decodeResource(getResources(), R.drawable.icon_refresh)))
                        .addItem(new ShareItem("搜索", Color.WHITE, 0xfffbc402, BitmapFactory.decodeResource(getResources(), R.drawable.icon_search)))
                        .addItem(new ShareItem("国内", Color.WHITE, 0xfffbc402, BitmapFactory.decodeResource(getResources(), R.drawable.icon_china)))
                        .addItem(new ShareItem("国外", Color.WHITE, 0xfffbc402, BitmapFactory.decodeResource(getResources(), R.drawable.icon_abroad)))
                        .setAnimType(FlipShareView.TYPE_SLIDE)
                        .setItemDuration(200)
                        .setSeparateLineColor(Color.WHITE)
                        .create();
                flipShareView.setOnFlipClickListener(new FlipShareView.OnFlipClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (position == 0) {
                            page = 1;
                            mCulturalPresenter.refreshAppreciatesData(TYPE, page + "", search_condition);

                        } else if (position == 1) {
                            new MaterialDialog.Builder(getActivity())
                                    .title("搜索")
                                    .titleColor(getResources().getColor(R.color.primary))
                                    .input("请输入关键字", "", new MaterialDialog.InputCallback() {
                                        @Override
                                        public void onInput(MaterialDialog dialog, CharSequence input) {
                                            if (!TextUtils.isEmpty(input)) {
                                                search_condition = input.toString().trim();
                                                page = 1;
                                                mCulturalPresenter.initAppreciatesData(TYPE, page + "", search_condition);
                                            }
                                        }
                                    }).show();

                        } else if (position == 2) {

                        } else if (position == 3) {

                        }
                    }

                    @Override
                    public void dismiss() {

                    }
                });
                break;
        }

    }

}
