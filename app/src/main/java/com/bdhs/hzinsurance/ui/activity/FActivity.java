package com.bdhs.hzinsurance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bdhs.hzinsurance.R;
import com.bdhs.hzinsurance.api.presenters.updateapp.CheckVersionEntity;
import com.bdhs.hzinsurance.api.presenters.updateapp.IUpdateView;
import com.bdhs.hzinsurance.api.presenters.updateapp.UpdatePresenter;
import com.bdhs.hzinsurance.updateapp.DeviceUtils;
import com.bdhs.hzinsurance.updateapp.UpdateManager;
import com.bdhs.hzinsurance.utils.CJYMHandler;
import com.bdhs.hzinsurance.utils.LogUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class FActivity extends AppCompatActivity implements View.OnClickListener,IUpdateView {
    private static final String TAG = "DepartsActivity";
    ImageView ivPW,ivGK,ivJR,ivGD;
    ImageView ivQSTB,ivLPZY,ivQA;
    UpdatePresenter updatePresenter;
    private UpdateManager mUpdateManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fpage_activity);
        initView();
        updatePresenter = new UpdatePresenter(this);
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
    protected void onResume() {
        super.onResume();
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("edition_name", DeviceUtils.getVersionCode(FActivity.this));
        updatePresenter.getNewVersion(params);
    }

    @Override
    public void onGetEvaluateSucc(CheckVersionEntity response) {

    }

    @Override
    public void onError(Throwable e) {

    }


    public static class HomeHandler extends CJYMHandler {
        private WeakReference<FActivity> fragmentWeakReference;
        public HomeHandler(FActivity factivity) {
            fragmentWeakReference = new WeakReference<FActivity>(factivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FActivity factivity = fragmentWeakReference.get();
            if (factivity == null) {
                LogUtils.e(TAG, "activity null,cannot handleMessage");
                return;
            }
            switch (msg.what) {
                case CJYMHandler.MSG_UPDATE_APP_SHOW:
                    if (factivity.mUpdateManager != null) {
                        factivity.mUpdateManager.updataProgressUpdate((Integer) msg.obj);
                    }
                    break;
                case CJYMHandler.MSG_UPDATE_APP_DISMISS:
                    if (factivity.mUpdateManager != null) {
                        factivity.mUpdateManager.dismissProgressUpdate();

                        if(msg.obj != null && msg.obj instanceof File) {
                            factivity.mUpdateManager.installApk((File) msg.obj);
                        }
                    }
                    break;
                case CJYMHandler.MSG_UPDATE_APP_FAIL:
                    Toast.makeText(factivity, "升级失败",Toast.LENGTH_LONG).show();
                    break;
            }
        }
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
                        EasyInsuranceActivity.class);
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
