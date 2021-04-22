package edu.mymzp.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class MyBindService extends Service {
    private static final String TAG = "TEST";

    private MyBinder mBinder;

    class MyBinder extends Binder {
        @Nullable
        @Override
        public String getInterfaceDescriptor() {
            return super.getInterfaceDescriptor();
        }

        protected MyBindService getService() {
            return MyBindService.this;
        }
    }

    public MyBindService() {
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        mBinder = new MyBinder();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
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
        return mBinder;
    }

    public int getRandomNumber() {
        return new Random().nextInt(100);
    }
}