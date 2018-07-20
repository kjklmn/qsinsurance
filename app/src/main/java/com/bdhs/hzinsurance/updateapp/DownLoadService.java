package com.bdhs.hzinsurance.updateapp;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

//import com.cjym.yunmabao.R;
//import com.cjym.yunmabao.application.MainApplication;
//import com.cjym.yunmabao.updateapp.fileload.FileCallback;
//import com.cjym.yunmabao.updateapp.fileload.FileResponseBody;
//import com.cjym.yunmabao.utils.CJYMHandler;
//import com.cjym.yunmabao.utils.LogUtils;

import com.bdhs.hzinsurance.R;
import com.bdhs.hzinsurance.application.MainApplication;
import com.bdhs.hzinsurance.updateapp.fileload.FileCallback;
import com.bdhs.hzinsurance.updateapp.fileload.FileResponseBody;
import com.bdhs.hzinsurance.utils.CJYMHandler;
import com.bdhs.hzinsurance.utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class DownLoadService extends Service {

    private static final String TAG = "DownLoadService";
    /**
     * 目标文件存储的文件夹路径
     */
    private String destFileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File
            .separator + "huizhong_APK";

    /**
     * 目标文件存储的文件名
     */
    private String destFileName = "qsinsurance.apk";

    private Context mContext;
    private int preProgress = 0;
    private int NOTIFY_ID = 1000;
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private Retrofit.Builder retrofit;
    //private String baseUrl = http://cimg.dev.chaojiyunma.com/ymb/app/app-dev.v1.1.apk
    private String baseUrl = "";
    private String name;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null) {
            mContext = this;
            String tUrl = intent.getStringExtra("download_url");
            LogUtils.w(TAG,"tUrl = "+tUrl);
            if(tUrl != null && tUrl.trim().length() > 7) {
                int indexofslash = tUrl.indexOf("/", 10);//从第8开始查找第一次出现的/的索引
                if(indexofslash > 0) {
                    baseUrl = tUrl.substring(0,indexofslash);//"http://cimg.dev.chaojiyunma.com/";
                    int index = tUrl.lastIndexOf("/");
                    name = tUrl.substring(index + 1, tUrl.length());//app-dev.v1.1.apk
                    initNotification();
                    loadFile();
                }
            }

        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 下载文件
     */
    private void loadFile() {
        LogUtils.w(TAG,"baseUrl = "+baseUrl+"; name = "+name);
        if (retrofit == null) {
            retrofit = new Retrofit.Builder();
        }
        retrofit.baseUrl(baseUrl)
                .client(initOkHttpClient())
                .build()
                .create(IFileLoad.class)
                .loadFile(name)
                .enqueue(new FileCallback(destFileDir, destFileName) {

                    @Override
                    public void onSuccess(File file) {
                        LogUtils.i(TAG, "请求成功");
                        // 安装软件
                        MainApplication.getInstance().homeHandler.obtainMessage(CJYMHandler.MSG_UPDATE_APP_DISMISS,file).sendToTarget();
                        cancelNotification();
//                        installApk(file);
                        stopSelf();//开始安装之后就停止
                    }

                    @Override
                    public void onLoading(long progress, long total) {
                        MainApplication.getInstance().homeHandler.obtainMessage(CJYMHandler.MSG_UPDATE_APP_SHOW,(int)(progress * 100 / total)).sendToTarget();
                        updateNotification(progress * 100 / total);
                        LogUtils.w(TAG,"progress = "+(int)(progress * 100 / total));
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e(TAG, "请求下载失败："+t.getMessage());
                        cancelNotification();
                        MainApplication.getInstance().homeHandler.obtainMessage(CJYMHandler.MSG_UPDATE_APP_FAIL).sendToTarget();
                        stopSelf();//解bug：无网络时，通过最近应用虚拟键退出app之后会抛错
                    }
                });
    }

    public interface IFileLoad {
        @GET("ymb/app/{name}")
        Call<ResponseBody> loadFile(@Path("name") String name);
    }

    /**
     * 安装软件
     *
     * @param file
     */
    private void installApk(File file) {
        Uri uri = Uri.fromFile(file);
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        install.setDataAndType(uri, "application/vnd.android.package-archive");
        // 执行意图进行安装
        mContext.startActivity(install);
    }

    /**
     * 初始化OkHttpClient
     *
     * @return
     */
    private OkHttpClient initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(100000, TimeUnit.SECONDS);
        builder.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse
                        .newBuilder()
                        .body(new FileResponseBody(originalResponse))
                        .build();
            }
        });
        return builder.build();
    }

    /**
     * 初始化Notification通知
     */
    public void initNotification() {
        builder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("0%")
                .setContentTitle("惠众保险更新")
                .setProgress(100, 0, false);
        notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    /**
     * 更新通知
     */
    public void updateNotification(long progress) {
        int currProgress = (int) progress;
        if (preProgress < currProgress) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, (int) progress, false);
            notificationManager.notify(NOTIFY_ID, builder.build());
        }
        preProgress = (int) progress;
    }

    /**
     * 取消通知
     */
    public void cancelNotification() {
        notificationManager.cancel(NOTIFY_ID);
    }
}
