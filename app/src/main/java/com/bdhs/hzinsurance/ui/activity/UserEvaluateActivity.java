package com.bdhs.hzinsurance.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bdhs.hzinsurance.R;
import com.bdhs.hzinsurance.api.presenters.evaluate.EvaluatePresenter;
import com.bdhs.hzinsurance.api.presenters.evaluate.IEvaluateView;
import com.bdhs.hzinsurance.entity.EvaluateBean;
import com.bdhs.hzinsurance.entity.EvaluateEntity;
import com.bdhs.hzinsurance.ui.view.RoundImageView;
import com.bdhs.hzinsurance.utils.LogUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

public class UserEvaluateActivity extends AppCompatActivity implements IEvaluateView {
    private static final String TAG = "UserEvaluateActivity";
    LinearLayout llRoot;
    TextView tvGoodRate,tvEvaluateNum;
    LayoutInflater inflater;
    private EvaluatePresenter evaluatePresenter;
    private String url;
    RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_evaluate);
        initView();
        url = getIntent().getStringExtra("url");
//        RoundImageView iv_head_pic = (RoundImageView) findViewById(R.id.iv_head_pic);
//        Glide.with(UserEvaluateActivity.this)
//                .load("http://thirdwx.qlogo.cn/mmopen/vi_32/dZdqTEESGtJ8gpGAurg10prnVicN4fOAlictnnvTMGrnw35RuqEn6kKbA6IcEu4pVmZ0MlFeotUiaicevNzvfqZtwA/96")
//                .error(R.mipmap.man)
//                .into(iv_head_pic);
        inflater = LayoutInflater.from(this);
//        View toast_layout = inflater.inflate(R.layout.toast,null);
        evaluatePresenter = new EvaluatePresenter(this);
        permissionCheck();
        mQueue = Volley.newRequestQueue(UserEvaluateActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jResponse) {
                        Log.d("TAG", jResponse.toString());
                        Gson gs = new Gson();
                        EvaluateBean response = gs.fromJson(jResponse.toString(),EvaluateBean.class);
                        if(response != null) {
                            LogUtils.i(TAG,"response:"+response.toString());
                            if(response.nice_ratio>0) {
                                tvGoodRate.setText(response.nice_ratio+"%");
                            }
                            if(response.total>0) {
                                tvEvaluateNum.setText(response.total+"条");
                            }
                            if (response.lists != null) {
                                int size = response.lists.size();
                                for(int k=0;k<size;k++) {
                                    EvaluateEntity evaluateEntity = response.lists.get(k);
                                    addEvaluate(evaluateEntity);
                                }
                            }
                        } else {
                            LogUtils.i(TAG,"response is null");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(jsonObjectRequest);
//        evaluatePresenter.getEvaluate("123942837421");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (evaluatePresenter != null) {
            evaluatePresenter.detachView();
        }
    }

    private void initView() {
        tvGoodRate = (TextView) findViewById(R.id.tv_goodrate);
        tvEvaluateNum = (TextView) findViewById(R.id.tv_evaluate_num);
        llRoot = (LinearLayout) findViewById(R.id.ll_root);
    }



    @Override
    public void onGetEvaluateSucc(EvaluateBean response) {
        if(response != null) {
            LogUtils.i(TAG,"response:"+response.toString());
            if(response.nice_ratio>0) {
                tvGoodRate.setText(response.nice_ratio+"%");
            }
            if(response.total>0) {
                tvEvaluateNum.setText(response.total+"条");
            }
            if (response.lists != null) {
                int size = response.lists.size();
                for(int k=0;k<size;k++) {
                    EvaluateEntity evaluateEntity = response.lists.get(k);
                    addEvaluate(evaluateEntity);
                }
            }
        } else {
            LogUtils.i(TAG,"response is null");
        }
    }

    @Override
    public void onError(Throwable e) {

    }
    private void addEvaluate(EvaluateEntity evaluateEntity) {
        if(evaluateEntity != null) {
            View view = inflater.inflate(R.layout.item_evaluate,null);
            RoundImageView ivIcon = view.findViewById(R.id.iv_head_pic);
            if(evaluateEntity.WeiXinHeadImgUrl != null) {
                Glide.with(UserEvaluateActivity.this)
                        .load(evaluateEntity.WeiXinHeadImgUrl)
                        .error(R.mipmap.man)
                        .into(ivIcon);
            } else {
                ivIcon.setImageResource(R.mipmap.man);
            }
            TextView tvTel = view.findViewById(R.id.tv_tel);
            if(evaluateEntity.Mobile != null) {
                tvTel.setText(evaluateEntity.Mobile);
            } else {
                tvTel.setText("");
            }

            ImageView iv1 = view.findViewById(R.id.iv_1);
            ImageView iv2 = view.findViewById(R.id.iv_2);
            ImageView iv3 = view.findViewById(R.id.iv_3);
            ImageView iv4 = view.findViewById(R.id.iv_4);
            ImageView iv5 = view.findViewById(R.id.iv_5);
            TextView tvLevel = view.findViewById(R.id.tv_level);
            if(evaluateEntity.Star != null) {
                int numStar = Integer.parseInt(evaluateEntity.Star);
                LogUtils.w(TAG,"numStar:"+numStar);
                int i = 0;
                while(numStar > i) {
                    i++;
                    if(i==1) {
                        iv1.setImageResource(R.mipmap.dark_star);
                    } else if(i==2) {
                        iv2.setImageResource(R.mipmap.dark_star);
                    }else if(i==3) {
                        iv3.setImageResource(R.mipmap.dark_star);
                    }else if(i==4) {
                        iv4.setImageResource(R.mipmap.dark_star);
                    }else if(i==5) {
                        iv5.setImageResource(R.mipmap.dark_star);
                    }
                }
                if(numStar == 3) {
                    tvLevel.setText("好");
                }else if(numStar == 4) {
                    tvLevel.setText("很好");
                } else if(numStar == 5) {
                    tvLevel.setText("非常好");
                }
            } else {
                tvLevel.setText("");
            }
            TextView tvDate = view.findViewById(R.id.tv_date);
            if(evaluateEntity.CreateTime != null) {
                tvDate.setText(evaluateEntity.CreateTime);
            } else {
                tvDate.setText("");
            }
            TextView tvContent = view.findViewById(R.id.tv_content);
            if(evaluateEntity.Content != null) {
                tvContent.setText(evaluateEntity.CreateTime);
            } else {
                tvContent.setText("");
            }
            llRoot.addView(view);
        }
    }


    private static final String[] permissionManifest = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    private final int PERMISSION_REQUEST_CODE = 0x001;

    private void permissionCheck() {
        if (Build.VERSION.SDK_INT >= 23) {
            boolean permissionState = true;
            for (String permission : permissionManifest) {
                if (ContextCompat.checkSelfPermission(this, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    permissionState = false;
                }
            }
            if (!permissionState) {
                ActivityCompat.requestPermissions(this, permissionManifest, PERMISSION_REQUEST_CODE);
            } else {
                setSupportCameraSize();
            }
        } else {
            setSupportCameraSize();
        }
    }

    private void setSupportCameraSize() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    if (Manifest.permission.CAMERA.equals(permissions[i])) {
                        setSupportCameraSize();
                    } else if (Manifest.permission.RECORD_AUDIO.equals(permissions[i])) {

                    }
                }
            }
        }
    }

}
