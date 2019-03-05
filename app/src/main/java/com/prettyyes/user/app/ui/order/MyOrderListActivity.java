package com.prettyyes.user.app.ui.order;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.MyFragmentPagerAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpPresenter.MyOrderPressenter;
import com.prettyyes.user.app.mvpView.MyOrderView;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

public class MyOrderListActivity extends BaseActivity implements MyOrderView {


    @ViewInject(R.id.vp_myorderAct_pager)
    private ViewPager vp_order;
    @ViewInject(R.id.tabLayout_myorderAct)
    private TabLayout tabLayout;
    private String[] titles = new String[]{"全部订单","未付款", "已付款", "退款", "所有打赏"};
    private MyOrderPressenter myOrderPressenter = new MyOrderPressenter(this);

    public static int ORDER_UN_PAY = 0;
    public static int ORDER_PAY_SUCCESS = 2;
    public static String ORDER_INDEX = "order";
    private int current_index = 0;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        current_index = getIntent().getIntExtra(ORDER_INDEX, 0);
    }

    @Override
    protected void initViews() {
        setActivtytitle(getString(R.string.title_activity_myorder));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    public static void goOrderListActivity(Context context) {
        Intent i = new Intent(context, MyOrderListActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public static void goOrderListActivity(Context context,int current_index) {
        Intent i = new Intent(context, MyOrderListActivity.class);
        i.putExtra(ORDER_INDEX,current_index);
        context.startActivity(i);
    }


    @Override
    protected void loadData() {
        myOrderPressenter.getVpData();
        vp_order.setCurrentItem(current_index);
    }

    @Override
    public void showFailedError(String tv) {
        showToastShort(tv);
    }

    @Override
    public BaseActivity getThis() {
        return this;
    }

    @Override
    public void setVpData(ArrayList<Fragment> fragments) {
        vp_order.setOffscreenPageLimit(fragments.size());
        vp_order.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles) {
        });
        tabLayout.setupWithViewPager(vp_order);
    }
}
