package com.example.timebank.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.timebank.R;


public class RegisterActivity extends BaseActivity {
    TextView mTvLocation;
    TextView mTvPhoneSend;
    TextView mTvPhone;
    TextView mTvPhoneCode;
    ImageView mIvArrowLocation;
    Button mBtRegister;
    RelativeLayout mActivityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_register);
        mTvLocation = findViewById(R.id.tv_location);
        mTvPhoneSend = findViewById(R.id.tv_register_phone_code);
        mTvPhone = findViewById(R.id.et_register_phone);
        mTvPhoneCode = findViewById(R.id.et_register_phone_code);
        mIvArrowLocation = findViewById(R.id.iv_arrow_location);
        mBtRegister = findViewById(R.id.bt_register);
        mActivityLogin = findViewById(R.id.activity_login);

        mIvArrowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loadLocation(RegisterActivity.this,mTvLocation);
            }
        });

    }

}
