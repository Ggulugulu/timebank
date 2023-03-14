package com.example.timebank.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timebank.Net.AppNetConfig;
import com.example.timebank.R;
import com.example.timebank.UI.RoundCornerDialog;
import com.example.timebank.adapter.FromMeAdapter;
import com.example.timebank.bean.FromMeBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FromMeActivity extends Activity {
    RecyclerView recyclerView;
    List<FromMeBean> datas = new ArrayList<FromMeBean>();
    List<FromMeBean> itemdatas = new ArrayList<FromMeBean>();
    ImageView back;
    private static final int SHENBAO = 1;
    static int DONE = 1;
    static int BACK = 2;
    private static final int FROMME = 4;
    FromMeAdapter fAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_me);
        recyclerView = (RecyclerView) findViewById(R.id.rel_from_me);
        back = findViewById(R.id.iv_title_back);

        GetNet();
        initData();



        //模拟的数据
        datas = new ArrayList<FromMeBean>();
        for (int i = 0; i < 4; i++) {
            FromMeBean fBean = new FromMeBean();

        }

        recyclerView.setAdapter(fAdapter);//完成数据渲染


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FromMeActivity.this,MainActivity.class);
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
                    fAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(fAdapter);//完成数据渲染
                    break;

            }
        }
    };
    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        fAdapter = new FromMeAdapter(FromMeActivity.this, itemdatas);
        fAdapter.setOnItemClickListener(MyItemClickListener);
    }

    private void GetNet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                    Request request = new Request.Builder()
                        .url(AppNetConfig.BASE_URL+"/project/setup")
                        .method("GET", null)
                        .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    JSONObject jsonObject1 = new JSONObject(responseData);
                    JSONArray ja =jsonObject1.getJSONArray("detail");

                    for(int i=0;i<ja.length();i++) {
                        JSONObject jsonObject=ja.getJSONObject(i);
                        FromMeBean fBean = new FromMeBean();

                        fBean.setId(jsonObject.getString("id"));
                        fBean.setName(jsonObject.getString("name"));

                        fBean.setValue(jsonObject.getInt("value"));
                        fBean.setTime(jsonObject.getInt("time"));
                        fBean.setCategory(jsonObject.getString("category"));
                        fBean.setState(jsonObject.getString("state"));

                        fBean.setCreateTime(jsonObject.getString("createTime"));
                        fBean.setStartTime(jsonObject.getString("startTime"));
                        fBean.setEndTime(jsonObject.getString("endTime"));
                        if(fBean.getState().equals("已结束")){
                            fBean.setShenbaoText("申报");
                            fBean.setShenbaoRecource(0xFFB39B61);
                        }

//                        if(fBean.getState().equals("成功")){
                            fBean.setText("查看详情");
                            fBean.setRecource(0xff303F9F);
//                        }else if(fBean.getState().equals("失败")){
//                            fBean.setText("失败原因");
//                            fBean.setRecource(0xffee5c5c);
//                        }
                        datas.add(fBean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(AppNetConfig.COMPLETED);
            }
        }).start();

    }

    private final FromMeAdapter.OnItemClickListener MyItemClickListener = new FromMeAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()){
                case R.id.tv_item_vol_xiangqing:
                    TextView vv = (TextView)v;
                    if (vv.getText().equals("查看详情")) {
                        Intent intent = new Intent(FromMeActivity.this, XiangQingActivity.class);
                        intent.putExtra("id",datas.get(position).getId());
                        startActivity(intent);
                        //startActivityForResult(intent,FROMME);
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("position", position);
//                    intent.putExtra("package", bundle);
//                    startActivityForResult(intent, COMLPAIN);
                    }else if(vv.getText().equals("失败原因")){
                        showPop();
                        //Toast.makeText(FromMeActivity.this,"失败",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.tv_item_shenbao:
                    TextView vvv = (TextView)v;
                    if(vvv.getText().equals("已申报")){
                        Toast.makeText(FromMeActivity.this,"已完成申报,不必重复申报",Toast.LENGTH_SHORT).show();
                    }else if(vvv.getText().equals("申报")){
                        Intent intent = new Intent(FromMeActivity.this,ShenBaoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("position",position);
                        intent.putExtra("package",bundle);
                        startActivityForResult(intent,SHENBAO);
                    }

            }
        }
    };

    private void showPop() {
        View v = View.inflate(FromMeActivity.this,R.layout.popup_shibai, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(FromMeActivity.this, 0, 0, v, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

        TextView tv_logout_confirm = v.findViewById(R.id.tv_logout_confirm);

        //确定
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();

            }
        });

    }

    /**
     * @param requestCode
     * @param resultCode
     * @param data
     * 处理从申报界面传来的结果
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SHENBAO) {
            if (resultCode == DONE) {
                int position = data.getBundleExtra("package").getInt("position");
                datas.get(position).setShenbaoText("已申报");
                datas.get(position).setShenbaoRecource(0xFF7D7D7D);
                //datas.get(position).setClickable(false);

                FromMeAdapter fromMeAdapter = new FromMeAdapter(FromMeActivity.this, datas);
                recyclerView.setAdapter(fromMeAdapter);//完成数据渲染
                fromMeAdapter.setOnItemClickListener(MyItemClickListener);
            } else if (resultCode == BACK) {

            }
        }
    }


    DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };






}