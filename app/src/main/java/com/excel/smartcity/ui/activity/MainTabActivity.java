package com.excel.smartcity.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.excel.smartcity.R;
import com.excel.smartcity.ui.adapter.MainFragmentAdapter;
import com.excel.smartcity.ui.fragment.AllServiceFragment;
import com.excel.smartcity.ui.fragment.BuildingFragment;
import com.excel.smartcity.ui.fragment.MainPageFragment;
import com.excel.smartcity.ui.fragment.NewFragment;
import com.excel.smartcity.ui.fragment.PersonInfoFragment;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class MainTabActivity extends AppCompatActivity {
    TabLayout mTabLayout;
    ViewPager mViewPager;
    List<String> titles = new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();
    String[] title = {"","",""};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintab);
        initView();
        initData();
    }

    private void initView(){
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewpager);
    }

    private void initData(){
        fragments.add(new MainPageFragment());
        fragments.add(new AllServiceFragment());
        fragments.add(new BuildingFragment());
        fragments.add(new NewFragment());
        fragments.add(new PersonInfoFragment());
        titles.add(getString(R.string.main_page));
        titles.add(getString(R.string.all_service));
        titles.add(getString(R.string.party_building));
        titles.add(getString(R.string.new_info));
        titles.add(getString(R.string.person_info));
        MainFragmentAdapter fragmentPagerAdapter = new MainFragmentAdapter(getSupportFragmentManager(),
                fragments,titles);
        mViewPager.setAdapter(fragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.setCurrentItem(0);
        setupTabIcons();
    }


    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setCustomView(getTabView(0));
        mTabLayout.getTabAt(1).setCustomView(getTabView(1));
        mTabLayout.getTabAt(2).setCustomView(getTabView(2));
        mTabLayout.getTabAt(3).setCustomView(getTabView(1));
        mTabLayout.getTabAt(4).setCustomView(getTabView(2));
    }


    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.tabitem_layout, null);
        TextView txt_title = (TextView) view.findViewById(R.id.tab_text);
        txt_title.setText(titles.get(position));
        ImageView img_title = (ImageView) view.findViewById(R.id.tab_iv);
        img_title.setImageResource(R.mipmap.ic_launcher);
        return view;
    }
}
