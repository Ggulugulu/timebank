package com.example.timebank.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timebank.R;
import com.example.timebank.utils.StringUtils;

public class ForgetPasswordActivity extends Activity {
    TextView mTvForget;
    EditText mEtPhone;

    public boolean isChange = false;
    private boolean tag = true;
    private int i = 60;
    Thread thread = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_forget_password);
        mTvForget = findViewById(R.id.tv_forget_phone_code);
        mTvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvForget.setClickable(true);
                isChange = true;
                changeBtnGetCode();
            }
        });
    }
    private boolean isvalidate() {
        // TODO Auto-generated method stub
        // 获取控件输入的值
        String phone = mEtPhone.getText().toString().trim();
        if (StringUtils.isEmpty(phone)) {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!StringUtils.isPhoneNumberValid(phone)) {
            Toast.makeText(this, "手机号有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void changeBtnGetCode() {
        //Toast.makeText(ForgetPasswordActivity.this,"123", Toast.LENGTH_SHORT).show();

         thread = new Thread() {
            @Override
            public void run() {
                if (tag) {
                    while (i > 0) {
                        i--;
                        if (ForgetPasswordActivity.this == null) {
                            break;
                        }
                        ForgetPasswordActivity.this
                                .runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mTvForget.setText("获取验证码(" + i + ")");
                                        mTvForget.setClickable(false);
                                        mTvForget.setTextColor(getResources().getColor(R.color.bottom_tv_gray));
                                    }
                                });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    tag = false;

                }
                i = 60;
                tag = true;
                if (ForgetPasswordActivity.this != null) {
                    ForgetPasswordActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTvForget.setText("获取验证码");
                            mTvForget.setClickable(true);
                            mTvForget.setTextColor(getResources().getColor(R.color.bottom_iv_blue));
                        }
                    });
                }
            };
        };
        thread.start();
    }



}