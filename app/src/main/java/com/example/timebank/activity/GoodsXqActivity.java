package com.example.timebank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timebank.R;
import com.example.timebank.common.ShoppingList;
import com.squareup.picasso.Picasso;

public class GoodsXqActivity extends Activity {
    ImageView mIvBack;
    ImageView mIvAdd;
    ImageView mIvDelete;
    ImageView header;
    ImageView pic1;
    ImageView pic2;
    ImageView pic3;
    ImageView pic4;
    ImageView pic5;

    TextView mIvNumber;
    TextView mTvDanjia;
    TextView name;
    Button mBtWant;


    TextView all_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_xq);
        mIvBack = findViewById(R.id.iv_title_back);
        mIvAdd = findViewById(R.id.iv_for_add);
        mIvDelete = findViewById(R.id.iv_for_subtract);
        mIvNumber = findViewById(R.id.tv_for_number);
        mBtWant = findViewById(R.id.bt_want);

        header = findViewById(R.id.img_header);
        pic1 = findViewById(R.id.goodsq_pic1);
        pic2 = findViewById(R.id.goodsq_pic2);
        pic3 = findViewById(R.id.goodsq_pic3);
        pic4 = findViewById(R.id.goodsq_pic4);
        pic5 = findViewById(R.id.goodsq_pic5);

        name = findViewById(R.id.goods_title);
        all_number = findViewById(R.id.tv_goods_last);
        mTvDanjia = findViewById(R.id.tv_goods_price);

        Bundle bundle = this.getIntent().getExtras(); //读取intent的数据给bundle对象
        handleBundle(bundle);

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodsXqActivity.this,MainActivity.class);
                intent.putExtra("id",2);
                startActivity(intent);
            }
        });

        mIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int want = Integer.parseInt(mIvNumber.getText().toString());
                //此时想要的数量小于等于库存数
                if(want < Integer.parseInt(all_number.getText().toString())){
                    want++;
                    mIvNumber.setText(""+want);
                }else{
                    Toast.makeText(GoodsXqActivity.this,"超过库存，不能再添加了",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int want = Integer.parseInt(mIvNumber.getText().toString());
                if(want >1){
                    want--;
                    mIvNumber.setText(""+want);
                }else{
                    Toast.makeText(GoodsXqActivity.this,"最少数量为1",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBtWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float cost = Float.parseFloat(mTvDanjia.getText().toString()) * Integer.parseInt(mIvNumber.getText().toString());
                Intent intent = new Intent(GoodsXqActivity.this,OrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putFloat("cost",cost);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


    }

    private void handleBundle(Bundle bundle) {
        int position =bundle.getInt("position");
       name.setText(bundle.getString("name"));


        Picasso.with(GoodsXqActivity.this)
                .load(ShoppingList.shooping[position])
                .fit()
                .into(header);

        Picasso.with(GoodsXqActivity.this)
                .load(ShoppingList.neirong[position][0])
                .into(pic1);
        Picasso.with(GoodsXqActivity.this)
                .load(ShoppingList.neirong[position][1])
                .into(pic2);
        Picasso.with(GoodsXqActivity.this)
                .load(ShoppingList.neirong[position][2])
                .into(pic3);
        Picasso.with(GoodsXqActivity.this)
                .load(ShoppingList.neirong[position][3])
                .into(pic4);
        Picasso.with(GoodsXqActivity.this)
                .load(ShoppingList.neirong[position][4])
                .into(pic5);
    }
}