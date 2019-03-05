package com.prettyyes.user.app.ui.order;

import android.support.v4.app.Fragment;

import com.prettyyes.user.R;
import com.prettyyes.user.app.fragments.SellerOrderListFragment;
import com.prettyyes.user.app.ui.common.MutiTabActivity;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/7/27.
 */

public class SellerOrderListActivity extends MutiTabActivity {

    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle(R.string.title_activity_sellerorderlist);
    }

    @Override
    public String[] addTabs() {
        return new String[]{"全部订单","进行中", "退款中", "已完成"};
    }

    @Override
    public ArrayList<Fragment> addFragment() {
        fragments.add(SellerOrderListFragment.newInstance("6"));
        fragments.add(SellerOrderListFragment.newInstance("1"));
        fragments.add(SellerOrderListFragment.newInstance("5"));
        fragments.add(SellerOrderListFragment.newInstance("3"));
        return fragments;
    }
}
