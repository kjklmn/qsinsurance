package com.bdhs.hzinsurance.manager.base;

import com.bdhs.hzinsurance.callback.CostCallback;

public abstract class BaseManager {
    public CostCallback callback;

    public void setCallback(CostCallback callback) {
        this.callback = callback;
    }

    public abstract void setPlan(int mPlan);
}
