package edu.mymzp.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button sendBroadcastBtn, registerReceiverBtn, unregisterReceiverBtn;
    private MyActionReceiver myActionReceiver;
    private MyCompletedReceiver myCompletedReceiver;
    static TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContent = findViewById(R.id.main_tv_content);

        sendBroadcastBtn = findViewById(R.id.main_btn_sendBroadcast2);

        registerReceiverBtn = findViewById(R.id.main_btn_registerReceiver);

        unregisterReceiverBtn = findViewById(R.id.main_btn_unregisterReceiver);

        sendBroadcastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("myBroadcast");
                intent.putExtra("result", "我是动态广播");
                sendBroadcast(intent);
            }
        });

        registerReceiverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myActionReceiver = new MyActionReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("myBroadcast");
                registerReceiver(myActionReceiver, intentFilter);
            }
        });

        unregisterReceiverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myActionReceiver != null) {
                    unregisterReceiver(myActionReceiver);
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myCompletedReceiver);
    }

    // 静态注册广播(已经不再被很好的支持了，推荐使用动态注册广播的方式注册广播接收器)
    public void sendBroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction("mySendBroadcast");
        intent.setComponent(new ComponentName("edu.mymzp.myapplication", "edu.mymzp.myapplication.MyReceiver"));
        intent.putExtra("msg", "我是广播");
        sendBroadcast(intent);
    }
}