package com.bdhs.hzinsurance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bdhs.hzinsurance.R;
import com.bdhs.hzinsurance.api.presenters.evaluate.EvaluatePresenter;
import com.bdhs.hzinsurance.api.presenters.evaluate.IEvaluateView;
import com.bdhs.hzinsurance.api.presenters.updateapp.CheckVersionEntity;
import com.bdhs.hzinsurance.api.presenters.updateapp.IUpdateView;
import com.bdhs.hzinsurance.api.presenters.updateapp.UpdatePresenter;
import com.bdhs.hzinsurance.application.MainApplication;
import com.bdhs.hzinsurance.config.InsuranceCategory;
import com.bdhs.hzinsurance.entity.EvaluateBean;
import com.bdhs.hzinsurance.entity.EvaluateEntity;
import com.bdhs.hzinsurance.updateapp.DeviceUtils;
import com.bdhs.hzinsurance.updateapp.UpdateManager;
import com.bdhs.hzinsurance.utils.CJYMHandler;
import com.bdhs.hzinsurance.utils.LogUtils;
import com.bdhs.hzinsurance.utils.StringUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class FActivity extends AppCompatActivity implements View.OnClickListener,IUpdateView {
    private static final String TAG = "DepartsActivity";
    ImageView ivPW,ivGK,ivJR,ivGD;
    ImageView ivQSTB,ivLPZY,ivQA;
    private UpdatePresenter updatePresenter;
    private EvaluatePresenter evaluatePresenter;
    private EvaluateBean evaluateBean;
    private UpdateManager mUpdateManager;
    RequestQueue mQueue;
    String updateUrl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fpage_activity);
        initView();
        updatePresenter = new UpdatePresenter(this);
        mQueue = Volley.newRequestQueue(FActivity.this);
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
        updateUrl = "http://www3.szhuizhong.cn/index/get_comment/"+ DeviceUtils.getVersionCode(this);
        LogUtils.i(TAG,"evaluateUrl:"+updateUrl);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(updateUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jResponse) {
                        Log.d(TAG, "是否主线程："+(Looper.getMainLooper() == Looper.myLooper()?"true":"false"));
                        Log.d(TAG, jResponse.toString());
                        Gson gs = new Gson();
                        CheckVersionEntity response = gs.fromJson(jResponse.toString(),CheckVersionEntity.class);
                        Log.d(TAG, response.toString());
                        if(response != null) {
                            if(response.error_code == 0) {
                                if(mUpdateManager == null) {
                                    mUpdateManager = new UpdateManager(FActivity.this);
                                }
                                LogUtils.w(TAG,"新版本的URL为："+response.data.baseUrl+response.data.name);
                                mUpdateManager .showNoticeDialog(response.data.desc, response.data.baseUrl+response.data.name);
                            } else {
                                Toast.makeText(FActivity.this, "检查更新失败，错误码 = " + response.error_code + ";" + response.error,Toast.LENGTH_LONG).show();
                                LogUtils.e(TAG, "checkVersionEntity.error_code = " + response.error_code + "; " + response.error);
                            }
                        } else {
                            LogUtils.i(TAG,"response is null");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
                Toast.makeText(FActivity.this, "检查更新失败",Toast.LENGTH_LONG).show();
                LogUtils.e(TAG, "checkVersionEntity is null");
            }
        });
        mQueue.add(jsonObjectRequest);
//        HashMap<String, Object> params = new HashMap<String, Object>();
//        params.put("edition_name", DeviceUtils.getVersionCode(FActivity.this));
//        updatePresenter.getNewVersion(params);
    }

    @Override
    public void onGetNewVersion(CheckVersionEntity checkVersionEntity) {
        if (checkVersionEntity != null) {
            if (checkVersionEntity.error_code == 0) {
                LogUtils.w(TAG, "down load url = " + checkVersionEntity.data);
                if(mUpdateManager == null) {
                    mUpdateManager = new UpdateManager(FActivity.this);
                }
                mUpdateManager .showNoticeDialog(checkVersionEntity.data.desc, checkVersionEntity.data.baseUrl+checkVersionEntity.data.name);
            } else {
                Toast.makeText(FActivity.this, "检查更新失败，错误码 = " + checkVersionEntity.error_code + ";" + checkVersionEntity.error,Toast.LENGTH_LONG).show();
                LogUtils.e(TAG, "checkVersionEntity.error_code = " + checkVersionEntity.error_code + "; " + checkVersionEntity.error);
            }
        } else {
            Toast.makeText(FActivity.this, "检查更新失败",Toast.LENGTH_LONG).show();
            LogUtils.e(TAG, "checkVersionEntity is null");
        }
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
