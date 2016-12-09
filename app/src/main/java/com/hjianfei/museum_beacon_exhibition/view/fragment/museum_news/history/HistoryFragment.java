package com.hjianfei.museum_beacon_exhibition.view.fragment.museum_news.history;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hjianfei.museum_beacon_exhibition.R;
import com.hjianfei.museum_beacon_exhibition.utils.widget.MetroImageView;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_dynasty.ChinaDynastyActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_hey_day.ChinaHistoryHeyDayActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.china_history_war.ChinaHistoryWarActivity;
import com.hjianfei.museum_beacon_exhibition.view.activity.history_check.HistoryCheckActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HistoryFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.china_history)
    MetroImageView chinaHistory;
    @BindView(R.id.china_hey_day)
    MetroImageView chinaHeyDay;
    @BindView(R.id.china_history_war)
    MetroImageView chinaHistoryWar;
    @BindView(R.id.china_history_big_thing)
    MetroImageView chinaHistoryBigThing;

    private String mParam1;
    private String mParam2;
    private Context mContext;


    public HistoryFragment() {
    }

    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
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
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.china_history, R.id.china_hey_day, R.id.china_history_war, R.id.china_history_big_thing})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.china_history:
                Intent intent = new Intent(mContext, ChinaDynastyActivity.class);
                startActivity(intent);
                break;
            case R.id.china_hey_day:
                Intent intent2 = new Intent(mContext, ChinaHistoryHeyDayActivity.class);
                startActivity(intent2);
                break;
            case R.id.china_history_war:
                Intent intent1 = new Intent(mContext, ChinaHistoryWarActivity.class);
                startActivity(intent1);
                break;
            case R.id.china_history_big_thing:
                Intent intent3 = new Intent(mContext, HistoryCheckActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
