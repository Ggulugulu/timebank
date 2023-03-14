package com.example.timebank.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.timebank.R;
import com.example.timebank.activity.MeManageActivity;
import com.example.timebank.utils.UIUtils;

/**
 * Created by wwbb04 on 2021/7/14.
 */

public class MeFragment extends BaseFragment {
    ImageView mIvTitleBack;
    ImageView mIvTitleSystem;
    TextView mTvTitle;
    TextView mTvUser;
    RelativeLayout mIvArrowHuizhang;
    RelativeLayout mIvArrowXiangmu;
    RelativeLayout mIvManage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = UIUtils.getView(R.layout.fragment_me);
        mIvTitleBack =view.findViewById(R.id.iv_title_back);
        //mIvTitleSystem = view.findViewById(R.id.iv_title_system);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvUser = view.findViewById(R.id.tv_id_style);

//        mIvArrowHuizhang = view.findViewById(R.id.rl_zyhzq);
//        mIvArrowXiangmu = view.findViewById(R.id.ll_check);
        mIvManage = view.findViewById(R.id.rl_management);



        initData();
        initTitle();

        mIvManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MeManageActivity.class);
                startActivityForResult(intent,1); //带请求码
            }
        });
//        //志愿徽章墙
//        mIvArrowHuizhang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), HuiZhangActivity.class);
//                startActivityForResult(intent,1); //带请求码去HuiZhangActivity
//            }
//        });
//
//        //审核项目
//        mIvArrowXiangmu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mTvUser.getText().toString().equals("审核人")){
//                    Intent intent = new Intent(getContext(), ShenHeActivity.class);
//                    startActivityForResult(intent,1); //带请求码
//                }else{
//                    Toast.makeText(getContext(),"对不起，您不是审核人，无法进入审核项目功能",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
        return view;
//        LoadingPage loadingPage = new LoadingPage(container.getContext()) {
//            @Override
//            public int layoutId() {
//                return getLayoutId();
//            }
//        };
//
//        return loadingPage;
    }


    @Override
    protected void initData() {

    }

    protected void initTitle() {
        mIvTitleBack.setVisibility(View.INVISIBLE);
        mTvTitle.setText("我的");
        //mIvTitleSystem.setVisibility(View.INVISIBLE);
    }

    public int getLayoutId(){
        return R.layout.fragment_me;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
