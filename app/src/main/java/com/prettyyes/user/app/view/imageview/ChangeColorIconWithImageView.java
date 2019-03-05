package com.prettyyes.user.app.view.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.prettyyes.user.R;


/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view
 * Author: SmileChen
 * Created on: 2016/8/8
 * Description: Nothing
 */
public class ChangeColorIconWithImageView extends ImageView {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint;
    /**
     * 颜色
     */
    private int mColor = 0xFF1BBDDD;
    /**
     * 透明度 0.0-1.0
     */

    private float mAlpha = 0f;


    public ChangeColorIconWithImageView(Context context) {
        super(context);
    }

    /**
     * 初始化自定义属性值
     *
     * @param context
     * @param attrs
     */
    public ChangeColorIconWithImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        startbitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.mipmap.ic_coupon_unuse);
        mPaint = new Paint();


    }

    Bitmap startbitmap;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(startbitmap, 0, 0, null);
        canvas.drawBitmap(startbitmap, 0, 0, mPaint);
    }


    public void setIconAlpha(float alpha) {
        mPaint.setColor(Color.rgb(246, 208, 84));
        mPaint.setAlpha((int) Math.ceil((255 * alpha)));
        invalidateView();
    }

    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }
}
