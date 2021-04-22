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
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    ServiceConnection mConnection;

    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, MyService.class);

        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        ((Button) findViewById(R.id.main_btn_startService)).setOnClickListener(this);
        ((Button) findViewById(R.id.main_btn_stopService)).setOnClickListener(this);
        ((Button) findViewById(R.id.main_btn_bindService)).setOnClickListener(this);
        ((Button) findViewById(R.id.main_btn_unbindService)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_btn_startService) {
            startService(intent);
        } else if (v.getId() == R.id.main_btn_stopService) {
            stopService(intent);
        } else if (v.getId() == R.id.main_btn_bindService) {
            if (++flag > 1) {
                Log.d("TEST", "再次绑定服务");
            }
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        } else if (v.getId() == R.id.main_btn_unbindService) {
            unbindService(mConnection);
        }
    }
}