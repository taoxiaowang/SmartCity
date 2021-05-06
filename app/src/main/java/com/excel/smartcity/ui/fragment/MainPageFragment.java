package com.excel.smartcity.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.excel.smartcity.R;
import com.excel.smartcity.constant.UrlConstant;
import com.excel.smartcity.module.bean.RoomServiceList;
import com.excel.smartcity.module.bean.RotationList;
import com.excel.smartcity.module.bean.ServiceRoomBean;
import com.excel.smartcity.module.impl.MainModel;
import com.excel.smartcity.module.interdefinition.IMainPage;
import com.excel.smartcity.module.lister.OnNetWorkResponseListener;
import com.excel.smartcity.util.Util;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainPageFragment extends Fragment implements OnBannerListener {

    IMainPage mainPageModule;
    OnNetWorkResponseListener<RotationList> mainListener;
    LinearLayout oneLineLayout;
    LinearLayout twoLineLayout;
    ArrayList<ServiceRoomBean> roomOldBeans;
    View contentView;
    List<ServiceRoomBean> twoLineList;
    List<ServiceRoomBean> oneLineList;
    RotationList mRotationData;
    Handler mHandler = new Handler(Looper.getMainLooper());
    private static final int FIRST_LINE = 5;
    private static final int SECOND_LINE = 9;
    private static String TestUrl = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.aiimg.com%2Fuploads%2Fuserup%2F0909%2F2Z64022L38.jpg&refer=http%3A%2F%2Fimg.aiimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1621862827&t=f99b01bc517b7d900870b92726f1d548";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_main, null);
        return contentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        getRotationData();
        showRoomUi();
    }

    private void showRoomUi() {
        oneLineLayout = contentView.findViewById(R.id.oneLineLayout);
        twoLineLayout = contentView.findViewById(R.id.twoLineLayout);
    }

    private void initOneLine() {
        if (roomOldBeans.size() > FIRST_LINE) {
            oneLineList = roomOldBeans.subList(0, FIRST_LINE);
        } else {
            oneLineList = roomOldBeans;
        }
    }


    private void initTwoLine() {
        if (roomOldBeans.size() > SECOND_LINE) {
            twoLineList = roomOldBeans.subList(FIRST_LINE, SECOND_LINE);
        } else if (roomOldBeans.size() > FIRST_LINE && roomOldBeans.size() < SECOND_LINE) {
            twoLineList = roomOldBeans.subList(FIRST_LINE, roomOldBeans.size());
        }
    }


    private  LinearLayout.LayoutParams  getItemViewParams(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                , LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        params.topMargin = getActivity().getResources().getDimensionPixelOffset(R.dimen.margin);
        params.bottomMargin = getActivity().getResources().getDimensionPixelOffset(R.dimen.margin);
        params.leftMargin = getActivity().getResources().getDimensionPixelOffset(R.dimen.margin);
        params.rightMargin = getActivity().getResources().getDimensionPixelOffset(R.dimen.margin);
        return params;
    }

    private View getItemView(ServiceRoomBean bean){
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.room_item_view, null);
        ImageView iv = contentView.findViewById(R.id.ivContent);
        Glide.with(getActivity()).load(TestUrl).apply(Util.getGlideOptions(getActivity())).into(iv);
//        Glide.with(getActivity()).load(UrlConstant.BASE_URL + bean.imgUrl).apply(Util.getGlideOptions(getActivity())).into(iv);
        TextView title = contentView.findViewById(R.id.tv_title);
        title.setText(bean.serviceName);
        return contentView;
    }

    private void addViewToRoom(LinearLayout layout, List<ServiceRoomBean> roomBeans, boolean isNext) {
        if (layout == null || roomBeans == null || roomBeans.size() <= 0) {
            return;
        }

        for (int i = 0; i < roomBeans.size(); i++) {
            layout.addView(getItemView(roomBeans.get(i)), getItemViewParams());
        }

        if(isNext && roomOldBeans.size() < SECOND_LINE && roomOldBeans.size() >  FIRST_LINE){
            for(int i = 0 ; i < FIRST_LINE - twoLineList.size();i++){
                View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.room_item_view, null);
                contentView.setVisibility(View.INVISIBLE);
                layout.addView(contentView, getItemViewParams());
            }
        }

        if (isNext && roomOldBeans.size() > SECOND_LINE) {
            View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.room_item_view, null);
            TextView title = contentView.findViewById(R.id.tv_title);
            title.setText("更多入口");
            layout.addView(contentView, getItemViewParams());
        }
    }

    private void initData() {
        mainPageModule = new MainModel();
        mainListener = new OnNetWorkResponseListener<RotationList>() {
            @Override
            public void onNetResponseError(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNetResponseSuccess(RotationList data) {
                mRotationData = data;
                if(data.rows != null && data.rows.size() > 0){
                    ArrayList<String> urls = new ArrayList<>();
                    ArrayList<String> titles = new ArrayList<>();
                    for (int i = 0 ; i < data.rows.size() ; i++){
                        urls.add(UrlConstant.BASE_URL + data.rows.get(i).imgUrl);
                        titles.add("");
                    }
                    playBanner(contentView.findViewById(R.id.bannerLayout),urls,titles);
                }

            }
        };
    }

    private void playBanner(Banner banner, ArrayList<String> urls,ArrayList<String> title) {
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new BannerImageLoader());
        //设置图片网址或地址的集合
        banner.setImages(urls);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        banner.setBannerTitles(title);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        banner.setBannerAnimation(Transformer.Default);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();

    }

    private class BannerImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }

    }

    private void getRotationData() {
        mainPageModule.httpRotationLists(1, 10, 47, mainListener);
        mainPageModule.httpReCommandList(1, 20, new OnNetWorkResponseListener<RoomServiceList>() {
            @Override
            public void onNetResponseError(String msg) {

            }

            @Override
            public void onNetResponseSuccess(final RoomServiceList data) {
                Log.v("wt_test","data -- >"+ new Gson().toJson(data));
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        roomOldBeans = data.rows;
                        Collections.sort(roomOldBeans);
                        initOneLine();
                        initTwoLine();
                        addViewToRoom(oneLineLayout, oneLineList, false);
                        addViewToRoom(twoLineLayout, twoLineList, true);
                    }
                });

            }
        });
    }

    @Override
    public void OnBannerClick(int i) {

    }
}
