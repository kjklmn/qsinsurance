package com.bdhs.hzinsurance.manager;

import com.bdhs.hzinsurance.config.InsuranceCategory;
import com.bdhs.hzinsurance.manager.base.BaseDepartManager;

public class YankeManager extends BaseDepartManager {

    public YankeManager(int depart_level) {
        this.depart_level = depart_level;
    }
    @Override
    public void setPlan(int mPlan) {
        this.mPlan = mPlan;
        switch (depart_level) {
            case InsuranceCategory.YANKEDepart.FIRST_LEVEL:
                disposeLevelFirst();
                break;
            case InsuranceCategory.YANKEDepart.SECOND_LEVEL:
                disposeLevelSecond();
                break;
            case InsuranceCategory.YANKEDepart.THIRD_LEVEL:
                disposeLevelThird();
                break;
            case InsuranceCategory.YANKEDepart.FOURTH_LEVEL:
                disposeLevelFourth();
                break;
        }
    }

    private void disposeCallback(String fee) {
        String ProductID = InsuranceCategory.YANKEDepart.getProductID(depart_level,mPlan);
        callback.callback(ProductID,mPlan,fee);
    }

    @Override
    protected void disposeLevelFirst() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.YANKEDepart.FirstLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.YANKEDepart.FirstLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.YANKEDepart.FirstLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelSecond() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.YANKEDepart.SecondLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.YANKEDepart.SecondLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.YANKEDepart.SecondLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelThird() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.YANKEDepart.ThirdLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.YANKEDepart.ThirdLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.YANKEDepart.ThirdLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelFourth() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.YANKEDepart.FourthLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.YANKEDepart.FourthLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.YANKEDepart.FourthLevel.expenseC);
                break;
        }
    }
}
