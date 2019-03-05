package com.prettyyes.user.app.view.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.prettyyes.user.core.utils.DensityUtil;

/**
 * Created by cc on 2015/12/30.
 */
public class CircleProgressView extends View {

    private static int maxProgress = 100;
    private static int progress = 30;
    private int progressStrokeWidth;
    private static int progressColor = Color.rgb(0, 0, 0);
    private static String text = "";
    RectF oval;
    Paint paint;

    public CircleProgressView(Context context) {
        super(context);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        oval = new RectF();
        paint = new Paint();
        progressStrokeWidth = DensityUtil.dip2px(this.getContext(), 3);


    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        // TODO 32自动生成的方法存根
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();
        if (width != height) {
            int min = Math.min(width, height);
            width = min;
            height = min;
        }
        //#87A3A5
        //#638999
        maxProgress = 100;
        paint.setAntiAlias(true); // 设置画笔为抗锯齿
        paint.setColor(Color.WHITE); // 设置画笔颜色
        canvas.drawColor(Color.TRANSPARENT); // 白色背景
        paint.setStrokeWidth(progressStrokeWidth); //线宽
        paint.setStyle(Paint.Style.STROKE);


        oval.left = progressStrokeWidth / 2;
        oval.top = progressStrokeWidth / 2;
        oval.right = width - progressStrokeWidth / 2;
        oval.bottom = height - progressStrokeWidth / 2;
        paint.setColor(Color.rgb(226, 226, 226));
        canvas.drawArc(oval, -90, 360, false, paint);

        paint.setColor(progressColor);
        canvas.drawArc(oval, -90, ((float) progress / maxProgress) * 360, false, paint);


        paint.setTextSize(DensityUtil.dip2px(this.getContext(), 18));
        float textWidth = (int) paint.measureText(text, 0, text.length());
        paint.setColor(Color.rgb(0, 0, 0));
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text, width / 2 - textWidth / 2, height * 1 / 2 + DensityUtil.dip2px(7), paint);

//        paint.setStrokeWidth(1);
//        String text = progress + "%";
//        paint.setTextSize(DensityUtil.dip2px(this.getContext(), 17));
//        float textWidth = (int) paint.measureText(text, 0, text.length());
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.rgb(252, 188, 21));
//
//        canvas.drawText(text, width / 2 - textWidth / 2, height / 2, paint);
////
//        paint.setTextSize(DensityUtil.dip2px(this.getContext(), 10));
//        text = "好问题";
//        textWidth = (int) paint.measureText(text, 0, text.length());
//        paint.setColor(Color.rgb(155, 155, 155));
//        canvas.drawText(text, width / 2 - textWidth / 2, height * 3 / 4, paint);

    }

    public int getMaxProgress() {
        return maxProgress;
    }


    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }


    public void setProgress(int progress) {
        isstart = false;
        max = progress;
        this.progress = progress;
        this.invalidate();

    }


    private int num = 0;
    private int max = 0;

    public void setProgressNotInUiThread(int progress) {
        max = progress;
        num = 0;
        isstart = true;
        handler.sendEmptyMessageDelayed(1, 0);
    }

    private boolean isstart = true;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                progress = num;
                CircleProgressView.this.invalidate();
                if (num < max) {
                    if (isstart) {
                        num++;
                    } else {
                        num = max;
                    }
//                    handler.sendEmptyMessageDelayed(1, 0);

                }

            }
        }
    };

    public int getProgressColor() {
        return progressColor;
    }


    public void setText(String text) {
        this.text = text;
        this.postInvalidate();

    }

    public void setTextColorProgress(String text, int color, int progress) {
        this.text = text;
        this.progressColor = color;
        this.progress = progress;
        this.postInvalidate();
    }
}
