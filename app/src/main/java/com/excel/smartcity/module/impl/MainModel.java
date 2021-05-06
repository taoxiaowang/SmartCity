package com.excel.smartcity.module.impl;

import com.excel.smartcity.constant.UrlConstant;
import com.excel.smartcity.module.bean.LoginResponse;
import com.excel.smartcity.module.bean.RoomServiceList;
import com.excel.smartcity.module.bean.RotationList;
import com.excel.smartcity.module.interdefinition.IMainPage;
import com.excel.smartcity.module.lister.OnNetWorkResponseListener;
import com.excel.smartcity.module.network.OkHttpCallBack;
import com.excel.smartcity.module.network.OkHttpUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

public class MainModel implements IMainPage {
    @Override
    public void httpRotationLists(int pageNum, int pageSize, int type, OnNetWorkResponseListener<RotationList> listener) {
        HashMap<String,String> requestMap = new HashMap<>();
        requestMap.put("pageNum",String.valueOf(pageNum));
        requestMap.put("pageSize",String.valueOf(pageSize));
        requestMap.put("type",String.valueOf(type));
        OkHttpUtil.get(UrlConstant.ROTATION_LIST, requestMap, new OkHttpCallBack() {
            @Override
            public void onSuccess(Call call, String jsonObject) {
                listener.onNetResponseSuccess( new Gson().fromJson(jsonObject,RotationList.class));
            }

            @Override
            public void onFailureCommand(Call call, IOException e) {
                listener.onNetResponseError(e.getLocalizedMessage());
            }
        });
    }

    @Override
    public void httpReCommandList(int pageNum, int pageSize,OnNetWorkResponseListener<RoomServiceList> listener) {
        HashMap<String,String> requestMap = new HashMap<>();
        requestMap.put("pageNum",String.valueOf(pageNum));
        requestMap.put("pageSize",String.valueOf(pageSize));
        OkHttpUtil.get(UrlConstant.RE_COMMAND, requestMap, new OkHttpCallBack() {
            @Override
            public void onSuccess(Call call, String jsonObject) {
                listener.onNetResponseSuccess( new Gson().fromJson(jsonObject,RoomServiceList.class));
            }

            @Override
            public void onFailureCommand(Call call, IOException e) {
                listener.onNetResponseError(e.getLocalizedMessage());
            }
        });
    }
}
