package com.example.timebank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.timebank.R;
import com.example.timebank.adapter.VolShowAdapter;
import com.example.timebank.fragment.ShenHeDoneFragment;
import com.example.timebank.fragment.ShenHeListFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

public class ShenHeActivity extends FragmentActivity {

    private String[] mTitles = {"审核列表", "已审核"};
    //将显示的fragment加到一个list当中，然后通过上方的标签来绑定这些内容
    private ArrayList<Fragment> mFragments2 = new ArrayList<>();
    ImageView mTvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shenhe);
        mTvBack = findViewById(R.id.iv_title_back);

        loadSlidingView(this);

        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShenHeActivity.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
    }

    private void loadSlidingView(Activity context) {
        FragmentManager fragmentManager =getSupportFragmentManager();
        //把编写好的fragment加到fragment的list中
        mFragments2.add(new ShenHeListFragment());
        mFragments2.add(new ShenHeDoneFragment());

        //找到存放fragment的viewpager标签
        ViewPager vp=context.findViewById(R.id.fixedViewPager_shenhe);
        VolShowAdapter md = new VolShowAdapter(fragmentManager, mTitles,mFragments2);
        vp.setAdapter(md);
        SlidingTabLayout slidingTabLayout=context.findViewById(R.id.slidingTabLayout_shenhe);
        slidingTabLayout.setTabSpaceEqual(true);
        slidingTabLayout.setViewPager(vp);
    }






}