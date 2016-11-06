package com.hjianfei.museum_beacon_exhibition.model.activity.exhibition_detail;


import com.hjianfei.museum_beacon_exhibition.bean.ExhibitionDetail;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/9/20.
 */

public class ExhibitionDetailIndicatorImpl implements ExhibitionDetailIndicator {

    @Override
    public void getExhibitionDetail(String detail_url, final onFinishListener listener) {
        NetWorkUtils.getApi().getExhibitionDetails(detail_url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ExhibitionDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();


                    }

                    @Override
                    public void onNext(ExhibitionDetail exhibitionDetail) {
                        listener.onInitExhibitionFinished(exhibitionDetail);

                    }
                });
    }
}
