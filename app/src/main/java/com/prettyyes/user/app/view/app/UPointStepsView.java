package com.prettyyes.user.app.view.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.core.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单步骤
 */

public class UPointStepsView extends View {

    private int currentPosition = 0;
    private int totalCount = 3;
    private float currentX = 0;
    int ringWidth = 4;//圆环宽度
    private int smallRound = 30;//小圆直径
    private int space = 80;
    private List<Float> radio = new ArrayList<>();//长度比例
    private int totalLength;
    private int color_center;

    private List<String> times = new ArrayList<>();


    public UPointStepsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ringWidth = DensityUtil.dip2px(2);
        smallRound = DensityUtil.dip2px(15);
        space = DensityUtil.dip2px(40);
        color_center = ContextCompat.getColor(context, R.color.theme_bggreen);
        init();

    }

    private void init() {
        for (int i = 0; i < totalCount; i++) {
            times.add("");
        }
        radio.add(0.5f);
        radio.add(0.5f);

    }

    public UPointStepsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UPointStepsView(Context context) {
        super(context);
        init();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //   setBackgroundResource(R.color.bg_main);


        totalLength = getMeasuredWidth();
        Paint paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setTextSize(DensityUtil.dip2px(12));
        paintText.setColor(Color.BLACK);

        //画进度条
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        canvas.drawRect(0 + space, getMeasuredHeight() / 3 - 1, getMeasuredWidth() - space, getMeasuredHeight() / 3 + 1, paint);


        for (int i = 0; i <= currentPosition; i++) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(ContextCompat.getColor(getContext(), R.color.theme_bggreen));
            paint.setStrokeWidth(2);

            String text = times.get(i);
            float textWidth = (int) paintText.measureText(text, 0, text.length());

            if (i == totalCount - 1) {
                canvas.drawCircle(currentX - space, getMeasuredHeight() / 3, smallRound / 2, paint);

            } else if (i == 0) {
                canvas.drawCircle(currentX + space, getMeasuredHeight() / 3, smallRound / 2, paint);
            } else {
                canvas.drawCircle(currentX, getMeasuredHeight() / 3, smallRound / 2, paint);
            }

            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(ringWidth);
            paint.setStyle(Paint.Style.STROKE);


            float draw_with = 0;
            if (i == totalCount - 1) {


                canvas.drawCircle(currentX - space, getMeasuredHeight() / 3, smallRound / 2, paint);
                canvas.drawText(times.get(i), doWidth(currentX - space - textWidth / 2,textWidth), getMeasuredHeight() * 2 / 3, paintText);

            } else if (i == 0) {
                canvas.drawCircle(currentX + space, getMeasuredHeight() / 3, smallRound / 2, paint);
                canvas.drawText(times.get(i), doWidth(currentX + space - textWidth / 2,textWidth), getMeasuredHeight() * 2 / 3, paintText);

            } else {
                canvas.drawCircle(currentX, getMeasuredHeight() / 3, smallRound / 2, paint);
                canvas.drawText(times.get(i), doWidth(currentX - textWidth / 2,textWidth), getMeasuredHeight() * 2 / 3, paintText);

            }


            if (i < totalCount - 1) {
                currentX += (radio.get(i) * totalLength);
            }

        }

        for (int i = currentPosition + 1; i < totalCount; i++) {

            String text = times.get(i);
            float textWidth = (int) paintText.measureText(text, 0, text.length());

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(getResources().getColor(R.color.bg_main));
            paint.setStrokeWidth(2);
            if (i == totalCount - 1) {
                canvas.drawCircle(currentX - space, getMeasuredHeight() / 3, smallRound / 2, paint);

            } else {
                canvas.drawCircle(currentX, getMeasuredHeight() / 3, smallRound / 2, paint);


            }

            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(ringWidth);
            paint.setStyle(Paint.Style.STROKE);
            if (i == totalCount - 1) {
                canvas.drawCircle(currentX - space, getMeasuredHeight() / 3, smallRound / 2, paint);
                canvas.drawText(times.get(i), doWidth(currentX - space - textWidth / 2,textWidth), getMeasuredHeight() * 2 / 3, paintText);

            } else {
                canvas.drawCircle(currentX, getMeasuredHeight() / 3, smallRound / 2, paint);
                canvas.drawText(times.get(i), doWidth(currentX - textWidth / 2,textWidth), getMeasuredHeight() * 2 / 3, paintText);


            }

            if (i < totalCount - 1) {
                currentX += (radio.get(i) * totalLength);
            }

        }
        currentX = 0;

    }

    private float doWidth(float width,float textWidth) {
        if (width < 0) {
            width = 0;
        } else if (width+textWidth > getWidth()) {
            width=getWidth()-textWidth;
        }
        return width;

    }


    @Override
    public void invalidate() {
        super.invalidate();
    }

    /**
     * time:点下面的文字集合
     */

    public void setCurrentTv(List<String> time) {
        this.times.clear();
        this.times.addAll(time);
        invalidate();
    }


    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * currentPosition:当前位置
     * totalCount:点的个数
     */
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        radio.clear();
        float len;
        for (int i = 0; i < totalCount - 1; i++) {
            len = (float) 1 / (totalCount - 1);
            radio.add(len);
        }
        invalidate();

    }
}
