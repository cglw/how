package com.prettyyes.user.core;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.prettyyes.user.core.utils.LogUtil;

/**
 * Created by chengang on 2017/5/16.
 */

public class MyBehavior extends AppBarLayout.Behavior {

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private static final String TAG = "MyBehavior";

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        LogUtil.i(TAG, "dy" + dy);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

    }
}
