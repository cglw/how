package com.prettyyes.user.app.fragments;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.TabFragmentPagerAdapter;
import com.prettyyes.user.app.base.BaseFragment;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;



/**
 * Created by chengang on 2017/11/27.
 */

public abstract class MutiTabFragment extends BaseFragment {

    @ViewInject(R.id.tabLayout)
    public TabLayout tabLayout;
    @ViewInject(R.id.vp)
    public ViewPager vp;
    private String[] titles;
    public ArrayList<Fragment> fragments;
    public TabFragmentPagerAdapter tabAdapter;

    @Override
    public int bindLayout() {
        return R.layout.layout_muti_tab;
    }

    @Override
    public void initView(View view) {

    }


    public boolean need_match() {
        return true;
    }

    @Override
    public void doBusiness(Context mContext) {
        fragments = new ArrayList<>();
        titles = addTabs();
        fragments = addFragment();
        vp.setOffscreenPageLimit(fragments.size());
        tabAdapter = new TabFragmentPagerAdapter(getChildFragmentManager(), fragments, titles) {
        };
        vp.setAdapter(tabAdapter);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(vp);
    }


    public abstract String[] addTabs();

    public abstract ArrayList<Fragment> addFragment();


}
