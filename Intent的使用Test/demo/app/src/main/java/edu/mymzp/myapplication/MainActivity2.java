package edu.mymzp.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvContent = findViewById(R.id.main2_tv_content);

        Intent intent = getIntent();

        tvContent.setText(intent.getExtras().getString("text1") + " , " + intent.getExtras().getString("text2") + "\n你们好！");
    }
    
    public void resultData(View view) {
        Intent intent = new Intent();

        Bundle bundle = new Bundle();

        bundle.putString("result", tvContent.getText().toString());

        intent.putExtras(bundle);

        setResult(2, intent);

        finish();
    }
}