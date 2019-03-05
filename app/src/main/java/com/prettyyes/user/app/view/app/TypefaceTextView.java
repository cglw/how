package com.prettyyes.user.app.view.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.core.utils.AppUtil;

/**
 * Created by chengang on 2017/4/7.
 * 加载字体的textview
 */

public class TypefaceTextView extends TextView {
    private int type_face;

    public TypefaceTextView(Context context) {
        super(context);

    }

    public TypefaceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeFace(context,attrs);
    }


    private void initTypeFace(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TypefaceTextView);
        type_face = a.getInt(R.styleable.TypefaceTextView_type_face, 2);
        switch (type_face) {
            case 2:
                AppUtil.loadTypaeFace(getContext(), this);
                break;
        }

    }


}
