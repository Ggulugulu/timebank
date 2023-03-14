package com.example.timebank.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.example.timebank.R;


public class WelcomeActivity extends Activity {
    RelativeLayout mRlWelcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏显示
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉窗口标题
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏顶部状态栏

        setContentView(R.layout.activity_welcome);
        mRlWelcome = (RelativeLayout) findViewById(R.id.rl_welcome);
        //提供启动动画
        setAnimation();
        }

    private Handler handler = new Handler();
    private void setAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);//透明度从完全透明到完全不透明
        alphaAnimation.setDuration(3000);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());//设置动画变化率

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);
        //启动动画
        mRlWelcome.startAnimation(alphaAnimation);
    }
}
