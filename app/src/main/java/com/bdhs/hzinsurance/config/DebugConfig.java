package com.bdhs.hzinsurance.config;

import com.bdhs.hzinsurance.application.MainApplication;
import com.bdhs.hzinsurance.utils.LogUtils;

public class DebugConfig {
    public static boolean DEBUG = true;


    public static String getDeviceId() {
        String device_id = android.provider.Settings.Secure.getString(MainApplication.getInstance().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
		LogUtils.w("device_id","ANDROID_ID = "+device_id);
        return device_id;
    }
    public static String[] qList = {
            "Q：手术风险如何产生？",
            "Q：什么是手术意外保险？",
            "Q：手术意外保险都有保障哪些内容？",
            "Q：手术前为什么要买手术意外保险？",
            "Q：如何选择保险方案？",
            "Q：手术意外身故、麻醉意外身故责任累计赔付吗？",
            "Q：手术意外身故、麻醉意外身故及伤残责任累计赔付吗？",
            "Q:手术意外保险与医保报销、商业保险是否有冲突？",
            "Q:手术意外身故及麻醉意外身故是否全额赔付？",
            "Q:手术意外及麻醉意外残疾等级划分及相应赔付比例是什么样的？",
            "Q:保险期间是怎样约定的？",
            "Q:如果患者是未成年人，怎么投保？",
            "Q:保险受益人有哪些？",
            "Q:购买保险后能退保吗？",
            "Q:出险后如何办理索赔手续？"};

    public static String[] aList = {
            "A：手术指为治疗疾病或损伤、挽救生命而施行的，医生用医疗器械对病人身体进行的切除、缝合等的治疗。手术时会对手术部位的正常组织造成一定的损伤，术中会出险手术风险、麻醉风险，术后会出现手术并发症等风险。",
            "A：在保险期间内，被保险人遭受医疗事故、医疗意外，并自该事故发生之日起180日内因该事故身故的，保险人按照保险单载明的身故保险金额给付身故保险金。",
            "A：手术意外保险承保患者在住院手术、手术麻醉过程中因医疗意外、医疗事故导致的身故、残疾，保险公司将按照约定支付相应的保险金。",
            "A：尽管医疗技术水平在不断提高，医疗意外风险仍然客观存在，依我国现行的法律和社会保障体系，缺乏对医疗意外风险的补偿机制。本产品为您的手术提供一份医疗意外保障，为您的家人提供一份安心。",
            "A：针对患者不同的需求，我们设计了医院共计11个科室的手术意外保险套餐，分别提供10万和20万两个保额供您选择，根据您的经济条件及投保需求选择投保。",
            "A：不累计赔付",
            "A：不累计赔付",
            "A:手术意外保险属于短期人身给付型保险，按您投保时约定的保险金额及保障内容给付，与医保及其他商业保险没有任何冲突，该产品赔付时不需要提供医药费用票据等原始票据。",
            "A:全额赔付",
            "A:需有资质的司法鉴定机构根据《人身保险伤残评定标准及代码》（标准编号为JR/T0083—2013的标准进行残疾鉴定，鉴定完毕之后按本产品的约定赔付比例进行赔付。身故及一级伤残按保额赔付，二级伤残按保额的90%赔付,三级伤残按保额的80%赔付，依次类推。",
            "A:自保单生效日起三十日内为保险期间。",
            "A:未成年的父母/养父母/经未成年父母同意的具有监护责任的人可以为其投保，但是对于未成年人不满10周岁，保额不得超过人民币20万元。",
            "A:法定受益人",
            "A:保单起保后不可退保。保单起保前可全额退保。",
            "A:拨打平安保险理赔专线95511或手术意外险专属理赔服务电话0755-82700603、1302664288理赔专员会为您提供咨询、指导填报索赔申请及其他理赔手续，按照理赔申请清单准备好理赔所需资料及凭证。也可以拨打惠众网服务电话0755-23487932，由惠众网协助您办理理赔事项。"
    };


}