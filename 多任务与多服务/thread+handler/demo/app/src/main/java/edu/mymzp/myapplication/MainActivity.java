package edu.mymzp.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvContent, tvCount;
    private Button startIntentBtn, startCountBtn, stopCountBtn, updateProgressBtn, stopUpdateProgressBtn;
    private ProgressBar pbProgress;

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.d("TAG", Thread.currentThread().getName());
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    tvContent.setText("内容改变了");
                    break;
                default:
                    break;
            }
        }
    };


    private Handler mHandler2 = new Handler();

    private Handler mHandler3 = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            pbProgress.setProgress(msg.arg1);
            mHandler3.post(mRunUpdateProgressImpl);
            if (msg.arg1 > 100) {
                mHandler3.removeCallbacks(mRunUpdateProgressImpl);
            }
            Log.d("TAG", msg.arg1 + "");

        }
    };

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("TAG", Thread.currentThread().getName() + "");

        setContentView(R.layout.activity_main);

        new ThreadChild().start();

        initView();
    }


    /**
     * 初始化方法
     */
    public void initView() {
        tvContent = findViewById(R.id.main_tv_hello);
        tvCount = findViewById(R.id.main_tv_count);

        pbProgress = findViewById(R.id.main_pb_progress);

        startIntentBtn = findViewById(R.id.main_btn_startIntent);
        startCountBtn = findViewById(R.id.main_btn_startCount);
        stopCountBtn = findViewById(R.id.main_btn_stopCount);
        updateProgressBtn = findViewById(R.id.main_btn_updateProgress);
        stopUpdateProgressBtn = findViewById(R.id.main_btn_stopUpdateProgress);


        startIntentBtn.setOnClickListener(this);
        startCountBtn.setOnClickListener(this);
        stopCountBtn.setOnClickListener(this);
        updateProgressBtn.setOnClickListener(this);
        stopUpdateProgressBtn.setOnClickListener(this);

    }


    public class ThreadChild extends Thread {
        @Override
        public void run() {
            super.run();
            Log.d("TAG", Thread.currentThread().getName() + "");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Message msg = new Message();
            msg.what = 1;
            mHandler.sendMessage(msg);
        }
    }

    /**
     * 弹出Toast的Runnable对象
     */
    Runnable run1 = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_SHORT).show();
        }
    };


    /**
     * 计数的Runnable对象
     */

    Runnable mRunCount = new Runnable() {
        @Override
        public void run() {
            tvCount.setText("Count: " + ++count);
            mHandler2.postDelayed(mRunCount, 1000);
        }
    };

    /**
     * 更新进度的Runnable对象
     */
    Runnable mRunUpdateProgressImpl = new Runnable() {
        @Override
        public void run() {
            Message msg = mHandler3.obtainMessage();
            msg.arg1 = pbProgress.getProgress() + 10;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mHandler3.sendMessage(msg);
        }
    };

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_btn_startIntent) {
            mHandler2.postAtTime(run1, SystemClock.uptimeMillis() + 5 * 1000);
        } else if(v.getId() == R.id.main_btn_startCount) {
            mHandler2.postDelayed(mRunCount, 1000);
        } else if (v.getId() == R.id.main_btn_stopCount) {
            mHandler2.removeCallbacks(mRunCount);
            Log.d("TAG", "on");
        } else if (v.getId() == R.id.main_btn_updateProgress) {
            mHandler3.post(mRunUpdateProgressImpl);
        } else if (v.getId() == R.id.main_btn_stopUpdateProgress) {
            mHandler3.removeCallbacks(mRunUpdateProgressImpl);
        }
    }
}