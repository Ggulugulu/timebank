package com.example.timebank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timebank.R;
import com.example.timebank.bean.PingJiaBean;
import com.hedgehog.ratingbar.RatingBar;

import java.util.List;

public class PingJiaAdapter extends RecyclerView.Adapter<PingJiaAdapter.PingJiaHolder>{
    private Context mContext;
    private List<PingJiaBean> datas;

    public PingJiaAdapter(Context context, List<PingJiaBean> datas){
        this.mContext = context;
        this.datas = datas;

    }

    /**
     * @param parent
     * @param viewType
     * @return 返回每一项的布局效果
     */
    @Override
    public PingJiaAdapter.PingJiaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pingjia,parent,false);
        PingJiaHolder viewHolder = new PingJiaHolder(view);
        return viewHolder;
    }

    /**
     * @param holder 封装好的viewholder对象
     * @param position 当前item的下标
     * 绑定数据
     */
    @Override
    public void onBindViewHolder(PingJiaAdapter.PingJiaHolder holder, int position) {
        PingJiaHolder ph = (PingJiaHolder) holder;
        PingJiaBean pingjiaBean = datas.get(position);
        ph.touxiang.setImageBitmap(pingjiaBean.getTouxiang());
        ph.username.setText(pingjiaBean.getUsername());
        ph.neirong.setText(pingjiaBean.getNeirong());
        ph.starbar.setStar(pingjiaBean.getStarbar());
        ph.state.setText(pingjiaBean.getState());
        ph.acceptime.setText(pingjiaBean.getAccepttime());
        ph.overtime.setText(pingjiaBean.getOvertime());


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }




    /**
     * 封装控件对象
     */
    class PingJiaHolder extends  RecyclerView.ViewHolder{
        ImageView touxiang;
        TextView username;
        TextView neirong;
        TextView state;
        TextView acceptime;
        TextView overtime;

        RatingBar starbar;

        public PingJiaHolder(View itemView) {
            super(itemView);
            touxiang = itemView.findViewById(R.id.pingjia_touxiang);
            username = itemView.findViewById(R.id.pingjia_username);
            neirong = itemView.findViewById(R.id.pingjia_neirong);
            starbar = itemView.findViewById(R.id.starbar);
            state = itemView.findViewById(R.id.userstate);
            acceptime = itemView.findViewById(R.id.accepttime);
            overtime  =itemView.findViewById(R.id.overtime);
        }
    }
}
