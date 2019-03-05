package com.prettyyes.user.app.view.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.prettyyes.user.R;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view.app
 * Author: SmileChen
 * Created on: 2016/12/6
 * Description: Nothing
 *
 * 优惠券选择的view
 */
public class SelectView extends View {
    private TickPlusDrawable tickPlusDrawable;
    private int COLOR_SELECT = 0XFF39D4C6;
    private int COLOR_EDGE = 0XFFFFFFFF;

    public SelectView(Context context) {
        super(context);
    }

    public void setSelectColor(int color) {
        this.COLOR_SELECT = color;
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SelectView);

        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.SelectView_select_color) {
                COLOR_SELECT = a.getColor(attr, 0XFF39D4C6);
            } else if (attr == R.styleable.SelectView_edge_color) {
                COLOR_EDGE = a.getColor(attr, 0XFFFFFFFF);
            }
        }
        tickPlusDrawable = new TickPlusDrawable(context.getResources().getDimensionPixelSize(R.dimen.stroke_width), COLOR_SELECT, Color.WHITE, COLOR_SELECT, Color.WHITE
        );
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(tickPlusDrawable);
        } else {
            setBackground(tickPlusDrawable);
        }
    }

    public SelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    public TickPlusDrawable getTickPlusDrawable() {
        return tickPlusDrawable;
    }

    public void setTickPlusDrawable(TickPlusDrawable tickPlusDrawable) {
        this.tickPlusDrawable = tickPlusDrawable;
    }
}
