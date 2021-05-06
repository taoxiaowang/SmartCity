package com.excel.smartcity.app;

import android.app.Application;

import com.excel.smartcity.module.network.OkHttpUtil;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpUtil.init();
    }
}
