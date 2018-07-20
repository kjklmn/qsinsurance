package com.bdhs.hzinsurance.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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
import com.bdhs.hzinsurance.application.MainApplication;
import com.bdhs.hzinsurance.config.DebugConfig;
import com.bdhs.hzinsurance.config.InsuranceCategory;
import com.bdhs.hzinsurance.entity.EvaluateBean;
import com.bdhs.hzinsurance.entity.EvaluateEntity;
import com.bdhs.hzinsurance.ui.view.BoneitemView;
import com.bdhs.hzinsurance.ui.view.RoundImageView;
import com.bdhs.hzinsurance.utils.LogUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONObject;


public class BoneActivity extends BaseActivity implements IEvaluateView {
    private static final String TAG = "BoneActivity";
    private int departs = -1;

    private int[] draws = {R.mipmap.pone,R.mipmap.ptwo,R.mipmap.pthree,R.mipmap.pfour};
    LayoutInflater inflater;
    LinearLayout llRootEva;
    LinearLayout llRootQA;
    private EvaluatePresenter evaluatePresenter;
    RequestQueue mQueue;
    String evaluateUrl = "";
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
        inflater = LayoutInflater.from(this);
        addQA();
        DebugConfig.getDeviceId();
//        evaluatePresenter = new EvaluatePresenter(this);
        mQueue = Volley.newRequestQueue(BoneActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        evaluateUrl = "http://www3.szhuizhong.cn/index/get_comment/"+InsuranceCategory.Depart.getProductId(departs);
        LogUtils.i(TAG,"evaluateUrl:"+evaluateUrl);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(evaluateUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jResponse) {
                        Log.d("TAG", jResponse.toString());
                        Gson gs = new Gson();
                        EvaluateBean response = gs.fromJson(jResponse.toString(),EvaluateBean.class);
                        if(response != null) {
                            LogUtils.i(TAG,"response:"+response.toString());
                            addEvaluate(response);
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
        TextView tvAllEvaluate = (TextView) findViewById(R.id.tv_all_evaluate);
        tvAllEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoneActivity.this,UserEvaluateActivity.class);
                intent.putExtra("url",evaluateUrl);
                startActivity(intent);
            }
        });
        //evaluatePresenter.getEvaluate("1000003800");
    }

    //添加二维码那行信息
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
    //添加评价
    private void addEvaluate(EvaluateBean response) {
        llRootEva = (LinearLayout) findViewById(R.id.ll_root_evaluate);
        TextView tvGoodRate = (TextView) findViewById(R.id.tv_goodrate);
        TextView tvEvaluateNum = (TextView) findViewById(R.id.tv_evaluate_num);
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
                    addEvaluateItem(evaluateEntity);
                    if(k>=3) break;//最多只显示3条
                }
            }
        } else {
            LogUtils.i(TAG,"response is null");
        }

    }
    private void addEvaluateItem(EvaluateEntity evaluateEntity) {
        if(evaluateEntity != null) {
            View view = inflater.inflate(R.layout.item_evaluate,null);
            RoundImageView ivIcon = view.findViewById(R.id.iv_head_pic);
            if(evaluateEntity.WeiXinHeadImgUrl != null) {
                Glide.with(BoneActivity.this)
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
            llRootEva.addView(view);
        }
    }

    //添加问答
    private void addQA() {
        llRootQA = (LinearLayout) findViewById(R.id.ll_root_qa);
        int size = DebugConfig.aList.length;
        for(int i=0;i<size;i++) {
            if(i>=4) break;//最多只显示4条
            addQAItem(llRootQA,DebugConfig.qList[i],DebugConfig.aList[i]);
        }
    }

    private void addQAItem(LinearLayout root,  String num, String amount) {
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

    @Override
    public void onGetEvaluateSucc(EvaluateBean response) {

    }

    @Override
    public void onError(Throwable e) {

    }
}
