package com.example.timebank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timebank.R;
import com.example.timebank.bean.ShenBaoBean;

import java.util.List;

public class ShenBaoAdapter extends RecyclerView.Adapter<ShenBaoAdapter.ShenBaoHolder> {
    private Context mContext;
    private List<ShenBaoBean> datas;

    public ShenBaoAdapter(Context context, List<ShenBaoBean> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public ShenBaoAdapter.ShenBaoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shenbao, parent, false);
        ShenBaoAdapter.ShenBaoHolder Holder = new ShenBaoAdapter.ShenBaoHolder(view);
        return Holder;
    }

    @Override
    public void onBindViewHolder(ShenBaoAdapter.ShenBaoHolder holder, int position) {
        ShenBaoBean shenBaoBean = datas.get(position);
        holder.name.setText(shenBaoBean.getName());
        holder.text.setText(shenBaoBean.getText());
        holder.star.setText(shenBaoBean.getStar());
        holder.time.setText(shenBaoBean.getTime());
        holder.last.setText(shenBaoBean.getLast());
        holder.touxiang.setImageBitmap(shenBaoBean.getTouxiang());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ShenBaoHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView text;
        TextView star;
        TextView time;
        TextView last;
        ImageView touxiang;

        public ShenBaoHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.shenbao_name);
            text = itemView.findViewById(R.id.shenbao_text);
            star = itemView.findViewById(R.id.shenbao_star);
            time = itemView.findViewById(R.id.shenbao_time);
            last =  itemView.findViewById(R.id.shenbao_last);
            touxiang =itemView.findViewById(R.id.img_header);

        }
    }
}
