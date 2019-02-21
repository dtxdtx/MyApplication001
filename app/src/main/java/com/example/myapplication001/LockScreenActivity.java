package com.example.myapplication001;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.TextView;


public class LockScreenActivity extends AppCompatActivity {
    Chronometer ch;
    Chronometer setedTime;
    int hour1;
    int minute1;
    int second1;
    Long cha;

    public static void startLockAction(Context context, int hour, int minute, int second, Long cha) {
        Intent intent = new Intent(context, LockScreenActivity.class);
        intent.putExtra("hour", hour);
        intent.putExtra("minute", minute);
        intent.putExtra("second", second);
        intent.putExtra("cha", cha);
        Log.d("主活动", "时：" + hour + "\t 分：" + minute + "\t 秒：" + second);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        if (savedInstanceState!=null){
            hour1=savedInstanceState.getInt("hour");
            minute1=savedInstanceState.getInt("minute");
            second1=savedInstanceState.getInt("second");
            cha=savedInstanceState.getLong("cha");
        }else {
            Intent intent = getIntent();
            hour1 = intent.getIntExtra("hour", 0);
            minute1 = intent.getIntExtra("minute", 1);
            second1 = intent.getIntExtra("second", 0);
            cha = intent.getLongExtra("cha", 0);
            Log.d("主活动", "\t 时：" + hour1 + "\t 分：" + minute1 + "\t 秒：" + second1+"\t 秒"+cha);
        }
        setedTime = findViewById(R.id.seted_time);
        setedTime.setBase(SystemClock.elapsedRealtime()-hour1*60*60*1000-minute1*1000*60-second1*1000);
        Log.d("主活动", "\t 时：" + hour1 + "\t 分：" + minute1 + "\t 秒：" + second1);
        ch = findViewById(R.id.timeshow);
        ch.setBase(SystemClock.elapsedRealtime() - cha);
        ch.setBase(ch.getBase());
        ch.setFormat("%s");
        ch.start();
        ch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - ch.getBase()) > ((hour1 * 3600 + minute1 * 60 + second1) * 1000)) {
                    ch.stop();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        startLockAction(LockScreenActivity.this, hour1, minute1, second1, SystemClock.elapsedRealtime() - ch.getBase());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("主程序",getClass().getSimpleName());
        startLockAction(LockScreenActivity.this, hour1, minute1, second1, SystemClock.elapsedRealtime() - ch.getBase());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("hour",hour1);
        outState.putInt("minute",minute1);
        outState.putInt("second",second1);
        outState.putLong("cha",SystemClock.elapsedRealtime()-ch.getBase());
        Log.d("主活动","onSaveInstanceState000");
    }
}
