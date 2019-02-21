package com.example.myapplication001;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText hour;
    private EditText minute;
    private EditText second;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        Log.d("主活动", "主活动已创建");
        hour = findViewById(R.id.hour);
        minute = findViewById(R.id.minute);
        second = findViewById(R.id.second);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button:
                        int hour1 = Integer.parseInt(hour.getText().toString());
                        int minute1 = Integer.parseInt(minute.getText().toString());
                        int second1 = Integer.parseInt(second.getText().toString());
                        if (hour1 == 0 && minute1 == 0 && second1 == 0) {
                            Toast.makeText(MainActivity.this, "请设置时间", Toast.LENGTH_SHORT).show();
                            Log.d("主活动", "设置时间");
                            break;
                        }
                        else {
                            LockScreenActivity.startLockAction(MainActivity.this, hour1, minute1,second1, (long) 0);
                        }
                        break;
                    default:
                        break;

                }

            }
        });
    }

}
