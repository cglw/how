package com.prettyyes.user.app.view.tvbtnetv;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Cg on 2016/4/8.
 * 判断textView是否显示完整
 */
public class OverFlowedTextView extends TextView {
    public OverFlowedTextView(Context context) {
        super(context);
    }

    public OverFlowedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OverFlowedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private int getAvailableWidth()
    {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }
    public boolean isOverFlowed()
    {
        Paint paint = getPaint();
        float width = paint.measureText(getText().toString());
        if (width > getAvailableWidth()) return true;
        return false;
    }
}
