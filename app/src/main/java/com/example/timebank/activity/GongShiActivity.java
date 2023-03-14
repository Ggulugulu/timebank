package com.example.timebank.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.timebank.Net.AppNetConfig;
import com.example.timebank.R;
import com.example.timebank.adapter.GongShiadapter;
import com.example.timebank.bean.GongShiBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GongShiActivity extends Activity {
    ImageView mImTitleBack;
    RecyclerView recyclerView;
    List<GongShiBean> datas = new ArrayList<GongShiBean>();
    List<GongShiBean> itemdatas = new ArrayList<GongShiBean>();
    GongShiadapter gongShiadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gong_shi);
        mImTitleBack = findViewById(R.id.iv_title_back);
        recyclerView = findViewById(R.id.gongshi_checkman_recyclerview);

        initData();
        GetvolNet();

        mImTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GongShiActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AppNetConfig.COMPLETED:
                    itemdatas.addAll(datas);
                    gongShiadapter.notifyDataSetChanged();
                    recyclerView.setAdapter(gongShiadapter);//完成数据渲染
                    break;

            }
        }
    };

    private void GetvolNet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    Request request = new Request.Builder()
                            .url(AppNetConfig.BASE_URL+"/reviewer/reviewerlist")
                            .method("GET", null)
                            .build();

                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    JSONObject jsonObject1 = new JSONObject(responseData);
                    JSONArray ja =jsonObject1.getJSONArray("detail");

                    for(int i=0;i<ja.length();i++)
                    {
                        JSONObject jsonObject=ja.getJSONObject(i);
                        GongShiBean gongShiBean=new GongShiBean();
                        gongShiBean.setUser(jsonObject.getString("name"));
                        gongShiBean.setBaozheng(jsonObject.getString("declaration"));
                        gongShiBean.setRenwu1(jsonObject.getString("reportTasks"));
                        gongShiBean.setRenwu2(jsonObject.getString("reviewTasks"));
                        gongShiBean.setRenwu3(jsonObject.getString("taskNumber"));

                        try(InputStream is = new URL(jsonObject.getString("avatar")).openStream()){
                            Bitmap bitmap = BitmapFactory.decodeStream(is);
                            gongShiBean.setTouxaing(bitmap);
                        }

                        gongShiBean.setStatus("获选");
                        datas.add(gongShiBean);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(AppNetConfig.COMPLETED);
            }
        }).start();

    }

    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GongShiActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        gongShiadapter = new GongShiadapter(GongShiActivity.this,datas);
    }
}