package edu.mymzp.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvTitle;
    private ProgressBar pbProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView () {
        tvTitle = findViewById(R.id.main_tv_title);

        pbProgress = findViewById(R.id.main_pb_progress);
    }

    public void executeFun (View v) {
        new MyTask().execute(1000);
    }

    public class MyTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            Log.d("TAG", "onPreExecute");
            tvTitle.setText("onPreExecute");
            pbProgress.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d("TAG", "onProgressUpdate");
            tvTitle.setText("onProgressUpdate");
            pbProgress.setProgress(values[0]);
            super.onProgressUpdate(values);
        }

        // 做耗时操作的方法，必须重写
        @Override
        protected String doInBackground(Integer... integers) {
            Log.d("TAG", Thread.currentThread().getName());
            Log.d("TAG", "doInBackground");
            while (pbProgress.getProgress() != pbProgress.getMax()) {
                try {
                    Thread.sleep(integers[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(pbProgress.getProgress() + 10);
            }
            return "执行完毕";
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("TAG", "onPostExecute");
            tvTitle.setText(s);
            super.onPostExecute(s);
        }
    }
}