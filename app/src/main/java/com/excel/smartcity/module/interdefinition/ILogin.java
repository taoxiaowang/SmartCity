package com.excel.smartcity.module.interdefinition;

import com.excel.smartcity.module.bean.BaseData;
import com.excel.smartcity.module.bean.LoginResponse;
import com.excel.smartcity.module.lister.OnNetWorkResponseListener;

public interface ILogin {
    void httpLoginRequest(String name , String pwd, OnNetWorkResponseListener<LoginResponse> listener);
    void httpRerRequest(String userName
            , String nickName, String phonenumber
            , String sex, String password, OnNetWorkResponseListener<BaseData> listener);
}
