package com.example.timebank.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.timebank.R;
import com.example.timebank.adapter.ShenHeAdapter;
import com.example.timebank.bean.ShenHeBean;

import java.util.ArrayList;
import java.util.List;

public class ShenHeListFragment extends Fragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //这里是填充viewpager的方法
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //把fragemen1这个xml布局文件填充到viewpage中
        View view = inflater.inflate(R.layout.fragment_shenhe_list, null);
        RecyclerView recyclerView = view.findViewById(R.id.shenhe_list_recyclerview);
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
        return view;
    }
    private ShenHeAdapter.OnItemClickListener MyItemClickListener = new ShenHeAdapter.OnItemClickListener(){
        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()) {
                case R.id.bt_can_pass:
                    Toast.makeText(getContext(),"审核结果：通过",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.bt_no_pass:
                    Toast.makeText(getContext(),"审核结果：否决",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };



}
