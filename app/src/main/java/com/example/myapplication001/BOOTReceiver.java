package com.example.myapplication001;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BOOTReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            Intent i=new Intent(context,LockScreenActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}