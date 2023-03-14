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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timebank.Net.AppNetConfig;
import com.example.timebank.R;
import com.example.timebank.adapter.CheckManAdapter;
import com.example.timebank.bean.CheckManBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VoteActivity extends Activity {
    ImageView mImTitleBack;
    LayoutInflater inflater = null;
    EditText search;
    public static boolean flag = true;
    RecyclerView recyclerView;
    CheckManAdapter checkManAdapter;
    List<CheckManBean> datas = new ArrayList<CheckManBean>();
    List<CheckManBean> itemdatas = new ArrayList<CheckManBean>();
    View v;
    private TextView name;
    private TextView level;
    private TextView workTime;
    private TextView reviewerDays;
    private TextView xuanyan;
    private TextView declaration;
    private String na;
    private String le;
    private String wo;
    private String re;
    private String xu;
    private String de;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_vote);
        mImTitleBack = findViewById(R.id.iv_title_back);
        search = findViewById(R.id.et_search);
        recyclerView = findViewById(R.id.checkman_recyclerview);
        GetNet();
        initData();


        mImTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataSearch();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VoteActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        checkManAdapter = new CheckManAdapter(VoteActivity.this,itemdatas);
        recyclerView.setAdapter (checkManAdapter);//完成数据渲染
        checkManAdapter.setOnItemClickListener(MyItemClickListener);

        v = getLayoutInflater().inflate(R.layout.popup_checkman,null);
        name = v.findViewById(R.id.name);
        level = v.findViewById(R.id.level);
        workTime = v.findViewById(R.id.workTime);
        reviewerDays = v.findViewById(R.id.reviewerDays);
        xuanyan = v.findViewById(R.id.xuanyan);
        declaration = v.findViewById(R.id.declaration);
    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AppNetConfig.COMPLETED:
                    itemdatas.addAll(datas);
                    checkManAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(checkManAdapter);//完成数据渲染
                    break;

                case AppNetConfig.COMPLETED2:
                    name.setText(na);
                    level.setText(le);
                    workTime.setText(wo);
                    reviewerDays.setText(re);
                    xuanyan.setText(xu);
                    declaration.setText(de);
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
                        .url(AppNetConfig.BASE_URL+"/reviewer/votelist")
                        .method("GET", null)
                        .build();

                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    JSONObject jsonObject1 = new JSONObject(responseData);
                    JSONArray ja =jsonObject1.getJSONArray("detail");
                    for(int i=0;i<ja.length();i++) {
                        JSONObject jsonObject = ja.getJSONObject(i);
                        CheckManBean cb = new CheckManBean();
                        cb.setRongyu(jsonObject.getString("xuanyan"));
                        cb.setUser(jsonObject.getString("name"));
                        try(InputStream is = new URL(jsonObject.getString("avatar")).openStream()){
                            Bitmap bitmap = BitmapFactory.decodeStream(is);
                            cb.setTouxiang(bitmap);
                        }

                        datas.add(cb);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(AppNetConfig.COMPLETED);
            }
        }).start();

    }

    private void dataSearch() {
        String str = search.getText().toString().trim();
    }

    private final CheckManAdapter.OnItemClickListener MyItemClickListener = new CheckManAdapter.OnItemClickListener(){
        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()){
                case R.id.bt_vote:
                    final Button vv = (Button) v;
                    if(vv.getText().equals("投票") && flag) {
                        vv.setBackgroundResource(R.drawable.shape_gray_button);
                        vv.setText("已投票");
                        Toast.makeText(VoteActivity.this, "投票成功！", Toast.LENGTH_SHORT).show();
                        flag = false;
                    }else if(vv.getText().equals("投票") && !flag){
                        Toast.makeText(VoteActivity.this, "您已经使用过一票投票权，若想投票请先撤票！", Toast.LENGTH_SHORT).show();
                    }else{
                        vv.setBackgroundResource(R.drawable.shape_green_button);
                        vv.setText("投票");
                        Toast.makeText(VoteActivity.this, "投票已撤销！", Toast.LENGTH_SHORT).show();
                        flag = true;
                    }
                    break;

                case R.id.ll_checkman:
                    GetNet2(position);
                    showSortPopup(v);
            }

        }
    };

    private void GetNet2(int position) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    Request request = new Request.Builder()
                            .url(AppNetConfig.BASE_URL+"/reviewer/votelist")
                            .method("GET", null)
                            .build();
                    Response response = null;
                    response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    JSONObject jsonObject1 = new JSONObject(responseData);
                    JSONArray ja = jsonObject1.getJSONArray("detail");

                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jsonObject = ja.getJSONObject(i);
                        if(i == position){
                            na = jsonObject.getString("name");
                            le = jsonObject.getString("level");
                            wo = jsonObject.getString("workTime");
                            re = jsonObject.getString("reviewerDays");
                            xu = jsonObject.getString("xuanyan");
                            de = jsonObject.getString("declaration");

                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(AppNetConfig.COMPLETED2);
            }
        }).start();
    }

    private void showSortPopup(View view)
    {
          //加载的布局
        PopupWindow popupWindow =new PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,true);
        //设置点击弹窗外面消失
        popupWindow.setOutsideTouchable(true);

        //设置防止软键盘遮挡popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);
        //显示弹窗
        popupWindow.showAsDropDown(view);

    }


}
