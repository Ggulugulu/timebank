package com.example.timebank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timebank.R;

public class OrderOKActivity extends Activity {
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ok);
        back = findViewById(R.id.back_shangcheng);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderOKActivity.this,MainActivity.class);
                intent.putExtra("id",2);
                startActivity(intent);
                Toast.makeText(OrderOKActivity.this,"返回商城",Toast.LENGTH_SHORT).show();
            }
        });
    }
}