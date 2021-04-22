package edu.mymzp.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private static final String TAG = "TEST";

    private MediaPlayer mPlayer;
    private MediaPlayer mPlayer2;

    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");

        Log.i(TAG, Thread.currentThread().getName());

        Toast.makeText(this, "后台服务启动", Toast.LENGTH_SHORT).show();

        if (intent.getExtras().get("music").equals("R.raw.music")) {
            mPlayer = MediaPlayer.create(this, R.raw.music);
            mPlayer.start();
        } else {
            mPlayer2 = MediaPlayer.create(this, R.raw.music2);
            mPlayer2.start();
        }

        Toast.makeText(this, "开始服务", Toast.LENGTH_SHORT).show();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }

        if (mPlayer2 != null) {
            mPlayer2.release();
            mPlayer2 = null;
        }

        Toast.makeText(this, "后台音乐服务停止", Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i(TAG, "onBind");
        return null;
    }
}