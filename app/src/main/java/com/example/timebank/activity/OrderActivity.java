package com.example.timebank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.timebank.R;

public class OrderActivity extends Activity {
    TextView mTvCost;
    RelativeLayout mLlPlaceChoose;
    TextView mTvPlace;
    EditText morePlace;
    TextView check;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_order);
        mTvCost = findViewById(R.id.tv_cost);
        mLlPlaceChoose = findViewById(R.id.ll_choosesite);
        mTvPlace = findViewById(R.id.tv_shopping_place);
        morePlace = findViewById(R.id.edit_site);
        check = findViewById(R.id.text_submit);
        back = findViewById(R.id.iv_title_back);

        Bundle bundle = this.getIntent().getExtras(); //读取intent的数据给bundle对象
        float cost = bundle.getFloat("cost");
        mTvCost.setText("¥"+cost);

        //收货地址选择
        mLlPlaceChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.loadLocation(OrderActivity.this,mTvPlace);
            }
        });

       morePlace.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               handerButton();
           }

           @Override
           public void afterTextChanged(Editable s) {
               handerButton();
           }
       });

        //确认订单
       check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check.getText().toString().equals("确认订单")){
                    Intent intent = new Intent(OrderActivity.this,OrderOKActivity.class);
                    startActivity(intent);
                }
            }
        });
       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(OrderActivity.this,GoodsXqActivity.class);
               startActivity(intent);
           }
       });



    }

    //根据是否填了地址判断是否可以提交订单
    private void handerButton() {
        if(!TextUtils.isEmpty(morePlace.getText().toString().trim())) {
            check.setBackgroundColor(0xff4DBE66);
            check.setTextColor(0xffffffff);
            check.setClickable(true);
            check.setText("确认订单");
        }else{
            check.setBackgroundColor(0xffe7e9e7);
            check.setTextColor(0xff979997);
            check.setClickable(false);
            check.setText("请完善地址");
        }
    }





}