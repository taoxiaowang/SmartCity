package com.excel.smartcity.util;

import android.content.Context;

import com.bumptech.glide.request.RequestOptions;
import com.excel.smartcity.R;

public class Util {

    public static RequestOptions getGlideOptions(Context context){
        return new RequestOptions().placeholder(context.getDrawable(R.mipmap.ic_launcher));
    }



}
