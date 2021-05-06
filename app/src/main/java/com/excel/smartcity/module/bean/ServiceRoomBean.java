package com.excel.smartcity.module.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


public class ServiceRoomBean implements Serializable ,Comparable<ServiceRoomBean>{
    public String searchValue;
    public String createBy;
    public String updateBy;
    public String updateTime;
    public String remark;
    public Object params;
    public int id;
    public String serviceName;
    public String serviceDesc;
    public String serviceType;
    public String imgUrl;
    public String pid;
    public String isRecommend;
    public String link;

    @Override
    public int compareTo(ServiceRoomBean bean) {
        return bean.id - this.id;
    }
}
