package com.excel.smartcity.module.network;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class OkHttpCallBack implements Callback {

    public abstract void onSuccess(final Call call, String jsonObject);
    public abstract void onFailureCommand(final Call call, IOException e);
    public Handler mHandler = new Handler(Looper.myLooper());

    @Override
    public void onResponse(Call call, Response response) throws IOException {

        if (response != null) {
            if (response.isSuccessful()) {
                try {
                    String str = response.body().string().trim();
                    Log.v("wt_test","servcie str --->"+str);
                    mHandler.post(() -> onSuccess(call, str));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                onFailure(call, null);
            }
        }

    }


    @Override
    public void onFailure(Call call, IOException e) {
        mHandler.post(() -> onFailureCommand(call,e));
    }

}