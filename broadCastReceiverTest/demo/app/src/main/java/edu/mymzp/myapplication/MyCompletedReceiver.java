package edu.mymzp.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "应用程序启动了", Toast.LENGTH_SHORT).show();
        Log.d("TAG", "应用程序启动了");
    }
}
