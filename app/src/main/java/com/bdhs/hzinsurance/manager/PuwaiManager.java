package com.bdhs.hzinsurance.manager;

import com.bdhs.hzinsurance.config.InsuranceCategory;
import com.bdhs.hzinsurance.manager.base.BaseDepartManager;
import com.bdhs.hzinsurance.utils.LogUtils;

public class PuwaiManager extends BaseDepartManager {

    private static final String TAG = "PuwaiManager";
    private int depart_level = -1;
    private int mPlan;
    public PuwaiManager(int depart_level) {
        this.depart_level = depart_level;
    }

    @Override
    public void setPlan(int mPlan) {
        LogUtils.w(TAG,"mPlan = "+mPlan);
        this.mPlan = mPlan;
        switch (depart_level) {
            case InsuranceCategory.PUWAIDepart.FIRST_LEVEL:
                disposeLevelFirst();
                break;
            case InsuranceCategory.PUWAIDepart.SECOND_LEVEL:
                disposeLevelSecond();
                break;
            case InsuranceCategory.PUWAIDepart.THIRD_LEVEL:
                disposeLevelThird();
                break;
            case InsuranceCategory.PUWAIDepart.FOURTH_LEVEL:
                disposeLevelFourth();
                break;
        }
    }

    private void disposeCallback(String fee) {
        String ProductID = InsuranceCategory.PUWAIDepart.getProductID(depart_level,mPlan);
        callback.callback(ProductID,mPlan,fee);
    }

    @Override
    protected void disposeLevelFirst() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.PUWAIDepart.PuwaiFirstLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.PUWAIDepart.PuwaiFirstLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.PUWAIDepart.PuwaiFirstLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelSecond() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.PUWAIDepart.PuwaiSecondLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.PUWAIDepart.PuwaiSecondLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.PUWAIDepart.PuwaiSecondLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelThird() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.PUWAIDepart.PuwaiThirdLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.PUWAIDepart.PuwaiThirdLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.PUWAIDepart.PuwaiThirdLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelFourth() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.PUWAIDepart.PuwaiFourthLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.PUWAIDepart.PuwaiFourthLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.PUWAIDepart.PuwaiFourthLevel.expenseC);
                break;
        }
    }
}
