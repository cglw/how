package com.prettyyes.user.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app
 * Author: SmileChen
 * Created on: 2016/12/6
 * Description: Nothing
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private ArrayList<String> titles_array;
    private ArrayList<Fragment> fragments;

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> titles_array) {
        super(fm);
        this.fragments = fragments;
        this.titles_array = titles_array;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null)
            return titles[position];
        return titles_array.get(position);
    }
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentFragment = (Fragment) object;
        super.setPrimaryItem(container, position, object);
    }

    private Fragment mCurrentFragment;

    public Fragment getmCurrentFragment() {
        return mCurrentFragment;
    }
}
