package com.example.timebank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timebank.R;
import com.example.timebank.bean.AddressBean;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressHolder> {
    private Context mContext;
    private List<AddressBean> datas;
    public AddressAdapter(Context context, List<AddressBean> datas) {
        this.mContext = context;
        this.datas = datas;
    }
    @Override
    public AddressHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_member, parent, false);
        AddressAdapter.AddressHolder Holder = new AddressAdapter.AddressHolder(view);
        return Holder;
    }

    @Override
    public void onBindViewHolder(AddressHolder holder, int position) {
        AddressBean ab = datas.get(position);
        holder.name.setText(ab.getName());
        holder.phone.setText(ab.getPhone());
        holder.address.setText(ab.getGuanxi());
        //holder.moren.setBackgroundResource(ab.getMoren());
    }

    @Override
    public int getItemCount() {
        return  datas.size();
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

    public class AddressHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView phone;
        private TextView address;
        private ImageView moren;
        private TextView delete;

        public AddressHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.address_name);
            phone = itemView.findViewById(R.id.address_phone);
            address = itemView.findViewById(R.id.address_address);
            //moren = itemView.findViewById(R.id.address_moren);
            delete = itemView.findViewById(R.id.address_delete);

//            moren.setOnClickListener(this);
            delete.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v,getAdapterPosition());
            }
        }
    }
}
