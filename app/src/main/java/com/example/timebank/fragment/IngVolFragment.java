package com.example.timebank.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.timebank.Net.AppNetConfig;
import com.example.timebank.R;
import com.example.timebank.activity.PingjiaActivity;
import com.example.timebank.activity.XiangQingActivity;
import com.example.timebank.adapter.VolAdapter;
import com.example.timebank.bean.VolBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IngVolFragment extends Fragment {
    private static final int PINGJIA = 1;
    List<VolBean> datas = new ArrayList<VolBean>();
    List<VolBean> itemdatas = new ArrayList<VolBean>();
    VolAdapter volAdapter;
    static int DONE = 1;
    static int BACK = 2;
    RecyclerView recyclerView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //这里是填充viewpager的方法
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //把这个xml布局文件填充到viewpage中
        View view = inflater.inflate(R.layout.fragment_ing_vol, null);
        recyclerView = view.findViewById(R.id.ing_recyclerview);

        GetvolNet();
        initData();


        return view;
    }

    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        volAdapter = new VolAdapter(getActivity(), itemdatas);
        volAdapter.setOnItemClickListener(MyItemClickListener);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AppNetConfig.COMPLETED:
                    itemdatas.addAll(datas);
                    volAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(volAdapter);//完成数据渲染

                    break;

            }
        }
    };

    private void GetvolNet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //
                    itemdatas.clear();
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    Request request = new Request.Builder()
                            .url(AppNetConfig.BASE_URL+"/project/myproject")
                            .method("GET", null)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    JSONObject jsonObject1 = new JSONObject(responseData);
                    JSONArray ja =jsonObject1.getJSONArray("detail");

                    for(int i=0;i<ja.length();i++)
                    {
                        JSONObject jsonObject=ja.getJSONObject(i);
                        String state = jsonObject.getString("state");
                        if(!state.equals("已完成的")) {
                            VolBean volBean = new VolBean();

                            volBean.setId(jsonObject.getString("id"));
                            volBean.setName(jsonObject.getString("name"));
                            volBean.setOwnerName(jsonObject.getString("ownerName"));
                            volBean.setValue(jsonObject.getString("value"));
                            volBean.setTime(jsonObject.getString("time"));
                            volBean.setCategory(jsonObject.getString("category"));
                            volBean.setState(jsonObject.getString("state"));

                            volBean.setCreateTime(jsonObject.getString("createTime"));
                            volBean.setStartTime(jsonObject.getString("startTime"));
                            volBean.setEndTime(jsonObject.getString("endTime"));

                            volBean.setVisibility(View.VISIBLE);
                            volBean.setButtonText("待评价");

                            if(volBean.getState().equals("待评价的")){
                                volBean.setTextRecource(0xffb1525e);
                            }else if(volBean.getState().equals("正在进行中")){
                                volBean.setTextRecource(0xff4DBE66);
                            }else{
                                volBean.setTextRecource(getResources().getColor(R.color.yellow_b3));
                            }
                            volBean.setRecource(R.drawable.shape_red_button);

                            datas.add(volBean);
                        }
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }
                mHandler.sendEmptyMessage(AppNetConfig.COMPLETED);
            }
        }).start();
    }

    private final  VolAdapter.OnItemClickListener MyItemClickListener = new VolAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()){
                case R.id.bt_attend:
                    final Button vv = (Button) v;
                    if (vv.getText().equals("待评价")) {
                        Intent intent = new Intent(getActivity(), PingjiaActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("position", position);
                        intent.putExtra("package", bundle);
                        startActivityForResult(intent, PINGJIA);
                    }
                    break;
                case R.id.tv_item_vol_xiangqing:
                    final Intent intent=new Intent(getActivity(), XiangQingActivity.class);
                    intent.putExtra("Vol",11);
                    intent.putExtra("id",datas.get(position).getId());
                    //Bundle bundle=new Bundle();
                    //intent.putExtra(bundle);
                    startActivity(intent);
                    break;
            }

        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PINGJIA){
            if(resultCode == DONE){
                int position = data.getBundleExtra("package").getInt("position");
                datas.get(position).setRecource(R.drawable.shape_gray_button);
                datas.get(position).setClickable(false);
                datas.get(position).setButtonText("已评价");

                VolAdapter volAdapter = new VolAdapter(getActivity(), datas);
                recyclerView.setAdapter(volAdapter);//完成数据渲染
                volAdapter.setOnItemClickListener(MyItemClickListener);
            }else if(resultCode == BACK){

            }
        }

    }



}
