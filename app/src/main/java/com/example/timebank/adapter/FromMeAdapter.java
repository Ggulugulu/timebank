package com.example.timebank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.timebank.R;
import com.example.timebank.bean.FromMeBean;

import java.util.List;

public class FromMeAdapter extends RecyclerView.Adapter<FromMeAdapter.FromMeHolder> {
    private Context mContext;
    private List<FromMeBean> datas;

    public FromMeAdapter(Context context, List<FromMeBean> datas){
        this.mContext = context;
        this.datas = datas;

    }

    /**
     * @param parent
     * @param viewType
     * @return 返回每一项的布局效果
     */
    @Override
    public FromMeAdapter.FromMeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_from_me,parent,false);
        FromMeHolder viewHolder = new FromMeHolder(view);
        return viewHolder;
    }

    /**
     * @param holder 封装好的viewholder对象
     * @param position 当前item的下标
     * 绑定数据
     */
    @Override
    public void onBindViewHolder(FromMeAdapter.FromMeHolder holder, int position) {
        FromMeHolder vh = (FromMeHolder) holder;
        FromMeBean fBean = datas.get(position);
//        vh.mIvHeader.setImageURI(uri);
        vh.name.setText(fBean.getName());
        vh.category.setText(fBean.getCategory());
        vh.state.setText(fBean.getState());

        vh.value.setText(String.valueOf(fBean.getValue()));
        vh.time.setText(String.valueOf(fBean.getTime()));

        vh.createTime.setText(fBean.getCreateTime().toString());
        vh.startTime.setText(fBean.getStartTime().toString());
        vh.endTime.setText(fBean.getEndTime().toString());

        vh.chakan.setText(fBean.getText());
        vh.chakan.setTextColor(fBean.getRecource());

        vh.shenbao.setVisibility(fBean.isVisibility());
        vh.shenbao.setText(fBean.getShenbaoText());
        vh.shenbao.setTextColor(fBean.getShenbaoRecource());


    }

    /**
     * @return 返回总共多少项
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }


    //1.自定义一个回调接口来实现Click和LongClick事件
    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public OnItemClickListener mOnItemClickListener;//第二步：声明自定义的接口

    //第三步：定义方法并暴露给外面的调用者
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }



    /**
     * 封装控件对象
     */
    class FromMeHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        String id;// 项目的唯一编号id
        TextView name;// 项目的名称
        TextView value;// 项目的时间货币
        TextView time;// 项目所需要的时间
        TextView category;// 项目的分类
        TextView state;// 项目的完成情况 可承接 已满员 已完成（已完成的需要公示两天）
        TextView createTime; // 项目的发布时间
        TextView startTime;// 项目的开始时间
        TextView endTime;// 项目的结束时间
        TextView chakan;
        TextView shenbao;
        public FromMeHolder(View View) {
            super(View);
            name = View.findViewById(R.id.vol_title);
            category = View.findViewById(R.id.tv_item_vol_category);
            state =  View.findViewById(R.id.tv_item_vol_state);

            value = View.findViewById(R.id.tv_item_vol_pay);
            time = View.findViewById(R.id.tv_item_vol_time);

            startTime = View.findViewById(R.id.tv_item_vol_start_time);
            endTime = View.findViewById(R.id.tv_item_vol_end_time);
            createTime = View.findViewById(R.id.tv_item_fa_time);
            chakan = View.findViewById(R.id.tv_item_vol_xiangqing);
            shenbao =View.findViewById(R.id.tv_item_shenbao);



            // 为item及item内部控件添加点击事件
            chakan.setOnClickListener(this);
            shenbao.setOnClickListener(this);

        }


        /**
         * @param v 点击事件的控件
         *          实现自定义点击事件
         */
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v,getAdapterPosition());
            }
        }

    }
}
