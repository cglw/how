package com.prettyyes.user.app.mvpPresenter;

import android.support.v4.app.Fragment;

import com.prettyyes.user.app.fragments.OrderListFragment;
import com.prettyyes.user.app.fragments.RewardPaperFragment;
import com.prettyyes.user.app.mvpView.MyOrderView;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpPresenter
 * Author: SmileChen
 * Created on: 2016/11/2
 * Description: Nothing
 */
public class MyOrderPressenter {
    private MyOrderView myOrderView;
    public MyOrderPressenter(MyOrderView myOrderView) {
        this.myOrderView = myOrderView;
    }

    public void getVpData() {
        OrderListFragment orderListFragment = OrderListFragment.newInstance(1);

        OrderListFragment orderListFragment1 = OrderListFragment.newInstance(2);
        OrderListFragment orderListFragment2 = OrderListFragment.newInstance(4);
        OrderListFragment orderListFragmen3 = OrderListFragment.newInstance(3);

        RewardPaperFragment rewardPaperFragment = new RewardPaperFragment();

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(orderListFragment);
        fragments.add(orderListFragment1);
        fragments.add(orderListFragment2);
        fragments.add(orderListFragmen3);
        fragments.add(rewardPaperFragment);
        myOrderView.setVpData(fragments);
    }
}
