package com.excel.smartcity.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MainFragmentAdapter  extends FragmentPagerAdapter {

    List<Fragment> contentList ;
    List<String> titleList;

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
    public MainFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public MainFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public MainFragmentAdapter(@NonNull FragmentManager fm, List<Fragment> list, List<String> titleList) {
        super(fm);
        this.contentList=list;
        this.titleList = titleList;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return contentList.get(position);
    }

    @Override
    public int getCount() {
        return contentList.size();
    }
}
