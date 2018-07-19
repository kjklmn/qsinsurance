package com.bdhs.hzinsurance.api.presenters.updateapp;

import com.bdhs.hzinsurance.entity.EvaluateBean;

/**
 * Created by SEELE on 2018/4/20.
 */

public interface IUpdateView {

    void onGetEvaluateSucc(CheckVersionEntity response);

    void  onError(Throwable e);
}
