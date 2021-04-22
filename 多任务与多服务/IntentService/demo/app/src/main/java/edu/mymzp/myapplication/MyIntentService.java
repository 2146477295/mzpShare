package edu.mymzp.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {


    /**
     * MyIntentService的构造方法
     */
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        Log.d("TAG", "服务启动了");
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            if ("1".equals(intent.getExtras().get("data"))) {
                Log.d("TAG", Thread.currentThread().getName());
                for (int i = 1; i <= 10; i++) {
                    try {
                        Thread.sleep(1000);
                        Log.d("TAG", "当前的数值为: " + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else if ("2".equals(intent.getExtras().get("data"))) {
                Log.d("TAG", Thread.currentThread().getName());
                for (int i = 10; i <= 20; i++) {
                    Log.d("TAG", "当前的数值为: " + i);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.d("TAG", "服务停止了");
        super.onDestroy();
    }
}