package edu.mymzp.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.view.ActionProvider;

public class MyActionProvider extends ActionProvider {

    /**
     * Creates a new instance. ActionProvider classes should always implement a
     * constructor that takes a single Context parameter for inflating from menu XML.
     *
     * @param context Context for accessing resources.
     */
    public MyActionProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_item, null, false);

        Button shoneBtn = view.findViewById(R.id.main_btn_shone);

        shoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "自定义ActionProvider", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public boolean onPerformDefaultAction() {
        Toast.makeText(getContext(), "自定义ActionProvider2", Toast.LENGTH_SHORT).show();
        return super.onPerformDefaultAction();
    }
}
