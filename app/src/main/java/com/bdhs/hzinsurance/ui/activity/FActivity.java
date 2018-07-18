package com.bdhs.hzinsurance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.bdhs.hzinsurance.R;

public class FActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "DepartsActivity";
    ImageView ivPW,ivGK,ivJR,ivGD;
    ImageView ivQSTB,ivLPZY,ivQA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fpage_activity);
        initView();
    }

    private void initView() {
        ivPW = (ImageView) findViewById(R.id.iv_puwai);
        ivGK = (ImageView) findViewById(R.id.iv_guke);
        ivJR = (ImageView) findViewById(R.id.iv_jieru);
        ivGD = (ImageView) findViewById(R.id.iv_gengduo);

        ivQSTB = (ImageView) findViewById(R.id.iv_qstb);
        ivLPZY = (ImageView) findViewById(R.id.iv_lpzy);
        ivQA = (ImageView) findViewById(R.id.iv_qa);
        ivPW.setOnClickListener(this);
        ivGK.setOnClickListener(this);
        ivJR.setOnClickListener(this);
        ivGD.setOnClickListener(this);
        ivQSTB.setOnClickListener(this);
        ivLPZY.setOnClickListener(this);
        ivQA.setOnClickListener(this);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    Intent intent;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_puwai:
                intent = new Intent(FActivity.this,BoneActivity.class);
                intent.putExtra("depart", 1);//position从0开始，科室编码从1开始
                startActivity(intent);
                break;
            case R.id.iv_guke:
                intent = new Intent(FActivity.this,BoneActivity.class);
                intent.putExtra("depart", 2);//position从0开始，科室编码从1开始
                startActivity(intent);
                break;
            case R.id.iv_jieru:
                intent = new Intent(FActivity.this,BoneActivity.class);
                intent.putExtra("depart", 11);//position从0开始，科室编码从1开始
                startActivity(intent);
                break;
            case R.id.iv_gengduo:
                intent = new Intent(FActivity.this,
                        DepartsActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_qstb:
                intent = new Intent(FActivity.this,
                        EasyInsurance.class);
                startActivity(intent);
                break;
            case R.id.iv_lpzy:
                intent = new Intent(FActivity.this,
                        LiPeiGuideActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_qa:
                intent = new Intent(FActivity.this,
                        QAActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
