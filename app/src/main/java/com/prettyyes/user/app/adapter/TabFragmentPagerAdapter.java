package com.prettyyes.user.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/11/20.
 */

public class TabFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private String[] titles;
    private ArrayList<String> titles_array;
    private ArrayList<Fragment> fragments;

    public TabFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    public TabFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public TabFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> titles_array) {
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

        if (titles != null && titles.length > 0)
            return titles[position];

        if (titles_array != null && titles_array.size() > 0)
            return titles_array.get(position);
        return "";
    }


    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

}
