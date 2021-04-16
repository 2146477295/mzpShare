package edu.mymzp.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private Button notificationBtn;
    private ProgressBar mProgressBar;
    private NotificationManager notificationManager;
    private NotificationChannel channel;
    private Notification notification;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                if (mProgressBar.getProgress() != mProgressBar.getMax()) {
                    int progressInt = mProgressBar.getProgress();
                    mProgressBar.setProgress(progressInt + 1);
                    sendEmptyMessageDelayed(1, 100);
                } else {
                    notificationManager.notify(1, notification);
                }
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView() {
        notificationBtn = findViewById(R.id.main_btn_notification);

        mProgressBar = findViewById(R.id.main_pb_progress);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        channel = new NotificationChannel("channelId_1", "channelName_1", NotificationManager.IMPORTANCE_DEFAULT);

        notificationManager.createNotificationChannel(channel);

        notification = (Notification) new NotificationCompat.Builder(this, channel.getId())
                .setContentTitle("这是我的消息")
                .setContentText("今天是个好日子")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.welcome_img))
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setShowWhen(true)
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity2.class), 0))
                .build();

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.sendEmptyMessage(1);
            }
        });
    }
}