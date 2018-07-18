package com.bdhs.hzinsurance.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bdhs.hzinsurance.R;
import com.bdhs.hzinsurance.config.DebugConfig;
import com.bdhs.hzinsurance.entity.EvaluateEntity;
import com.bdhs.hzinsurance.ui.view.RoundImageView;
import com.bumptech.glide.Glide;

public class UserEvaluateActivity extends AppCompatActivity {
    private static final String TAG = "DepartsActivity";
    LinearLayout llRoot;
    TextView tvGoodRate,tvEvaluateNum;
    LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_evaluate);
//        setContentView(R.layout.item_evaluate);
        initView();
//        RoundImageView iv_head_pic = (RoundImageView) findViewById(R.id.iv_head_pic);
//        Glide.with(UserEvaluateActivity.this)
//                .load("http://thirdwx.qlogo.cn/mmopen/vi_32/dZdqTEESGtJ8gpGAurg10prnVicN4fOAlictnnvTMGrnw35RuqEn6kKbA6IcEu4pVmZ0MlFeotUiaicevNzvfqZtwA/96")
//                .error(R.mipmap.man)
//                .into(iv_head_pic);
        inflater = LayoutInflater.from(this);
        View toast_layout = inflater.inflate(R.layout.toast,null);
    }

    private void initView() {
        tvGoodRate = (TextView) findViewById(R.id.tv_goodrate);
        tvEvaluateNum = (TextView) findViewById(R.id.tv_evaluate_num);



        llRoot = (LinearLayout) findViewById(R.id.ll_root);
        int size = DebugConfig.aList.length;
        for(int i=0;i<size;i++) {
            addLL(llRoot,DebugConfig.qList[i],DebugConfig.aList[i]);
        }
    }

    private void addEvaluate(EvaluateEntity evaluateEntity) {
        if(evaluateEntity != null) {
            View view = inflater.inflate(R.layout.item_evaluate,null);
            TextView tvIcon = view.findViewById(R.id.iv_head_pic);
            TextView tvTel = view.findViewById(R.id.tv_tel);
            ImageView iv1 = view.findViewById(R.id.iv_1);
            ImageView iv2 = view.findViewById(R.id.iv_2);
            ImageView iv3 = view.findViewById(R.id.iv_3);
            ImageView iv4 = view.findViewById(R.id.iv_4);
            ImageView iv5 = view.findViewById(R.id.iv_5);
            TextView tvLevel = view.findViewById(R.id.tv_level);
            TextView tvDate = view.findViewById(R.id.tv_date);
            TextView tvContent = view.findViewById(R.id.tv_content);
            llRoot.addView(view);
        }
    }

    private void addLL(LinearLayout root,  String num, String amount) {
        LinearLayout layout2 = new LinearLayout(this);
        layout2.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,20,0,0);
        layout2.setLayoutParams(params);


        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView tv2 = new TextView(this);
        tv2.setTextSize(18);
        tv2.setTextColor(Color.parseColor("#fb6000"));
        tv2.setLayoutParams(params2);
        tv2.setText(num);
        tv2.setMaxLines(6);
        layout2.addView(tv2);

        params2.setMargins(0,6,0,0);
        TextView tv3 = new TextView(this);
        tv3.setTextSize(16);
        tv3.setTextColor(Color.parseColor("#a7a7a7"));
        tv2.setMaxLines(6);
        tv3.setLayoutParams(params2);
        tv3.setText(amount);
        tv3.setGravity(Gravity.LEFT);
        layout2.addView(tv3);
        root.addView(layout2);
    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
