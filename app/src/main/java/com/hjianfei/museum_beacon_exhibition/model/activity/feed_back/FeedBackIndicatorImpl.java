package com.hjianfei.museum_beacon_exhibition.model.activity.feed_back;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/11/21.
 */

public class FeedBackIndicatorImpl implements FeedBackIndicator {
    @Override
    public void saveFeedBack(Map<String, Object> map, final onFinishListener listener) {
        NetWorkUtils.getApi().saveFeedBack(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultCode>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        listener.onError();
                    }

                    @Override
                    public void onNext(ResultCode resultCode) {
                        listener.feedBackInfoFinished(resultCode);
                    }
                });

    }
}
