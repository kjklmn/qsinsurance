package com.bdhs.hzinsurance.api.presenters.evaluate;

import com.bdhs.hzinsurance.api.presenters.BasePresenter;
import com.bdhs.hzinsurance.entity.EvaluateBean;
import com.bdhs.hzinsurance.utils.LogUtils;

import rx.Subscriber;

/**
 * Created by SEELE on 2018/4/20.
 */

public class EvaluatePresenter extends BasePresenter<IEvaluateView> {
    public EvaluatePresenter(IEvaluateView view) {
        super(view);
    }

    public void getEvaluate(String phoneNum) {
        addSubscription(mApiService.getEvaluate(phoneNum), new Subscriber<EvaluateBean>() {
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
            public void onNext(EvaluateBean response) {
                LogUtils.e("kejian",response.toString());
                mView.onGetEvaluateSucc(response);
            }
        });
    }
}
