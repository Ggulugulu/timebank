package com.example.timebank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.timebank.R;

public class PingjiaActivity extends Activity {
    Button mSendJudge;
    ImageView mback;
    static int DONE = 1;
    static int BACK = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_judge);
        mSendJudge = findViewById(R.id.send_judge);
        mback = findViewById(R.id.iv_title_back);

        mSendJudge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PingjiaActivity.this,"评价发布成功",Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                setResult(DONE,intent);
                finish();
            }
        });

        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                setResult(BACK,intent);
                finish();
            }
        });
    }
}