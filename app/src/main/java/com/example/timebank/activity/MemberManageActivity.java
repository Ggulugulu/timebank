package com.example.timebank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.timebank.R;
import com.example.timebank.adapter.AddressAdapter;
import com.example.timebank.bean.AddressBean;

import java.util.ArrayList;
import java.util.List;

public class MemberManageActivity extends Activity {
    List<AddressBean> datas;
    AddressAdapter addressAdapter;
    Button add;
    ImageView back;
    static int ADD = 100;
    static int DONE=1;
    static int BACK=11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_manage);
        add = findViewById(R.id.bt_address_add);
        back =findViewById(R.id.iv_title_back);
        RecyclerView recyclerView = findViewById(R.id.rel_address);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MemberManageActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //添加成员
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberManageActivity.this, MemberAddActivity.class);
                startActivityForResult(intent,ADD);
            }
        });

        //返回
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberManageActivity.this,MeManageActivity.class);
                startActivity(intent);
            }
        });

        //模拟的数据
        datas = new ArrayList<AddressBean>();
        for(int i =0;i<2;i++){
            AddressBean ab=new AddressBean();
            ab.setName("吴思婷");
            ab.setPhone("18888888888");
            ab.setGuanxi("女儿");
            datas.add(ab);
        }
        addressAdapter = new AddressAdapter(MemberManageActivity.this,datas);
        recyclerView.setAdapter (addressAdapter);//完成数据渲染
        addressAdapter.setOnItemClickListener(MyItemClickListener);
    }

    private final AddressAdapter.OnItemClickListener MyItemClickListener = new AddressAdapter.OnItemClickListener(){
        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()){
//                case R.id.address_moren:
//                        v.setBackgroundResource(R.drawable.select);
//                    break;
                case R.id.address_delete:
                    datas.remove(datas.get(position));
                    addressAdapter.notifyDataSetChanged();
                    break;
            }

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //此处可以根据两个Code进行判断，本页面和结果页面跳过来的值
        if (requestCode == ADD && resultCode == DONE) {
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            String guanxi = data.getStringExtra("guanxi");
            AddressBean ab=new AddressBean();
            ab.setName(name);
            ab.setPhone(phone);
            ab.setGuanxi(guanxi);
            datas.add(ab);
            addressAdapter.notifyDataSetChanged();
        }
    }

}