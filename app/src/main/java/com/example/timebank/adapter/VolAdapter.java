package com.example.timebank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.timebank.R;
import com.example.timebank.bean.VolBean;

import java.util.List;

/**
 * 志愿列表
 */
public class VolAdapter extends RecyclerView.Adapter<VolAdapter.VolHolder> {
    private Context mContext;
    private List<VolBean> datas;


    public VolAdapter(Context context, List<VolBean> datas){
        this.mContext = context;
        this.datas = datas;

    }

    public void setVol(List<VolBean> VolBean) {
        datas = VolBean;
    }

    /**
     * @param parent
     * @param viewType
     * @return 返回每一项的布局效果
     */
    @Override
    public VolAdapter.VolHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_vol_show,parent,false);
        VolHolder viewHolder = new VolHolder(view);
        return viewHolder;
    }

    /**
     * @param holder 封装好的viewholder对象
     * @param position 当前item的下标
     * 绑定数据
     */
    @Override
    public void onBindViewHolder(VolAdapter.VolHolder holder, int position) {
        VolHolder vh = (VolHolder) holder;
        VolBean volBean = datas.get(position);
//        vh.mIvHeader.setImageURI(uri);
        vh.name.setText(volBean.getName());
        vh.ownerName.setText(volBean.getOwnerName());
        vh.value.setText(volBean.getValue());
        vh.time.setText(volBean.getTime().toString());
        vh.category.setText(volBean.getCategory());
        vh.state.setText(volBean.getState());
        vh.state.setTextColor(volBean.getTextRecource());
        vh.createTime.setText(volBean.getCreateTime().toString());
        vh.startTime.setText(volBean.getStartTime().toString());
        vh.endTime.setText(volBean.getEndTime().toString());

        vh.chakan.setVisibility(volBean.getVisibility());
        vh.attend.setText(volBean.getButtonText());
        vh.attend.setBackgroundResource(volBean.getRecource());
        vh.attend.setClickable(volBean.getClickable());


    }


    //删除条目
    public void removeData(int position) {
        datas.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
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
    public class VolHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
         ImageView mIvHeader;
         String id;// 项目的唯一编号id
         TextView name;// 项目的名称
         TextView ownerName;// 项目发起者的用户名
         TextView value;// 项目的时间货币
         TextView time;// 项目所需要的时间
         TextView category;// 项目的分类
         TextView state;// 项目的完成情况 可承接 已满员 已完成（已完成的需要公示两天）
         TextView createTime; // 项目的发布时间
         TextView startTime;// 项目的开始时间
         TextView endTime;// 项目的结束时间
         TextView chakan;
         Button attend;
         LinearLayout item;

        public VolHolder(View View) {
            super(View);
            item = View.findViewById(R.id.item);
            mIvHeader = View.findViewById(R.id.img_header);
            name = View.findViewById(R.id.vol_title);
            ownerName = View.findViewById(R.id.author);
            value = View.findViewById(R.id.tv_item_vol_pay);
            time = View.findViewById(R.id.tv_item_vol_time);
            category = View.findViewById(R.id.tv_item_vol_category);
            state =  View.findViewById(R.id.tv_item_vol_state);
            createTime = View.findViewById(R.id.tv_item_fa_time);
            startTime = View.findViewById(R.id.tv_item_vol_start_time);
            endTime = View.findViewById(R.id.tv_item_vol_end_time);
            chakan = View.findViewById(R.id.tv_item_vol_xiangqing);
            attend = View.findViewById(R.id.bt_attend);


            // 为item及item内部控件添加点击事件
            chakan.setOnClickListener(this);
            attend.setOnClickListener(this);

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
