package edu.mymzp.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button sendMessengerBtn;

    Intent intent = null;
    ServiceConnection mConnection;
    boolean mBind = false;

    Messenger rMessenger;

    Messenger mMessenger;

    Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MyService.SET_UI_VALUE:
                    sendMessengerBtn.setText(msg.getData().getString("data2"));
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendMessengerBtn = findViewById(R.id.main_btn_send);

        intent = new Intent(this, MyService.class);

        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d("TEST", "已连接服务");
                rMessenger = new Messenger(service);
                mBind = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d("TEST", "已断开服务");

            }
        };

        mMessenger = new Messenger(mHandler);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBind) {
            unbindService(mConnection);
            mBind = false;
        }
    }

    public void sendMessenger(View v) {
        if (!mBind) {
            Toast.makeText(this, "当前未绑定服务", Toast.LENGTH_SHORT).show();
            return;
        }

        Message msg = Message.obtain(null, MyService.PRO_MESSENGER);
        Bundle bundle = new Bundle();
        bundle.putString("data", "今天天气真好");
        msg.setData(bundle);

        // msg通过replyTo属性可以携带一个Messenger对象，我们可以将这个对象发送到服务，然后服务通过这个信使对象向绑定的对应的客户端发送消息。
        msg.replyTo = mMessenger;

        // 通过信使对象向客户端绑定的服务发送消息
        try {
            rMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void disConnection(View v) {
        if (mBind) {
            unbindService(mConnection);
            mBind = false;
            Toast.makeText(this, "已断开服务", Toast.LENGTH_SHORT).show();
        }
    }
}