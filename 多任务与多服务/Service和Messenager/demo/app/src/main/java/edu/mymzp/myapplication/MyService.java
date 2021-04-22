package edu.mymzp.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MyService extends Service {
    private static final String TAG = "TEST";

    static final int PRO_MESSENGER = 1;
    static final int SET_UI_VALUE = 2;

    private Messenger mMessenger = null;

    class ServiceHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case PRO_MESSENGER:
                    Toast.makeText(MyService.this, "没错!! " + msg.getData().getString("data"), Toast.LENGTH_SHORT).show();

                    Message toMsg = Message.obtain(null, SET_UI_VALUE);

                    Bundle bundle = new Bundle();
                    bundle.putString("data2", "我的世界");
                    toMsg.setData(bundle);

                    if (msg.replyTo != null) {
                        try {
                            msg.replyTo.send(toMsg);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }

    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        mMessenger = new Messenger(new ServiceHandler());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        // TODO: Return the communication channel to the service.
        return mMessenger.getBinder();
    }
}