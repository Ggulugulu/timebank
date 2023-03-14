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
import android.widget.TextView;
import android.widget.Toast;

import com.example.timebank.Net.AppNetConfig;
import com.example.timebank.R;
import com.example.timebank.adapter.PingJiaAdapter;
import com.example.timebank.bean.PingJiaBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class XiangQingActivity extends Activity {
    List<PingJiaBean> datas= new ArrayList<PingJiaBean>();
    List<PingJiaBean> itemdatas= new ArrayList<PingJiaBean>();
    private RecyclerView recyclerView;
    private PingJiaAdapter pingjiaAdapter;
    private ImageView mIvback;
    private TextView member;
    private TextView phone;
    private TextView contactPersonName;
    private TextView worktime;
    private TextView address;
    private TextView needpeople;
    private TextView nowpeople;
    private TextView createtime;
    private TextView endtime;
    private TextView startime;
    private TextView time;
    private TextView value;
    private TextView status;
    private TextView category;
    private TextView ownerName;
    private TextView name;
    private TextView description;
    private Button attend;
    String pp;
    String co;
    String wo;
    String ad;
    int ne;
    int no;
    String cr;
    String en;
    String st;
    double ti;
    double va;
    String sta;
    String ca;
    String ow;
    String na;
    String de;
    int i;
    int backflag = 0;
    static int VOL= 100;
    static int ME= 110;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        mIvback = findViewById(R.id.iv_title_back);
        member = findViewById(R.id.member_shenqing);

        name = findViewById(R.id.vol_title);
        ownerName = findViewById(R.id.author);
        category = findViewById(R.id.tv_item_vol_category);
        status = findViewById(R.id.tv_item_vol_state);
        value = findViewById(R.id.tv_item_vol_pay);
        time = findViewById(R.id.tv_item_vol_time);
        startime =findViewById(R.id.tv_item_vol_start_time);
        endtime = findViewById(R.id.tv_item_vol_end_time);
        createtime =findViewById(R.id.tv_item_fa_time);
        nowpeople =findViewById(R.id.nowpeople);
        needpeople =findViewById(R.id.needpeople);
        address =findViewById(R.id.address);
        worktime =findViewById(R.id.worktime);
        contactPersonName =findViewById(R.id.contactPersonName);
        phone = findViewById(R.id.phone);
        description = findViewById(R.id.description);

        recyclerView = findViewById(R.id.pingjia_recyclerview);
        attend = findViewById(R.id.bt_attend);

        handleIntent();
        initData();

        mIvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(backflag  == ME){
                    intent = new Intent(XiangQingActivity.this, FromMeActivity.class);
                }else{
                    intent = new Intent(XiangQingActivity.this, MainActivity.class);
                    intent.putExtra("id",3);
                }
                startActivity(intent);

            }
        });
        //成员管理
        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XiangQingActivity.this, MemberSqActivity.class);
                startActivity(intent);
            }
        });
        //参加活动
        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XiangQingActivity.this, "提交成功，请等待申请通过",Toast.LENGTH_SHORT).show();
                attend.setClickable(false);
                attend.setBackground(getResources().getDrawable(R.drawable.shape_gray_button));
            }
        });

    }

    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        pingjiaAdapter = new PingJiaAdapter(this, itemdatas);
    }

    private void handleIntent() {
        String jxString = getIntent().getStringExtra("path");
        i = getIntent().getIntExtra("Vol",1);
        if(getIntent() != null){
            if(jxString !=null){
                GetNet("1");
            }
                if (i == 11) {
                    member.setVisibility(View.INVISIBLE);
                    backflag = VOL;
                }else{
                member.setVisibility(View.VISIBLE);
                backflag = ME;
            }
                String id = getIntent().getExtras().getString("id");
                GetNet(id);
                GteNetPinglun();
        }

    }



    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AppNetConfig.COMPLETED:
                    phone.setText(pp);
                    contactPersonName.setText(co);
                    worktime.setText(wo);
                    address.setText(ad);
                    needpeople.setText(String.valueOf(ne));
                    nowpeople.setText(String.valueOf(no));
                    createtime.setText(cr);
                    endtime.setText(en);
                    startime.setText(sta);
                    time.setText(String.valueOf(ti));
                    value.setText(String.valueOf(va));
                    status.setText(st);
                    category.setText(ca);
                    ownerName.setText(ow);
                    name.setText(na);
                    description.setText(de);

                    break;
                case AppNetConfig.COMPLETED2:
                    itemdatas.addAll(datas);
                    pingjiaAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(pingjiaAdapter);//完成数据渲染
                    break;
            }
        }
    };
    private void GteNetPinglun() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                    Request request = new Request.Builder()
                        .url(AppNetConfig.BASE_URL+"/project/userlist")
                        .method("GET", null)
                        .build();

                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    JSONObject jsonObject1 = new JSONObject(responseData);
                    JSONArray ja =jsonObject1.getJSONArray("detail");
                    for(int i=0;i<ja.length();i++) {
                        JSONObject jsonObject = ja.getJSONObject(i);
                        PingJiaBean p = new PingJiaBean();
                        try(InputStream is = new URL(jsonObject.getString("userAvatar")).openStream()){
                            Bitmap bitmap = BitmapFactory.decodeStream(is);
                            p.setTouxiang(bitmap);
                        }

                        p.setUsername(jsonObject.getString("userName"));
                        p.setNeirong(jsonObject.getString("userComment"));
                        p.setStarbar(jsonObject.getInt("star"));
                        p.setState(jsonObject.getString("userState"));
                        p.setAccepttime(jsonObject.getString("acceptTime"));
                        p.setOvertime(jsonObject.getString("overTime"));

                        datas.add(p);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(AppNetConfig.COMPLETED2);
            }
        }).start();
    }
    private void GetNet(String id) {
        String url = AppNetConfig.BASE_URL+"/project/projectbyid?id=";
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                Request request = new Request.Builder()
                        .url(url+id)
                        .method("GET", null)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    JSONObject jsonObject1 = new JSONObject(responseData);
                    JSONObject jsonObject = jsonObject1.getJSONObject("detail");

                    pp = jsonObject.getString("phone");
                    co = jsonObject.getString("contactPersonName");
                    wo = jsonObject.getString("workTime");
                    ad = jsonObject.getString("address");

                    ne = jsonObject.getInt("needPeople");
                    no = jsonObject.getInt("nowPeople");
                    cr =jsonObject.getString("createTime");
                    en =jsonObject.getString("endTime");
                    sta =jsonObject.getString("startTime");
                    ti = jsonObject.getDouble("time");
                    va =jsonObject.getDouble("value");
                    st =jsonObject.getString("state");
                    ca =jsonObject.getString("category");
                    ow =jsonObject.getString("ownerName");
                    na =jsonObject.getString("name");
                    de =jsonObject.getString("description");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(AppNetConfig.COMPLETED);
            }
        }).start();
    }

}