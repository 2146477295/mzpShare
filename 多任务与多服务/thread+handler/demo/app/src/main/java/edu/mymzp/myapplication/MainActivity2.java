package edu.mymzp.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private TextView tvContent;
    private Button updateProgressBtn;
    private ProgressBar pbProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
    }

    void initView() {
        tvContent = findViewById(R.id.main2_tv_content);
        updateProgressBtn = findViewById(R.id.main2_btn_updateProgress);
        pbProgress = findViewById(R.id.main2_pb_progress);

        updateProgressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTaskChild().execute(1000, 2000);
            }
        });
    }

    public class AsyncTaskChild extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            Log.d("TAG", "onPreExecute");
            tvContent.setText("onPreExecute");
            pbProgress.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d("TAG", "onProgressUpdate");
            tvContent.setText("onProgressUpdate");
            pbProgress.setProgress(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            Log.d("TAG", "doInBackground");
            for (int i = 0 ; i < 10; i++) {
                try {
                    Thread.sleep(integers[1]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress((i + 1) * 10);
            }
            return "执行完毕";
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("TAG", "onPostExecute");
            tvContent.setText(s);
            super.onPostExecute(s);
        }
    }


}