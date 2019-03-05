package com.prettyyes.user.app.ui.common;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.MyFragmentPagerAdapter;
import com.prettyyes.user.app.base.BaseActivity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

public abstract class MutiTabActivity extends BaseActivity {


    @ViewInject(R.id.tabLayout)
    public TabLayout tabLayout;
    @ViewInject(R.id.vp)
    public ViewPager vp;
    private String[] titles;
    public ArrayList<Fragment> fragments;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_muti_tab;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData() {

        fragments = new ArrayList<>();
        titles = addTabs();
        fragments = addFragment();
        vp.setOffscreenPageLimit(fragments.size());
        vp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles) {
        });
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(vp);
    }

    public abstract String[] addTabs();

    public abstract ArrayList<Fragment> addFragment();

}
