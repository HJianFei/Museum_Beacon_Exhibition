package com.hjianfei.museum_beacon_exhibition.view.fragment.guide;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.OnItemClickListener;
import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
import com.hjianfei.museum_beacon_exhibition.application.BaseApplication;
import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.hjianfei.museum_beacon_exhibition.bean.StepView;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.widget.radar_custom_view.RadarView;
import com.hjianfei.museum_beacon_exhibition.view.activity.guide_detail.GuideDetailActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GuideFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.id_scan_circle)
    RadarView scan_circle;
    @BindView(R.id.step_view)
    TextView step_view;
    @BindView(R.id.guide_recyclerview)
    RecyclerView guide_recyclerview;
    @BindView(R.id.guide_info)
    TextView guideInfo;
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private List<BeaconAppreciate> mBeaconAppreciateList = BaseApplication.getInstance().getmBeaconAppreciateList();
    private CommonAdapter<BeaconAppreciate> mAdapter;

    public GuideFragment() {
    }


    public static GuideFragment newInstance(String param1, String param2) {
        GuideFragment fragment = new GuideFragment();
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
        EventBus.getDefault().register(this);

    }

    @Override
    public void onAttach(Context context) {
        this.mContext = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mAdapter = new CommonAdapter<BeaconAppreciate>(mContext, R.layout.guide_recyclerview_item, mBeaconAppreciateList) {
            @Override
            public void setData(ViewHolder holder, BeaconAppreciate beaconAppreciate) {
                holder.setImageWithUrl(R.id.guide_item_image, beaconAppreciate.getBeaconAppreciate().getImg_url().split(",")[0].trim());
                holder.setText(R.id.guide_item_title, beaconAppreciate.getBeaconAppreciate().getTitle());
                holder.setVisible(R.id.guide_item_new, beaconAppreciate.getBeaconAppreciate().isIs_new());
            }
        };
        guide_recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        guide_recyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                mBeaconAppreciateList.get(position).getBeaconAppreciate().setIs_new(false);
                view.findViewById(R.id.guide_item_new).setVisibility(View.GONE);
                Intent intent = new Intent(mContext, GuideDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("beaconAppreciate", mBeaconAppreciateList.get(position));
                intent.putExtra("guide_detail", bundle);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        step_view.setText(BaseApplication.getInstance().getStep());

    }

    @Subscribe
    public void eventBeaconMessage(BeaconAppreciate beaconAppreciate) {

        LogUtils.d("onResponse", beaconAppreciate.toString());
//        mBeaconAppreciateList.add(beaconAppreciate);
        mAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void eventStepMessage(StepView stepView) {
        LogUtils.d("onResponse", stepView.toString());
        step_view.setText(stepView.getStepView().getStep_name());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
