package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.prettyyes.user.R;

/**
 * Created by chengang on 2017/4/14.
 */

public class BgCoffeeLinearView extends LinearLayout {
    public BgCoffeeLinearView(Context context) {
        super(context);
    }

    public BgCoffeeLinearView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BgCoffeeLinearView);
        color_triangelcolor = a.getColor(R.styleable.BgCoffeeLinearView_ll_triangelcolor,
               Color.BLUE);
        color_bg = a.getColor(R.styleable.BgCoffeeLinearView_ll_bgcolor, Color.WHITE);

    }

    public BgCoffeeLinearView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private int color_triangelcolor;
    private int color_bg;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color_triangelcolor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        Path path = new Path();
        path.moveTo(0, 0);// 此点为多边形的起点
        path.moveTo(getWidth(), 0);// 此点为多边形的起点
        path.lineTo(getWidth(), getHeight() * 1 / 4);
        path.lineTo(0, getHeight() * 1 / 2);
        path.close(); // 使这些点构成封闭的多边形
        paint.setColor(color_bg);
        canvas.drawPath(path, paint);
    }

}
