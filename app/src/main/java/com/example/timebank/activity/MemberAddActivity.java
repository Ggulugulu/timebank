package com.example.timebank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timebank.R;

public class MemberAddActivity extends Activity {
    EditText name;
    EditText phone;
    TextView place;
    EditText guanxi;
    TextView send;
    ImageView back;
    static int DONE=1;
    static int BACK=11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_member_add);
        name = findViewById(R.id.et_name);
        phone = findViewById(R.id.et_phone);
        guanxi = findViewById(R.id.et_guanxi);

        send = findViewById(R.id.add_send);
        back =findViewById(R.id.iv_title_back);

        //添加
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length() !=0 && phone.getText().toString().length() !=0 &&
                        guanxi.getText().toString().length() !=0){
                    Intent intent = new Intent();
                    intent.putExtra("name",name.getText().toString());
                    intent.putExtra("phone",phone.getText().toString());
                    intent.putExtra("guanxi",guanxi.getText().toString());
                    setResult(DONE,intent);
                    finish();
                }else{
                    Toast.makeText(MemberAddActivity.this,"信息未填写完成",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //返回
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(BACK,intent);
                finish();
            }
        });

    }
}