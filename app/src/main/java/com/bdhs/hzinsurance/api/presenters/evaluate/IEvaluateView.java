package com.bdhs.hzinsurance.api.presenters.evaluate;

import com.bdhs.hzinsurance.entity.EvaluateBean;

/**
 * Created by SEELE on 2018/4/20.
 */

public interface IEvaluateView {

    void onGetEvaluateSucc(EvaluateBean response);

    void  onError(Throwable e);
}
