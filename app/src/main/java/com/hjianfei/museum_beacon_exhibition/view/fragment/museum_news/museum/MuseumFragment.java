package com.hjianfei.museum_beacon_exhibition.view.fragment.museum_news.museum;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
import com.hjianfei.museum_beacon_exhibition.bean.Museum;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.museum_news.museum.MuseumPresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.museum_news.museum.MuseumPresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.ToastUtil;
import com.hjianfei.museum_beacon_exhibition.view.activity.museum_detail.MuseumDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import me.wangyuwei.flipshare.FlipShareView;
import me.wangyuwei.flipshare.ShareItem;

public class MuseumFragment extends Fragment implements MuseumView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.museum_recyclerView)
    LRecyclerView museumRecyclerView;
    @BindView(R.id.museum_search)
    FloatingActionButton museumSearch;
    @BindView(R.id.museum_root_layout)
    CoordinatorLayout museumRootLayout;
    private Context mContext;
    private CommonAdapter<Museum.MuseumsBean> mAdapter;
    private List<Museum.MuseumsBean> museumsBeanList = new ArrayList<>();
    private MuseumPresenter mMuseumPresenter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;


    private String mParam1;
    private String mParam2;
    private int page = 1;
    private static final String TYPE = "博物馆";
    private SweetAlertDialog dialog;
    private long startTime;
    private long stopTime;


    public MuseumFragment() {

    }


    public static MuseumFragment newInstance(String param1, String param2) {
        MuseumFragment fragment = new MuseumFragment();
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
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_museum, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initData() {
        mMuseumPresenter = new MuseumPresenterImpl(this);
        mMuseumPresenter.initMuseumsData(TYPE, page + "");
    }

    private void initView() {
        mAdapter = new CommonAdapter<Museum.MuseumsBean>(mContext, R.layout.museum_recyclerview_item, museumsBeanList) {
            @Override
            public void setData(ViewHolder holder, Museum.MuseumsBean museumsBean) {
                holder.setImageWithUrl(R.id.museum_item_img, museumsBean.getMuseum_img());
                holder.setText(R.id.museum_item_title, museumsBean.getMuseum_title());
                holder.setText(R.id.museum_item_name, museumsBean.getMuseum_name());

            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mContext, mAdapter);
        museumRecyclerView.setAdapter(mLRecyclerViewAdapter);
        museumRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        museumRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        museumRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(mContext, MuseumDetailActivity.class);
                intent.putExtra("museum_name", museumsBeanList.get(i).getMuseum_name());
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                view.findViewById(R.id.museum_item_img), getString(R.string.transition));
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });
        museumRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mMuseumPresenter.refreshMuseumsData(TYPE, page + "");
            }

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(museumRecyclerView);
                if (state == LoadingFooter.State.Loading) {
                    return;
                }
                page++;
                mMuseumPresenter.loadMuseumsMore(TYPE, page + "");
            }

            @Override
            public void onScrolled(int i, int i1) {

            }
        });


    }

    @Override
    public void initMuseumData(List<Museum.MuseumsBean> museumsBean) {
        museumsBeanList.addAll(museumsBean);
        museumRecyclerView.refreshComplete();
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void loadMoreMuseumData(List<Museum.MuseumsBean> museumsBean) {
        if (null != museumsBean) {
            museumsBeanList.addAll(museumsBean);
            museumRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            museumRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refreshMuseumData(List<Museum.MuseumsBean> museumsBean) {
        museumsBeanList.clear();
        museumsBeanList.addAll(museumsBean);
        museumRecyclerView.refreshComplete();
        mAdapter.notifyDataSetChanged();
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
        stopTime = SystemClock.currentThreadTimeMillis();
        if (stopTime - startTime > 500) {
            if (null != dialog) {
                dialog.dismiss();
            }
        } else {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (null != dialog) {
                        dialog.dismiss();
                    }
                }
            }, 500);
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @OnClick(R.id.museum_search)
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.museum_search:
                FlipShareView flipShareView = new FlipShareView.Builder(getActivity(), museumSearch)
                        .addItem(new ShareItem("刷新", Color.WHITE, 0xff43549C))
                        .addItem(new ShareItem("搜索", Color.WHITE, 0xff4999F0))
                        .addItem(new ShareItem("国内", Color.WHITE, 0xffD9392D))
                        .addItem(new ShareItem("国外", Color.WHITE, 0xff57708A))
                        .setAnimType(FlipShareView.TYPE_SLIDE)
                        .create();
                flipShareView.setOnFlipClickListener(new FlipShareView.OnFlipClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        ToastUtil.showToast(mContext, position + "");
                    }

                    @Override
                    public void dismiss() {

                    }
                });

                break;
        }

    }
}
