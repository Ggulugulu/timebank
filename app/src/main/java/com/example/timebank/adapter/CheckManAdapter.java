package com.example.timebank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.timebank.R;
import com.example.timebank.bean.CheckManBean;

import java.util.List;

public class CheckManAdapter extends RecyclerView.Adapter<CheckManAdapter.CheckManHolder> {
    private Context mContext;
    private List<CheckManBean> datas;

    public CheckManAdapter(Context context, List<CheckManBean> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    /**
     * @param parent
     * @param viewType
     * @return 返回每一项的布局效果
     */
    @Override
    public CheckManAdapter.CheckManHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_checkman, parent, false);
        CheckManAdapter.CheckManHolder Holder = new CheckManAdapter.CheckManHolder(view);
        return Holder;
    }

    /**
     * @param holder   封装好的viewholder对象
     * @param position 当前item的下标
     *                 绑定数据
     */
    @Override
    public void onBindViewHolder(CheckManAdapter.CheckManHolder holder, int position) {
        CheckManAdapter.CheckManHolder  checkManHolder = (CheckManAdapter.CheckManHolder ) holder;
        CheckManBean checkManBean = datas.get(position);
       checkManHolder.mIvHeader.setImageBitmap(checkManBean.getTouxiang());
        checkManHolder.mTvUser.setText(checkManBean.getUser());
        checkManHolder.mTvRongyu.setText(checkManBean.getRongyu());
    }
    //获取数据源总的条数
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
     * 自定义的ViewHolder
     */
    class CheckManHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mIvHeader;
        private TextView mTvUser;
        private TextView mTvRongyu;
        private RelativeLayout zong;
        Button mBtVote;

        public CheckManHolder(View View) {
            super(View);

            mIvHeader = View.findViewById(R.id.img_header);
            mTvUser = View.findViewById(R.id.tv_user);
            mTvRongyu = View.findViewById(R.id.tv_rongyu);
            mBtVote = View.findViewById(R.id.bt_vote);
            zong = View.findViewById(R.id.ll_checkman);

            mBtVote.setOnClickListener(this);
            zong.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v,getAdapterPosition());
            }
        }


    }
}
