package com.example.timebank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timebank.R;

public class BeRealUserActivity extends Activity {
    ImageView mImTitleBack;
    Button bereal;
    TextView realname;
    TextView realid;
    String name;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_be_real_user);
        mImTitleBack = findViewById(R.id.iv_title_back);

        bereal = findViewById(R.id.bt_be_real);
        realname = findViewById(R.id.et_real_name);
        realid = findViewById(R.id.et_real_id);

        Bundle bundle = this.getIntent().getExtras(); //读取intent的数据给bundle对象
        handleBundle(bundle);



        mImTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeRealUserActivity.this, MeManageActivity.class);
                intent.putExtra("name",realname.getText().toString());
                intent.putExtra("id",realid.getText().toString());
                intent.putExtra("done",true);

                startActivity(intent);
            }
        });

        bereal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!realname.getText().toString().equals("") && !realid.getText().toString().equals("") ){
                    Toast.makeText(BeRealUserActivity.this,"提交成功！",Toast.LENGTH_SHORT).show();
                    bereal.setTextColor(0xff979997);
                    bereal.setBackgroundColor(0xffe7e9e7);
                    bereal.setClickable(false);
                    bereal.setText("已认证");

                    Intent intent = new Intent(BeRealUserActivity.this,MeManageActivity.class);
                    intent.putExtra("name",realname.getText().toString());
                    intent.putExtra("id",realid.getText().toString());
                    intent.putExtra("done",true);

                    startActivity(intent);
                }else{
                    Toast.makeText(BeRealUserActivity.this,"消息未填写完成",Toast.LENGTH_SHORT).show();
                }
            }
        });

        beRealCheck(name,id);
    }

    private void handleBundle(Bundle bundle) {
        if(bundle != null){
            name = bundle.getString("name");
            id = bundle.getString("id");

        }
    }

    private void beRealCheck(String name, String id) {
        if(name !=null && id!=null){
            realname.setText(name);
            realid.setText(id);
            realid.setEnabled(false);
            realname.setEnabled(false);

            bereal.setTextColor(0xff979997);
            bereal.setBackgroundColor(0xffe7e9e7);
            bereal.setClickable(false);
            bereal.setText("已认证");
        }
    }
}