package com.bdhs.hzinsurance.manager;

import com.bdhs.hzinsurance.config.InsuranceCategory;
import com.bdhs.hzinsurance.manager.base.BaseDepartManager;

public class GangchangManager extends BaseDepartManager {

    public GangchangManager(int depart_level) {
        this.depart_level = depart_level;
    }
    @Override
    public void setPlan(int mPlan) {
        this.mPlan = mPlan;
        switch (depart_level) {
            case InsuranceCategory.GANGCHANGDepart.FIRST_LEVEL:
                disposeLevelFirst();
                break;
            case InsuranceCategory.GANGCHANGDepart.SECOND_LEVEL:
                disposeLevelSecond();
                break;
            case InsuranceCategory.GANGCHANGDepart.THIRD_LEVEL:
                disposeLevelThird();
                break;
            case InsuranceCategory.GANGCHANGDepart.FOURTH_LEVEL:
                disposeLevelFourth();
                break;
        }
    }

    private void disposeCallback(String fee) {
        String ProductID = InsuranceCategory.GANGCHANGDepart.getProductID(depart_level,mPlan);
        callback.callback(ProductID,mPlan,fee);
    }
    
    @Override
    protected void disposeLevelFirst() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.GANGCHANGDepart.FirstLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.GANGCHANGDepart.FirstLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.GANGCHANGDepart.FirstLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelSecond() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.GANGCHANGDepart.SecondLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.GANGCHANGDepart.SecondLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.GANGCHANGDepart.SecondLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelThird() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.GANGCHANGDepart.ThirdLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.GANGCHANGDepart.ThirdLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.GANGCHANGDepart.ThirdLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelFourth() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.GANGCHANGDepart.FourthLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.GANGCHANGDepart.FourthLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.GANGCHANGDepart.FourthLevel.expenseC);
                break;
        }
    }
}
