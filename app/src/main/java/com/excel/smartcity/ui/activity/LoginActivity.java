package com.excel.smartcity.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.excel.smartcity.R;
import com.excel.smartcity.module.bean.BaseData;
import com.excel.smartcity.module.bean.LoginResponse;
import com.excel.smartcity.module.impl.LoginModel;
import com.excel.smartcity.module.interdefinition.ILogin;
import com.excel.smartcity.module.lister.OnNetWorkResponseListener;

public class LoginActivity extends AppCompatActivity {
    EditText mEdtName;
    EditText mEdtPwd;
    Button mBtnLogin;
    ILogin loginModule;
    OnNetWorkResponseListener<LoginResponse> loginListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    private void initView(){
        mEdtName = findViewById(R.id.edt_name);
        mEdtPwd = findViewById(R.id.edt_pwd);
        mBtnLogin = findViewById(R.id.login_btn);
        mBtnLogin.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this,MainTabActivity.class));
            loginModule.httpRerRequest("wangtao2", "wangtao2"
                    , "15828028350", "1", "123456", new OnNetWorkResponseListener<BaseData>() {
                        @Override
                        public void onNetResponseError(String msg) {

                        }

                        @Override
                        public void onNetResponseSuccess(BaseData data) {

                        }
                    });
            loginModule.httpLoginRequest(mEdtName.getText().toString()
                    ,mEdtPwd.getText().toString(),loginListener);
//            mainPageModule.httpRotationLists(1,10,47,mainListener);
        });
    }

    private void initData(){
        loginModule = new LoginModel();
        loginListener = new OnNetWorkResponseListener<LoginResponse>() {
            @Override
            public void onNetResponseError(String msg) {
                Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNetResponseSuccess(LoginResponse data) {
                startActivity(new Intent(LoginActivity.this,MainTabActivity.class));
            }
        };


    }
}
