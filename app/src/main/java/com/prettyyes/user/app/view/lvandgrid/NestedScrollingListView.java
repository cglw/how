package com.prettyyes.user.app.view.lvandgrid;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view.lvandgrid
 * Author: SmileChen
 * Created on: 2016/12/19
 * Description: Nothing
 */
public class NestedScrollingListView  extends ListView implements NestedScrollingChild {

    private final int[] mScrollOffset = new int[2];
    private final int[] mScrollConsumed = new int[2];
    private final int[] mNestedOffsets = new int[2];

    private static final int INVALID_POINTER = -1;

    public static final int SCROLL_STATE_IDLE = 0;

    private int mLastTouchX;
    private int mLastTouchY;

    private int mScrollPointerId = INVALID_POINTER;
    private NestedScrollingChildHelper mNestedScrollingChildHelper;

    public NestedScrollingListView(final Context context) {
        super(context);
        initHelper();

    }

    public NestedScrollingListView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        initHelper();
    }

    public NestedScrollingListView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHelper();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NestedScrollingListView(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initHelper();
    }

    private void initHelper() {
        mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    @Override
    public void setNestedScrollingEnabled(final boolean enabled) {
        mNestedScrollingChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(final int axes) {
        return mNestedScrollingChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        mNestedScrollingChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return mNestedScrollingChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(final int dxConsumed, final int dyConsumed, final int dxUnconsumed, final int dyUnconsumed, final int[] offsetInWindow) {
        return mNestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(final int dx, final int dy, final int[] consumed, final int[] offsetInWindow) {
        return mNestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(final float velocityX, final float velocityY, final boolean consumed) {
        return mNestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(final float velocityX, final float velocityY) {
        return mNestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }
    @Override
    public boolean onTouchEvent(MotionEvent e) { //重写这个方法

        super.onTouchEvent(e);

        final MotionEvent vtev = MotionEvent.obtain(e);
        final int action = MotionEventCompat.getActionMasked(e);
        final int actionIndex = MotionEventCompat.getActionIndex(e);

        if (action == MotionEvent.ACTION_DOWN) {
            mNestedOffsets[0] = mNestedOffsets[1] = 0;
        }
        vtev.offsetLocation(mNestedOffsets[0], mNestedOffsets[1]);

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                mScrollPointerId = MotionEventCompat.getPointerId(e, 0);
                mLastTouchX = (int) (e.getX() + 0.5f);
                mLastTouchY = (int) (e.getY() + 0.5f);

//                getParent().requestDisallowInterceptTouchEvent(true);

                // Clear the nested offsets
                mNestedOffsets[0] = mNestedOffsets[1] = 0;

                int nestedScrollAxis = ViewCompat.SCROLL_AXIS_NONE;

                nestedScrollAxis |= ViewCompat.SCROLL_AXIS_VERTICAL;
                startNestedScroll(nestedScrollAxis);
            } break;

            case MotionEventCompat.ACTION_POINTER_DOWN: {
                mScrollPointerId = MotionEventCompat.getPointerId(e, actionIndex);
                mLastTouchX = (int) (MotionEventCompat.getX(e, actionIndex) + 0.5f);
                mLastTouchY = (int) (MotionEventCompat.getY(e, actionIndex) + 0.5f);
            } break;

            case MotionEvent.ACTION_MOVE: {
                final int index = MotionEventCompat.findPointerIndex(e, mScrollPointerId);

                final int x = (int) (MotionEventCompat.getX(e, index) + 0.5f);
                final int y = (int) (MotionEventCompat.getY(e, index) + 0.5f);
                int dx = mLastTouchX - x;
                int dy = mLastTouchY - y;

                if (dispatchNestedPreScroll(dx, dy, mScrollConsumed, mScrollOffset)) {
                    vtev.offsetLocation(mScrollOffset[0], mScrollOffset[1]);
                    // Updated the nested offsets
                    mNestedOffsets[0] += mScrollOffset[0];
                    mNestedOffsets[1] += mScrollOffset[1];
                }

                mLastTouchX = x - mScrollOffset[0];
                mLastTouchY = y - mScrollOffset[1];
            }
            break;

            case MotionEvent.ACTION_UP:{
                stopNestedScroll();
                break;
            }
        }
        return true;
    }

}
