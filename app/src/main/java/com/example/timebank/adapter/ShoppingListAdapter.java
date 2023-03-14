package com.example.timebank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.timebank.R;
import com.example.timebank.bean.ShoppingListDataBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListHolder> {
    private Context mContext;
    private List<ShoppingListDataBean> datas;

    public ShoppingListAdapter(Context context, List<ShoppingListDataBean> datas){
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public ShoppingListAdapter.ShoppingListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shopping_list,parent,false);
        ShoppingListHolder viewHolder = new ShoppingListHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShoppingListAdapter.ShoppingListHolder holder, int position) {
        ShoppingListDataBean shoppingListDataBean = datas.get(position);
        Picasso.with(mContext)
                .load(shoppingListDataBean.getUrl())
                .fit()
                .into(holder.mIvHeader);

        holder.name.setText(shoppingListDataBean.getName());
        holder.price.setText(shoppingListDataBean.getPrice());
        holder.last.setText(shoppingListDataBean.getLast());

    }

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


    class ShoppingListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout all;
        ImageView mIvHeader;
        TextView name;
        TextView price;
        TextView last;
        public ShoppingListHolder(View itemView) {
            super(itemView);
            all = itemView.findViewById(R.id.ll_shooping_list);
            mIvHeader = itemView.findViewById(R.id.iv_shpping_list_img);
            name = itemView.findViewById(R.id.tv_shopping_list_title);
            price = itemView.findViewById(R.id.tv_shopping_list_price);
            last = itemView.findViewById(R.id.tv_shopping_list_last);

            all.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v,getAdapterPosition());
            }
        }


    }
}
