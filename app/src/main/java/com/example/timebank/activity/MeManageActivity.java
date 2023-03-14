package com.example.timebank.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.timebank.R;
import com.example.timebank.UI.ChangeNameDialog;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MeManageActivity extends TakePhotoActivity {

    private RelativeLayout memberManage;
    private RelativeLayout changePhone;
    private ImageView mIvback;
    private ImageView mIvBeReal;
    private ImageView mIvArrowLocation;
    private TextView mTvLocation;
    private TextView mTvUser;
    private TextView phone;
    private ImageView mIvChangeName;
    private ImageView mIvBeBusiness;
    private ImageView touxiang;
    private TextView fenshu;
    private Button out;
    static String name;
    static String id;
    static String business_name;
    static String business_position;
    static boolean done;
    static boolean business_done;


    //动态权限
    String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    List<String> mPermissionList = new ArrayList<>();
    private final int mRequestCode = 100;//权限请求码

    //TakePhoto
    private TakePhoto takePhoto;
    private CropOptions cropOptions;    //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;       //图片保存路径



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_manage);
        mIvback = findViewById(R.id.iv_title_back);
        mIvBeReal = findViewById(R.id.be_real_user);
        mIvBeBusiness = findViewById(R.id.be_real_business);
        mIvArrowLocation = findViewById(R.id.iv_arrow_location);
        mTvLocation = findViewById(R.id.tv_location);
        mTvUser = findViewById(R.id.tv_user);
        touxiang = findViewById(R.id.img_head);
        mIvChangeName = findViewById(R.id.iv_change_name);
        memberManage = findViewById(R.id.rel_managementsite);
        out = findViewById(R.id.btn_quit);

        changePhone = findViewById(R.id.accountmanagement_widget_45);
        phone = findViewById(R.id.tv_phone);
        fenshu = findViewById(R.id.tv_fenshu);

        Bundle bundle = this.getIntent().getExtras(); //读取intent的数据给bundle对象
        handleBundle(bundle);

//        if (Build.VERSION.SDK_INT >= 23) {  //6.0才用动态权限
//            //申请相关权限
//            initPermission();
//        }
        initData();  //设置压缩、裁剪参数

        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();



        beRealCheck();



        mIvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeManageActivity.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });

        //家庭成员
        memberManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeManageActivity.this, MemberManageActivity.class);
                startActivity(intent);
            }
        });
        //实名认证
        mIvBeReal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name !=null && id!=null){
                    Intent intent = new Intent(MeManageActivity.this, BeRealUserActivity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MeManageActivity.this, BeRealUserActivity.class);
                    startActivity(intent);
                }

            }
        });

        //企业认证
        mIvBeBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(business_name !=null && business_position!=null){
                    Intent intent = new Intent(MeManageActivity.this, BusinessActivity.class);
                    intent.putExtra("business_name",business_name);
                    intent.putExtra("business_position",business_position);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MeManageActivity.this, BusinessActivity.class);
                    startActivity(intent);
                }

            }
        });

        //所在地地址选择
        mIvArrowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.loadLocation(MeManageActivity.this,mTvLocation);
            }
        });

        //更改头像
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //ImageUtils.showImagePickDialog(MeManageActivity.this);
                showTypeDialog();
            }
        });


        //更改名称
        mIvChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeNameDialog dialog = new ChangeNameDialog(MeManageActivity.this, mTvUser);
                dialog.setView(new EditText(getApplicationContext()));
                    //MeManageActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                dialog.show();
            }
        });


        //修改电话号码
        changePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeNameDialog dialog = new ChangeNameDialog(MeManageActivity.this,phone);
                dialog.setView(new EditText(getApplicationContext()));
                //MeManageActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                dialog.show();
            }
        });

        //退出
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeManageActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * 初始化权限
     */
    private void initPermission() {
        mPermissionList.clear();        //清空没有通过的权限
        //逐个判断你要的权限是否已经通过
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);//添加还未授予的权限
            }
        }
        //申请权限
        if (mPermissionList.size() > 0) {//有权限没有通过，需要申请
            ActivityCompat.requestPermissions(this, permissions, mRequestCode);
        } else {
            //说明权限都已经通过，可以做你想做的事情去
        }
    }

    /**
     * 初始化裁剪参数
     */
    private void initData() {
        takePhoto = getTakePhoto();
        //设置裁剪参数
        cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
        //设置压缩参数
        compressConfig = new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create();
        takePhoto.onEnableCompress(compressConfig, true);    //设置为需要压缩
    }


    /**
     * @param bundle
     * 判断两个认证图标是否要变成已认证
     */
    private void handleBundle(Bundle bundle) {
        if(bundle != null){
            name = bundle.getString("name");
            id = bundle.getString("id");
            done = bundle.getBoolean("done");

            business_name = bundle.getString("business_name");
            business_position = bundle.getString("business_position");
            business_done = bundle.getBoolean("business_done");

        }

    }

    private void beRealCheck() {
        if(done){
            mIvBeReal.setImageDrawable(getResources().getDrawable(R.drawable.yes_real_user));
        }
        if(business_done){
            mIvBeBusiness.setImageDrawable(getResources().getDrawable(R.drawable.yes_real_user));
        }
    }

    /**
     * 展示 相册/相机 对话框
     */
    private void showTypeDialog() {
        //显示对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(MeManageActivity.this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(MeManageActivity.this, R.layout.dialog_select_photo, null);
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                //                //从相册中选取图片并裁剪
//                takePhoto.onPickFromGalleryWithCrop(imageUri, cropOptions);
//                //从相册中选取不裁剪
                takePhoto.onPickFromGallery();
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
                imageUri = getImageCropUri();
                //拍照并裁剪
//                takePhoto.onPickFromCaptureWithCrop(imageUri, cropOptions);
                //仅仅拍照不裁剪
                takePhoto.onPickFromCapture(imageUri);
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    @Override
    public void takeSuccess(TResult result) {//第一种方法 成功
        super.takeSuccess(result);
        Log.i("1111111", "takeSuccess : " + result.getImage().getOriginalPath());
//        Glide.with(this).load(new File(result.getImage().getOriginalPath())).into(ivPhoto);
        super.takeSuccess(result);
        String iconPath = result.getImage().getOriginalPath();
        //Toast显示图片路径
        Toast.makeText(this, "imagePath:" + iconPath, Toast.LENGTH_SHORT).show();
        //Google Glide库 用于加载图片资源
        Glide.with(this).load(iconPath).into(touxiang);
    }


    @Override
    public void takeFail(TResult result, String msg) {//第二种 失败
        super.takeFail(result, msg);
    }

    @Override
    public void takeCancel() {//第三种退出 主要看第一种成功
        super.takeCancel();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermissionDismiss = false;//有权限没有通过
        if (mRequestCode == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    hasPermissionDismiss = true;
                }
            }
            //如果有权限没有被允许
            if (hasPermissionDismiss) {
//                showPermissionDialog();//跳转到系统设置权限页面，或者直接关闭页面，不让他继续访问
            } else {
                //全部权限通过，可以进行下一步操作。。。

            }
        }
    }
    //获得照片的输出保存Uri
    private Uri getImageCropUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }

}
