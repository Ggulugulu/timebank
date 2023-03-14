package com.example.timebank.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.example.timebank.R;
import com.example.timebank.activity.CheckSqActivity;
import com.example.timebank.activity.ComplainlActivity;
import com.example.timebank.activity.FromMeActivity;
import com.example.timebank.activity.GetVolActivity;
import com.example.timebank.activity.GongShiActivity;
import com.example.timebank.activity.MainActivity;
import com.example.timebank.activity.VoteActivity;
import com.example.timebank.activity.XiangQingActivity;
import com.example.timebank.bean.BannerBean;
import com.recker.flybanner.FlyBanner;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private static final int REQUEST_CODE = 001;
    LinearLayout mLlGetVolList;
    LinearLayout mLlShenqingVol;
    LinearLayout mLlGetShenheList;
    LinearLayout mLlShenqingShenhe;
    LinearLayout mLiFromMeVol;

    LinearLayout mLlComplain;
    LinearLayout mLlGongShi;
    SliderLayout mSlider;
    ImageView saoma;
    PagerIndicator indicator;
    List<BannerBean> listBanner;

    FlyBanner mBanner;
    //动态权限
    String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    List<String> mPermissionList = new ArrayList<>();
    private final int mRequestCode = 100;//权限请求码

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baoguo, container, false);
        mLlGetVolList = view.findViewById(R.id.ll_get_vol_list);
        mLlShenqingVol = view.findViewById(R.id.ll_shenqing_vol);
        mLlGetShenheList = view.findViewById(R.id.ll_get_shenhe_list);
        mLlShenqingShenhe = view.findViewById(R.id.ll_shenqing_shenhe);
        mLiFromMeVol = view.findViewById(R.id.ll_check_vol);

        mLlComplain = view.findViewById(R.id.ll_go_complain);
        mLlGongShi = view.findViewById(R.id.ll_gongshi_shenhe);

        mBanner = view.findViewById(R.id.banner);
        saoma = view.findViewById(R.id.saoma);

        initImageUrl();
        if (Build.VERSION.SDK_INT >= 23) {  //6.0才用动态权限
            //申请相关权限
            initPermission();
        }

        mLlGetVolList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerClick(1);
            }
        });
        mLlShenqingVol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerClick(2);
            }
        });
        mLlGetShenheList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerClick(3);
            }
        });
        mLlShenqingShenhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerClick(4);
            }
        });
        mLlComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerClick(5);
            }
        });
        mLlGongShi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerClick(6);
            }
        });
        mLiFromMeVol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerClick(7);
            }
        });
        saoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用系统相机功能，就是跳转到摄像头的界面
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                //用此方法跳转的原因是：为了回调下面onActivityResult的方法
                startActivityForResult(intent, REQUEST_CODE);

            }
        });

        return view;
    }

    private void initPermission() {
        /**
         * 初始化权限
         */
            mPermissionList.clear();        //清空没有通过的权限
            //逐个判断你要的权限是否已经通过
            for (int i = 0; i < permissions.length; i++) {
                if (ContextCompat.checkSelfPermission(getActivity(), permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    mPermissionList.add(permissions[i]);//添加还未授予的权限
                }
            }
            //申请权限
            if (mPermissionList.size() > 0) {//有权限没有通过，需要申请
                ActivityCompat.requestPermissions(getActivity(), permissions, mRequestCode);
            } else {
                //说明权限都已经通过，可以做你想做的事情去
            }
    }

    // 轮播图  transition
    public void initImageUrl() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.example1);
        list.add(R.drawable.example2);
        list.add(R.drawable.example3);
        mBanner.setPointsIsVisible(true);
        mBanner.setImages(list);
    }


    private void handlerClick(int i) {
        switch (i){
            case 1:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setselect(3);
                break;
            case 2:
                Intent intent = new Intent(getActivity(), GetVolActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(getActivity(), VoteActivity.class);
                startActivity(intent);
                break;
            case 4:
                showAlert();
                break;
            case 5:
                intent = new Intent(getActivity(), ComplainlActivity.class);
                startActivity(intent);
                break;
            case 6:
                intent = new Intent(getActivity(), GongShiActivity.class);
                startActivity(intent);
                break;
            case 7:
                intent = new Intent(getActivity(), FromMeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    /**
     *setIcon 设置对话框图标
     *setTitle 设置对话框标题
     *setMessage 设置对话框消息提示
     *setXXX方法返回Dialog对象，因此可以链式设置属性
     */
    private void showAlert() {
            final AlertDialog.Builder normalDialog =
                    new AlertDialog.Builder(getContext());
            //normalDialog.setIcon(R.drawable.icon_dialog);
            normalDialog.setTitle("申请审核人提交");
            normalDialog.setMessage("您是否确认提交50时间币来申请成为审核人候选人?");
            normalDialog.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           Intent intent = new Intent(getActivity(), CheckSqActivity.class);
                           startActivity(intent);
                        }
                    });
            normalDialog.setNegativeButton("关闭",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //...To-do
                        }
                    });
            // 显示
            normalDialog.show();
    }

    //扫描回传值
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }
            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                //这是拿到解析扫描到的信息，并转成字符串
                String result = bundle.getString(CodeUtils.RESULT_STRING);

                //Toast.makeText(getContext(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                //解析扫到的二维码后就跳转页面
                Intent intent = new Intent(getContext(), XiangQingActivity.class);
                //把扫到并解析到的信息(既:字符串)带到详情页面
                intent.putExtra("path", result);
                //Toast.makeText(getContext(),result,Toast.LENGTH_SHORT).show();
                startActivity(intent);
            } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                //否则土司解析二维码失败
                //Toast.makeText(getContext(), "解析二维码失败:", Toast.LENGTH_LONG).show();
            }
        }

    }

}
