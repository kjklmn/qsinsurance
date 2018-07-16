package com.bdhs.hzinsurance.ui.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.bdhs.hzinsurance.R;
import com.bdhs.hzinsurance.config.InsuranceCategory;
import com.bdhs.hzinsurance.ui.view.BoneitemView;
import com.bdhs.hzinsurance.utils.LogUtils;


public class BoneActivity extends BaseActivity {
    private static final String TAG = "BoneActivity";
    private int departs = -1;

    private int[] draws = {R.mipmap.pone,R.mipmap.ptwo,R.mipmap.pthree,R.mipmap.pfour};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bone);
        departs = getIntent().getIntExtra("depart",-1);
        if(departs > 0 && departs < 12) {
            addLL(departs);
        } else {
            LogUtils.e(TAG,"departs = "+departs+";部门序号不存在");
        }
    }

    private void addLL(int departs) {
        int[] temp = InsuranceCategory.Depart.getLevels(departs);
        if(temp != null && temp.length > 0) {
            LinearLayout ll = (LinearLayout) findViewById(R.id.llayout);
            int drawLength = draws.length;
            int length = temp.length;
            for(int i = 0;i<length;i++) {
                LogUtils.w(TAG,"departs = "+departs+"; LEVEL = "+temp[i]);
                LinearLayout ll_one = new BoneitemView(this, departs,temp[i],draws[i]);
                ll.addView(ll_one);
            }
        } else {
            LogUtils.e(TAG,"获取到的为空");
        }
    }
}
