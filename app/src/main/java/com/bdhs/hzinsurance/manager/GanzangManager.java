package com.bdhs.hzinsurance.manager;

import com.bdhs.hzinsurance.config.InsuranceCategory;
import com.bdhs.hzinsurance.manager.base.BaseDepartManager;

public class GanzangManager extends BaseDepartManager {

    public GanzangManager(int depart_level) {
        this.depart_level = depart_level;
    }
    @Override
    public void setPlan(int mPlan) {
        this.mPlan = mPlan;
        switch (depart_level) {
            case InsuranceCategory.GANZANGDepart.FIRST_LEVEL:
                disposeLevelFirst();
                break;
            case InsuranceCategory.GANZANGDepart.SECOND_LEVEL:
                disposeLevelSecond();
                break;
            case InsuranceCategory.GANZANGDepart.THIRD_LEVEL:
                disposeLevelThird();
                break;
            case InsuranceCategory.GANZANGDepart.FOURTH_LEVEL:
                disposeLevelFourth();
                break;
        }
    }


    private void disposeCallback(String fee) {
        String ProductID = InsuranceCategory.GANZANGDepart.getProductID(depart_level,mPlan);
        callback.callback(ProductID,mPlan,fee);
    }
    

    @Override
    protected void disposeLevelFirst() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.GANZANGDepart.GanzangFirstLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.GANZANGDepart.GanzangFirstLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.GANZANGDepart.GanzangFirstLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelSecond() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.GANZANGDepart.GanzangSecondLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.GANZANGDepart.GanzangSecondLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.GANZANGDepart.GanzangSecondLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelThird() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.GANZANGDepart.GanzangThirdLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.GANZANGDepart.GanzangThirdLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.GANZANGDepart.GanzangThirdLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelFourth() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.GANZANGDepart.GanzangFourthLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.GANZANGDepart.GanzangFourthLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.GANZANGDepart.GanzangFourthLevel.expenseC);
                break;
        }
    }
}
