package com.example.timebank.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timebank.R;
import com.example.timebank.activity.GoodsXqActivity;
import com.example.timebank.adapter.ShoppingListAdapter;
import com.example.timebank.bean.ShoppingListDataBean;
import com.example.timebank.common.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListFragment extends Fragment {
    

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //这里是填充viewpager的方法
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //把fragemen1这个xml布局文件填充到viewpage中
        View view = inflater.inflate(R.layout.fragment_shopping_list, null);
        RecyclerView recyclerView = view.findViewById(R.id.rv_shopping_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //模拟的数据
        List<ShoppingListDataBean> datas = new ArrayList<ShoppingListDataBean>();
        datas = initData();

        ShoppingListAdapter shoppingListAdapter = new ShoppingListAdapter(getActivity(),datas);
        recyclerView.setAdapter(shoppingListAdapter);//完成数据渲染
        shoppingListAdapter.setOnItemClickListener(MyItemClickListener);

        return view;
    }

    private List<ShoppingListDataBean> initData() {
        List<ShoppingListDataBean> datas = new ArrayList<>();
        for(int i = 0;i< ShoppingList.shooping.length;++i){
            ShoppingListDataBean s = new  ShoppingListDataBean();
            s.setUrl(ShoppingList.shooping[i]);
            s.setName(ShoppingList.name[i]);
            s.setPrice(""+i);
            s.setLast(""+i);
            datas.add(s);
        }

        return datas;

    }

    private ShoppingListAdapter.OnItemClickListener MyItemClickListener = new ShoppingListAdapter.OnItemClickListener(){
        @Override
        public void onItemClick(View v, int position) {
            if (v.getId() == R.id.ll_shooping_list) {
                final Intent intent = new Intent(getActivity(), GoodsXqActivity.class);
                intent.putExtra("name",ShoppingList.name[position]);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        }
    };




}
