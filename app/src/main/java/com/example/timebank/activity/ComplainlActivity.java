package com.example.timebank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.timebank.R;

public class ComplainlActivity extends Activity {
    ImageView mImTitleBack;
    Button mSend;
    static int DONE = 1;
    static int BACK = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_complain);
        Spinner spinner = (Spinner) findViewById(R.id.complain_spinner);
        mImTitleBack = findViewById(R.id.iv_title_back);
        mSend = findViewById(R.id.complain_send);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                String[] duixiang = getResources().getStringArray(R.array.duixiang);
                Toast.makeText(ComplainlActivity.this, "你点击的是:"+duixiang[pos], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        mImTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getIntent() != null){
                    Intent intent = getIntent();
                    setResult(BACK,intent);
                    finish();
                }else{
                    Intent intent = new Intent(ComplainlActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ComplainlActivity.this,"上传成功！请等待处理...",Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                setResult(DONE,intent);
                finish();
            }
        });

    }





}
