//package com.example.timebank.UI;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//
//import com.example.timebank.R;
//import com.example.timebank.utils.UIUtils;
//
//public abstract class LoadingPage extends FrameLayout {
//    //定义加载状态
//    private static final int STATE_LOADING = 1;
//    private static final int STATE_ERROR = 2;
//    private static final int STATE_EMPTY = 3;
//    private static final int STATE_SUCCESS = 4;
//
//    private int state_current = STATE_LOADING; //初始状态为加载中
//
//    //定义四种界面
//    private View view_loading ;
//    private View view_error;
//    private View view_empty;
//    private View view_success;
//    private LayoutParams params;
//
//    public LoadingPage(@NonNull Context context) {
//        this(context,null);
//    }
//    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs,0);
//    }
//    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    private void init() {
//        params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
//        if(view_loading == null){
//            view_loading = UIUtils.getView(R.layout.page_loading);
//            addView(view_loading,params);
//        }
//
//        if(view_empty == null){
//            view_empty = UIUtils.getView(R.layout.page_empty);
//            addView(view_empty,params);
//        }
//
//        if(view_error == null){
//            view_error= UIUtils.getView(R.layout.page_error);
//            addView(view_error,params);
//        }
//        showSafePage();
//    }
//
//    //更新界面（主线程进行）
//    private void showSafePage() {
//        UIUtils.runOnUiThread(new Runnable(){
//            @Override
//            public void run() {
//                showPage();
//            }
//        });
//    }
//
//    private void showPage() {
//        //根据state_current的值来判断显示哪个page
//        view_loading.setVisibility(state_current == STATE_LOADING ? View.VISIBLE :View.INVISIBLE);
//        view_error.setVisibility(state_current == STATE_ERROR ? View.VISIBLE :View.INVISIBLE);
//        view_empty.setVisibility(state_current == STATE_EMPTY ? View.VISIBLE :View.INVISIBLE);
//        if(view_success == null){
//            view_success = UIUtils.getView(layoutId());
//         addView(view_success,params);
//        }
//        view_success.setVisibility(state_current == STATE_SUCCESS ? View.VISIBLE :View.INVISIBLE);
//    }
//    public abstract int layoutId();
//    //实现联网加载
//    public void show(){
//
//    }
//}