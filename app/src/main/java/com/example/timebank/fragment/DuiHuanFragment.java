package com.example.timebank.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timebank.R;
import com.example.timebank.adapter.VolShowAdapter;
import com.example.timebank.utils.UIUtils;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

/**
 * Created by wwbb04 on 2021/7/19.
 */
public class DuiHuanFragment extends BaseFragment {

    private String[] mTitles = {"积分信息","服务兑换"};
    //将显示的fragment加到一个list当中，然后通过上方的标签来绑定这些内容
    private ArrayList<Fragment> mFragments1 = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = UIUtils.getView(R.layout.fragment_shopping);

        loadSlidingView(view);
        initData();

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

    private void loadSlidingView(View view) {
        //把编写好的fragment加到fragment的list中
        mFragments1.add(new DuiMessageFragment());
        mFragments1.add(new DuiServiceFragment());
//        mFragments1.add(new ShoppingMeFragment());

        //找到存放fragment的viewpager标签
        ViewPager vp=view.findViewById(R.id.fixedViewPager_shopping);
        VolShowAdapter md=new VolShowAdapter(getActivity().getSupportFragmentManager(), mTitles,mFragments1);
        vp.setAdapter(md);
        SlidingTabLayout slidingTabLayout=view.findViewById(R.id.slidingTabLayout_shopping);
        slidingTabLayout.setTabSpaceEqual(true);
        slidingTabLayout.setViewPager(vp);
    }

    @Override
    protected void initData() {
    }

    public int getLayoutId(){
        return R.layout.fragment_shopping;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
