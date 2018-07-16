package com.bdhs.hzinsurance.manager;

import com.bdhs.hzinsurance.config.InsuranceCategory;
import com.bdhs.hzinsurance.manager.base.BaseDepartManager;
import com.bdhs.hzinsurance.manager.base.BaseManager;
import com.bdhs.hzinsurance.utils.LogUtils;

public class BoneManager extends BaseDepartManager {
    private static final String TAG = "BoneManager";
    private int depart_level = -1;
    private int mPlan;
    public BoneManager(int depart_level) {
        this.depart_level = depart_level;
    }


    @Override
    public void setPlan(int mPlan) {
        LogUtils.w(TAG,"mPlan = "+mPlan);
        this.mPlan = mPlan;
        switch (depart_level) {
            case InsuranceCategory.BoneDepart.FIRST_LEVEL:
                disposeLevelFirst();
                break;
            case InsuranceCategory.BoneDepart.SECOND_LEVEL:
                disposeLevelSecond();
                break;
            case InsuranceCategory.BoneDepart.THIRD_LEVEL:
                disposeLevelThird();
                break;
            case InsuranceCategory.BoneDepart.FOURTH_LEVEL:
                disposeLevelFourth();
                break;
        }
    }

    private void disposeCallback(String fee) {
        String ProductID = InsuranceCategory.BoneDepart.getProductID(depart_level,mPlan);
        callback.callback(ProductID,mPlan,fee);
    }

    @Override
    protected void disposeLevelFirst() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.BoneDepart.BoneFirstLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.BoneDepart.BoneFirstLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.BoneDepart.BoneFirstLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelSecond() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.BoneDepart.BoneSecondLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.BoneDepart.BoneSecondLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.BoneDepart.BoneSecondLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelThird() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.BoneDepart.BoneThirdLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.BoneDepart.BoneThirdLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.BoneDepart.BoneThirdLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelFourth() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.BoneDepart.BoneFourthLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.BoneDepart.BoneFourthLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.BoneDepart.BoneFourthLevel.expenseC);
                break;
        }
    }
}
