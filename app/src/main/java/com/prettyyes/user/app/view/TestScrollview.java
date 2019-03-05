package com.prettyyes.user.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by chengang on 2017/8/25.
 */

public class TestScrollview extends ScrollView {
    private static final String TAG = "TestScrollview";

    public TestScrollview(Context context) {
        super(context);
    }

    public TestScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTouch();
    }

    private float lastY = 0;

    private void initTouch() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.e(TAG,  "--->" + v + ">--distance" + event.getY());
                float dy = event.getY();
                float distance = dy - lastY;
                lastY = dy;
                int child_height = getChildAt(0).getHeight();
                int result = (int) (child_height - (getHeight() + getScaleY()));
                Log.e(TAG, result + "--->" + dy + ">--distance" + distance);

                if (distance >= 0) {
                    if (result == (
                            child_height - getHeight())) {
                        requestDisallowInterceptTouchEvent(false);

                    } else {
                        requestDisallowInterceptTouchEvent(true);

                    }
                } else if (distance < 0) {
                    if (result == 0) {
                        requestDisallowInterceptTouchEvent(false);
                    } else {
                        requestDisallowInterceptTouchEvent(true);
                    }
                }


                return false;
            }
        });

    }


}
