package com.example.timebank.fragment;

import android.app.Activity;
import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.timebank.Net.AppNetConfig;
import com.example.timebank.R;
import com.example.timebank.activity.XiangQingActivity;
import com.example.timebank.adapter.ListDropDownAdapter;
import com.example.timebank.adapter.VolAdapter;
import com.example.timebank.bean.VolBean;
import com.yyydjk.library.DropDownMenu;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class AllVolFragment extends Fragment {

    public Activity mActivity;
    //模拟的数据
    List<VolBean> datas = new ArrayList<VolBean>();
    List<VolBean> itemdatas = new ArrayList<VolBean>();
    String allowId= null;
    String thingId= null;
    String payId= null;
    String timeId= null;
    VolAdapter volAdapter = new VolAdapter(getActivity(),itemdatas);
    DropDownMenu mDropDownMenu;
    RecyclerView recyclerView;
    private  View view;

    List<View> popupViews = new ArrayList<>();
    String tabs[]={"状态","分类","时间币","发布时间"};



    /**
     *安全获取fragment所在的activity
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //这里是填充viewpager的方法
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_all_vol, container, false);
            recyclerView = view.findViewById(R.id.all_recyclerview);
            mDropDownMenu = view.findViewById(R.id.dropDownMenu);
        }

        GetvolNet();
        initData();
        initView();

        return view;
    }

    private void initData() {
        if (recyclerView.getParent() != null){
            ViewGroup parent = (ViewGroup) recyclerView.getParent();
            parent.removeView(recyclerView);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        volAdapter= new VolAdapter(getActivity(),itemdatas);
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
                    itemdatas.clear();
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    Request request = new Request.Builder()
                            .url(AppNetConfig.BASE_URL+"/project/projectslist")
                            .method("GET", null)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    JSONObject jsonObject1 = new JSONObject(responseData);
                    JSONArray ja =jsonObject1.getJSONArray("detail");

                    for(int i=0;i<ja.length();i++)
                    {
                        JSONObject jsonObject=ja.getJSONObject(i);
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
                        volBean.setButtonText("参加活动");
                        if(volBean.getState().equals("已满员")){
                            volBean.setTextRecource(0xffb1525e);
                        }else if(volBean.getState().equals("可承接")){
                            volBean.setTextRecource(0xff4DBE66);
                        }else{
                            volBean.setTextRecource(getResources().getColor(R.color.yellow_b3));
                        }
                        volBean.setRecource(R.drawable.shape_green_button);

                        datas.add(volBean);
                    }
                    //Log.d("data", String.valueOf(itemdatas));
                } catch (Exception e) {
                    //e.printStackTrace();
                }
                mHandler.sendEmptyMessage(AppNetConfig.COMPLETED);
            }
        }).start();
    }



    /**
     * 配置属性筛选栏
     */
    private void initView() {

            final String[] allow = {"全部", "可承接","已满员", "已完成"};
            final String[] thing= {"全部", "照顾老人", "照顾小孩","帮忙做事"};
            final String[] pay = {"全部","0-5","5-10","10以上"};
            final String[] time = {"全部","一天内发布","近三天发布","三天以上"};
            //状态
            final ListView allowView = new ListView(getContext());
            allowView.setDividerHeight(0);
            final ListDropDownAdapter allowAdapter = new ListDropDownAdapter(getContext(), Arrays.asList(allow));
            allowView.setAdapter(allowAdapter);
            //分类
            final ListView thingView = new ListView(getContext());
            thingView.setDividerHeight(0);
            final ListDropDownAdapter thingAdapter = new ListDropDownAdapter(getContext(), Arrays.asList(thing));
            thingView.setAdapter(thingAdapter);
            //时间币
            final ListView payView = new ListView(getContext());
            payView.setDividerHeight(0);
            final ListDropDownAdapter payAdapter = new ListDropDownAdapter(getContext(), Arrays.asList(pay));
            payView.setAdapter(payAdapter);
            //发布时间
            final ListView timeView = new ListView(getContext());
            timeView.setDividerHeight(0);
            final ListDropDownAdapter timeAdapter = new ListDropDownAdapter(getContext(), Arrays.asList(time));
            timeView.setAdapter(timeAdapter);

            popupViews.add(allowView);
            popupViews.add(thingView);
            popupViews.add(payView);
            popupViews.add(timeView);

            allowView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    allowAdapter.setCheckItem(position);
                    mDropDownMenu.setTabText(allow[position]);
                    mDropDownMenu.closeMenu();

                    String str=allow[position];
                    refreshData(str,1);


                }
            });

            thingView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    thingAdapter.setCheckItem(position);
                    mDropDownMenu.setTabText(thing[position]);
                    mDropDownMenu.closeMenu();

                    String str=thing[position];
                    refreshData(str,2);
                }
            });

            payView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    payAdapter.setCheckItem(position);
                    mDropDownMenu.setTabText(pay[position]);
                    mDropDownMenu.closeMenu();

                    String str=pay[position];   //获取所需要筛选出来的类型
                    refreshData(str,3);

                }
            });
            timeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    timeAdapter.setCheckItem(position);
                    mDropDownMenu.setTabText(time[position]);
                    mDropDownMenu.closeMenu();

                    String str=time[position];   //获取所需要筛选出来的类型
                    refreshData(str,4);
                }
            });

        mDropDownMenu.setDropDownMenu(Arrays.asList(tabs), popupViews, recyclerView);
    }

    private void refreshData(String str, int i) {
        itemdatas.clear();
        switch (i){
            case 1:
                allowId = str;
                break;
            case 2:
                thingId = str;
                break;
            case 3:
                payId = str;
                break;
            case 4:
                timeId =str;
                break;
        }
        for (int a=0;a<datas.size();a++) {
            boolean isallow = false;
            boolean isthing =false;
            boolean ispay = false;
            boolean istime = false;
            VolBean item = datas.get(a);

            //筛选状态
            if(allowId != null){
                String allow =item.getState();
                if(allowId.equals("全部")){
                    isallow = true;
                }
                if(allowId.equals(allow)){
                    isallow = true;
                }
            }else{
                isallow = true;
            }

            //筛选分类
            if(thingId != null){
                String allow =item.getCategory();
                if(thingId.equals("全部")){
                    isthing = true;
                }
                if(thingId.equals(allow)){
                    isthing = true;
                }
            }else{
                isthing = true;
            }

            //筛选时间币
            if (payId !=null){
                float pay= Integer.parseInt(item.getValue());

                if(payId.equals("全部")){
                    ispay = true;
                }

                if(payId.equals("0-5")){
                    if(pay > 0 && pay <=5){
                        ispay = true;
                    }
                }else if(payId.equals("5-10")){
                    if(pay >=5 && pay <= 10){
                        ispay = true;
                    }
                }else {
                    if (pay > 10) {
                        ispay = true;
                    }
                }
            }else{
                ispay = true;
            }

            //筛选时间
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            String y = item.getCreateTime().substring(0, 4);
            String m =item.getCreateTime().substring(5, 7);
            String d = item.getCreateTime().substring(8, 10).trim();

            if(timeId != null) {

                if(timeId.equals("全部")){
                    istime = true;
                }

                if (timeId.equals("一天内发布")) {
                    if (Integer.parseInt(m) == month && Integer.parseInt(d) == day) {
                       istime = true;
                    }
                } else if (timeId.equals("近三天发布")) {
                    if (Integer.parseInt(m) == month && day - Integer.parseInt(d) < 3) {
                        istime = true;
                    }
                } else {
                    if (day - Integer.parseInt(d) > 3) {
                        istime = true;
                    }
                }
            }else{
                istime = true;
            }


            if(isallow && istime && ispay && isthing){
                itemdatas.add(item);
            }

        }
        volAdapter.notifyDataSetChanged();

    }


    private VolAdapter.OnItemClickListener MyItemClickListener = new VolAdapter.OnItemClickListener(){
        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()) {
                case R.id.tv_item_vol_xiangqing:
                    final Intent intent=new Intent(getActivity(),XiangQingActivity.class);
                    intent.putExtra("Vol",11);
                    intent.putExtra("id",datas.get(position).getId());
                    //Bundle bundle=new Bundle();
                    //intent.putExtra(bundle);
                    startActivity(intent);
                    break;
                case R.id.bt_attend:
                    final Button vv = (Button) v;
                    if(vv.getText().equals("参加活动")){
                        vv.setBackgroundResource(R.drawable.shape_gray_button);
                        vv.setText("已加入");
                        Toast.makeText(getContext(),"加入成功！",Toast.LENGTH_SHORT).show();
                    }else{
                        vv.setBackgroundResource(R.drawable.shape_green_button);
                        vv.setText("参加活动");
                        Toast.makeText(getContext(),"退出成功！",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (view != null) {
            ViewGroup parentView = (ViewGroup) view.getParent();
            if (parentView != null) {
                parentView.removeView(view);
            }
        }
    }

}