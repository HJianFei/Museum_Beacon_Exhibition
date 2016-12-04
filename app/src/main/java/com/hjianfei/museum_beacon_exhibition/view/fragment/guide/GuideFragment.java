package com.hjianfei.museum_beacon_exhibition.view.fragment.guide;


import android.bluetooth.BluetoothAdapter;
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
import android.widget.Toast;

import com.brtbeacon.sdk.BRTBeacon;
import com.brtbeacon.sdk.BRTBeaconManager;
import com.brtbeacon.sdk.BRTThrowable;
import com.brtbeacon.sdk.callback.BRTBeaconManagerListener;
import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.adapter.common.CommonAdapter;
import com.hjianfei.museum_beacon_exhibition.adapter.common.OnItemClickListener;
import com.hjianfei.museum_beacon_exhibition.adapter.common.ViewHolder;
import com.hjianfei.museum_beacon_exhibition.application.BaseApplication;
import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.hjianfei.museum_beacon_exhibition.bean.StepView;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.guide.GuidePresenter;
import com.hjianfei.museum_beacon_exhibition.presenter.fragment.guide.GuidePresenterImpl;
import com.hjianfei.museum_beacon_exhibition.utils.widget.radar_custom_view.RadarView;
import com.hjianfei.museum_beacon_exhibition.view.activity.guide_detail.GuideDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_CANCELED;


public class GuideFragment extends Fragment implements GuideView, BRTBeaconManagerListener {

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
    private BRTBeaconManager beaconManager;
    private Set<BRTBeacon> brtBeacons = BaseApplication.getInstance().getBrtBeacons();
    private GuidePresenter mGuidePresenter;
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
        //检测手机蓝牙是否开启
        checkBlueToothIsOpen();
        initView();
        return view;
    }

    private void initView() {
        mGuidePresenter = new GuidePresenterImpl(this);
        beaconManager = BaseApplication.getInstance().getBRTBeaconManager();
        beaconManager.setBRTBeaconManagerListener(this);
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
                startActivity(intent);

            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

    }

    /**
     * 检测手机蓝牙是否开启
     */
    private void checkBlueToothIsOpen() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            guideInfo.setVisibility(View.VISIBLE);
            guideInfo.setText("使用此功能需要打开蓝牙");
            // 如果本地蓝牙没有开启，则开启
            // 我们通过startActivityForResult()方法发起的Intent将会在onActivityResult()回调方法中获取用户的选择，比如用户单击了Yes开启，
            // 那么将会收到RESULT_OK的结果，
            // 如果RESULT_CANCELED则代表用户不愿意开启蓝牙
            Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(mIntent, Constants.REQUEST_ENABLE_BT);
            // 用enable()方法来开启，无需询问用户(实惠无声息的开启蓝牙设备),这时就需要用到android.permission.BLUETOOTH_ADMIN权限。
            // mBluetoothAdapter.enable();
            // mBluetoothAdapter.disable();//关闭蓝牙

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(mContext, "使用此功能需要打开蓝牙", Toast.LENGTH_SHORT).show();
            } else {
                guideInfo.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void getStepViewSuccess(StepView stepView) {
        step_view.setText(stepView.getStepView().getStep_name());

    }

    @Override
    public void getBeaconAppreciateByMinorSuccess(BeaconAppreciate beaconAppreciate) {

        if (null != beaconAppreciate) {
            mBeaconAppreciateList.add(beaconAppreciate);
            mAdapter.notifyDataSetChanged();
            guide_recyclerview.scrollToPosition(mBeaconAppreciateList.size() - 1);
        }

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onUpdateBeacon(ArrayList<BRTBeacon> arrayList) {

    }

    @Override
    public void onNewBeacon(BRTBeacon brtBeacon) {

        if (brtBeacon.getMajor() == 2001) {//判断大范围的Beacon，一个区域
            mGuidePresenter.getStepView(brtBeacon.getMinor() + "");
        }

        boolean contains = brtBeacons.contains(brtBeacon);

        if (!contains) {//不在里面，新的Beacon
            if (brtBeacon.getMajor() == 2002) {//某一件具体的文物
                brtBeacons.add(brtBeacon);
                mGuidePresenter.getBeaconAppreciateByMinor(brtBeacon.getMinor() + "");
            }
        }

    }

    @Override
    public void onGoneBeacon(BRTBeacon brtBeacon) {

    }

    @Override
    public void onError(BRTThrowable brtThrowable) {

    }

    @Override
    public void onResume() {
        beaconManager.startRanging();
        super.onResume();
    }

    @Override
    public void onPause() {
        beaconManager.stopRanging();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
