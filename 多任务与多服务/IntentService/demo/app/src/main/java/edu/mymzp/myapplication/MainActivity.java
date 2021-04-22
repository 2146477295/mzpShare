package edu.mymzp.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    void initView() {
        intent = new Intent(this, MyIntentService.class);

        ((Button) findViewById(R.id.main_btn_start)).setOnClickListener(this);
        ((Button) findViewById(R.id.main_btn_start2)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_btn_start) {
            intent.putExtra("data", "1");
            startService(intent);
        } else if (v.getId() == R.id.main_btn_start2) {
            intent.putExtra("data", "2");
            startService(intent);
        }
    }
}