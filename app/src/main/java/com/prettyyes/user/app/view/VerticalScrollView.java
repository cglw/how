package com.prettyyes.user.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by chengang on 2017/8/25.
 *
 */

public class VerticalScrollView extends ScrollView{

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return true;
        }
        return false;
    }

    private static final String TAG = "VerticalScrollView";

    private boolean mCanScroll = true;
    private float mDownY;

    public VerticalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int scrollY = getScrollY();

        Log.e(TAG,"scrollY"+scrollY+"mDownY"+mDownY+" ev.getY() "+ev.getY());
        Log.e(TAG,"child"+getChildAt(0).getHeight()+"getHeight()"+getHeight()+" "+scrollY);

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                //滑到顶部或底部
//                 && mDownY <= ev.getY()
                if ((scrollY == 0 && mDownY < ev.getY())
                        || (getChildAt(0).getHeight() == (scrollY + getHeight()) && mDownY > ev.getY())) {
                    mCanScroll = false;
                }
                else
                    mCanScroll=true;
                Log.e(TAG,"mCanScroll"+mCanScroll);

                break;
            case MotionEvent.ACTION_UP:
                mCanScroll = true;
                break;
            case MotionEvent.ACTION_CANCEL:
                mCanScroll = true;
                break;
        }
        if (mCanScroll) {
            //通知ViewPager不要干扰自身的操作
            getParent().requestDisallowInterceptTouchEvent(true);
            return super.onTouchEvent(ev);
        } else {
            getParent().requestDisallowInterceptTouchEvent(false);
            return false;
        }
    }
}
