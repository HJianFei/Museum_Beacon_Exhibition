package com.hjianfei.museum_beacon_exhibition.model.fragment.home;

import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.bean.Exhibitions;
import com.hjianfei.museum_beacon_exhibition.bean.ViewPager;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/11/3.
 */

public class HomeIndicatorImpl implements HomeIndicator {
    @Override
    public void getViewPagerData(final onFinishedListener listener) {
        NetWorkUtils.getApi().getViewPager()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ViewPager>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.OnError();

                    }

                    @Override
                    public void onNext(ViewPager viewPager) {
                        listener.OnViewPagerFinished(viewPager);

                    }
                });

    }

    @Override
    public void getAppreciateRecyclerView(final onFinishedListener listener) {
        NetWorkUtils.getApi().getAllAppreciatesByRandom()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Appreciates>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Appreciates appreciates) {
                        listener.onAppreciateRecyclerViewFinished(appreciates);


                    }
                });

    }

    @Override
    public void getHotExhibition(String type, final onFinishedListener listener) {
        NetWorkUtils.getApi().getAllExhibitionByType(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Exhibitions>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(Constants.TAG, e.toString());
                    }

                    @Override
                    public void onNext(Exhibitions exhibitions) {
                        LogUtils.d(Constants.TAG, exhibitions.toString());
                        listener.onHotExhibitionFinished(exhibitions);

                    }
                });

    }
}
