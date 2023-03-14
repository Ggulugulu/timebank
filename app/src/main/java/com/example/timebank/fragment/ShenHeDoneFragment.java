package com.example.timebank.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timebank.R;
import com.example.timebank.adapter.ShenHeAdapter;
import com.example.timebank.bean.ShenHeBean;

import java.util.ArrayList;
import java.util.List;

public class ShenHeDoneFragment extends Fragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //这里是填充viewpager的方法
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //把fragemen1这个xml布局文件填充到viewpage中
        View view = inflater.inflate(R.layout.fragment_shenhe_done, null);
        RecyclerView recyclerView = view.findViewById(R.id.shenhe_done_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        //模拟的数据
        List<ShenHeBean> datas = new ArrayList<ShenHeBean>();
        for(int i =0;i< 8;i++){
            ShenHeBean shenheBean =new ShenHeBean();
            shenheBean.setVolname(i+"抗洪救灾");
            shenheBean.setMoney(i+"时间");
            shenheBean.setUsername("好人"+i);
            datas.add(shenheBean);
        }
        ShenHeAdapter shenHeAdapter = new ShenHeAdapter(getActivity(),datas);
        recyclerView.setAdapter(shenHeAdapter);//完成数据渲染


        return  view;
    }

}
