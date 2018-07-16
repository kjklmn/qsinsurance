package com.bdhs.hzinsurance.application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bdhs.hzinsurance.ui.activity.DepartsActivity;
import com.bdhs.hzinsurance.ui.activity.GuideActivity;

public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent i = new Intent(context, GuideActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
