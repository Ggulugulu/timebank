package com.example.timebank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.timebank.R;

public class CheckSqActivity extends Activity {
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_check_sq);

        send = findViewById(R.id.bt_be_business);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckSqActivity.this, MainActivity.class);
                Toast.makeText(CheckSqActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}