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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.timebank.Net.AppNetConfig;
import com.example.timebank.R;
import com.example.timebank.adapter.ShenBaoAdapter;
import com.example.timebank.bean.ShenBaoBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShenBaoActivity extends Activity {
    List<ShenBaoBean> list= new ArrayList<ShenBaoBean>();
    List<ShenBaoBean> itemlist= new ArrayList<ShenBaoBean>();
    ShenBaoAdapter mShenBaoAdapter;
    RecyclerView recyclerView;
    ImageView back;
    Button send;
    static int DONE = 1;
    static int BACK = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shen_bao);
        back = findViewById(R.id.iv_title_back);
        send = findViewById(R.id.shenbao_send);
        recyclerView = findViewById(R.id.rel_shenbao);

        GetNet();
        initData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                setResult(BACK,intent);
                finish();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShenBaoActivity.this,"申报成功",Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                setResult(DONE,intent);
                finish();
            }
        });
    }

    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShenBaoActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mShenBaoAdapter = new ShenBaoAdapter(ShenBaoActivity.this, itemlist);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AppNetConfig.COMPLETED2:
                    itemlist.addAll(list);
                    mShenBaoAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(mShenBaoAdapter);//完成数据渲染
                    break;
            }
        }
    };

    private void GetNet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    Request request = new Request.Builder()
                            .url("http://10.97.127.49:9090/project/userlist")
                            .method("GET", null)
                            .build();

                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    JSONObject jsonObject1 = new JSONObject(responseData);
                    JSONArray ja =jsonObject1.getJSONArray("detail");
                    for(int i=0;i<ja.length();i++) {
                        JSONObject jsonObject = ja.getJSONObject(i);
                        ShenBaoBean p =new ShenBaoBean();
                        try(InputStream is = new URL(jsonObject.getString("userAvatar")).openStream()){
                            Bitmap bitmap = BitmapFactory.decodeStream(is);
                            p.setTouxiang(bitmap);
                        }
                        p.setName(jsonObject.getString("userName"));
                        p.setText(jsonObject.getString("userComment"));
                        p.setStar(jsonObject.getInt("star") + "星");
                        p.setTime(jsonObject.getString("acceptTime"));
                        p.setLast(jsonObject.getString("overTime"));

                        list.add(p);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(AppNetConfig.COMPLETED2);
            }
        }).start();
    }


}