package com.bdhs.hzinsurance.updateapp.fileload;


import com.bdhs.hzinsurance.utils.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class FileCallback implements Callback<ResponseBody> {
    public static final String TAG = "FileCallback";
    /**
     * 订阅下载进度
     */
    private CompositeSubscription rxSubscriptions = new CompositeSubscription();
    /**
     * 目标文件存储的文件夹路径
     */
    private String destFileDir;
    /**
     * 目标文件存储的文件名
     */
    private String destFileName;

    public FileCallback(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
        subscribeLoadProgress();// 订阅下载进度
    }
    /**
     * 成功后回调
     */
    public abstract void onSuccess(File file);

    /**
     * 下载过程回调
     */
    public abstract void onLoading(long progress, long total);

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
            saveFile(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File saveFile(Response<ResponseBody> response) throws Exception {
        InputStream in = null;
        FileOutputStream out = null;
        byte[] buf = new byte[2048*10];
        int len;
        try {
            File dir = new File(destFileDir);
            if (!dir.exists()) {// 如果文件不存在新建一个
                dir.mkdirs();
                LogUtils.i(TAG, "请求成功");
            }
            LogUtils.i(TAG, "1");
            in = response.body().byteStream();
            LogUtils.i(TAG, "2");
            File file = new File(dir,destFileName);
            LogUtils.i(TAG, "3");
            out = new FileOutputStream(file);
            LogUtils.i(TAG, "4");
            while ((len = in.read(buf)) != -1){
                out.write(buf,0,len);
            }
            // 回调成功的接口
            onSuccess(file);
            unSubscribe();// 取消订阅
            return file;
        }finally {
            in.close();
            out.close();
        }
    }
    /**
     * 订阅文件下载进度
     */
    private void subscribeLoadProgress() {
        rxSubscriptions.add(RxBus.getDefault()
                .toObservable(FileLoadingBean.class)
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FileLoadingBean>() {
                    @Override
                    public void call(FileLoadingBean fileLoadEvent) {
                        onLoading(fileLoadEvent.getProgress(), fileLoadEvent.getTotal());
                    }
                }));
//                .subscribe((FileLoadingBean fileLoadEvent) -> {
//                        onLoading(fileLoadEvent.getProgress(), fileLoadEvent.getTotal());
//                }));
    }
    /**
     * 取消订阅，防止内存泄漏
     */
    private void unSubscribe() {
        if (!rxSubscriptions.isUnsubscribed()) {
            rxSubscriptions.unsubscribe();
        }
    }
}
