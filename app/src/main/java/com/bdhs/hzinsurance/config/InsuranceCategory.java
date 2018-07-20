package com.bdhs.hzinsurance.config;

public class InsuranceCategory {

    //测试
    public static final String Base_URL = "http://www3.szhuizhong.cn/insure_non_login/insurance_hospital/";
    public static final String Base_Api_URL = "http://www3.szhuizhong.cn";

//    //生产
//    public static final String Base_URL = "http://mobile.szhuizhong.cn/insure_non_login/insurance_hospital/";

    public static final String DistributorID="10055";

    //总共有11个科室
    public static class Depart {
        public static final int PUWAI = 1;
        public static final int BONE = 2;
        public static final int MINIAO = 3;
        public static final int GANZANG = 4;
        public static final int MUYIN = 5;
        public static final int XINXIONG = 6;
        public static final int GANGCHANG = 7;
        public static final int FUKE = 8;
        public static final int DANDAO = 9;
        public static final int YANKE = 10;
        public static final int JIERU = 11;

        public static int[] getLevels(int departs) {//
            switch (departs) {
                case InsuranceCategory.Depart.PUWAI:
                    return PUWAIDepart.levels;
                case InsuranceCategory.Depart.BONE:
                    return BoneDepart.levels;
                case InsuranceCategory.Depart.MINIAO:
                    return MINIAODepart.levels;
                case InsuranceCategory.Depart.GANZANG:
                    return GANZANGDepart.levels;
                case InsuranceCategory.Depart.MUYIN:
                    return MUYINGDepart.levels;
                case InsuranceCategory.Depart.XINXIONG:
                    return XINXIONGDepart.levels;
                case InsuranceCategory.Depart.GANGCHANG:
                    return GANGCHANGDepart.levels;
                case InsuranceCategory.Depart.FUKE:
                    return FUKEDepart.levels;
                case InsuranceCategory.Depart.DANDAO:
                    return DANDAODepart.levels;
                case InsuranceCategory.Depart.YANKE:
                    return YANKEDepart.levels;
                case InsuranceCategory.Depart.JIERU:
                    return JIERUDepart.levels;
                default:
                    break;
            }
            return null;
        }

        public static String getProductId(int departs) {//
            switch (departs) {
                case InsuranceCategory.Depart.PUWAI:
                    return "1000002900";
                case InsuranceCategory.Depart.BONE:
                    return "1000002800";
                case InsuranceCategory.Depart.MINIAO:
                    return "1000003100";
                case InsuranceCategory.Depart.GANZANG:
                    return "1000003200";
                case InsuranceCategory.Depart.MUYIN:
                    return "1000003300";
                case InsuranceCategory.Depart.XINXIONG:
                    return "1000003400";
                case InsuranceCategory.Depart.GANGCHANG:
                    return "1000003600";
                case InsuranceCategory.Depart.FUKE:
                    return "1000003800";
                case InsuranceCategory.Depart.DANDAO:
                    return "1000003000";
                case InsuranceCategory.Depart.YANKE:
                    return "1000003700";
                case InsuranceCategory.Depart.JIERU:
                    return "1000003500";
                default:
                    break;
            }
            return null;
        }
    }



    //第0 普外手术
    public static class PUWAIDepart {
        //普外科室有四个等级的意外
        public static final int FIRST_LEVEL = 1001;
        public static final int SECOND_LEVEL = 1002;
        public static final int THIRD_LEVEL = 1003;
        public static final int FOURTH_LEVEL = 1004;

//        public static int[] levels = {1001,1002,1003,1004};
        public static int[] levels = {1001};

        public static final String ProductID_First  = "1000002900";
        public static final String ProductID_Second = "1000002900";
        public static final String ProductID_Third  = "1000002900";
        public static final String ProductID_Fourth = "1000002900";

        public static String getProductID(int level,int plan) {
            switch (level) {
                case FIRST_LEVEL:
                    return ProductID_First;
                case SECOND_LEVEL:
                    return ProductID_Second;
                case THIRD_LEVEL:
                    return ProductID_Third;
                case FOURTH_LEVEL:
                    return ProductID_Fourth;
            }
            return null;
        }

