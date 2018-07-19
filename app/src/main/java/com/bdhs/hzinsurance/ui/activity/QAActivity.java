package com.bdhs.hzinsurance.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bdhs.hzinsurance.R;
import com.bdhs.hzinsurance.config.DebugConfig;

public class QAActivity extends AppCompatActivity {
    private static final String TAG = "DepartsActivity";
    LinearLayout llRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_qa);
        initView();
//        Display display = getWindowManager().getDefaultDisplay();
//        int heigth = display.getWidth();
//        int width = display.getHeight();
//        Log.w("kejian","height:"+heigth+"; width:"+width);
    }

    private void initView() {
        llRoot = (LinearLayout) findViewById(R.id.ll_root);
        int size = DebugConfig.aList.length;
        for(int i=0;i<size;i++) {
            addLL(llRoot,DebugConfig.qList[i],DebugConfig.aList[i]);
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
