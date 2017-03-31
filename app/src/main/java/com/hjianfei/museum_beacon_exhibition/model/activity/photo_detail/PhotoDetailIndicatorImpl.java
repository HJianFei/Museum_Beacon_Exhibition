package com.hjianfei.museum_beacon_exhibition.model.activity.photo_detail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.canstants.Constants;
import com.hjianfei.museum_beacon_exhibition.utils.LogUtils;
import com.hjianfei.museum_beacon_exhibition.utils.NetWorkUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HJianFei on 2016/12/4.
 */

public class PhotoDetailIndicatorImpl implements PhotoDetailIndicator {
    @Override
    public void savePicFromNet(String picPath, final onFinishedListener listener) {
        NetWorkUtils.getApi().downloadPicFromNet(picPath)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Func1<ResponseBody, ResultCode>() {
//                    @Override
//                    public ResultCode call(ResponseBody responseBody) {
//                        return savePic(responseBody);
//                    }
//                });
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(Constants.TAG, e.toString());

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        ResultCode resultCode = savePic(responseBody);
                        listener.onSaveSuccess(resultCode);

                    }
                });
    }

    private ResultCode savePic(ResponseBody responseBody) {
        ResultCode resultCode = new ResultCode();
        File appDir = new File(Environment.getExternalStorageDirectory(), "museum_exhibition");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(responseBody.byteStream());
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            resultCode.code = 200;
            resultCode.msg = "保存成功";
        } catch (IOException e) {
            resultCode.code = 500;
            resultCode.msg = "保存失败";
            LogUtils.d(Constants.TAG, e.toString());
            e.printStackTrace();
        }
        return resultCode;

    }

}
