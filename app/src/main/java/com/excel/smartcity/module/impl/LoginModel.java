package com.excel.smartcity.module.impl;

import android.util.Log;

import com.excel.smartcity.constant.UrlConstant;
import com.excel.smartcity.module.bean.BaseData;
import com.excel.smartcity.module.bean.LoginResponse;
import com.excel.smartcity.module.interdefinition.ILogin;
import com.excel.smartcity.module.lister.OnNetWorkResponseListener;
import com.excel.smartcity.module.network.OkHttpCallBack;
import com.excel.smartcity.module.network.OkHttpUtil;
import com.google.gson.Gson;


import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

public class LoginModel implements ILogin {
    private static final String TAG = "LoginModel";

    @Override
    public void httpLoginRequest(String name, String pwd, OnNetWorkResponseListener<LoginResponse> listener) {
        HashMap<String,String> requestMap = new HashMap<>();
        requestMap.put("username",name);
        requestMap.put("password",pwd);
        OkHttpUtil.post(UrlConstant.LOGIN_URL, requestMap, new OkHttpCallBack() {
            @Override
            public void onSuccess(Call call, String jsonObject) {
                try{
                    Log.v("wt_test","login --->"+ jsonObject);
                    listener.onNetResponseSuccess( new Gson().fromJson(jsonObject, LoginResponse.class));
                }catch (Exception e){
                    e.printStackTrace();
                    listener.onNetResponseSuccess(null);
                }
            }

            @Override
            public void onFailureCommand(Call call, IOException e) {
                listener.onNetResponseError(e.getLocalizedMessage());
            }
        });

    }

    @Override
    public void httpRerRequest(String userName, String nickName, String phonenumber, String sex, String password, OnNetWorkResponseListener<BaseData> listener) {
        HashMap<String,String> requestMap = new HashMap<>();
        requestMap.put("userName",userName);
        requestMap.put("nickName",nickName);
        requestMap.put("phonenumber",phonenumber);
        requestMap.put("sex",sex);
        requestMap.put("password",password);
        OkHttpUtil.post(UrlConstant.REGISTER_URL, requestMap, new OkHttpCallBack() {
            @Override
            public void onSuccess(Call call, String jsonObject) {
                try{
                    Log.v("wt_test","REGISTER_URL --->"+ jsonObject);
                    listener.onNetResponseSuccess( new Gson().fromJson(jsonObject, BaseData.class));
                }catch (Exception e){
                    e.printStackTrace();
                    listener.onNetResponseSuccess(null);
                }
            }

            @Override
            public void onFailureCommand(Call call, IOException e) {
                listener.onNetResponseError(e.getLocalizedMessage());
            }
        });
    }
}
