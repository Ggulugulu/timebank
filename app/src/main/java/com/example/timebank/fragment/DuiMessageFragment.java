package com.example.timebank.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timebank.R;

public class DuiMessageFragment extends Fragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //这里是填充viewpager的方法
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //把fragemen1这个xml布局文件填充到viewpage中
        View view = inflater.inflate(R.layout.fragment_shopping_list, null);

        return view;
    }
}
