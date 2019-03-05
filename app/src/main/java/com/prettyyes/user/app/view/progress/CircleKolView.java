package com.prettyyes.user.app.view.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cc on 2015/12/30.
 */
public class CircleKolView extends View {

    private int maxProgress = 100;
    private double progress = 0;
    private double progressBig = 0;
    private float progressStrokeWidth;
    private int progressStrokeBigWidth;
    private int reply = 0;
    private float distance;


    RectF oval;
    Paint paint;
    private String totalnum = "0";

    public CircleKolView(Context context) {
        super(context);
    }

    public CircleKolView(Context context, AttributeSet attrs) {
        super(context, attrs);
        oval = new RectF();
        paint = new Paint();
        progressStrokeWidth = (int) dip2px(this.getContext(), 8);
        progressStrokeBigWidth = (int) dip2px(this.getContext(), 13);
        distance = progressStrokeBigWidth - progressStrokeWidth;


    }

    public CircleKolView(Context context, AttributeSet attrs, int defStyleAttr) {
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


        RectF ovalbig1 = new RectF(progressStrokeBigWidth / 2, progressStrokeBigWidth / 2, getWidth() - progressStrokeBigWidth / 2, getHeight() - progressStrokeBigWidth / 2);
        paint.setColor(Color.rgb(229, 229, 229));
        canvas.drawArc(ovalbig1, 0, 360, false, paint);
//

        //黄色的
        canvas.save();
        RectF oval = new RectF(progressStrokeBigWidth / 2, progressStrokeBigWidth / 2, getWidth() - progressStrokeBigWidth / 2, getHeight() - progressStrokeBigWidth / 2);
        paint.setColor(Color.rgb(255, 241, 117));
        canvas.drawArc(oval, -45, ((float) (progress + progressBig) / maxProgress) * 360, false, paint);
        canvas.restore();


        canvas.save();
        paint.setStrokeWidth(progressStrokeBigWidth);
        RectF ovalbig = new RectF(progressStrokeBigWidth / 2, progressStrokeBigWidth / 2, getWidth() - progressStrokeBigWidth / 2, getHeight() - progressStrokeBigWidth / 2);
        paint.setColor(Color.rgb(178, 235, 242));
        canvas.drawArc(ovalbig, -45, ((float) progressBig / maxProgress) * 360, false, paint);
        canvas.restore();


        paint.setStyle(Paint.Style.FILL);
        if (progress > 0) {
            //黄色的圆
            canvas.save();
            paint.setStrokeWidth((float) (progressStrokeWidth / 2));
            paint.setColor(Color.rgb(255, 241, 117));

            canvas.rotate(((float) (progress + progressBig) / maxProgress) * 360 + 45, getWidth() / 2, getHeight() / 2);
            canvas.drawCircle(getWidth() / 2, progressStrokeBigWidth / 2, (float) (progressStrokeWidth / 2), paint);
            canvas.restore();

        }


        paint.setStrokeWidth(progressStrokeBigWidth / 2);
        paint.setColor(Color.rgb(178, 235, 242));
        if (progressBig > 0) {
            //绿色的圆
            canvas.save();
            canvas.rotate(((float) (progressBig) / maxProgress) * 360 + 45, getWidth() / 2, getHeight() / 2);
            canvas.drawCircle(getWidth() / 2, progressStrokeBigWidth / 2, progressStrokeBigWidth / 2, paint);
            canvas.restore();
        }

        if (progressBig > 0) {
            canvas.save();
            canvas.rotate(45, getWidth() / 2, getHeight() / 2);
            canvas.drawCircle(getWidth() / 2, progressStrokeBigWidth / 2, progressStrokeBigWidth / 2, paint);
            canvas.restore();
        }


        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(dip2px(getContext(), 17));

        String text = new StringBuilder().append(reply).toString();
        float textWidth = (int) paint.measureText(text, 0, text.length());
        canvas.drawText(text, width / 2 - textWidth / 2, height / 2 - dip2px(getContext(), 10), paint);


        String textConetent = "回复数";
        float tvWidth = (int) paint.measureText(textConetent, 0, textConetent.length());
        canvas.drawText(textConetent, width / 2 - tvWidth / 2, height / 2 + dip2px(getContext(), 25), paint);

        paint.setStrokeWidth(3);
        canvas.drawLine(getWidth() / 4, getHeight() / 2, 3 * getWidth() / 4, getHeight() / 2, paint);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                progress = 0;
                progressBig = 0;

                break;
        }

        return true;
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

    public void setProgress(double progressBig, double progress, int total) {
        this.progressBig = progressBig;
        this.progress = progress;
        this.totalnum = total + "";
        this.reply = total;
        this.invalidate();

    }


    public void setProgressBig(int progressBig) {
        this.progressBig = progressBig;
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
                CircleKolView.this.invalidate();
                if (num < max) {
                    if (isstart) {
                        num++;
                    } else {
                        num = max;
                    }
                    handler.sendEmptyMessageDelayed(1, 0);

                }

            }
        }
    };

    public static float dip2px(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return pxValue * scale;
    }

}
