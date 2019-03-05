package com.prettyyes.user.app.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view
 * Author: SmileChen
 * Created on: 2016/8/9
 * Description: 可以左右点击的viewpager
 */
public class ClipViewPager extends ViewPager {
    private final int touchSlop;

    private boolean misLvScroll;
    private boolean iscanClickOther = true;

    public ClipViewPager(Context context) {
        super(context);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    public ClipViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    public boolean iscanClickOther() {
        return iscanClickOther;
    }

    public void setIscanClickOther(boolean iscanClickOther) {
        this.iscanClickOther = iscanClickOther;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (iscanClickOther) {

            if (ev.getAction() == MotionEvent.ACTION_UP) {
                View view = viewOfClickOnScreen(ev);
                if (view != null) {
                    int index = indexOfChild(view);
                    if (getCurrentItem() != index && getChildCount() > 0) {
                        setCurrentItem(indexOfChild(view));
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private float startY;
    private float startX;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 记录手指按下的位置
                startY = ev.getY();
                startX = ev.getX();
                // 初始化标记
                misLvScroll = false;
                break;
            case MotionEvent.ACTION_MOVE:
                // 如果listView正在拖拽中，那么不拦截它的事件，直接return false；
                if (misLvScroll) {
                    return false;
                }

                // 获取当前手指位置
                float endY = ev.getY();
                float endX = ev.getX();
                float distanceX = Math.abs(endX - startX);
                float distanceY = Math.abs(endY - startY);
                // 如果Y轴位移大于X轴位移，那么将事件交给lv处理。
                if (distanceY > touchSlop && distanceY > distanceX) {
                    misLvScroll = true;
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // 初始化标记
                misLvScroll = false;
                break;
        }
        // 如果是Y轴位移大于X轴，事件交给swipeRefreshLayout处理。
        return super.onInterceptTouchEvent(ev);
    }


    /**
     * @param ev
     * @return
     */
    private View viewOfClickOnScreen(MotionEvent ev) {
        int childCount = getChildCount();
        int[] location = new int[2];
        for (int i = 0; i < childCount; i++) {
            View v = getChildAt(i);
            v.getLocationOnScreen(location);
            int minX = location[0];
            int minY = location[1];
            int maxX = location[0] + v.getWidth();
            int maxY = minY + v.getHeight();
            float x = ev.getRawX();
            float y = ev.getRawY();
            if ((x > minX && x < maxX) && (y > minY && y < maxY)) {
                return v;
            }
        }


        return null;
    }


    /**
     * 利用java反射机制，将自定义Scroll和ViewPager结合来调节ViewPager的滑动效果
     **/
    public void setSpeedScroller(int duration) {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            SpeedScroller scroller = new SpeedScroller(this.getContext(),
                    new AccelerateInterpolator());
            mScroller.set(this, scroller);
            scroller.setmDuration(duration);
        } catch (NoSuchFieldException e) {

        } catch (IllegalArgumentException e) {

        } catch (IllegalAccessException e) {

        }
    }

    private class SpeedScroller extends Scroller {
        private int mDuration = 1500;

        public SpeedScroller(Context context) {
            super(context);
        }

        public SpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public void setmDuration(int time) {
            mDuration = time;
        }

        public int getmDuration() {
            return mDuration;
        }
    }
}
