package com.zxf.empty.aspectjstu;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhangxf.plugin.OnClickView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBt();
    }

    private void initBt() {
        Button button = (Button) findViewById(R.id.bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "button", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.bt_annotation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickView(v);
            }
        });
    }

    @OnClickView
    private void onClickView(View v) {
        Toast.makeText(MainActivity.this, "OnClickView", Toast.LENGTH_SHORT).show();
    }
}