package com.bdhs.hzinsurance.manager;

import android.widget.TextView;

import com.bdhs.hzinsurance.config.InsuranceCategory;
import com.bdhs.hzinsurance.manager.base.BaseManager;
import com.bdhs.hzinsurance.callback.CostCallback;

public class Manager extends BaseManager {
    private int depart = -1;//什么科
    private int depart_level = -1;//几级意外
    BaseManager mBaseManager;
    public Manager(int depart,int depart_level,CostCallback costCallback) {
        this.depart = depart;
        this.depart_level = depart_level;
        initManager();
        setCallback(costCallback);
    }
    public void setCallback(CostCallback callback) {
        if (mBaseManager != null) {
            mBaseManager.setCallback(callback);
        }
    }

    public void setPlan(int mPlan) {
        if (mBaseManager != null) {
            mBaseManager.setPlan(mPlan);
        }
    }

    private void initManager() {
        switch (depart) {
            case InsuranceCategory.Depart.PUWAI:
                mBaseManager = new PuwaiManager(depart_level);
                break;
            case InsuranceCategory.Depart.BONE:
                mBaseManager = new BoneManager(depart_level);
                break;
            case InsuranceCategory.Depart.MINIAO:
                mBaseManager = new MiniaoManager(depart_level);
                break;
            case InsuranceCategory.Depart.GANZANG:
                mBaseManager = new GanzangManager(depart_level);
                break;
            case InsuranceCategory.Depart.MUYIN:
                mBaseManager = new MuyingManager(depart_level);
                break;
            case InsuranceCategory.Depart.XINXIONG:
                mBaseManager = new XinxiongManager(depart_level);
                break;
            case InsuranceCategory.Depart.GANGCHANG:
                mBaseManager = new GangchangManager(depart_level);
                break;
            case InsuranceCategory.Depart.FUKE:
                mBaseManager = new FukeManager(depart_level);
                break;
            case InsuranceCategory.Depart.DANDAO:
                mBaseManager = new DandaoManager(depart_level);
                break;
            case InsuranceCategory.Depart.YANKE:
                mBaseManager = new YankeManager(depart_level);
                break;
            case InsuranceCategory.Depart.JIERU:
                mBaseManager = new JieruManager(depart_level);
                break;
        }
    }


