package com.example.timebank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.timebank.R;
import com.example.timebank.adapter.HuiZhangAdapter;
import com.example.timebank.bean.HuiZhangBean;

import java.util.ArrayList;
import java.util.List;

public class HuiZhangActivity extends Activity {
    private RecyclerView recyclerView;
    private HuiZhangAdapter huizhangAdapter;
    private ImageView mIvback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huizhang);
        mIvback = findViewById(R.id.iv_title_back);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);

        //模拟的数据
        List<HuiZhangBean> list= new ArrayList<HuiZhangBean>();
        for(int i =0;i< 8;i++){
            HuiZhangBean huizhangBean =new HuiZhangBean();
            huizhangBean.setName("支援小兵"+i);
            list.add(huizhangBean);
        }
        huizhangAdapter = new HuiZhangAdapter(this,list);

        recyclerView = findViewById(R.id.huizhang_recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(huizhangAdapter);

        mIvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HuiZhangActivity.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
    }
}