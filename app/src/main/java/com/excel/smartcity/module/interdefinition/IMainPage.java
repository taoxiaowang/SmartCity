package com.excel.smartcity.module.interdefinition;

import com.excel.smartcity.module.bean.RoomServiceList;
import com.excel.smartcity.module.bean.RotationList;
import com.excel.smartcity.module.lister.OnNetWorkResponseListener;

public interface IMainPage {
    void httpRotationLists(int pageNum , int pageSize,int type, OnNetWorkResponseListener<RotationList> listener);
    void httpReCommandList(int pageNum , int pageSize,OnNetWorkResponseListener<RoomServiceList> listener );
}