    public void setTitle(TextView tvLevel) {
        String title = "";
        switch (depart) {
            case InsuranceCategory.Depart.PUWAI:
                title = title+"普外手术安心保障险";

                break;
            case InsuranceCategory.Depart.BONE:
                title = title+"骨科手术安心保障险";

                break;
            case InsuranceCategory.Depart.MINIAO:
                title = title+"尿泌手术安心保障险";

                break;
            case InsuranceCategory.Depart.GANZANG:
                title = title+"肝脏手术安心保障险";

                break;
            case InsuranceCategory.Depart.MUYIN:
                title = title+"母婴安康生育险";

                break;
            case InsuranceCategory.Depart.XINXIONG:
                title = title+"心胸外科手术安心保障险";

                break;
            case InsuranceCategory.Depart.GANGCHANG:
                title = title+"肛肠手术安心保障险";

                break;
            case InsuranceCategory.Depart.FUKE:
                title = title+"妇科手术安心保障险";

                break;
            case InsuranceCategory.Depart.DANDAO:
                title = title+"胆道胆囊手术安心保障险";

                break;
            case InsuranceCategory.Depart.YANKE:
                title = title+"眼科手术安心保障险";

                break;
            case InsuranceCategory.Depart.JIERU:
                title = title+"介入诊疗手术安心保障险";

                break;
        }
        tvLevel.setText(title);
    }

//    public void setTitle(TextView tvLevel) {
//        String title = "";
//        switch (depart) {
//            case InsuranceCategory.Depart.PUWAI:
//                title = title+"普外手术";
//                switch (depart_level) {
//                    case InsuranceCategory.PUWAIDepart.FIRST_LEVEL:
//                        title = title+"一级意外";
//                        break;
//                    case InsuranceCategory.PUWAIDepart.SECOND_LEVEL:
//                        title = title+"二级意外";
//                        break;
//                    case InsuranceCategory.PUWAIDepart.THIRD_LEVEL:
//                        title = title+"三级意外";
//                        break;
//                    case InsuranceCategory.PUWAIDepart.FOURTH_LEVEL:
//                        title = title+"四级意外";
//                        break;
//                }
//                break;
//            case InsuranceCategory.Depart.BONE:
//                title = title+"骨科手术";
//                switch (depart_level) {
//                    case InsuranceCategory.BoneDepart.FIRST_LEVEL:
//                        title = title+"一级意外";
//                        break;
//                    case InsuranceCategory.BoneDepart.SECOND_LEVEL:
//                        title = title+"二级意外";
//                        break;
//                    case InsuranceCategory.BoneDepart.THIRD_LEVEL:
//                        title = title+"三级意外";
//                        break;
//                    case InsuranceCategory.BoneDepart.FOURTH_LEVEL:
//                        title = title+"四级意外";
//                        break;
//                }
//                break;
//            case InsuranceCategory.Depart.MINIAO:
//                title = title+"泌尿手术";
//                switch (depart_level) {
//                    case InsuranceCategory.MINIAODepart.FIRST_LEVEL:
//                        title = title+"一级意外";
//                        break;
//                    case InsuranceCategory.MINIAODepart.SECOND_LEVEL:
//                        title = title+"二级意外";
//                        break;
//                    case InsuranceCategory.MINIAODepart.THIRD_LEVEL:
//                        title = title+"三级意外";
//                        break;
//                    case InsuranceCategory.MINIAODepart.FOURTH_LEVEL:
//                        title = title+"四级意外";
//                        break;
//                }
//                break;
//            case InsuranceCategory.Depart.GANZANG:
//                title = title+"肝脏手术";
//                switch (depart_level) {
//                    case InsuranceCategory.GANZANGDepart.FIRST_LEVEL:
//                        title = title+"一级意外";
//                        break;
//                    case InsuranceCategory.GANZANGDepart.SECOND_LEVEL:
//                        title = title+"二级意外";
//                        break;
//                    case InsuranceCategory.GANZANGDepart.THIRD_LEVEL:
//                        title = title+"三级意外";
//                        break;
//                    case InsuranceCategory.GANZANGDepart.FOURTH_LEVEL:
//                        title = title+"四级意外";
//                        break;
//                }
//                break;
//            case InsuranceCategory.Depart.MUYIN:
//                title = title+"母婴安康生育";
//                switch (depart_level) {
//                    case InsuranceCategory.MUYINGDepart.FIRST_LEVEL:
//                        title = title+"一级意外";
//                        break;
//                    case InsuranceCategory.MUYINGDepart.SECOND_LEVEL:
//                        title = title+"二级意外";
//                        break;
//                    case InsuranceCategory.MUYINGDepart.THIRD_LEVEL:
//                        title = title+"三级意外";
//                        break;
//                    case InsuranceCategory.MUYINGDepart.FOURTH_LEVEL:
//                        title = title+"四级意外";
//                        break;
//                }
//                break;
//            case InsuranceCategory.Depart.XINXIONG:
//                title = title+"心胸外科手术";
//                switch (depart_level) {
//                    case InsuranceCategory.XINXIONGDepart.FIRST_LEVEL:
//                        title = title+"一级意外";
//                        break;
//                    case InsuranceCategory.XINXIONGDepart.SECOND_LEVEL:
//                        title = title+"二级意外";
//                        break;
//                    case InsuranceCategory.XINXIONGDepart.THIRD_LEVEL:
//                        title = title+"三级意外";
//                        break;
//                    case InsuranceCategory.XINXIONGDepart.FOURTH_LEVEL:
//                        title = title+"四级意外";
//                        break;
//                }
//                break;
//            case InsuranceCategory.Depart.GANGCHANG:
//                title = title+"肛肠手术";
//                switch (depart_level) {
//                    case InsuranceCategory.GANGCHANGDepart.FIRST_LEVEL:
//                        title = title+"一级意外";
//                        break;
//                    case InsuranceCategory.GANGCHANGDepart.SECOND_LEVEL:
//                        title = title+"二级意外";
//                        break;
//                    case InsuranceCategory.GANGCHANGDepart.THIRD_LEVEL:
//                        title = title+"三级意外";
//                        break;
//                    case InsuranceCategory.GANGCHANGDepart.FOURTH_LEVEL:
//                        title = title+"四级意外";
//                        break;
//                }
//                break;
//            case InsuranceCategory.Depart.FUKE:
//                title = title+"妇科手术";
//                switch (depart_level) {
//                    case InsuranceCategory.FUKEDepart.FIRST_LEVEL:
//                        title = title+"一级意外";
//                        break;
//                    case InsuranceCategory.FUKEDepart.SECOND_LEVEL:
//                        title = title+"二级意外";
//                        break;
//                    case InsuranceCategory.FUKEDepart.THIRD_LEVEL:
//                        title = title+"三级意外";
//                        break;
//                    case InsuranceCategory.FUKEDepart.FOURTH_LEVEL:
//                        title = title+"四级意外";
//                        break;
//                }
//                break;
//            case InsuranceCategory.Depart.DANDAO:
//                title = title+"胆道胆囊手术";
//                switch (depart_level) {
//                    case InsuranceCategory.DANDAODepart.FIRST_LEVEL:
//                        title = title+"一级意外";
//                        break;
//                    case InsuranceCategory.DANDAODepart.SECOND_LEVEL:
//                        title = title+"二级意外";
//                        break;
//                    case InsuranceCategory.DANDAODepart.THIRD_LEVEL:
//                        title = title+"三级意外";
//                        break;
//                    case InsuranceCategory.DANDAODepart.FOURTH_LEVEL:
//                        title = title+"四级意外";
//                        break;
//                }
//                break;
//            case InsuranceCategory.Depart.YANKE:
//                title = title+"眼科手术";
//                switch (depart_level) {
//                    case InsuranceCategory.YANKEDepart.FIRST_LEVEL:
//                        title = title+"一级意外";
//                        break;
//                    case InsuranceCategory.YANKEDepart.SECOND_LEVEL:
//                        title = title+"二级意外";
//                        break;
//                    case InsuranceCategory.YANKEDepart.THIRD_LEVEL:
//                        title = title+"三级意外";
//                        break;
//                    case InsuranceCategory.YANKEDepart.FOURTH_LEVEL:
//                        title = title+"四级意外";
//                        break;
//                }
//                break;
//            case InsuranceCategory.Depart.JIERU:
//                title = title+"介入手术";
//                switch (depart_level) {
//                    case InsuranceCategory.JIERUDepart.FIRST_LEVEL:
//                        title = title+"一级意外";
//                        break;
//                    case InsuranceCategory.JIERUDepart.SECOND_LEVEL:
//                        title = title+"二级意外";
//                        break;
//                    case InsuranceCategory.JIERUDepart.THIRD_LEVEL:
//                        title = title+"三级意外";
//                        break;
//                    case InsuranceCategory.JIERUDepart.FOURTH_LEVEL:
//                        title = title+"四级意外";
//                        break;
//                }
//                break;
//        }
//        tvLevel.setText(title);
//    }

}
