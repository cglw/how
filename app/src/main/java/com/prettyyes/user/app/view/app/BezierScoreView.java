package com.prettyyes.user.app.view.app;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zouxiang on 2016/9/22.
 */

public class BezierScoreView extends View {
    private float lineSmoothness = 0.2f;
    private List<Point> mPointList = new ArrayList<>();
    private Path mPath;
    private Path mAssistPath;
    private float drawScale = 1f;
    private PathMeasure mPathMeasure;
    private float defYAxis = 700f;
    private float defXAxis = 10f;
    private float rdius = 3.5f;
    private float bottom_heigh = 30f;
    private float tv_margin_bottom = 10f;

    public BezierScoreView(Context context) {
        super(context);
    }

    public BezierScoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setPointList(List<Point> pointList) {


        mPointList = pointList;
        Log.e("TAG", "getHeight()" + getHeight());


    }

    private List<Float> points;

    public void setPoints(List<Float> points) {
        this.points = points;

        postInvalidate();

//        
//

//        for (int i = 0; i < ; i++) {
//         Point point=new Point();
//
//        }
        Log.e("TAG", "getHeight()" + getHeight());


    }

    public void setLineSmoothness(float lineSmoothness) {
        if (lineSmoothness != this.lineSmoothness) {
            this.lineSmoothness = lineSmoothness;
            measurePath();
            postInvalidate();
        }
    }

    public void setDrawScale(float drawScale) {
        this.drawScale = drawScale;
        postInvalidate();
    }

    public void startAnimation(long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "drawScale", 0f, 1f);
        animator.setDuration(duration);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        if (mPointList == null)
//            return;
        width = getWidth();
        Log.e("onDraw", getWidth() + "-->");
        height = getHeight();
//
        measurePath();
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        //绘制辅助线
//        canvas.drawPath(mAssistPath,paint);

