package com.excel.smartcity.module.lister;


public interface OnNetWorkResponseListener<T> {
    void onNetResponseError(String msg);

    void onNetResponseSuccess(T data);

}
