package com.prettyyes.user.core;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.core.event.MoreReplyAlphaEvent;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.AppRxBus;

import java.lang.ref.WeakReference;

/**
 * Created by cyandev on 2016/12/14.
 */
public class HeaderFloatBehavior extends CoordinatorLayout.Behavior<View> {
    private static final String TAG = "HeaderFloatBehavior";

    private WeakReference<View> dependentView;
    private ArgbEvaluator argbEvaluator;

    public HeaderFloatBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

        argbEvaluator = new ArgbEvaluator();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        LogUtil.i(TAG, "layoutDependsOn");
        if (dependency != null && dependency.getId() == R.id.appbarlayout_more_reply) {
            dependentView = new WeakReference<>(dependency);
            return true;
        }
        return false;
    }

//    @Override
//    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
//        LogUtil.i(TAG,"onNestedPreScroll"+dy);
//        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
//    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        LogUtil.i(TAG, "onDependentViewChanged");

        Resources resources = getDependentView().getResources();
        final float progress = (float) (1.f -
                Math.abs(dependency.getTop() * 1.0 / (dependency.getHeight()-AppUtil.dip2px(44))));
        AppRxBus.getInstance().post(new MoreReplyAlphaEvent(1 - progress));

        // Translation
        final float collapsedOffset = resources.getDimension(R.dimen.collapsed_float_offset_y);
        final float initOffset = offset;
//        final float translateY = collapsedOffset + (initOffset - collapsedOffset) * progress;

        // Background
        child.setBackgroundColor((int) argbEvaluator.evaluate(
                progress,
                resources.getColor(R.color.colorCollapsedFloatBackground),
                resources.getColor(R.color.colorInitFloatBackground)));

//        // Margins
        final float collapsedMargin = resources.getDimension(R.dimen.collapsed_float_margin);
        final float initMargin = resources.getDimension(R.dimen.init_float_margin);
        final int margin = (int) (collapsedMargin + (initMargin - collapsedMargin) * progress);
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();

        int top = dependency.getBottom() - AppUtil.dip2px(44);
        if (top <= 0)
            top = 0;
        lp.setMargins(margin, top, margin, 0);
        child.setLayoutParams(lp);


//        child.setY(dependency.getY()+dependency.getHeight());

        return true;
    }

    public int offset = 0;//初始化的offset

    public void setOffset(int offset) {
        this.offset = offset;
    }

    private View getDependentView() {
        return dependentView.get();
    }

}
