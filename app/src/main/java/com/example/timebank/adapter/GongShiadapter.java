package com.example.timebank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timebank.R;
import com.example.timebank.bean.GongShiBean;

import java.util.List;

public class GongShiadapter extends RecyclerView.Adapter<GongShiadapter.GongShiHolder> {
    private Context mContext;
    private List<GongShiBean> datas;

    public GongShiadapter(Context context, List<GongShiBean> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public GongShiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_gongshi_checkman, parent, false);
        GongShiHolder Holder = new GongShiHolder(view);
        return Holder;
    }

    @Override
    public void onBindViewHolder(GongShiHolder holder, int position) {
        GongShiBean gongShiBean = datas.get(position);
        holder.user.setText(gongShiBean.getUser());
        holder.baozheng.setText(gongShiBean.getBaozheng());
        holder.renwu1.setText(gongShiBean.getRenwu1());
        holder.renwu2.setText(gongShiBean.getRenwu2());
        holder.renwu3.setText(gongShiBean.getRenwu3());
//        holder.beginTime.setText(gongShiBean.getBeginTime());
//        holder.endTime.setText(gongShiBean.getEndTime());
        holder.status.setText(gongShiBean.getStatus());
        holder.touxiang.setImageBitmap(gongShiBean.getTouxaing());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class GongShiHolder extends RecyclerView.ViewHolder {
        TextView user;
        TextView baozheng;
        TextView renwu1;
        TextView renwu2;
        TextView renwu3;
        TextView beginTime;
        TextView endTime;
        TextView status;
        ImageView touxiang;

        public GongShiHolder(View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.tv_user);
            baozheng = itemView.findViewById(R.id.tv_baozheng);
            renwu1 = itemView.findViewById(R.id.tv_renwu1);
            renwu2 = itemView.findViewById(R.id.tv_renwu2);
            renwu3 = itemView.findViewById(R.id.tv_renwu3);
            touxiang = itemView.findViewById(R.id.touxiang);
//            beginTime = itemView.findViewById(R.id.tv_begintime);
//            endTime  = itemView.findViewById(R.id.tv_endtime);
            status = itemView.findViewById(R.id.tv_status);
        }
    }
}
