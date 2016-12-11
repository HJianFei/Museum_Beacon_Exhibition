//package com.hjianfei.museum_beacon_exhibition.view.fragment.appreciate.thematic_appreciation;
//
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.BitmapFactory;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.os.SystemClock;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.ActivityOptionsCompat;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.GridLayoutManager;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.afollestad.materialdialogs.MaterialDialog;
//import com.github.jdsjlzx.interfaces.OnItemClickListener;
//import com.github.jdsjlzx.recyclerview.HeaderSpanSizeLookup;
//import com.github.jdsjlzx.recyclerview.LRecyclerView;
//import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
//import com.github.jdsjlzx.recyclerview.ProgressStyle;
//import com.github.jdsjlzx.util.RecyclerViewStateUtils;
//import com.github.jdsjlzx.view.LoadingFooter;
//import com.hjianfei.museum_beacon_exhibition.R;
//import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
//import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
//import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
//import com.hjianfei.museum_beacon_exhibition.presenter.activity.appreciate.AppreciatePresenter;
//import com.hjianfei.museum_beacon_exhibition.presenter.activity.appreciate.AppreciatePresenterImpl;
//import com.hjianfei.museum_beacon_exhibition.view.activity.appreciate_detail.AppreciateDetailActivity;
//import com.hjianfei.museum_beacon_exhibition.view.fragment.appreciate.AppreciateView;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import cn.pedant.SweetAlert.SweetAlertDialog;
//import me.wangyuwei.flipshare.FlipShareView;
//import me.wangyuwei.flipshare.ShareItem;
//
//
//public class Thematic_appreciationFragment extends Fragment implements AppreciateView {
//
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//    @BindView(R.id.thematic_appreciation_recyclerView)
//    LRecyclerView thematicAppreciationRecyclerView;
//    @BindView(R.id.thematic_appreciation_search)
//    FloatingActionButton thematicAppreciationSearch;
//    private CommonAdapter<Appreciates.AppreciatesBean> mAdapter;
//    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
//    private List<Appreciates.AppreciatesBean> appreciatesBeanList = new ArrayList<>();
//    private AppreciatePresenter mAppreciatePresenter;
//    private Context mContext;
//
//
//    private String mParam1;
//    private String mParam2;
//    private int page = 1;
//    private static final String TYPE = "专题鉴赏";
//    private SweetAlertDialog dialog;
//    private long startTime;
//    private long stopTime;
//    private String search_condition = "";
//
//
//    public Thematic_appreciationFragment() {
//
//    }
//
//
//    public static Thematic_appreciationFragment newInstance(String param1, String param2) {
//        Thematic_appreciationFragment fragment = new Thematic_appreciationFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        this.mContext = context;
//        super.onAttach(context);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_thematic_appreciation, container, false);
//        ButterKnife.bind(this, view);
//        initData();
//        initView();
//        return view;
//    }
//
//    private void initView() {
//        mAdapter = new CommonAdapter<Appreciates.AppreciatesBean>(mContext, R.layout.cultural_recyclerview_item, appreciatesBeanList) {
//            @Override
//            public void setData(ViewHolder holder, Appreciates.AppreciatesBean appreciatesBean) {
//                holder.setImageWithUrl(R.id.appreciate_item_image, appreciatesBean.getImg_url());
//                holder.setText(R.id.appreciate_item_title, appreciatesBean.getContent());
//
//            }
//        };
//        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mContext, mAdapter);
//        thematicAppreciationRecyclerView.setAdapter(mLRecyclerViewAdapter);
//        //setLayoutManager
//        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
//        manager.setSpanSizeLookup(new HeaderSpanSizeLookup((LRecyclerViewAdapter) thematicAppreciationRecyclerView.getAdapter(), manager.getSpanCount()));
//        thematicAppreciationRecyclerView.setLayoutManager(manager);
//        thematicAppreciationRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        thematicAppreciationRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
//        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int i) {
//                Intent intent = new Intent(getActivity(), AppreciateDetailActivity.class);
//                intent.putExtra("cultural_detail_url", appreciatesBeanList.get(i).getDetail_url());
//                intent.putExtra("cultural_name", appreciatesBeanList.get(i).getContent());
//                intent.putExtra("post_type", TYPE);
//                Map<String, Object> map = new HashMap<>();
//                map.put("id", appreciatesBeanList.get(i).getId());
//                map.put("view_count", appreciatesBeanList.get(i).getView_count() + 1);
//                mAppreciatePresenter.updateAppreciateViewCount(map);
//                ActivityOptionsCompat options =
//                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
//                                view.findViewById(R.id.appreciate_item_image), getString(R.string.transition));
//                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
//            }
//
//            @Override
//            public void onItemLongClick(View view, int i) {
//
//            }
//        });
//        thematicAppreciationRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
//            @Override
//            public void onRefresh() {
//                page = 1;
//                search_condition = "";
//                mAppreciatePresenter.refreshAppreciatesData(TYPE, page + "", search_condition);
//
//            }
//
//            @Override
//            public void onScrollUp() {
//
//            }
//
//            @Override
//            public void onScrollDown() {
//
//            }
//
//            @Override
//            public void onBottom() {
//                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(thematicAppreciationRecyclerView);
//                if (state == LoadingFooter.State.Loading) {
//                    return;
//                }
//                page++;
//                mAppreciatePresenter.loadMoreAppreciatesData(TYPE, page + "", search_condition);
//            }
//
//            @Override
//            public void onScrolled(int i, int i1) {
//
//            }
//        });
//    }
//
//    private void initData() {
//        mAppreciatePresenter = new AppreciatePresenterImpl(this);
//        mAppreciatePresenter.onInitAppreciateData(TYPE, page + "", search_condition);
//    }
//
//    @Override
//    public void initAppreciateData(List<Appreciates.AppreciatesBean> appreciatesBeans) {
//        if (null != appreciatesBeans) {
//            appreciatesBeanList.clear();
//            appreciatesBeanList.addAll(appreciatesBeans);
//            thematicAppreciationRecyclerView.refreshComplete();
//            mAdapter.notifyDataSetChanged();
//        } else {
//            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
//            dialog.setTitleText("搜索结果为空");
//            dialog.show();
//        }
//    }
//
//    @Override
//    public void refreshAppreciateData(List<Appreciates.AppreciatesBean> appreciatesBeans) {
//        if (null != appreciatesBeans) {
//            appreciatesBeanList.clear();
//            appreciatesBeanList.addAll(appreciatesBeans);
//            thematicAppreciationRecyclerView.refreshComplete();
//            mAdapter.notifyDataSetChanged();
//        } else {
//            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
//            dialog.setTitleText("搜索结果为空");
//            dialog.show();
//        }
//    }
//
//    @Override
//    public void loadMoreAppreciateData(List<Appreciates.AppreciatesBean> appreciatesBeans) {
//        if (null != appreciatesBeans) {
//            appreciatesBeanList.addAll(appreciatesBeans);
//            thematicAppreciationRecyclerView.refreshComplete();
//            mAdapter.notifyDataSetChanged();
//        } else {
//            thematicAppreciationRecyclerView.refreshComplete();
//            mAdapter.notifyDataSetChanged();
//            dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
//            dialog.setTitleText("数据加载完啦");
//            dialog.show();
//        }
//    }
//
//    @Override
//    public void showDialog() {
//        dialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
//        dialog.setTitleText("加载中");
//        dialog.show();
//        startTime = SystemClock.currentThreadTimeMillis();
//    }
//
//    @Override
//    public void hideDialog() {
////        stopTime = SystemClock.currentThreadTimeMillis();
////        if (stopTime - startTime > 500) {
//        if (null != dialog) {
//            dialog.dismiss();
//        }
////        } else {
////            new Handler().postDelayed(new Runnable() {
////                public void run() {
////                    if (null != dialog) {
////                        dialog.dismiss();
////                    }
////                }
////            }, 500);
////        }
//    }
//
//    @Override
//    public void showError() {
//
//    }
//
//    @Override
//    public void showEmpty() {
//
//    }
//
//    @OnClick(R.id.thematic_appreciation_search)
//    public void onClickListener(View v) {
//        switch (v.getId()) {
//            case R.id.thematic_appreciation_search:
//                FlipShareView flipShareView = new FlipShareView.Builder(getActivity(), thematicAppreciationSearch)
//                        .addItem(new ShareItem("刷新", Color.WHITE, 0xfffbc402, BitmapFactory.decodeResource(getResources(), R.drawable.icon_refresh)))
//                        .addItem(new ShareItem("搜索", Color.WHITE, 0xfffbc402, BitmapFactory.decodeResource(getResources(), R.drawable.icon_search)))
//                        .addItem(new ShareItem("国内", Color.WHITE, 0xfffbc402, BitmapFactory.decodeResource(getResources(), R.drawable.icon_china)))
//                        .addItem(new ShareItem("国外", Color.WHITE, 0xfffbc402, BitmapFactory.decodeResource(getResources(), R.drawable.icon_abroad)))
//                        .setAnimType(FlipShareView.TYPE_HORIZONTAL)
//                        .setItemDuration(100)
//                        .setSeparateLineColor(Color.WHITE)
//                        .create();
//                flipShareView.setOnFlipClickListener(new FlipShareView.OnFlipClickListener() {
//                    @Override
//                    public void onItemClick(int position) {
//                        if (position == 0) {
//                            page = 1;
//                            mAppreciatePresenter.refreshAppreciatesData(TYPE, page + "", search_condition);
//
//                        } else if (position == 1) {
//                            new MaterialDialog.Builder(getActivity())
//                                    .title("搜索")
//                                    .titleColor(getResources().getColor(R.color.primary))
//                                    .input("请输入关键字", "", new MaterialDialog.InputCallback() {
//                                        @Override
//                                        public void onInput(MaterialDialog dialog, CharSequence input) {
//                                            if (!TextUtils.isEmpty(input)) {
//                                                search_condition = input.toString().trim();
//                                                page = 1;
//                                                mAppreciatePresenter.onInitAppreciateData(TYPE, page + "", search_condition);
//                                            }
//                                        }
//                                    }).show();
//
//                        } else if (position == 2) {
//
//                        } else if (position == 3) {
//
//                        }
//                    }
//
//                    @Override
//                    public void dismiss() {
//
//                    }
//                });
//                break;
//        }
//
//    }
//}
