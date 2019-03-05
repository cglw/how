package com.prettyyes.user.app.view.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.prettyyes.user.R;

/**
 * Project Name: priceSelect
 * Package Name: com.prettyyes.priceselect
 * Author: SmileChen
 * Created on: 2016/7/28
 * Description: Nothing
 * 价格选择
 */
public class PriceSelectView extends View {
    Paint paint_cicle;//画外圆的画笔
    Paint paint_oval;//画内圆的画笔
    Paint paint_center;//画中间进度
    Paint paint_all;//画整个进度
    Paint paint_text;
    private int height;
    private int width;
    private int lastX;
    private int leftmove = 0;
    private int leftlocation = 0;//左边圆的初始圆心位置
    private int rightlocation = 0;//右边圆的初始圆心位置
    private int rightmove = 0;

    private int r_cicle = 8;//内圆的半径
    private int height_progress = 10;//进度的高度
    private int color_center = getResources().getColor(R.color.price_color);//中间的颜色
    private int color_all = getResources().getColor(R.color.grey_txt);//剩余部分颜色
    private int color_circle_inner = Color.WHITE;//内圆的颜色
    private int color_circlr_big = getResources().getColor(R.color.price_color);//外圆的颜色
    private int color_txt = Color.BLUE;//字的颜色
    private int r_big = r_cicle * 2;//外圆半径
    private int distance = 0;//两个圆的距离
    boolean isfirst = true;//是否是第一次
    private int extral = r_big / 2;


    public PriceSelectView(Context context) {
        super(context);

    }

    public PriceSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PriceSelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        initFirst();
        initPaint();
        drawall(canvas);
        drawOval(canvas);
        drawCenter(canvas);
        drawCircle(canvas);
        // changeCircle();
        super.onDraw(canvas);
    }


//    private void changeCircle() {
//        leftlocation += leftmove;
//        rightlocation += rightmove;
//    }

    private void initFirst() {
        if (isfirst) {
            width = getWidth();
            height = getHeight();
            height_progress = 2 * height / 5 / 2;
            r_cicle = height_progress / 2;
            r_big = height / 4;
            rightlocation = getWidth() - r_big;
            leftlocation = r_big;//圆环加圆
            isfirst = false;

            extral = 0;
            distance = r_big * 2 + 2 * extral;

        }
    }

    private void drawall(Canvas canvas) {
        RectF rect = new RectF();
        rect.set(r_big, height / 2 - height_progress / 2, width - r_big, height / 2 + height_progress / 2);
        canvas.drawRect(rect, paint_all);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(leftlocation, height / 2, r_cicle, paint_cicle);
        canvas.drawCircle(rightlocation, height / 2, r_cicle, paint_cicle);
    }

    private void drawOval(Canvas canvas) {
        canvas.drawCircle(leftlocation, height / 2, r_big, paint_oval);
        canvas.drawCircle(rightlocation, height / 2, r_big, paint_oval);
    }

    private void drawCenter(Canvas canvas) {
        RectF rect = new RectF();
        rect.set(leftlocation + r_cicle, height / 2 - height_progress / 2, rightlocation - r_cicle, height / 2 + height_progress / 2);
        canvas.drawRect(rect, paint_center);
    }

    private void initPaint() {
        if (paint_text == null) {
            paint_text = new Paint();
            paint_text.setAntiAlias(true);
            paint_text.setColor(color_txt);
            paint_text.setTextSize(18);

        }
        if (paint_oval == null) {
            paint_oval = new Paint();
            paint_oval.setAntiAlias(true);
            paint_oval.setStyle(Paint.Style.FILL);
            paint_oval.setStrokeWidth(r_big);
            paint_oval.setColor(color_circlr_big);
        }
        if (paint_cicle == null) {
            paint_cicle = new Paint();
            paint_cicle.setAntiAlias(true);
            paint_cicle.setStyle(Paint.Style.FILL);
            paint_cicle.setStrokeWidth(r_cicle);
            paint_cicle.setColor(color_circle_inner);
        }
        if (paint_center == null) {
            paint_center = new Paint();
            paint_center.setAntiAlias(true);
            paint_center.setStyle(Paint.Style.FILL);
            paint_center.setStrokeWidth(height_progress);
            paint_center.setColor(color_center);
        }
        if (paint_all == null) {
            paint_all = new Paint();
            paint_all.setAntiAlias(true);
            paint_all.setStyle(Paint.Style.FILL);
            paint_all.setStrokeWidth(height_progress);
            paint_all.setColor(color_all);
        }
    }

    private int tag = 2;//左边

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            lastX = (int) event.getX();

            if (lastX <= leftlocation + r_big + extral && lastX >= leftlocation - r_big - extral) {
                tag = 0;
            } else if (lastX <= rightlocation + r_big + extral && lastX >= rightlocation - r_big - extral) {
                tag = 1;
            } else {
                tag = 2;
            }

        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

            if (tag == 2) {
                return true;
            } else {
                if (tag == 0) {
                    leftmove = (int) (event.getX() - lastX);

                    rightmove = 0;
                } else if (tag == 1) {
                    rightmove = (int) (event.getX() - lastX);
                    leftmove = 0;

                }
                lastX = (int) event.getX();
                if (rightlocation + rightmove - (leftlocation + leftmove) >= distance) {
                    if (rightlocation + rightmove <= width - r_big && leftlocation + leftmove >= r_big) {
                        leftlocation += leftmove;
                        rightlocation += rightmove;
                        invalidate();
                        if (callbak != null) {
                            callbak.progress(getLeftCurrent(), getRightCurrent());
                        }
                        //  Log.e("TGA", "重新画--》" + leftmove + "-->" + rightmove);
                    }
                }
            }


        }
        return true;
    }

    public int getLeftCurrent() {
        return (leftlocation - r_big) * 100 / (width - r_big * 2);
    }

    public int getRightCurrent() {
        return (rightlocation - r_big) * 100 / (width - r_big * 2);
    }

    public interface CallBackPro {
        public void progress(int left, int right);
    }

    public CallBackPro callbak;

    public void setOnCallbackProListener(CallBackPro callbackPro) {
        this.callbak = callbackPro;
    }

}
