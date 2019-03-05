package com.prettyyes.user.app.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;

/**
 * Created by chengang on 2018/1/23.
 */

public class GestureLayout extends RelativeLayout {
    private int mDownX;
    private int mDownY;
    private int mTouchSlop;
    private int mTempX;
    private boolean isSilding;
    private int totalMoveX;
    private SwipListener swipeListener;

    public void setSwipeListener(SwipListener swipeListener) {
        this.swipeListener = swipeListener;
    }

    public interface SwipListener{
        void onLeftSwipe();
        void onRightSwipe();
    }
    public GestureLayout(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    public GestureLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    public GestureLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = mTempX = (int) ev.getRawX();
                mDownY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getRawX();
                // 满足此条件屏蔽SildingFinishLayout里面子类的touch事件
                if (Math.abs(moveX - mDownX) > mTouchSlop
                        && Math.abs((int) ev.getRawY() - mDownY) < mTouchSlop) {
                    return true;
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getRawX();
                int deltaX = mTempX - moveX;
//          Log.i("debug", "deltaX:" + deltaX + "mTouchSlop:" + mTouchSlop);
                mTempX = moveX;
                if (Math.abs(moveX - mDownX) > mTouchSlop
                        && Math.abs((int) event.getRawY() - mDownY) < mTouchSlop) {
                    isSilding = true;
                }

                if (Math.abs(moveX - mDownX) >= 0 && isSilding) {
//              mContentView.scrollBy(deltaX, 0);
                    totalMoveX += deltaX;
                }
                break;
            case MotionEvent.ACTION_UP:
                isSilding = false;
//          Log.i("debug", "TotoalMoveX:" + totalMoveX + "viewVidth:" + viewWidth);
                if (Math.abs(totalMoveX) >=100) {
                    if (totalMoveX > 0) {
                        swipeListener.onLeftSwipe();
                    }else {
                        swipeListener.onRightSwipe();
                    }
                }
                totalMoveX = 0;
                break;
        }

        return true;
    }
}
