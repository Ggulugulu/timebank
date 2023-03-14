package com.example.timebank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.timebank.R;
import com.example.timebank.bean.MemberBean;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberHolder> {
    private Context mContext;
    private List<MemberBean> datas;

    public MemberAdapter(Context context, List<MemberBean> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public MemberAdapter.MemberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_member_sq, parent, false);
        MemberAdapter.MemberHolder Holder = new MemberAdapter.MemberHolder(view);
        return Holder;
    }

    @Override
    public void onBindViewHolder(MemberAdapter.MemberHolder holder, int position) {
        MemberBean memberBean = datas.get(position);
        holder.name.setText(memberBean.getId());
        holder.time.setText(memberBean.getTime());
        holder.level.setText(memberBean.getLevel());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //删除条目
    public void removeData(int position) {
        datas.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
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

    public class MemberHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView time;
        TextView level;

        TextView xiangqing;
        TextView yes;
        TextView no;
        public MemberHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_member_id);
            time = itemView.findViewById(R.id.tv_member_time);
            level = itemView.findViewById(R.id.tv_member_level);

            xiangqing = itemView.findViewById(R.id.tv_member_xq);
            no = itemView.findViewById(R.id.tv_member_no);
            yes = itemView.findViewById(R.id.tv_member_yes);

            xiangqing.setOnClickListener(this);
            no.setOnClickListener(this);
            yes.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v,getAdapterPosition());
            }
        }
    }
}
