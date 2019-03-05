package com.prettyyes.user.app.view.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.prettyyes.user.core.utils.DensityUtil;


/**
 * Created by cc on 2015/12/30.
 * 水平进度view
 */
public class HorProgressView extends View {

    private int maxProgress = 100;
    private int progress = 0;
    private int progressStrokeWidth;
    private String showtv="";

    RectF oval;
    Paint paint;

    public HorProgressView(Context context) {

        super(context);
        oval = new RectF();
        paint = new Paint();
        progressStrokeWidth = DensityUtil.dip2px(this.getContext(), 4);
    }

    public HorProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        oval = new RectF();
        paint = new Paint();
        progressStrokeWidth = DensityUtil.dip2px(this.getContext(), 4);


    }

    public HorProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        // TODO 32自动生成的方法存根
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();
        paint.setAntiAlias(true); // 设置画笔为抗锯齿
        paint.setColor(Color.WHITE); // 设置画笔颜色
        canvas.drawColor(Color.TRANSPARENT); // 白色背景
        paint.setStrokeWidth(progressStrokeWidth); //线宽
        RectF rect = new RectF(0, 0, getWidth(), getHeight());
        RectF Progress = new RectF(0, 0, getWidth() * progress / maxProgress, getHeight());
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect, paint);
        paint.setColor(Color.BLACK);
        canvas.drawRect(Progress,paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        String text =showtv;
        paint.setTextSize(DensityUtil.dip2px(this.getContext(), 15));
        float textWidth = (int) paint.measureText(text, 0, text.length());

        Rect targetRect = new Rect(0, 0, getWidth(), getHeight());
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        // 转载请注明出处：http://blog.csdn.net/hursing
        int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawText(text, width / 2 - textWidth / 2,baseline, paint);

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
        showtv=progress+"%";
        this.invalidate();
    }
    public void setTv(String tv) {
        showtv=tv;
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
                HorProgressView.this.invalidate();
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
