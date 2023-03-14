package com.example.timebank.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.example.timebank.common.MyApp;

/**
 * 处理UI相关问题的工具类
 */

public class UIUtils {

    public static Context getContext(){
        return MyApp.context;
    }
    public static Handler getHandler(){
        return  MyApp.handler;
    }
    //返回指定的颜色
    public static int getColor(int colorid){
        return getContext().getResources().getColor(colorid);
    }
    //返回指定的视图对象
    public static View getView(int viewid){
        View view = View.inflate(getContext(),viewid,null);
        return view;
    }

    public static String[] getStringArr(int strArrid){
        return getContext().getResources().getStringArray(strArrid);
    }

    //dp转化成px
    public static int dp2px(int dp){
        //获取手机密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int)(dp*density + 0.5);
    }

    //px转化成dp
    public static int px2dp(int px){
        //获取手机密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int)(px / density + 0.5);
    }

    //保证操作在主线程中进行
    public static void runOnUiThread(Runnable runnable) {
        if(isInMainThread()){
            runnable.run();
        }else{
            UIUtils.getHandler().post(runnable);
        }
    }

    //判断当前线程是否为主线程
    private static boolean isInMainThread() {
        int currentThreadId = android.os.Process.myTid();
        return MyApp.mainThreadId == currentThreadId;
    }
}
