package edu.mymzp.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String strContent = intent.getStringExtra("msg");
        Log.d("TAG", "接受到了对应的广播信息: " + strContent);

        MainActivity.tvContent.setText(strContent);
    }
}
