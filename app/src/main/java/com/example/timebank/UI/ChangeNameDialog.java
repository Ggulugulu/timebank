package com.example.timebank.UI;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timebank.R;

public class ChangeNameDialog extends AlertDialog implements View.OnClickListener {
    EditText mEtNewName;
    Button mBtnCancel, mBtnConnect;
    Context mContext;
    TextView mTv;

    public ChangeNameDialog(Context context, TextView tv) {
        super(context);
        mContext = context;
        mTv =tv;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_name);
        mEtNewName = (EditText) findViewById(R.id.et_changename);
        this.setCancelable(false);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);
        mBtnCancel.setOnClickListener(this);
        mBtnConnect = (Button) findViewById(R.id.btn_connect);
        mBtnConnect.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                this.dismiss();
                break;
            case R.id.btn_connect:
                if (TextUtils.isEmpty(mEtNewName.getText())) {
                    Toast.makeText(mContext, "新名称不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    mTv.setText(mEtNewName.getText().toString());
                    this.dismiss();
                    //Toast.makeText(mContext, mEtNewName.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

}
