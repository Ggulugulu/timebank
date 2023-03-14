package com.example.timebank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.timebank.R;
import com.example.timebank.adapter.MemberAdapter;
import com.example.timebank.bean.MemberBean;

import java.util.ArrayList;
import java.util.List;

public class MemberSqActivity extends Activity {
    MemberAdapter mMemberAdapter;
    RecyclerView recyclerView;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_sq);
        back = findViewById(R.id.iv_title_back);
        String name[] ={"叶浩", "李强", "何倩", "付秀黄"};
        String time[] ={"2021-10-22 13:38:00", "2021-10-22 15:38:00", "2021-10-21 13:00:25", "2021-10-20 12:22:00"};
        String lv[] ={"Lv2", "Lv5", "Lv2", "Lv3"};
        //模拟的数据
        List<MemberBean> list= new ArrayList<MemberBean>();
        for(int i =0;i< 4;i++){
            MemberBean memberaBean =new MemberBean();
            memberaBean.setId(name[i]);
            memberaBean.setTime(time[i]);
            memberaBean.setLevel(lv[i]);
            //PingJiaBean.setStarbar();
            list.add(memberaBean);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mMemberAdapter = new MemberAdapter(this, list);

        recyclerView = findViewById(R.id.rel_member);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mMemberAdapter);
        mMemberAdapter.setOnItemClickListener(MyItemClickListener);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberSqActivity.this,XiangQingActivity.class);
                startActivity(intent);
            }
        });
    }

    private MemberAdapter.OnItemClickListener MyItemClickListener =new MemberAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()){
                case R.id.tv_member_xq:
                    popUp(v);
                    break;
                case R.id.tv_member_yes:
                    Toast.makeText(MemberSqActivity.this,"通过该成员",Toast.LENGTH_SHORT).show();
                    mMemberAdapter.removeData(position);
                    break;
                case R.id.tv_member_no:
                    Toast.makeText(MemberSqActivity.this,"拒绝该成员",Toast.LENGTH_SHORT).show();
                    mMemberAdapter.removeData(position);
                    break;
            }
        }
    };

    /**
     * @param view 显示成员详情区域
     */
    private void popUp(View view) {
        View v=getLayoutInflater().inflate(R.layout.popup_member,null);  //加载的布局
        PopupWindow popupWindow =new PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,true);
        //设置点击弹窗外面消失
        popupWindow.setOutsideTouchable(true);
        //设置防止软键盘遮挡popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);
        //显示弹窗
        popupWindow.showAsDropDown(view);
    }


}