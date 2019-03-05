package com.prettyyes.user.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.prettyyes.user.core.utils.ViewUtils;

/**
 * Created by chengang on 2017/7/5.
 */

public class EditInScrollText extends android.support.v7.widget.AppCompatEditText {
    public EditInScrollText(Context context) {
        super(context);
    }

    public EditInScrollText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTouch();
    }

    private void initTouch() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //触摸的是EditText并且当前EditText可以滚动则将事件交给EditText处理；否则将事件交由其父类处理
                if ((ViewUtils.canVerticalScroll(EditInScrollText.this))) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }

                return false;
            }
        });
    }


}
