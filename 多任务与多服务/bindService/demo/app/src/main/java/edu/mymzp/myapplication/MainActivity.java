package edu.mymzp.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private ServiceConnection mConnection;
    private MyBindService mService;
    private boolean mBind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, MyBindService.class);

        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d("TEST", "连接成功");
                mService = ((MyBindService.MyBinder) service).getService();
                mBind = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d("TEST", "断开连接");
                mBind = false;
            }
        };

    }

    public void bindService(View v) {
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }


    public void unbindService(View v) {
        if (mBind) {
            unbindService(mConnection);
            mBind = false;
            mService = null;
        }
    }

    public void getNumber(View v) {
        if (mBind) {
            Toast.makeText(this, "获取到的随机数是: " + mService.getRandomNumber(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "当前未连接到服务", Toast.LENGTH_SHORT).show();
        }
    }
}