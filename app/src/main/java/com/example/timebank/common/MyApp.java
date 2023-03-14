package com.example.timebank.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by wwbb04 on 2021/7/18.
 */

public class MyApp extends Application {
    public static Context context;
    public static Handler handler;
    public static Thread mainThread;//提供 主线程对象
    public static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this.getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();//实例化当前app的线程为主线程
        mainThreadId = Process.myTid();//获取主线程id
        ZXingLibrary.initDisplayOpinion(this);//初始化ZXING包
        //初始化okhttputils
        //initOkhttpClient();

    }

//    private void initOkhttpClient() {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
////                .addInterceptor(new LoggerInterceptor("TAG"))
//                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                //其他配置
//                .build();
//
//        OkHttpUtils.initClient(okHttpClient);
//    }
}
