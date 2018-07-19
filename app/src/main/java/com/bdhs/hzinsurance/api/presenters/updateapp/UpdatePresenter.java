package com.bdhs.hzinsurance.api.presenters.updateapp;

import com.bdhs.hzinsurance.api.presenters.BasePresenter;
import com.bdhs.hzinsurance.utils.LogUtils;

import java.util.HashMap;

import rx.Subscriber;

/**
 * Created by SEELE on 2018/4/20.
 */

public class UpdatePresenter extends BasePresenter<IUpdateView> {
    public UpdatePresenter(IUpdateView view) {
        super(view);
    }

    public void getNewVersion(HashMap<String,Object> params) {
        addSubscription(mApiService.checkAPPVersion(params), new Subscriber<CheckVersionEntity>() {
            @Override
            public void onCompleted() {
                LogUtils.e("kejian","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("kejian",e.getMessage());
                mView.onError(e);
            }

            @Override
            public void onNext(CheckVersionEntity response) {
                LogUtils.e("kejian",response.toString());
                mView.onGetNewVersion(response);
            }
        });
    }
}
