package com.example.timebank.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timebank.R;

public class GetVolActivity extends BaseActivity {
    TextView mVolTimeBegin;
    TextView mVolTimeEnd;
    ImageView mImTitleBack;
    CheckBox mCheckBox;

    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_get_vol);
        mImTitleBack = findViewById(R.id.iv_title_back);
        mVolTimeBegin = findViewById(R.id.click_begin_time);
        mVolTimeEnd = findViewById(R.id.click_end_time);
        send = findViewById(R.id.bt_send);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                String[] vol = getResources().getStringArray(R.array.vol);
                //Toast.makeText(GetVolActivity.this, "你点击的是:"+duixiang[pos], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
//        mCheckBox = findViewById(R.id.radio_jiaji);
//
        //志愿时间开始选择
        mVolTimeBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTime(mVolTimeBegin);
            }
        });

        //志愿时间结束选择
        mVolTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTime(mVolTimeEnd);
            }
        });

        mImTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetVolActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GetVolActivity.this,"提交成功，等待审核",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GetVolActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