        paint.setColor(Color.WHITE);
        Path dst = new Path();
        dst.rLineTo(0, 0);
        drawLocation(canvas);
        float distance = mPathMeasure.getLength() * drawScale;
        if (mPathMeasure.getSegment(0, distance, dst, true)) {
            //绘制线
            canvas.drawPath(dst, paint);
            float[] pos = new float[2];
            mPathMeasure.getPosTan(distance, pos, null);
            //绘制阴影
//            drawShadowArea(canvas, dst, pos);
            //绘制点
//            drawPoint(canvas,pos);
        }
        /*greenPaint.setPathEffect(getPathEffect(mPathMeasure.getLength()));
        canvas.drawPath(mPath, greenPaint);*/
        //mPath.reset();adb shell screenrecord --bit-rate 2000000 /sdcard/test.mp4

    }

    private void drawLocation(Canvas c) {

        int tv_height = getHeight() - dip2px(tv_margin_bottom);
        int line_height = getHeight() - dip2px(bottom_heigh);
        int rel_width = width - 2 * dip2px(rdius);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        Path path = new Path();

        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < 13; i++) {
            if (i == 0)

                paint.setStrokeWidth(2);
            else
                paint.setStrokeWidth(1);
            path.moveTo(dip2px(rdius) + i * rel_width / 12, dip2px(2 * rdius));
            path.lineTo(dip2px(rdius) + i * rel_width / 12, line_height);
            path.close();
            paint.setStyle(Paint.Style.STROKE);

            c.drawPath(path, paint);

            paint.setStrokeWidth(1);
            paint.setStyle(Paint.Style.FILL);
            c.drawCircle(dip2px(rdius) + i * rel_width / 12, line_height, dip2px(rdius), paint);

            if (i == 0)
                c.drawCircle(dip2px(rdius) + i * rel_width / 12, dip2px(rdius), dip2px(rdius), paint);

            paint.setTextSize(dip2px(9));

            if (i > 0) {
                String textConetent = i + "月";
                float tvWidth = (int) paint.measureText(textConetent, 0, textConetent.length());
                c.drawText(textConetent, dip2px(rdius) + i * rel_width / (12) - rel_width / 24 - tvWidth / 2, tv_height, paint);
            }


        }
    }

    /**
     * 绘制阴影
     *
     * @param canvas
     * @param path
     * @param pos
     */
    private void drawShadowArea(Canvas canvas, Path path, float[] pos) {
        path.lineTo(pos[0], defYAxis);
        path.lineTo(defXAxis, defYAxis);
        path.close();
        Paint paint = new Paint();
        //设置渐变
        LinearGradient linearGradient = new LinearGradient(pos[0], defYAxis, defXAxis, defYAxis, Color.parseColor("#00000000"), Color.parseColor("#FDB4A2"), Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0x88CCCCCC);
        canvas.drawPath(path, paint);
    }

    /**
     * 绘制点
     *
     * @param canvas
     * @param pos
     */
    private void drawPoint(Canvas canvas, final float[] pos) {
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        redPaint.setStrokeWidth(3);
        redPaint.setStyle(Paint.Style.FILL);
        for (Point point : mPointList) {
            if (point.x > pos[0]) {
                break;
            }
            canvas.drawCircle(point.x, point.y, 10, redPaint);
        }
    }

    private PathEffect getPathEffect(float length) {
        return new DashPathEffect(new float[]{length * drawScale, length}, 0);
    }

    private void measurePath() {
        if (mPointList.size() > 0)
            return;
        data_change();
        mPath = new Path();
        mAssistPath = new Path();
        float prePreviousPointX = Float.NaN;
        float prePreviousPointY = Float.NaN;
        float previousPointX = Float.NaN;
        float previousPointY = Float.NaN;
        float currentPointX = Float.NaN;
        float currentPointY = Float.NaN;
        float nextPointX;
        float nextPointY;

        final int lineSize = mPointList.size();
        for (int valueIndex = 0; valueIndex < lineSize; ++valueIndex) {
            if (Float.isNaN(currentPointX)) {
                Point point = mPointList.get(valueIndex);
                currentPointX = point.x;
                currentPointY = point.y;
            }
            if (Float.isNaN(previousPointX)) {
                //是否是第一个点
                if (valueIndex > 0) {
                    Point point = mPointList.get(valueIndex - 1);
                    previousPointX = point.x;
                    previousPointY = point.y;
                } else {
                    //是的话就用当前点表示上一个点
                    previousPointX = currentPointX;
                    previousPointY = currentPointY;
                }
            }

            if (Float.isNaN(prePreviousPointX)) {
                //是否是前两个点
                if (valueIndex > 1) {
                    Point point = mPointList.get(valueIndex - 2);
                    prePreviousPointX = point.x;
                    prePreviousPointY = point.y;
                } else {
                    //是的话就用当前点表示上上个点
                    prePreviousPointX = previousPointX;
                    prePreviousPointY = previousPointY;
                }
            }

            // 判断是不是最后一个点了
            if (valueIndex < lineSize - 1) {
                Point point = mPointList.get(valueIndex + 1);
                nextPointX = point.x;
                nextPointY = point.y;
            } else {
                //是的话就用当前点表示下一个点
                nextPointX = currentPointX;
                nextPointY = currentPointY;
            }

            if (valueIndex == 0) {
                // 将Path移动到开始点
                mPath.moveTo(currentPointX, currentPointY);
                mAssistPath.moveTo(currentPointX, currentPointY);
            } else {
                // 求出控制点坐标
                final float firstDiffX = (currentPointX - prePreviousPointX);
                final float firstDiffY = (currentPointY - prePreviousPointY);
                final float secondDiffX = (nextPointX - previousPointX);
                final float secondDiffY = (nextPointY - previousPointY);
                final float firstControlPointX = previousPointX + (lineSmoothness * firstDiffX);
                final float firstControlPointY = previousPointY + (lineSmoothness * firstDiffY);
                final float secondControlPointX = currentPointX - (lineSmoothness * secondDiffX);
                final float secondControlPointY = currentPointY - (lineSmoothness * secondDiffY);
                //画出曲线
                mPath.cubicTo(firstControlPointX, firstControlPointY, secondControlPointX, secondControlPointY,
                        currentPointX, currentPointY);
                //将控制点保存到辅助路径上
                mAssistPath.lineTo(firstControlPointX, firstControlPointY);
                mAssistPath.lineTo(secondControlPointX, secondControlPointY);
                mAssistPath.lineTo(currentPointX, currentPointY);
            }

            // 更新值,
            prePreviousPointX = previousPointX;
            prePreviousPointY = previousPointY;
            previousPointX = currentPointX;
            previousPointY = currentPointY;
            currentPointX = nextPointX;
            currentPointY = nextPointY;
        }
        mPathMeasure = new PathMeasure(mPath, false);
    }

    int height = 0;
    int width = 0;

    private void data_change() {

        int bottom_height = dip2px(30 + 7);
        int top_height = dip2px(7);
        int real_height = height - bottom_height - top_height;
        if (points == null)
            points = new ArrayList<>();
        mPointList = new ArrayList<>();

        if (points.size() <= 0)
            return;
        Float data = 2 * Collections.max(points);

        for (int i = 0; i < points.size(); i++) {

            if (points.get(i) < 0)
                points.set(i, 0f);
            int h = (int) (top_height + (1 - (points.get(i) / data)) * real_height);
            int w = dip2px(3.5) + i * width / 12;
            Log.e("h", "h" + h + "-->w" + w);
            Point point = new Point();
            point.set(w, h);
            mPointList.add(point);
        }


    }

    public int dip2px(double dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
