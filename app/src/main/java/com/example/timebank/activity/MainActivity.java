package com.example.timebank.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timebank.R;
import com.example.timebank.fragment.HomeFragment;
import com.example.timebank.fragment.MeFragment;
import com.example.timebank.fragment.DuiHuanFragment;
import com.example.timebank.fragment.VolShowFragment;
import com.example.timebank.utils.UIUtils;

public class MainActivity extends FragmentActivity {

    FrameLayout mFlMain ;
    ImageView mIvMainHome;
    TextView mTvMainHome;
    LinearLayout mLlMainHome;
    ImageView mIvMainMe;
    TextView mTvMainMe;
    LinearLayout mLlMainMe;
    LinearLayout mActivityMain;
    ImageView mIvMainShopping;
    TextView mTvMainShopping;
    LinearLayout mLlMainShopping;
    private static FragmentTransaction ft;
    private HomeFragment mHomeFragment;
    private MeFragment meFragment;
    private DuiHuanFragment shoppingFragment;
    private VolShowFragment volshowFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFlMain = findViewById(R.id.fl_main);
        mIvMainHome =findViewById(R.id.iv_main_home);
        mTvMainHome =findViewById(R.id.tv_main_home);
        mLlMainHome =findViewById(R.id.ll_main_home);

        mIvMainMe =findViewById(R.id.iv_main_me);
        mTvMainMe =findViewById(R.id.tv_main_me);
        mLlMainMe =findViewById(R.id.ll_main_me);

        mActivityMain =findViewById(R.id.activity_main);

        mIvMainShopping =findViewById(R.id.iv_main_shopping);
        mTvMainShopping =findViewById(R.id.tv_main_shopping);
        mLlMainShopping=findViewById(R.id.ll_main_shopping);

        //默认显示首页
        setselect(0);
        //点击事件
        mLlMainHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setselect(0);
            }
        });
        mLlMainShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setselect(1);
            }
        });
        mLlMainMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setselect(2);
            }
        });


    }


    //提供页面显示
    public void setselect(int i) {
         ft = getSupportFragmentManager().beginTransaction();
        //隐藏其他页面
        hideFragments();
        //重置图标状态
        resetTab();

        switch (i) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    ft.add(R.id.fl_main, mHomeFragment);
                }
                //显示当前页面
                ft.show(mHomeFragment);

                //改变图标点击效果
                mIvMainHome.setImageResource(R.drawable.homeblue);
                mTvMainHome.setTextColor(UIUtils.getColor(R.color.bottom_iv_blue));
                break;

            case 1:
                if (shoppingFragment == null) {
                    shoppingFragment = new DuiHuanFragment();
                    ft.add(R.id.fl_main, shoppingFragment);
                }
                //显示当前页面
                ft.show(shoppingFragment);

                //改变图标点击效果
                mIvMainShopping.setImageResource(R.drawable.shoppingblue);
                mTvMainShopping.setTextColor(UIUtils.getColor(R.color.bottom_iv_blue));
                break;

            case 2:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    ft.add(R.id.fl_main, meFragment);
                }
                //显示当前页面
                ft.show(meFragment);

                //改变图标点击效果
                mIvMainMe.setImageResource(R.drawable.meblue);
                mTvMainMe.setTextColor(UIUtils.getColor(R.color.bottom_iv_blue));
                break;

            case 3:
                if (volshowFragment == null) {
                    volshowFragment = new VolShowFragment();
                    ft.add(R.id.fl_main, volshowFragment);
                }
                //显示当前页面
                ft.show(volshowFragment);
                break;

        }
        ft.commit();
    }

    private void resetTab() {
        mIvMainHome.setImageResource(R.drawable.homeblack);
        mTvMainHome.setTextColor(UIUtils.getColor(R.color.bottom_tv_gray));
        mIvMainMe.setImageResource(R.drawable.meblack);
        mTvMainMe.setTextColor(UIUtils.getColor(R.color.bottom_tv_gray));
        mIvMainShopping.setImageResource(R.drawable.shoppingblack);
        mTvMainShopping.setTextColor(UIUtils.getColor(R.color.bottom_tv_gray));
    }

    private void hideFragments() {
        if (mHomeFragment != null) {
            ft.hide(mHomeFragment);
        }

        if (meFragment != null) {
            ft.hide(meFragment);
        }
        if (shoppingFragment != null) {
            ft.hide(shoppingFragment);
        }
        if(volshowFragment != null){
            ft.hide(volshowFragment);
        }
    }


    //实现两次点击退出应用
    private boolean flag = true;
    private static final int WHAT_RESET_BACK = 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_RESET_BACK:
                    flag = true;//复原flag
                    break;
            }
        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && flag) {
            Toast.makeText(MainActivity.this, "再点击一次退出程序", Toast.LENGTH_SHORT).show();
            flag = false;
            //发送延迟消息
            mHandler.sendEmptyMessageDelayed(WHAT_RESET_BACK, 2000);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void onResume() {
        super.onResume();
        int id = getIntent().getIntExtra("id", 0);

        //返回 我的 页面
        if (id == 1) {
            setselect(2);
        }
        //返回 商城 页面
        if (id == 2) {
            setselect(1);
        }
        //返回 志愿列表 页面
        if (id == 3) {
            setselect(3);
        }

    }

    //避免内存泄漏，移除所有未被执行的消息
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}