        //每个等级的意外对应着ABC三款投保计划
        public final class PuwaiFirstLevel {
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "468.00";
        }
        public final class PuwaiSecondLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "1314.00";
        }
        public final class PuwaiThirdLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "2343.00";
        }
        public final class PuwaiFourthLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "3188.00";
        }

    }

    //第1 骨科手术
    public static class BoneDepart {

        public static final int FIRST_LEVEL = 1011;
        public static final int SECOND_LEVEL = 1012;
        public static final int THIRD_LEVEL = 1013;
        public static final int FOURTH_LEVEL = 1014;

//        public static int[] levels = {1011,1012,1013,1014};
        public static int[] levels = {1011};

        public static final int PLAN_A = 101;
        public static final int PLAN_B = 102;
        public static final int PLAN_C = 103;

        public static final String ProductID_First  = "1000002800";
        public static final String ProductID_Second = "1000002800";
        public static final String ProductID_Third  = "1000002800";
        public static final String ProductID_Fourth = "1000002800";

        public static String getProductID(int level,int plan) {
            switch (level) {
                case FIRST_LEVEL:
                    return ProductID_First;
                case SECOND_LEVEL:
                    return ProductID_Second;
                case THIRD_LEVEL:
                    return ProductID_Third;
                case FOURTH_LEVEL:
                    return ProductID_Fourth;
            }
            return null;
        }

        public final class BoneFirstLevel {
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "468.00";
        }
        public final class BoneSecondLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "1314.00";
        }
        public final class BoneThirdLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "2343.00";
        }
        public final class BoneFourthLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "3188.00";
        }
    }

    //第2 泌尿
    public static class MINIAODepart {
        //普外科室有四个等级的意外
        public static final int FIRST_LEVEL = 1021;
        public static final int SECOND_LEVEL = 1022;
        public static final int THIRD_LEVEL = 1023;
        public static final int FOURTH_LEVEL = 1024;

//        public static int[] levels = {1021,1022,1023,1024};
        public static int[] levels = {1021};

        public static final String ProductID_First  = "1000003100";
        public static final String ProductID_Second = "1000003100";
        public static final String ProductID_Third  = "1000003100";
        public static final String ProductID_Fourth = "1000003100";

        public static String getProductID(int level,int plan) {
            switch (level) {
                case FIRST_LEVEL:
                    return ProductID_First;
                case SECOND_LEVEL:
                    return ProductID_Second;
                case THIRD_LEVEL:
                    return ProductID_Third;
                case FOURTH_LEVEL:
                    return ProductID_Fourth;
            }
            return null;
        }
        //每个等级的意外对应着ABC三款投保计划
        public final class MiniaoFirstLevel {
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "468.00";
        }
        public final class MiniaoSecondLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "1314.00";
        }
        public final class MiniaoThirdLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "2343.00";
        }
        public final class MiniaoFourthLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "3188.00";
        }

    }

    //第3 肝脏
    public static class GANZANGDepart {
        //普外科室有四个等级的意外
        public static final int FIRST_LEVEL = 1031;
        public static final int SECOND_LEVEL = 1032;
        public static final int THIRD_LEVEL = 1033;
        public static final int FOURTH_LEVEL = 1034;

//        public static int[] levels = {1031,1032,1033,1034};
        public static int[] levels = {1031};
        public static final String ProductID_First  = "1000003200";
        public static final String ProductID_Second = "1000003200";
        public static final String ProductID_Third  = "1000003200";
        public static final String ProductID_Fourth = "1000003200";

        public static String getProductID(int level,int plan) {
            switch (level) {
                case FIRST_LEVEL:
                    return ProductID_First;
                case SECOND_LEVEL:
                    return ProductID_Second;
                case THIRD_LEVEL:
                    return ProductID_Third;
                case FOURTH_LEVEL:
                    return ProductID_Fourth;
            }
            return null;
        }
        //每个等级的意外对应着ABC三款投保计划
        public final class GanzangFirstLevel {
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "468.00";
        }
        public final class GanzangSecondLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "1314.00";
        }
        public final class GanzangThirdLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "2343.00";
        }
        public final class GanzangFourthLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "3188.00";
        }

    }

    //第4 母婴安康
    public static class MUYINGDepart {
        //普外科室有四个等级的意外
        public static final int FIRST_LEVEL = 1041;
        public static final int SECOND_LEVEL = 1042;
        public static final int THIRD_LEVEL = 1043;
        public static final int FOURTH_LEVEL = 1044;

//        public static int[] levels = {1041,1042,1043,1044};
        public static int[] levels = {1041};
        public static final String ProductID_First  = "1000003300";
        public static final String ProductID_Second = "1000003300";
        public static final String ProductID_Third  = "1000003300";
        public static final String ProductID_Fourth = "1000003300";

        public static String getProductID(int level,int plan) {
            switch (level) {
                case FIRST_LEVEL:
                    return ProductID_First;
                case SECOND_LEVEL:
                    return ProductID_Second;
                case THIRD_LEVEL:
                    return ProductID_Third;
                case FOURTH_LEVEL:
                    return ProductID_Fourth;
            }
            return null;
        }
        //每个等级的意外对应着ABC三款投保计划
        public final class FirstLevel {
            public static final String expenseA = "56.00";
            public static final String expenseB = "86.00";
            public static final String expenseC = "468.00";
        }
        public final class SecondLevel{
            public static final String expenseA = "56.00";
            public static final String expenseB = "86.00";
            public static final String expenseC = "1314.00";
        }
        public final class ThirdLevel{
            public static final String expenseA = "56.00";
            public static final String expenseB = "86.00";
            public static final String expenseC = "2343.00";
        }
        public final class FourthLevel{
            public static final String expenseA = "56.00";
            public static final String expenseB = "86.00";
            public static final String expenseC = "3188.00";
        }

    }

    //第5 心胸外科手术
    public static class XINXIONGDepart {
        //普外科室有四个等级的意外
        public static final int FIRST_LEVEL = 1051;
        public static final int SECOND_LEVEL = 1052;
        public static final int THIRD_LEVEL = 1053;
        public static final int FOURTH_LEVEL = 1054;

//        public static int[] levels = {1051,1052,1053,1054};
        public static int[] levels = {1051};
        public static final String ProductID_First  = "1000003400";
        public static final String ProductID_Second = "1000003400";
        public static final String ProductID_Third  = "1000003400";
        public static final String ProductID_Fourth = "1000003400";

        public static String getProductID(int level,int plan) {
            switch (level) {
                case FIRST_LEVEL:
                    return ProductID_First;
                case SECOND_LEVEL:
                    return ProductID_Second;
                case THIRD_LEVEL:
                    return ProductID_Third;
                case FOURTH_LEVEL:
                    return ProductID_Fourth;
            }
            return null;
        }
        //每个等级的意外对应着ABC三款投保计划
        public final class FirstLevel {
            public static final String expenseA = "480.00";
            public static final String expenseB = "960.00";
            public static final String expenseC = "468.00";
        }
        public final class SecondLevel{
            public static final String expenseA = "480.00";
            public static final String expenseB = "960.00";
            public static final String expenseC = "1314.00";
        }
        public final class ThirdLevel{
            public static final String expenseA = "480.00";
            public static final String expenseB = "960.00";
            public static final String expenseC = "2343.00";
        }
        public final class FourthLevel{
            public static final String expenseA = "480.00";
            public static final String expenseB = "960.00";
            public static final String expenseC = "3188.00";
        }

    }

    //第6 肛肠手术
    public static class GANGCHANGDepart {
        //普外科室有四个等级的意外
        public static final int FIRST_LEVEL = 1061;
        public static final int SECOND_LEVEL = 1062;
        public static final int THIRD_LEVEL = 1063;
        public static final int FOURTH_LEVEL = 1064;

//        public static int[] levels = {1061,1062,1063,1064};
        public static int[] levels = {1061};
        public static final String ProductID_First  = "1000003600";
        public static final String ProductID_Second = "1000003600";
        public static final String ProductID_Third  = "1000003600";
        public static final String ProductID_Fourth = "1000003600";

        public static String getProductID(int level,int plan) {
            switch (level) {
                case FIRST_LEVEL:
                    return ProductID_First;
                case SECOND_LEVEL:
                    return ProductID_Second;
                case THIRD_LEVEL:
                    return ProductID_Third;
                case FOURTH_LEVEL:
                    return ProductID_Fourth;
            }
            return null;
        }
        //每个等级的意外对应着ABC三款投保计划
        public final class FirstLevel {
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "468.00";
        }
        public final class SecondLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "1314.00";
        }
        public final class ThirdLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "2343.00";
        }
        public final class FourthLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "3188.00";
        }

    }

    //第7 妇科手术
    public static class FUKEDepart {
        //普外科室有四个等级的意外
        public static final int FIRST_LEVEL = 1071;
        public static final int SECOND_LEVEL = 1072;
        public static final int THIRD_LEVEL = 1073;
        public static final int FOURTH_LEVEL = 1074;

//        public static int[] levels = {1071,1072,1073,1074};
        public static int[] levels = {1071};
        public static final String ProductID_First  = "1000003800";
        public static final String ProductID_Second = "1000003800";
        public static final String ProductID_Third  = "1000003800";
        public static final String ProductID_Fourth = "1000003800";

        public static String getProductID(int level,int plan) {
            switch (level) {
                case FIRST_LEVEL:
                    return ProductID_First;
                case SECOND_LEVEL:
                    return ProductID_Second;
                case THIRD_LEVEL:
                    return ProductID_Third;
                case FOURTH_LEVEL:
                    return ProductID_Fourth;
            }
            return null;
        }
        //每个等级的意外对应着ABC三款投保计划
        public final class FirstLevel {
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "468.00";
        }
        public final class SecondLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "1314.00";
        }
        public final class ThirdLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "2343.00";
        }
        public final class FourthLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "3188.00";
        }

    }

    //第8 弹道胆囊手术
    public static class DANDAODepart {
        //普外科室有四个等级的意外
        public static final int FIRST_LEVEL = 1081;
        public static final int SECOND_LEVEL = 1082;
        public static final int THIRD_LEVEL = 1083;
        public static final int FOURTH_LEVEL = 1084;

//        public static int[] levels = {1081,1082,1083,1084};
        public static int[] levels = {1081};
        public static final String ProductID_First  = "1000003000";
        public static final String ProductID_Second = "1000003000";
        public static final String ProductID_Third  = "1000003000";
        public static final String ProductID_Fourth = "1000003000";

        public static String getProductID(int level,int plan) {
            switch (level) {
                case FIRST_LEVEL:
                    return ProductID_First;
                case SECOND_LEVEL:
                    return ProductID_Second;
                case THIRD_LEVEL:
                    return ProductID_Third;
                case FOURTH_LEVEL:
                    return ProductID_Fourth;
            }
            return null;
        }
        //每个等级的意外对应着ABC三款投保计划
        public final class FirstLevel {
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "468.00";
        }
        public final class SecondLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "1314.00";
        }
        public final class ThirdLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "2343.00";
        }
        public final class FourthLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "3188.00";
        }

    }

    //第9 眼科手术
    public static class YANKEDepart {
        //普外科室有四个等级的意外
        public static final int FIRST_LEVEL = 1091;
        public static final int SECOND_LEVEL = 1092;
        public static final int THIRD_LEVEL = 1093;
        public static final int FOURTH_LEVEL = 1094;

//        public static int[] levels = {1091,1092,1093,1094};
        public static int[] levels = {1091};
        public static final String ProductID_First  = "1000003700";
        public static final String ProductID_Second = "1000003700";
        public static final String ProductID_Third  = "1000003700";
        public static final String ProductID_Fourth = "1000003700";

        public static String getProductID(int level,int plan) {
            switch (level) {
                case FIRST_LEVEL:
                    return ProductID_First;
                case SECOND_LEVEL:
                    return ProductID_Second;
                case THIRD_LEVEL:
                    return ProductID_Third;
                case FOURTH_LEVEL:
                    return ProductID_Fourth;
            }
            return null;
        }
        //每个等级的意外对应着ABC三款投保计划
        public final class FirstLevel {
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "468.00";
        }
        public final class SecondLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "1314.00";
        }
        public final class ThirdLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "2343.00";
        }
        public final class FourthLevel{
            public static final String expenseA = "368.00";
            public static final String expenseB = "598.00";
            public static final String expenseC = "3188.00";
        }

    }

    //第10 介入手术
    public static class JIERUDepart {
        //普外科室有四个等级的意外
        public static final int FIRST_LEVEL = 1101;
        public static final int SECOND_LEVEL = 1102;
        public static final int THIRD_LEVEL = 1103;
        public static final int FOURTH_LEVEL = 1104;

//        public static int[] levels = {1101,1102,1103,1104};
        public static int[] levels = {1101};
        public static final String ProductID_First  = "1000003500";
        public static final String ProductID_Second = "1000003500";
        public static final String ProductID_Third  = "1000003500";
        public static final String ProductID_Fourth = "1000003500";

        public static String getProductID(int level,int plan) {
            switch (level) {
                case FIRST_LEVEL:
                    return ProductID_First;
                case SECOND_LEVEL:
                    return ProductID_Second;
                case THIRD_LEVEL:
                    return ProductID_Third;
                case FOURTH_LEVEL:
                    return ProductID_Fourth;
            }
            return null;
        }
        //每个等级的意外对应着ABC三款投保计划
        public final class FirstLevel {
            public static final String expenseA = "460.00";
            public static final String expenseB = "920.00";
            public static final String expenseC = "468.00";
        }
        public final class SecondLevel{
            public static final String expenseA = "460.00";
            public static final String expenseB = "920.00";
            public static final String expenseC = "1314.00";
        }
        public final class ThirdLevel{
            public static final String expenseA = "460.00";
            public static final String expenseB = "920.00";
            public static final String expenseC = "2343.00";
        }
        public final class FourthLevel{
            public static final String expenseA = "460.00";
            public static final String expenseB = "920.00";
            public static final String expenseC = "3188.00";
        }

    }
}
