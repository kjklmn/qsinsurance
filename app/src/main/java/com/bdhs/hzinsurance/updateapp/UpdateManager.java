package com.bdhs.hzinsurance.updateapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.bdhs.hzinsurance.BuildConfig;
import com.bdhs.hzinsurance.utils.LogUtils;

import java.io.File;

public class UpdateManager {
    public static final String TAG = "UpdateManager";
    Activity mActivity;

    public UpdateManager(Activity context) {
        this.mActivity = context;
    }

    /**
     * 检测软件更新
     */
    public void checkUpdate(final boolean isToast) {
        /**
         * 在这里请求后台接口，获取更新的内容和最新的版本号
         */
        // 版本的更新信息
        String version_info = "更新内容\n" + "    1. 数据库更新\n" + "    2. 增加电商功能\n" + "    ";
        int mVersion_code = DeviceUtils.getVersionCode(mActivity);// 当前的版本号
        int nVersion_code = 101;//从网络中或取得版本号
        LogUtils.w("UpdateManager", "mVersion_code = " + mVersion_code);
        if (mVersion_code < nVersion_code) {
            // 显示提示对话
            //showNoticeDialog(version_info);
        } else {
            if (isToast) {
                Toast.makeText(mActivity, "已经是最新版本", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 显示更新对话框
     *
     * @param
     */
    public void showNoticeDialog(String version_info, final String url) {
        if(url == null || url.trim().equals(""))
        {
            LogUtils.e("UpdateManager","url is null");
            return;
        }
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("当前非最新版，请点击更新！");
        builder.setMessage(version_info);
        if(BuildConfig.DEBUG) {
            builder.setCancelable(true);
        } else {
            builder.setCancelable(false);
        }

        // 更新
//        builder.setPositiveButton("立即更新", (DialogInterface dialog, int which)-> {
//                dialog.dismiss();
//                Intent intent = new Intent(mActivity, DownLoadService.class);
//                intent.putExtra("download_url", url);
//                //                intent.putExtra("download_name", name);
//                mActivity.startService(intent);
//                //                mContext.startService(new Intent(mContext, DownLoadService.class));
//        });
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(mActivity, DownLoadService.class);
                intent.putExtra("download_url", url);
                //                intent.putExtra("download_name", name);
                mActivity.startService(intent);
            }
        });
        // 稍后更新
        //        builder.setNegativeButton("以后更新", new DialogInterface.OnClickListener() {
        //            @Override
        //            public void onClick(DialogInterface dialog, int which) {
        //                dialog.dismiss();
        //            }
        //        });
        AlertDialog noticeDialog = builder.create();
        noticeDialog.show();
        noticeDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(0xff333333);
    }


    ProgressDialog progressUpdate;
    public void initProgress() {
        progressUpdate = new ProgressDialog(mActivity);
        progressUpdate.setTitle("更新");
        progressUpdate.setMessage("更新中，请稍候...");
        progressUpdate.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressUpdate.setProgress(0);
        progressUpdate.setCancelable(false);
        progressUpdate.setMax(100);
    }

    public void updataProgressUpdate(int progress) {
        if(progressUpdate == null) {
            initProgress();
        }
        if (progressUpdate != null) {
            if (!progressUpdate.isShowing()) {
                progressUpdate.show();
            }
            progressUpdate.setProgress(progress);
        }
    }

    public void dismissProgressUpdate() {
        if (progressUpdate != null) {
            progressUpdate.dismiss();
        }
    }

    public void installApk(File file) {
        Intent install = new Intent(Intent.ACTION_VIEW);


        if (Build.VERSION.SDK_INT >= 24) {
            //provider authorities

            Uri apkUri = FileProvider.getUriForFile(mActivity, BuildConfig.APPLICATION_ID+".fileprovider", file);
//            LogUtils.w(TAG,"apkUri = "+apkUri);
            //Granting Temporary Permissions to a URI
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        // 执行意图进行安装
        mActivity.startActivity(install);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
