package com.bdhs.hzinsurance.ui.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bdhs.hzinsurance.R;
import com.bdhs.hzinsurance.callback.CostCallback;
import com.bdhs.hzinsurance.config.InsuranceCategory;
import com.bdhs.hzinsurance.manager.Manager;
import com.bdhs.hzinsurance.utils.LogUtils;
import com.bdhs.hzinsurance.utils.QRCodeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 项目名称：hzInsurance
 * 类描述：
 * 创建人：kejian
 * 创建时间：2018-01-12 9:46
 * 修改人：Administrator
 * 修改时间：2018-01-12 9:46
 * 修改备注：
 */
public class BoneitemView extends LinearLayout implements View.OnClickListener,CostCallback {
    private static final String TAG = "BoneitemView";
    private Calendar cal;
    private int year, month, day;
    private Date dayTomo,monthTwo;//明天，两个月后
    private Context context;
    private TextView tvDate, tvPlanA, tvPlanB, tvPlanC, tvLevel,tvCost,tv_description,tv_fee_surgical,tv_fee_anesthetic,tv_fee_disable;
    private Manager mManager;
    private ImageView ivQrcode,ivPic;
    private int drawId;
    private String DateStart = null;
    private int depart;//具体哪一科：比如普外，骨科等

    public BoneitemView(Context context, int depart,int depart_level,int drawId) {
        super(context);
        this.context = context;
        this.drawId = drawId;
        this.depart = depart;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.insurance_item, this);
        mManager = new Manager(depart,depart_level,this);
        testDate();
        initDatePicker();
        initView();
    }

    private void testDate() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar mCalendar = Calendar.getInstance();

        mCalendar.add(mCalendar.DAY_OF_MONTH, +1);//设置1天后
        dayTomo = mCalendar.getTime();
        String defaultStartDate = sdf.format(dayTomo);
        LogUtils.w(TAG,"defaultStartDate 1天后 = "+defaultStartDate);

        mCalendar = Calendar.getInstance();
        mCalendar.add(mCalendar.MONTH, +2);  //设置为2个月后
        monthTwo = mCalendar.getTime();
        defaultStartDate = sdf.format(monthTwo);
        LogUtils.w(TAG,"defaultStartDate 2个月后 = "+defaultStartDate);
    }

    private void initView() {
        tvDate = findViewById(R.id.tvShowDate);
        DateStart = year + "-" + get2Str(++month) + "-" + get2Str(day);
        tvDate.setText(DateStart);
        tvDate.setOnClickListener(this);
        tvPlanA = findViewById(R.id.tv_plan_a);
        tvPlanA.setOnClickListener(this);
        tvPlanB = findViewById(R.id.tv_plan_b);
        tvPlanB.setOnClickListener(this);
        tvPlanC = findViewById(R.id.tv_plan_c);
        tvPlanC.setVisibility(View.GONE );
        tvPlanC.setOnClickListener(this);
        tvLevel = findViewById(R.id.tv_level);
        tvCost = findViewById(R.id.tv_cost);
        ivQrcode = findViewById(R.id.iv_qrcode);
        ivPic = findViewById(R.id.iv_pic);
        ivPic.setImageResource(drawId);
        tv_description = findViewById(R.id.tv_description);
        tv_fee_surgical = findViewById(R.id.tv_fee_surgical);
        tv_fee_anesthetic = findViewById(R.id.tv_fee_anesthetic);
        tv_fee_disable = findViewById(R.id.tv_fee_disable);

        mManager.setTitle(tvLevel);
        //初始的状态
        mManager.setPlan(InsuranceCategory.BoneDepart.PLAN_A);//设置默认的选择，通过回调来显示内容
        setPlanBg(InsuranceCategory.BoneDepart.PLAN_A);
    }

    DatePickerDialog dialog;
    private boolean isSelect = false;//是否选择过日期
    private void initDatePicker() {
        cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);       //获取年月日时分秒
        month = cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        cal.add(cal.DAY_OF_MONTH, +1);//设置为2个月后
        day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker arg0, int year, int month, int day) {
                //为了解决系统bug：onDateSet被回调两次
                if(isSelect) {
                    return;
                } else {
                    isSelect = true;
                }
                DateStart = year + "-" + get2Str(++month) + "-" + get2Str(day);
                tvDate.setText(DateStart);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                LogUtils.w(TAG,"OnDateSetListener");
                setQRCode();
            }
        };
        dialog = new DatePickerDialog(context, 0, listener, year, month, day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
        dialog.getDatePicker().setMinDate(dayTomo.getTime());
        dialog.getDatePicker().setMaxDate(monthTwo.getTime());
    }

    private String get2Str(int i) {
        return i<10?("0"+i):(""+i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvShowDate: {
                isSelect = false;
                if (dialog == null) {
                    initDatePicker();
                }
                dialog.show();
            }
                break;
            case R.id.tv_plan_a:
                mManager.setPlan(InsuranceCategory.BoneDepart.PLAN_A);
                setPlanBg(InsuranceCategory.BoneDepart.PLAN_A);
                break;
            case R.id.tv_plan_b:
                mManager.setPlan(InsuranceCategory.BoneDepart.PLAN_B);
                setPlanBg(InsuranceCategory.BoneDepart.PLAN_B);
                break;
            case R.id.tv_plan_c:
                mManager.setPlan(InsuranceCategory.BoneDepart.PLAN_C);
                setPlanBg(InsuranceCategory.BoneDepart.PLAN_C);
                break;
        }
    }

    /*
     * 设置ABC款的背景颜色
     */
    private void setPlanBg(int bg) {
        switch (bg) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                tvPlanA.setBackgroundResource(R.drawable.bg_plan_b);
                tvPlanA.setTextColor(getResources().getColor(R.color.text_color_2));//白色
                if(depart == InsuranceCategory.Depart.MUYIN) {
                    tv_description.setText(getResources().getString(R.string.description_a_1));
                } else {
                    tv_description.setText(getResources().getString(R.string.description_a));
                }
                tv_description.setBackgroundResource(R.mipmap.select_one);
                tvPlanB.setBackgroundResource(R.drawable.bg_plan_a);
                tvPlanB.setTextColor(getResources().getColor(R.color.text_color_1));//黑色

                tvPlanC.setBackgroundResource(R.drawable.bg_plan_a);
                tvPlanC.setTextColor(getResources().getColor(R.color.text_color_1));
                break;
            case InsuranceCategory.BoneDepart.PLAN_B:
                tvPlanA.setBackgroundResource(R.drawable.bg_plan_a);
                tvPlanA.setTextColor(getResources().getColor(R.color.text_color_1));
                tvPlanB.setBackgroundResource(R.drawable.bg_plan_b);
                tvPlanB.setTextColor(getResources().getColor(R.color.text_color_2));//白色
                tvPlanC.setBackgroundResource(R.drawable.bg_plan_a);
                tvPlanC.setTextColor(getResources().getColor(R.color.text_color_1));
                if(depart == InsuranceCategory.Depart.MUYIN) {
                    tv_description.setText(getResources().getString(R.string.description_b_1));
                } else {
                    tv_description.setText(getResources().getString(R.string.description_b));
                }
                tv_description.setBackgroundResource(R.mipmap.select_two);
                break;
            case InsuranceCategory.BoneDepart.PLAN_C:
                tvPlanA.setBackgroundResource(R.drawable.bg_plan_a);
                tvPlanA.setTextColor(getResources().getColor(R.color.text_color_1));

                tvPlanB.setBackgroundResource(R.drawable.bg_plan_a);
                tvPlanB.setTextColor(getResources().getColor(R.color.text_color_1));

                tvPlanC.setBackgroundResource(R.drawable.bg_plan_b);
                tvPlanC.setTextColor(getResources().getColor(R.color.text_color_2));//白色

                tv_description.setText(getResources().getString(R.string.description_c));
                tv_description.setBackgroundResource(R.mipmap.select_three);
                break;
        }
        if(depart == InsuranceCategory.Depart.MUYIN) {

        }
    }

    private String ProductID;
    private int planID;
    private String money;
    @Override
    public void callback(String ProductID,int planID,String money) {
        //TODO:在这里改变金额以及通过url生成新的二维码的
        showCost(money);
        this.ProductID = ProductID;
        this.planID = getPlanID(planID);
        this.money = money;
        setInsuranceDetails(this.planID);
        setQRCode();
    }

    private int getPlanID(int planID) {
        switch (planID) {
            case InsuranceCategory.BoneDepart.PLAN_A:
                return 1;
            case InsuranceCategory.BoneDepart.PLAN_B:
                return 2;
            case InsuranceCategory.BoneDepart.PLAN_C:
                return 3;
        }

        return 0;
    }
    String url;

    //设值二维码内容
    private void setQRCode() {
        url = InsuranceCategory.Base_URL+ProductID+"?PlanID="+planID+"&DateStart="+DateStart+"&Fee="+money+"&DistributorID="+InsuranceCategory.DistributorID+"&Depart="+depart;
        LogUtils.w(TAG,"url = "+url);
        Bitmap bmp = QRCodeUtils.generateBitmap(url,162,162);
        LogUtils.w(TAG,bmp.getByteCount()+"个字节");
        ivQrcode.setImageBitmap(bmp);
    }

    //设置保险详细内容
    private void setInsuranceDetails(int planID) {
        LogUtils.w(TAG,"planID = "+planID +"; depart = "+depart);
        if(depart != InsuranceCategory.Depart.MUYIN) {
            switch (planID) {
                case 1:
                    setInsuranceAmount(10,10,10);
                    break;
                case 2:
                    setInsuranceAmount(20,20,20);
                    break;
                default:
                    break;
            }
        } else {
            switch (planID) {
                case 1:
                    setInsuranceAmount(5,5,5);
                    break;
                case 2:
                    setInsuranceAmount(8,8,8);
                    break;
                default:
                    break;
            }
        }
    }

    private void setInsuranceAmount(int i,int j,int k) {
        tv_fee_surgical.setText(i+"万元");
        tv_fee_anesthetic.setText(j+"万元");
        tv_fee_disable.setText(k+"万元");
    }

    //设置显示投保费用
    private void showCost(String cost) {
        String money = "￥"+cost;
        int dotIndex = money.indexOf(".");
        SpannableString styledText = new SpannableString(money);
        styledText.setSpan(new TextAppearanceSpan(context, R.style.style0), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(context, R.style.style1), 1, dotIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(context, R.style.style2), dotIndex, money.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvCost.setText(styledText, TextView.BufferType.SPANNABLE);
    }
}
