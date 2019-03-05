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
 * Project Name: test
 * Package Name: com.cg.test
 * Author: SmileChen
 * Created on: 2016/10/17
 * Description: 三角形
 * @author chengang
 */
public class TriangleView extends View {
    private int color;
    private int direction;

    public TriangleView(Context context) {
        super(context);
    }

    public TriangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TriangleView);
        color = a.getColor(R.styleable.TriangleView_draw_color, Color.WHITE);
        direction = a.getInt(R.styleable.TriangleView_direction, 0);
        a.recycle();
    }

    public TriangleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    Paint paint;

    private void initPaint() {
        if (paint == null) {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
        }
    }

    Path path;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (path == null) {
            path = new Path();
        }
        path.reset();
        initPaint();
        paint.setColor(color);

        switch (direction) {
            default:
                break;
            //方向朝左
            case 0:
                // 此点为多边形的起点
                path.moveTo(0, getHeight() / 2);
                path.lineTo(1.732f * getHeight() / 2, 0);
                path.lineTo(1.732f * getHeight() / 2, getHeight());
                break;
            //方向朝上
            case 1:
                // 此点为多边形的起点
                path.moveTo(getWidth() / 2, getHeight() - 1.732f * getHeight() / 2);
                path.lineTo(0, getHeight());
                path.lineTo(getWidth(), getHeight());

                break;
            //方向朝右
            case 2:
                // 此点为多边形的起点
                path.moveTo(1.732f * getWidth() / 2, getHeight() / 2);
                path.lineTo(0, 0);
                path.lineTo(0, getHeight());
                break;
            //方向朝下
            case 3:
                // 此点为多边形的起点
                path.moveTo(getWidth() / 2, getHeight());
                path.lineTo(0, getHeight() - 1.732f * getHeight() / 2);
                path.lineTo(getWidth(), getHeight() - 1.732f * getHeight() / 2);
                break;
            case 4:
                // 此点为多边形的起点
                path.moveTo(getWidth(), getHeight());
                path.lineTo(0, getHeight());
                path.lineTo(getWidth(), 0);
                break;
            case 5:
                // 此点为多边形的起点
                path.moveTo(getWidth(), 0);
                path.lineTo(0, getHeight());
                path.lineTo(getWidth(), getHeight());
                break;
        }
        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, paint);
    }
}
