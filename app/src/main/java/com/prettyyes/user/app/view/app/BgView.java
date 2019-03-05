package com.prettyyes.user.app.view.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.prettyyes.user.R;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view
 * Author: SmileChen
 * Created on: 2016/9/21
 * Description: 页面 的背景view
 */
public class BgView extends View {

    private int color_triangelcolor;
    private int color_bg;
    public BgView(Context context) {
        super(context);
    }

    public BgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BgView);
        color_triangelcolor=a.getColor(R.styleable.BgView_triangelcolor, Color.WHITE);
        color_bg=a.getColor(R.styleable.BgView_bgcolor, Color.WHITE);
    }

    public BgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color_bg);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0,0,getWidth(),getHeight(),paint);
        Path path = new Path();
        path.moveTo(0, getHeight());// 此点为多边形的起点
        path.lineTo(getWidth(), getHeight()/2);
        path.lineTo(getWidth(), getHeight());
        path.close(); // 使这些点构成封闭的多边形
        paint.setColor(color_triangelcolor);
        canvas.drawPath(path, paint);
    }
}
