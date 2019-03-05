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

import com.prettyyes.user.core.utils.DensityUtil;

/**
 * Created by cc on 2015/12/30.
 */
public class CircleView extends View {

    private int maxProgress = 100;
    private int progress = 0;
    private int progressBig = 0;
    private int progressStrokeWidth;
    private float distancebig;
    private float distance;
    private float radius;
    private float radius_small;

    RectF oval;
    Paint paint;
    private String totalnum="0";

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        oval = new RectF();
        paint = new Paint();
        progressStrokeWidth = DensityUtil.dip2px(this.getContext(), 1);
        float density = context.getResources().getDisplayMetrics().density;
        distancebig = density * 5;
        distance = density * 12;
        radius = density * 5;
        radius_small = density * 3;

    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
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


        RectF ovalbig = new RectF(distancebig, distancebig, getWidth() - distancebig, getHeight() - distancebig);
        paint.setColor(Color.rgb(229, 229, 229));
        canvas.drawArc(ovalbig, -45, 360, false, paint);

        paint.setColor(Color.rgb(126, 244, 243));
        canvas.drawArc(ovalbig, -45, ((float) progressBig / maxProgress) * 360, false, paint);

        canvas.save();
        paint.setStyle(Paint.Style.FILL);
        canvas.rotate(((float) progressBig / maxProgress) * 360 + 45, getWidth() / 2, getHeight() / 2);
        canvas.drawCircle(getWidth() / 2, distancebig, radius, paint);
        canvas.restore();


        paint.setStyle(Paint.Style.STROKE);
        RectF oval = new RectF(distance, distance, getWidth() - distance, getHeight() - distance);
        paint.setColor(Color.rgb(255, 241, 117));
        canvas.drawArc(oval, -45, ((float) progress / maxProgress) * 360, false, paint);


        canvas.save();
        paint.setStyle(Paint.Style.FILL);
        canvas.rotate(((float) progress / maxProgress) * 360 + 45, getWidth() / 2, getHeight() / 2);
        canvas.drawCircle(getWidth() / 2, distance, radius_small, paint);
        canvas.restore();


        int realheight = (int) (height - distancebig * 2);
        paint.setStrokeWidth(1);
        String text = totalnum + "";
        paint.setTextSize(DensityUtil.dip2px(this.getContext(), 48));
        float textWidth = (int) paint.measureText(text, 0, text.length());
        paint.setColor(Color.BLACK);
        canvas.drawText(text, width / 2 - textWidth / 2, distancebig + realheight * 43 / 77, paint);


        String textConetent = "回复数";
        paint.setTextSize(DensityUtil.dip2px(this.getContext(), 17));
        float tvWidth = (int) paint.measureText(textConetent, 0, textConetent.length());
        paint.setColor(Color.BLACK);
        canvas.drawText(textConetent, width / 2 - tvWidth / 2, distancebig + realheight * 56 / 77, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                progress=0;
                progressBig=0;

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
    public void setProgress(int progressBig,int progress,int total)
    {
        this.progressBig=progressBig;
        this.progress=progress;
        this.totalnum=total+"";
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
                CircleView.this.invalidate();
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
}
