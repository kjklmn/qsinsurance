package com.bdhs.hzinsurance.application;
import android.app.Application;
import android.content.Context;

import com.bdhs.hzinsurance.utils.CJYMHandler;

public class MainApplication extends Application {

    private static MainApplication instance;
    private static Context mContext;//上下文
    public CJYMHandler homeHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext =  getApplicationContext();
    }
    public static Context getContext() {
        return mContext;
    }
    public static MainApplication getInstance() {
        return instance;
    }
}
