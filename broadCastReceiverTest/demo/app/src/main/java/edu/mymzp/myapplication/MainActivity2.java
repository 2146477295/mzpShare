package edu.mymzp.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {
    MyCompletedReceiver myCompletedReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        requestPermissions(new String[]{Manifest.permission.RECEIVE_BOOT_COMPLETED}, 101);

        myCompletedReceiver = new MyCompletedReceiver();

        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction("android.intent.action.BOOT_COMPLETED");

        registerReceiver(myCompletedReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myCompletedReceiver);
    }
}