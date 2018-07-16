package com.bdhs.hzinsurance.manager;

import com.bdhs.hzinsurance.config.InsuranceCategory;
import com.bdhs.hzinsurance.manager.base.BaseDepartManager;

public class DandaoManager extends BaseDepartManager {

    public DandaoManager(int depart_level) {
        this.depart_level = depart_level;
    }
    @Override
    public void setPlan(int mPlan) {
        this.mPlan = mPlan;
        switch (depart_level) {
            case InsuranceCategory.DANDAODepart.FIRST_LEVEL:
                disposeLevelFirst();
                break;
            case InsuranceCategory.DANDAODepart.SECOND_LEVEL:
                disposeLevelSecond();
                break;
            case InsuranceCategory.DANDAODepart.THIRD_LEVEL:
                disposeLevelThird();
                break;
            case InsuranceCategory.DANDAODepart.FOURTH_LEVEL:
                disposeLevelFourth();
                break;
        }
    }

    private void disposeCallback(String fee) {
        String ProductID = InsuranceCategory.DANDAODepart.getProductID(depart_level,mPlan);
        callback.callback(ProductID,mPlan,fee);
    }

    @Override
    protected void disposeLevelFirst() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.DANDAODepart.FirstLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.DANDAODepart.FirstLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.DANDAODepart.FirstLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelSecond() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.DANDAODepart.SecondLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.DANDAODepart.SecondLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.DANDAODepart.SecondLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelThird() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.DANDAODepart.ThirdLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.DANDAODepart.ThirdLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.DANDAODepart.ThirdLevel.expenseC);
                break;
        }
    }

    @Override
    protected void disposeLevelFourth() {
        switch (mPlan) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                disposeCallback(InsuranceCategory.DANDAODepart.FourthLevel.expenseA);
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                disposeCallback(InsuranceCategory.DANDAODepart.FourthLevel.expenseB);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                disposeCallback(InsuranceCategory.DANDAODepart.FourthLevel.expenseC);
                break;
        }
    }
}
