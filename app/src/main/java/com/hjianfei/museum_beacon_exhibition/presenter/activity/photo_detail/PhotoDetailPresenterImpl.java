package com.hjianfei.museum_beacon_exhibition.presenter.activity.photo_detail;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.model.activity.photo_detail.PhotoDetailIndicator;
import com.hjianfei.museum_beacon_exhibition.model.activity.photo_detail.PhotoDetailIndicatorImpl;
import com.hjianfei.museum_beacon_exhibition.view.activity.photo_detail.PhotoDetailView;

/**
 * Created by HJianFei on 2016/12/4.
 */

public class PhotoDetailPresenterImpl implements PhotoDetailPresenter, PhotoDetailIndicator.onFinishedListener {

    private PhotoDetailView mPhotoDetailView;
    private PhotoDetailIndicator mPhotoDetailIndicator;

    public PhotoDetailPresenterImpl(PhotoDetailView mPhotoDetailView) {
        this.mPhotoDetailView = mPhotoDetailView;
        mPhotoDetailIndicator = new PhotoDetailIndicatorImpl();
    }

    @Override
    public void onSaveSuccess(ResultCode resultCode) {
        if (null != mPhotoDetailView) {
            mPhotoDetailView.onSavePicSuccess(resultCode);
        }

    }

    @Override
    public void savePicFromNet(String picPath) {
        mPhotoDetailIndicator.savePicFromNet(picPath, this);

    }

}
