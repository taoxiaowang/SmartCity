package com.excel.smartcity.module.network;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtil {
    private static OkHttpClient mOkHttpClient = null;

    public static void init() {
        if (mOkHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            mOkHttpClient = builder.build();
        }
    }

    public static void get(String url, HashMap<String, String> requestMap, OkHttpCallBack okHttpCallback) {
        Call call;

        Request.Builder reqBuild = new Request.Builder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url)
                .newBuilder();
        if (requestMap != null) {
            for (HashMap.Entry<String, String> entry : requestMap.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        reqBuild.url(urlBuilder.build());
        Request request = reqBuild.build();

        try {
            call = mOkHttpClient.newCall(request);
            call.enqueue(okHttpCallback);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void post(String url, HashMap<String, String> bodyMap, OkHttpCallBack okHttpCallback) {
        Call call;
        MediaType FORM_CONTENT_TYPE
                = MediaType.parse("application/json;charset=utf-8");
        try {
            //方式一
//            FormBody.Builder builder = new FormBody.Builder();
//            if(bodyMap != null){
//                for (HashMap.Entry<String, String> entry : bodyMap.entrySet()) {
//                    builder.add(entry.getKey(), entry.getValue());
//                }
//            }
//            RequestBody body = builder.build();
//            Request request = new Request.Builder().post(body).url(url).build();
            // 方式二
            StringBuffer sb = new StringBuffer();
            if (bodyMap != null) {
                for (HashMap.Entry<String, String> entry : bodyMap.entrySet()) {
                    sb.append(entry.getKey()+"="+entry.getValue());
                }
            }
            RequestBody body = RequestBody.create(FORM_CONTENT_TYPE,sb.toString());
            Request request = new Request.Builder().post(body).url(url).build();
            call = mOkHttpClient.newCall(request);
            call.enqueue(okHttpCallback);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

