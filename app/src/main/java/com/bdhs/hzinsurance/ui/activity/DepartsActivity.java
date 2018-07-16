package com.bdhs.hzinsurance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.bdhs.hzinsurance.R;
import com.bdhs.hzinsurance.ui.adapter.CategoryAdapter;
import com.bdhs.hzinsurance.utils.LogUtils;

public class DepartsActivity extends AppCompatActivity {
    private static final String TAG = "DepartsActivity";
    Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_departs);
        initView();
    }

    private void initView() {
        CategoryAdapter adapter = new CategoryAdapter(this);
        GridView gv = (GridView) findViewById(R.id.gv_charge_select);
        gv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mIntent = new Intent(DepartsActivity.this,BoneActivity.class);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position >=0 && position <= 10) {
                    mIntent.putExtra("depart", position+1);//position从0开始，科室编码从1开始
                    startActivity(mIntent);
                } else {
                    LogUtils.e(TAG,"position 对应的科室ID超出范围，无法跳转");
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
