package edu.mymzp.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rgGroup;
    private EditText etText1;
    private EditText etText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 101);

        rgGroup = findViewById(R.id.main_rg_group);

        etText1 = findViewById(R.id.main_et_text1);
        etText2 = findViewById(R.id.main_et_text2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 2) {
                Toast.makeText(this, data.getExtras().getString("result"), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void startIntent(View v) {

        if (v.getId() == R.id.main_btn1) {
            Intent intent = new Intent();
//            ComponentName comp = new ComponentName(this, MainActivity2.class);

//            ComponentName comp = new ComponentName("edu.mymzp.myapplication", "edu.mymzp.myapplication.MainActivity2");

            ComponentName comp = new ComponentName(this, "edu.mymzp.myapplication.MainActivity2");


            intent.setComponent(comp);
            startActivity(intent);
        }

        if (v.getId() == R.id.main_btn2) {
            Intent intent = new Intent();

            intent.setAction("button2");

            startActivity(intent);
        }


        if (v.getId() == R.id.main_btn3) {
            Intent intent = new Intent();

            intent.setAction(Intent.ACTION_VIEW);

            intent.setData(Uri.parse("http://www.baidu.com"));

            startActivity(intent);
        }


        if (v.getId() == R.id.main_btn4) {
            Intent intent = new Intent();

            intent.setAction(Intent.ACTION_VIEW);

            intent.setData(Uri.parse("content://com.android.contacts/contacts"));

            startActivity(intent);
        }

        if (v.getId() == R.id.main_btn5) {
            Intent intent = new Intent();

            intent.setAction(Intent.ACTION_VIEW);

            intent.setData(Uri.parse("content://com.android.contacts/contacts/2"));

            startActivity(intent);
        }

        if (v.getId() == R.id.main_btn6) {
            Intent intent = new Intent();

            intent.setAction(Intent.ACTION_MAIN);
//
//            intent.addCategory(Intent.CATEGORY_APP_CALENDAR);

            intent.addCategory("android.intent.category.APP_CALENDAR");

            startActivity(intent);
        }

        if (v.getId() == R.id.main_btn7) {
            Intent intent = new Intent();

//            intent.setAction("button3");

            intent.setAction("button2");

            intent.addCategory("category3");

            startActivity(intent);
        }
    }

    public void sendIntent(View view) {

        Intent intent = null;

        if (rgGroup.getCheckedRadioButtonId() == R.id.main_rb_btn1) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://taobao.com"));
        }

        if (rgGroup.getCheckedRadioButtonId() == R.id.main_rb_btn2) {
            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:18029567191"));
        }

        if (rgGroup.getCheckedRadioButtonId() == R.id.main_rb_btn3) {
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:18029567191"));
        }

        if (rgGroup.getCheckedRadioButtonId() == R.id.main_rb_btn4) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:128.0,213.4"));
        }

        if (rgGroup.getCheckedRadioButtonId() == R.id.main_rb_btn5) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?=query"));
        }

        if (rgGroup.getCheckedRadioButtonId() == R.id.main_rb_btn6) {
//            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent = new Intent("android.media.action.IMAGE_CAPTURE");
        }

        if (rgGroup.getCheckedRadioButtonId() == R.id.main_rb_btn7) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
        }

        if (rgGroup.getCheckedRadioButtonId() == R.id.main_rb_btn8) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/1"));
        }

        if (intent != null) startActivity(intent);
    }


    public void sendData(View view) {
        Intent intent = new Intent();

//        ComponentName comp = new ComponentName(this, MainActivity2.class);
//        intent.setComponent(comp);

        intent.setAction("button2");

        Bundle bundle = new Bundle();
        
        bundle.putString("text1", etText1.getText().toString());
        bundle.putString("text2", etText2.getText().toString());
        
        intent.putExtras(bundle);

        startActivityForResult(intent, 1);
    }

}