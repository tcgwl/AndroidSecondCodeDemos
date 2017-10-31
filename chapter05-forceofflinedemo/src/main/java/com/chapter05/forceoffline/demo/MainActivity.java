package com.chapter05.forceoffline.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chapter05.forceoffline.demo.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button forceOfflineBtn = (Button) findViewById(R.id.force_offline);
        forceOfflineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ACTION_FORCE_OFFLINE);
                sendBroadcast(intent);
            }
        });
    }
}
