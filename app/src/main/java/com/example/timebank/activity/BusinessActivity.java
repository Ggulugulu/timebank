package com.example.timebank.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.timebank.R;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;

import java.io.File;

public class BusinessActivity extends TakePhotoActivity {
    ImageView mImTitleBack;
    ImageView add1;
    ImageView add2;
    ImageView add3;
    Button bebusiness;
    TextView business_name;
    TextView business_position;
    String businessname;
    String businessposition;

    //TakePhoto
    private TakePhoto takePhoto;
    private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;  //图片保存路径
    int shoseImg = 0;// 标识一下选择的哪个 img，单张照片可不用设置
    ImageView[] add1view;
    ImageView[] add2view;
    ImageView[] add3view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_business);

        Spinner spinner = (Spinner) findViewById(R.id.gongsi_spinner);
        mImTitleBack = findViewById(R.id.iv_title_back);
        business_name = findViewById(R.id.et_business_name);
        business_position = findViewById(R.id.et_business_position);
        bebusiness = findViewById(R.id.bt_be_business);
        add1 = findViewById(R.id.business_add1);
        add2 = findViewById(R.id.business_add2);
        add3 = findViewById(R.id.business_add3);

        add1view = new ImageView[]{findViewById(R.id.business_add11),
                findViewById(R.id.business_add12),
                findViewById(R.id.business_add13),
                findViewById(R.id.business_add14)
        };

        add1view = new ImageView[]{findViewById(R.id.business_add11),
                findViewById(R.id.business_add12),
                findViewById(R.id.business_add13),
                findViewById(R.id.business_add14)
        };

        add2view = new ImageView[]{findViewById(R.id.business_add21),
                findViewById(R.id.business_add22),
                findViewById(R.id.business_add23),
                findViewById(R.id.business_add24)
        };

        add3view = new ImageView[]{findViewById(R.id.business_add31),
                findViewById(R.id.business_add32),
                findViewById(R.id.business_add33),
                findViewById(R.id.business_add34)
        };


        Bundle bundle = this.getIntent().getExtras(); //读取intent的数据给bundle对象
        handleBundle(bundle);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String[] duixiang = getResources().getStringArray(R.array.gongsi);
                //Toast.makeText(BusinessActivity.this, "你点击的是:"+duixiang[pos], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置状态值
                shoseImg = 1;
                initData();
                imageUri = getImageCropUri();
                takePhoto.onPickMultiple(5);
            }
        });

        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置状态值
                shoseImg = 2;
                initData();
                imageUri = getImageCropUri();
                takePhoto.onPickMultiple(5);
            }
        });
        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置状态值
                shoseImg = 3;
                initData();
                imageUri = getImageCropUri();
                takePhoto.onPickMultiple(5);
            }
        });



        mImTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessActivity.this, MeManageActivity.class);
                intent.putExtra("business_name", business_name.getText().toString());
                intent.putExtra("business_position",business_position.getText().toString());
                intent.putExtra("business_done",true);
                startActivity(intent);
            }
        });
        bebusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!business_name.getText().toString().equals("") && !business_position.getText().toString().equals("") ){
                    Toast.makeText(BusinessActivity.this,"提交成功！",Toast.LENGTH_SHORT).show();
                    bebusiness.setTextColor(0xff979997);
                    bebusiness.setBackgroundColor(0xffe7e9e7);
                    bebusiness.setClickable(false);
                    bebusiness.setText("已认证");

                    Intent intent = new Intent(BusinessActivity.this,MeManageActivity.class);
                    intent.putExtra("business_name", business_name.getText().toString());
                    intent.putExtra("business_position",business_position.getText().toString());
                    intent.putExtra("business_done",true);

                    startActivity(intent);
                }else{
                    Toast.makeText(BusinessActivity.this,"消息未填写完成",Toast.LENGTH_SHORT).show();
                }
            }
        });

        beRealCheck(businessname,businessposition);


    }

    private void handleBundle(Bundle bundle) {
        if(bundle != null){
            businessname = bundle.getString("business_name");
            businessposition = bundle.getString("business_position");

        }
    }

    private void beRealCheck(String name, String id) {
        if(name !=null && id!=null){
            business_name.setText(name);
            business_position.setText(id);
            business_position.setEnabled(false);
            business_name.setEnabled(false);

            bebusiness.setTextColor(0xff979997);
            bebusiness.setBackgroundColor(0xffe7e9e7);
            bebusiness.setClickable(false);
            bebusiness.setText("已认证");
        }
    }
    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        int size = result.getImages().size();
        switch (shoseImg){
            case 1:
                if(size > 0){
                    Glide.with(this).load(result.getImages().get(0).getCompressPath()).into(add1);
                    for(int i=1;i<size;i++){
                        Glide.with(this).load(result.getImages().get(i).getCompressPath()).into(add1view[i-1]);
                    }
                    for(int i =size; i<5;i++){
                        add1view[i-1].setImageDrawable(null);
                    }
                }
                break;
            case 2:
                Glide.with(this).load(result.getImages().get(0).getCompressPath()).into(add2);
                if(size>1){
                    for(int i=1;i<size;i++){
                        Glide.with(this).load(result.getImages().get(i).getCompressPath()).into(add2view[i-1]);
                    }
                }
                break;
            case 3:
                Glide.with(this).load(result.getImages().get(0).getCompressPath()).into(add3);
                if(size>1){
                    for(int i=1;i<size;i++){
                        Glide.with(this).load(result.getImages().get(i).getCompressPath()).into(add3view[i-1]);
                    }
                }
                break;
        }
    }
    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }




    private void initData() {
        //获取TakePhoto实例
        takePhoto = getTakePhoto();
        //设置裁剪参数
        cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
        //设置压缩参数
        compressConfig=new CompressConfig.Builder().setMaxSize(50*1024).setMaxPixel(800).create();
        takePhoto.onEnableCompress(compressConfig,true);  //设置为需要压缩
    }
    //获得照片的输出保存Uri
    private Uri getImageCropUri() {
        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }



}