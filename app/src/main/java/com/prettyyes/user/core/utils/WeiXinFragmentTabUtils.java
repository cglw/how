package com.prettyyes.user.core.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.prettyyes.user.app.view.tvbtnetv.ChangeColorIconWithTextView;

import java.util.ArrayList;
import java.util.List;


public class WeiXinFragmentTabUtils {


    private int pageIndex;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private FragmentPagerAdapter mAdapter;
    private ViewGroup viewGroup;
    private FragmentManager fm;
    private List<ChangeColorIconWithTextView> mTabIndicator = new ArrayList<ChangeColorIconWithTextView>();


    public WeiXinFragmentTabUtils(ViewGroup viewGroup, int pageIndex, ViewPager viewPager, List<Fragment> fragments, FragmentManager fm) {
        this.fm = fm;
        this.viewGroup = viewGroup;
        this.pageIndex = pageIndex;
        this.viewPager = viewPager;
        this.fragments = fragments;
        mTabIndicator = new ArrayList<>();
        initAdapter();
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 0) {
                    ChangeColorIconWithTextView left = mTabIndicator.get(position);
                    ChangeColorIconWithTextView right = mTabIndicator.get(position + 1);

                    left.setIconAlpha(1 - positionOffset);
                    right.setIconAlpha(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initTabIndicator();
    }

    private void initTabIndicator() {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            ChangeColorIconWithTextView x = (ChangeColorIconWithTextView) viewGroup.getChildAt(i);
            x.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetOtherTabs();
                    mTabIndicator.get((Integer) v.getTag()).setIconAlpha(1.0f);
                    viewPager.setCurrentItem((Integer) v.getTag(), false);
                }
            });
            x.setTag(i);
            mTabIndicator.add(x);
            if (i == pageIndex)
                x.setIconAlpha(1.0f);
        }
    }

    private void initAdapter() {
        mAdapter = new MyAdapter(fm);
        viewPager.setAdapter(mAdapter);


    }


    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    /**
     * 重置其他的Tab
     */
    private void resetOtherTabs() {
        for (int i = 0; i < mTabIndicator.size(); i++) {
            mTabIndicator.get(i).setIconAlpha(0);
        }
    }

    /**
     * 切换tab额外功能功能接口
     */
    public static interface OnRgsExtraCheckedChangedListener {
        public void OnRgsExtraCheckedChanged(ViewGroup viewgroup, int checkedId, int index);
    }

}