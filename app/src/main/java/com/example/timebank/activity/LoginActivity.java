package com.example.timebank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timebank.R;
import com.example.timebank.utils.StringUtils;


public class LoginActivity extends Activity {
    Button mBtLogin;
    TextView mTvRegister;
    EditText mEtLoginUser;
    EditText mEtLoginPassword;
    TextView mTvForgetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_login);
        mBtLogin = findViewById(R.id.bt_login);
        mTvRegister = findViewById(R.id.tv_register);
        mEtLoginUser = findViewById(R.id.et_login_user);
        mEtLoginPassword = findViewById(R.id.et_login_password);
        mTvForgetPassword = findViewById(R.id.tv_forget_password);

        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=mEtLoginUser.getText().toString().trim();
                String password=mEtLoginPassword.getText().toString().trim();
                login(account,password);

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        mTvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }
    private void login(String account, String pwd) {
        if (StringUtils.isEmpty(account)) {
             Toast.makeText(LoginActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
             Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }

//        //第一步创建OKHttpClient
//        OkHttpClient client = new OkHttpClient.Builder()
//                .build();
//        Map m = new HashMap();
//        m.put("phoneNumber", account);
//        m.put("password", pwd);
//        JSONObject jsonObject = new JSONObject(m);
//        String jsonStr = jsonObject.toString();
//        RequestBody requestBodyJson =
//                RequestBody.create(MediaType.parse("application/json;charset=utf-8")
//                        , jsonStr);
//        //第三步创建Rquest
//        Request request = new Request.Builder()
//                .url(AppNetConfig.BASE_URl +AppNetConfig.LOGIN )
//                .addHeader("contentType", "application/json;charset=UTF-8")
//                .post(requestBodyJson)
//                .build();
//        //第四步创建call回调对象
//        final Call call = client.newCall(request);
//        //第五步发起请求
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("onFailure", e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String result = response.body().string();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(LoginActivity.this,result,Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
    }

    }

