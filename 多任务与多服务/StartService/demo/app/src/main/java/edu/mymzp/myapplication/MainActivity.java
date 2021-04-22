package edu.mymzp.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button startServiceBtn, startServiceBtn2, stopServiceBtn;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, MyService.class);

        initView();

    }

    void initView() {
        ((Button) findViewById(R.id.main_btn_startService)).setOnClickListener(this);
        ((Button) findViewById(R.id.main_btn_startService2)).setOnClickListener(this);
        ((Button) findViewById(R.id.main_btn_stopService)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_btn_startService) {
            intent.putExtra("music", "R.raw.music");
            startService(intent);
        } else if (v.getId() == R.id.main_btn_startService2) {
            intent.putExtra("music", "R.raw.music2");
            startService(intent);
        } else if (v.getId() == R.id.main_btn_stopService) {
            stopService(intent);
        }
    }
}