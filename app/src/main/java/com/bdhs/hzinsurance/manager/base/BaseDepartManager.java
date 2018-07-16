package com.bdhs.hzinsurance.manager.base;

public abstract class BaseDepartManager extends BaseManager {

    protected int depart_level = -1;
    protected int mPlan;

    protected abstract void disposeLevelFirst();
    protected abstract void disposeLevelSecond();
    protected abstract void disposeLevelThird();
    protected abstract void disposeLevelFourth();
}
