package com.example.timebank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timebank.R;
import com.example.timebank.bean.HuiZhangBean;

import java.util.List;

public class HuiZhangAdapter extends  RecyclerView.Adapter<HuiZhangAdapter.HuiZhangHolder> {
    private Context mContext;
    private List<HuiZhangBean> datas;

    public HuiZhangAdapter(Context context, List<HuiZhangBean> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    /**
     * @param parent
     * @param viewType
     * @return 返回每一项的布局效果
     */
    @Override
    public HuiZhangAdapter.HuiZhangHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_huizhang, parent, false);
        HuiZhangAdapter.HuiZhangHolder Holder = new HuiZhangAdapter.HuiZhangHolder(view);
        return Holder;
    }

    /**
     * @param holder   封装好的viewholder对象
     * @param position 当前item的下标
     *                 绑定数据
     */
    @Override
    public void onBindViewHolder(HuiZhangAdapter.HuiZhangHolder holder, int position) {
        HuiZhangBean huiZhangBean = datas.get(position);
        //holder.mTvHuiZhang.setImageURI(uri);
       holder.mTvName.setText(huiZhangBean.getName());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 自定义的ViewHolder
     */
    static class HuiZhangHolder extends RecyclerView.ViewHolder {
        private ImageView mIvHuiZhang;
        private TextView mTvName;

        public HuiZhangHolder(View View) {
            super(View);

            mIvHuiZhang = View.findViewById(R.id.iv_huizhang);
            mTvName = View.findViewById(R.id.tv_huizhang_name);
        }
    }
}
