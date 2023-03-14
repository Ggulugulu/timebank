package com.example.timebank.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timebank.R;
import com.example.timebank.activity.MainActivity;
import com.example.timebank.adapter.VolShowAdapter;
import com.example.timebank.utils.UIUtils;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

/**
 * Created by wwbb04 on 2021/7/14.
 */

public class VolShowFragment extends BaseFragment {
    ImageView mIvTitleBack;
    ImageView mIvTitleSystem;
    TextView mTvTitle;
    private Context context;
    ViewPager vp;

    private String[] mTitles = {"全部志愿", "未完成", "已完成"};
    //将显示的fragment加到一个list当中，然后通过上方的标签来绑定这些内容
    private ArrayList<Fragment> mFragments = new ArrayList<>();


    //声明适配器
    private VolShowAdapter mHomeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = UIUtils.getView(R.layout.fragment_home);
        context = getContext();
        mIvTitleBack =view.findViewById(R.id.iv_title_back);
        //mIvTitleSystem = view.findViewById(R.id.iv_title_system);
        mTvTitle = view.findViewById(R.id.tv_title);

        loadSlidingView(view);
        initData();
        initTitle();

        mIvTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.setselect(0);
            }
        });
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



    /**
     * @param view
     * 加载滑动副标题栏
     */
    private void loadSlidingView(View view) {
        //把编写好的fragment加到fragment的list中
        mFragments.add(new AllVolFragment());
        mFragments.add(new IngVolFragment());
        mFragments.add(new MyVolFragment());

        //找到存放fragment的viewpager标签
        vp=view.findViewById(R.id.fixedViewPager);
        VolShowAdapter md=new VolShowAdapter(getActivity().getSupportFragmentManager(), mTitles,mFragments);
        vp.setAdapter(md);
        SlidingTabLayout slidingTabLayout=view.findViewById(R.id.slidingTabLayout);
        slidingTabLayout.setTabSpaceEqual(true);
        slidingTabLayout.setViewPager(vp);
        vp.setOffscreenPageLimit(2);

    }




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    protected void initData() {
//        String url = "http://www.csdn.net/";
//        OkHttpUtils
//                .get()
//                .url(url)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//
//                    }
//                });
    }


    /**
     * 设置标题栏
     */
    protected void initTitle() {
        mTvTitle.setText("志愿活动");
//        mIvTitleSystem.setVisibility(View.INVISIBLE);
    }

    public int getLayoutId(){
        return R.layout.fragment_home;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
