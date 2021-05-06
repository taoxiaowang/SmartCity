package com.excel.smartcity.module.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class RoomBean implements Serializable,Comparable<RoomBean> {
    public int orderId;
    public String imageUrl;
    public String title;


    public static ArrayList<RoomBean> mockData(){
        ArrayList<RoomBean> roomBeans = new ArrayList<>();
        for (int i = 0 ; i < 20 ; i++){
            RoomBean roomBean = new RoomBean();
            roomBean.imageUrl = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F1812.img.pp.sohu.com.cn%2Fimages%2Fblog%2F2009%2F11%2F18%2F18%2F8%2F125b6560a6ag214.jpg&refer=http%3A%2F%2F1812.img.pp.sohu.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1621844856&t=45b96ff57c668ab50b2a7938293b3054";
            roomBean.orderId = new Random().nextInt();
            roomBean.title = "入口"+i;
            roomBeans.add(roomBean);
        }
        return roomBeans;
    }

    @Override
    public int compareTo(RoomBean bean) {
        return this.orderId - bean.orderId;
    }
}
