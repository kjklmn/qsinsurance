package com.bdhs.hzinsurance.manager;

import com.bdhs.hzinsurance.config.InsuranceCategory;
import com.bdhs.hzinsurance.manager.base.BaseDepartManager;

public class FukeManager extends BaseDepartManager {

    public FukeManager(int depart_level) {
        this.depart_level = depart_level;
    }
    @Override
    public void setPlan(int mPlan) {
        this.mPlan = mPlan;
        switch (depart_level) {
            case InsuranceCategory.FUKEDepart.FIRST_LEVEL:
                disposeLevelFirst();
                break;
            case InsuranceCategory.FUKEDepart.SECOND_LEVEL:
                disposeLevelSecond();
                break;
            case InsuranceCategory.FUKEDepart.THIRD_LEVEL:
                disposeLevelThird();
                break;
            case InsuranceCategory.FUKEDepart.FOURTH_LEVEL:
                disposeLevelFourth();
                break;
        }
    }

    private void disposeCallback(String fee) {
        String ProductID = InsuranceCategory.FUKEDepart.getProductID(depart_level,mPlan);
        callback.callback(ProductID,mPlan,fee);
    }
    
    @Override
    protected void disposeLevelFirst() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.FUKEDepart.FirstLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.FUKEDepart.FirstLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.FUKEDepart.FirstLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelSecond() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.FUKEDepart.SecondLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.FUKEDepart.SecondLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.FUKEDepart.SecondLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelThird() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.FUKEDepart.ThirdLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.FUKEDepart.ThirdLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.FUKEDepart.ThirdLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelFourth() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.FUKEDepart.FourthLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.FUKEDepart.FourthLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.FUKEDepart.FourthLevel.expenseC);
                break;
        }
    }
}
