package com.example.timebank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timebank.R;
import com.example.timebank.bean.ShenHeBean;

import java.util.List;

public class ShenHeAdapter extends RecyclerView.Adapter<ShenHeAdapter.ShenheHolder> {
    private Context mContext;
    private List<ShenHeBean> datas;

    public ShenHeAdapter(Context context, List<ShenHeBean> datas) {
        this.mContext = context;
        this.datas = datas;

    }

    /**
     * @param parent
     * @param viewType
     * @return 返回每一项的布局效果
     */
    @Override
    public ShenHeAdapter.ShenheHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shenhe_show, parent, false);
        ShenheHolder shenheHolder = new ShenheHolder(view);
        return shenheHolder;
    }

    /**
     * @param holder   封装好的viewholder对象
     * @param position 当前item的下标
     *                 绑定数据
     */
    @Override
    public void onBindViewHolder(ShenHeAdapter.ShenheHolder holder, int position) {
        ShenheHolder vh = (ShenheHolder) holder;
        ShenHeBean shenheBean = datas.get(position);
//        vh.mIvHeader.setImageURI(uri);
        vh.mTvTitle.setText(shenheBean.getVolname());  //志愿名称
        vh.mTvAuthor.setText(shenheBean.getUsername()); //志愿发布人
        vh.mTvVolMeaage.setText(String.valueOf(shenheBean.getMoney()));  //志愿详情 钱
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
    class ShenheHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        private ImageView mIvHeader;
        private TextView mTvTitle;
        private TextView mTvAuthor;
        private TextView mTvVolMeaage;
        private Button mBtCanPass;
        private Button mBtNotPass;


        public ShenheHolder(View View) {
            super(View);

            mIvHeader = View.findViewById(R.id.img_header);
            mTvTitle = View.findViewById(R.id.vol_title);
            mTvAuthor = View.findViewById(R.id.author);
            mTvVolMeaage = View.findViewById(R.id.vol_message);
            mBtCanPass = View.findViewById(R.id.bt_can_pass);
            mBtNotPass = View.findViewById(R.id.bt_no_pass);

            mBtCanPass.setOnClickListener(this);
            mBtNotPass.setOnClickListener(this);
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