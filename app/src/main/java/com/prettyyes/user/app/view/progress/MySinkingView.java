
package com.prettyyes.user.app.view.progress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.core.utils.DensityUtil;

/**
 * 水波浪球形进度View
 *
 * @author chengang
 */
public class MySinkingView extends View {

    /**
     * y= A * sin(w*x + t0) + C
     */
    private Paint mPaint = new Paint();
    private Bitmap mBitmap;
    private Bitmap mScaledBitmap;
    private int offsetX = 0;//每画完一次 ，函数平移
    private int Speed = 4;//每次平移的距离
    private int TextSize = 26;
    private int mPercent = 50;
    private int colorOval;
    private int Res;
    int width;
    int height;

    public MySinkingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setPercent(float percent, int Res, int color) {
        this.mPercent = (int) percent;
        mBitmap = BitmapFactory.decodeResource(getContext().getResources(), Res);
        mScaledBitmap = Bitmap.createScaledBitmap(mBitmap, getWidth(), getHeight(), false);
        mBitmap.recycle();
        mBitmap = null;
        this.Res = Res;
        this.colorOval = color;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
       // refreshCanvas();//平移刷新

    }

    public void clear() {
        if (mScaledBitmap != null) {
            mScaledBitmap.recycle();
            mScaledBitmap = null;
        }
        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    private Canvas canvasAll;


    @Override
    protected void onDraw(Canvas canvas) {
        width = getWidth();
        height = getHeight();
        drawAll(canvas);
        canvasAll = canvas;
        Message message = Message.obtain();
        message.what = 1;
        handler.sendMessageDelayed(message, 50);


    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1)
                refreshCanvas();
        }
    };

    private void drawAll(Canvas canvas) {
        ClipPathToCircle(canvas);//裁剪成圆区域
        drawBitmap(canvas);//画背景图片
        drawText(canvas);//画文字
        drawWhiteArea(canvas);//画白色区域
        drawOval(canvas);//画外面圆环
    }


    private void refreshCanvas() {
        offsetX += Speed;
        if (offsetX >= getWidth()) {
            offsetX = 0;
        }
        invalidate();

        // postInvalidate();
    }

    private Paint ovalPaint;

    private void drawOval(Canvas canvas) {
//        if (ovalPaint == null) {
//            ovalPaint = new Paint();
//            ovalPaint.setStyle(Style.STROKE);
//            ovalPaint.setStrokeWidth(2);
//            ovalPaint.setAntiAlias(true);
//            ovalPaint.setColor(colorOval);
//        }
//        canvas.drawCircle(width / 2, height / 2, width / 2 - 1, ovalPaint);
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);
        mPaint.setColor(colorOval);
        canvas.drawCircle(width / 2, height / 2, width / 2 - 1, mPaint);
    }

    private Paint areaPaint;

    private void drawWhiteArea(Canvas canvas) {
        if (areaPaint == null) {
            areaPaint = new Paint();
            areaPaint.setColor(Color.WHITE);
            areaPaint.setStyle(Style.FILL);
        }
        int startX = 0;
        while (startX < getWidth()) {
            int startY = (int) (10 * Math.sin(2.0 * Math.PI * (startX - offsetX) / getWidth()));


            canvas.drawLine(startX, 0, startX, startY + getYByPercent(mPercent), areaPaint);
            startX++;
        }
    }

    private int getYByPercent(int mPercent) {
        int min = (int) (getHeight() * 0.6);
        int y = 0;
        y = (int) (min + mPercent * 0.3 * getHeight() / 100);


        return getHeight() - y;
    }

    private Paint textPaint;

    private void drawText(Canvas canvas) {
        if (textPaint == null) {
            textPaint = new Paint();
            textPaint.setColor(Color.WHITE);
            textPaint.setTextSize(DensityUtil.dip2px(getContext(), 25));
            textPaint.setStyle(Style.FILL);
        }
        String str = (int) (mPercent) + "%";
        canvas.drawText(str, (getWidth() - textPaint.measureText(str)) / 2, getHeight() / 2 + TextSize / 2, textPaint);
    }


    private void drawBitmap(Canvas canvas) {
        if (mScaledBitmap == null) {
            if (mBitmap == null) {
                mBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.bg_ask);
            }
            mScaledBitmap = Bitmap.createScaledBitmap(mBitmap, getWidth(), getHeight(), false);
            mBitmap.recycle();
            mBitmap = null;
        }
        mPaint.setAntiAlias(true);
        canvas.drawBitmap(mScaledBitmap, 0, 0, mPaint);
    }

    private Path clippath;

    private void ClipPathToCircle(Canvas canvas) {


//        canvas.save();
//        path.reset();
//        canvas.clipPath(path);
        if (clippath == null) {
            clippath = new Path();
            clippath.addCircle(width / 2, height / 2, width / 2, Path.Direction.CCW);
        }
        canvas.clipPath(clippath, Region.Op.REPLACE);
    }

}
