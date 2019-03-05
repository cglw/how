package com.prettyyes.user.app.view.app;


import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.Property;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.prettyyes.user.core.utils.DensityUtil;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

public class TickPlusDrawable extends Drawable {

    private static final long ANIMATION_DURATION = 1000;
    private static final Interpolator ANIMATION_INTERPOLATOR = new DecelerateInterpolator();

    private Paint mLinePaint;
    private Paint mBackgroundPaint;
    private Paint mProgressPaint;
    private Paint mWholePaint;

    private float[] mPoints = new float[8];
    private final RectF mBounds = new RectF();

    private boolean mTickMode;
    private boolean ishaveClick = false;
    private ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();

    private float mRotation;
    private float mRadius;

    private int mStrokeWidth = 2;
    private int mTickColor = Color.BLUE;
    private int mPlusColor = Color.WHITE;
    private float progressStrokeWidth = 4.15f;
    private float relprogressStrokeWidth = 1f;
    private int mSelectColor = Color.RED;
    private int mUnselectColor = Color.WHITE;
    RectF oval = new RectF();
    Rect all;

    public TickPlusDrawable() {
        this(10, Color.BLUE, Color.WHITE);
    }

    public TickPlusDrawable(int strokeWidth, int tickColor, int plusColor) {
        mStrokeWidth = strokeWidth;
        mTickColor = tickColor;
        mPlusColor = plusColor;

        setupPaints();
    }

    public TickPlusDrawable(int strokeWidth, int tickColor, int plusColor, int selectColor, int unselectColor) {
        mStrokeWidth = strokeWidth;
        mTickColor = tickColor;
        mPlusColor = plusColor;
        mSelectColor = selectColor;
        mUnselectColor = unselectColor;
        setupPaints();
    }

    private void setupPaints() {
        mLinePaint = new Paint(ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(mPlusColor);
        mLinePaint.setStrokeWidth(mStrokeWidth);
        mLinePaint.setStrokeCap(Paint.Cap.ROUND);

        mBackgroundPaint = new Paint(ANTI_ALIAS_FLAG);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setColor(mTickColor);
        progressStrokeWidth = DensityUtil.dip2px(progressStrokeWidth);
        relprogressStrokeWidth = DensityUtil.dip2px(relprogressStrokeWidth);

        mProgressPaint = new Paint(ANTI_ALIAS_FLAG);
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setColor(mSelectColor);
        mProgressPaint.setStrokeWidth(relprogressStrokeWidth);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);

        mWholePaint = new Paint(ANTI_ALIAS_FLAG);
        mWholePaint.setAntiAlias(true);
        mProgressPaint.setStrokeWidth(relprogressStrokeWidth);
        mWholePaint.setColor(mUnselectColor);
        mWholePaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        Log.e("TAG", "onBoundsChange");
        super.onBoundsChange(bounds);
        all = bounds;

        oval.left = progressStrokeWidth / 2;
        oval.top = progressStrokeWidth / 2;
        oval.right = bounds.right - progressStrokeWidth / 2;
        oval.bottom = bounds.bottom - progressStrokeWidth / 2;
        Log.e("TAG", "h->" + (bounds.top - bounds.bottom) + "w-->" + (bounds.right - bounds.left));

        int padding = (int) (bounds.centerX() / 1.5);
        Log.e("TAG", "Padding-->" + padding);
        mBounds.left = bounds.left + padding;
        mBounds.right = bounds.right - padding;
        mBounds.top = bounds.top + padding;
        mBounds.bottom = bounds.bottom - padding;

        setupPlusMode();
    }

    private void setupPlusMode() {
        mPoints[0] = mBounds.left;
        mPoints[1] = mBounds.centerY();
        mPoints[2] = mBounds.right;
        mPoints[3] = mBounds.centerY();
        mPoints[4] = mBounds.centerX();
        mPoints[5] = mBounds.top;
        mPoints[6] = mBounds.centerX();
        mPoints[7] = mBounds.bottom;
    }

    private float x(int pointIndex) {
        return mPoints[xPosition(pointIndex)];
    }

    private float y(int pointIndex) {
        return mPoints[yPosition(pointIndex)];
    }

    private int xPosition(int pointIndex) {
        return pointIndex * 2;
    }

    private int yPosition(int pointIndex) {
        return xPosition(pointIndex) + 1;
    }

    @Override
    public void draw(Canvas canvas) {
        Log.e("TAG", "draw");


        //选中勾
        if (ishaveClick)
            if (mTickMode) {
                // mWholePaint.setColor(mUnselectColor);
                mProgressPaint.setColor(mSelectColor);
            } else {
                // mWholePaint.setColor(mSelectColor);
                mProgressPaint.setColor(mSelectColor);
                mProgressPaint.setStrokeWidth(relprogressStrokeWidth);
                canvas.drawArc(oval, -90, 360, false, mProgressPaint);
                mProgressPaint.setColor(mUnselectColor);
            }

        canvas.drawCircle(mBounds.centerX(), mBounds.centerY(), mBounds.centerX() - progressStrokeWidth, mBackgroundPaint);
        canvas.save();
        canvas.rotate(180 * mRotation, (x(0) + x(1)) / 2, (y(0) + y(1)) / 2);
        canvas.drawLine(x(0), y(0), x(1), y(1), mLinePaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(180 * mRotation, (x(2) + x(3)) / 2, (y(2) + y(3)) / 2);
        canvas.drawLine(x(2), y(2), x(3), y(3), mLinePaint);
        canvas.restore();


        if (mTickMode) {
            mProgressPaint.setStrokeWidth(relprogressStrokeWidth);
            canvas.drawArc(oval, -90, (mRotation / 1) * 360, false, mProgressPaint);
        } else {
            mProgressPaint.setStrokeWidth(progressStrokeWidth);
            canvas.drawArc(oval, -90, (mRotation / 1) * 360, false, mProgressPaint);
        }

    }

    public void setSelect() {
        mTickMode = true;
        animateTickWithShortTime();
        mTickMode = true;
        ishaveClick = true;
    }
    public void setUnSelect() {
        mTickMode = false;
        animatePlusWithShortTime();
        mTickMode = false;
        ishaveClick = true;
    }

    public void toggle() {
        if (mTickMode) {
            animatePlus();
        } else {
            animateTick();
        }
        mTickMode = !mTickMode;
        ishaveClick = true;
    }

    public void toggle(boolean start) {
        if (start == mTickMode)
            return;
        if (start) {
            animateTick();
        } else {
            animatePlus();
        }
        mTickMode = start;
        ishaveClick = true;
    }
    public void toggleNotime(boolean start) {
        if (start == mTickMode)
            return;
        if (start) {
            animateTickWithShortTime();
        } else {
            animatePlusWithShortTime();
        }
        mTickMode = start;
        ishaveClick = true;
    }

    public void animateTick() {
        Log.e("TAG", "animateTick");

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(this, mPropertyPointAX, mBounds.left),
                ObjectAnimator.ofFloat(this, mPropertyPointAY, mBounds.centerY()),

                ObjectAnimator.ofFloat(this, mPropertyPointBX, mBounds.centerX()),
                ObjectAnimator.ofFloat(this, mPropertyPointBY, mBounds.bottom),

                ObjectAnimator.ofFloat(this, mPropertyPointCX, mBounds.right),
                ObjectAnimator.ofFloat(this, mPropertyPointCY, mBounds.centerX() / 2),

                ObjectAnimator.ofFloat(this, mPropertyPointDX, mBounds.centerX()),
                ObjectAnimator.ofFloat(this, mPropertyPointDY, mBounds.bottom),
                ObjectAnimator.ofFloat(this, mRotationProperty, 0f, 1f),
                ObjectAnimator.ofObject(this, mLineColorProperty, mArgbEvaluator, mTickColor),
                ObjectAnimator.ofObject(this, mBackgroundColorProperty, mArgbEvaluator, Color.WHITE)

        );
        set.setDuration(ANIMATION_DURATION);
        set.setInterpolator(ANIMATION_INTERPOLATOR);
        set.start();
    }

    public void animateTickWithShortTime() {
        Log.e("TAG", "animateTick");

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(this, mPropertyPointAX, mBounds.left),
                ObjectAnimator.ofFloat(this, mPropertyPointAY, mBounds.centerY()),

                ObjectAnimator.ofFloat(this, mPropertyPointBX, mBounds.centerX()),
                ObjectAnimator.ofFloat(this, mPropertyPointBY, mBounds.bottom),

                ObjectAnimator.ofFloat(this, mPropertyPointCX, mBounds.right),
                ObjectAnimator.ofFloat(this, mPropertyPointCY, mBounds.centerX() / 2),

                ObjectAnimator.ofFloat(this, mPropertyPointDX, mBounds.centerX()),
                ObjectAnimator.ofFloat(this, mPropertyPointDY, mBounds.bottom),
                ObjectAnimator.ofFloat(this, mRotationProperty, 0f, 1f),
                ObjectAnimator.ofObject(this, mLineColorProperty, mArgbEvaluator, mTickColor),
                ObjectAnimator.ofObject(this, mBackgroundColorProperty, mArgbEvaluator, Color.WHITE)

        );
        set.setDuration(1);
        set.setInterpolator(ANIMATION_INTERPOLATOR);
        set.start();
    }


    public void animatePlus() {
        Log.e("TAG", "animatePlus");

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(this, mPropertyPointAX, mBounds.left),
                ObjectAnimator.ofFloat(this, mPropertyPointAY, mBounds.centerY()),

                ObjectAnimator.ofFloat(this, mPropertyPointBX, mBounds.right),
                ObjectAnimator.ofFloat(this, mPropertyPointBY, mBounds.centerY()),

                ObjectAnimator.ofFloat(this, mPropertyPointCX, mBounds.centerX()),
                ObjectAnimator.ofFloat(this, mPropertyPointCY, mBounds.top),

                ObjectAnimator.ofFloat(this, mPropertyPointDX, mBounds.centerX()),
                ObjectAnimator.ofFloat(this, mPropertyPointDY, mBounds.bottom),

                ObjectAnimator.ofFloat(this, mRotationProperty, 0f, 1f),
                ObjectAnimator.ofObject(this, mLineColorProperty, mArgbEvaluator, Color.WHITE),
                ObjectAnimator.ofObject(this, mBackgroundColorProperty, mArgbEvaluator, mTickColor)
        );
        set.setDuration(ANIMATION_DURATION);
        set.setInterpolator(ANIMATION_INTERPOLATOR);
        set.start();
    }
    public void animatePlusWithShortTime() {
        Log.e("TAG", "animatePlus");

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(this, mPropertyPointAX, mBounds.left),
                ObjectAnimator.ofFloat(this, mPropertyPointAY, mBounds.centerY()),

                ObjectAnimator.ofFloat(this, mPropertyPointBX, mBounds.right),
                ObjectAnimator.ofFloat(this, mPropertyPointBY, mBounds.centerY()),

                ObjectAnimator.ofFloat(this, mPropertyPointCX, mBounds.centerX()),
                ObjectAnimator.ofFloat(this, mPropertyPointCY, mBounds.top),

                ObjectAnimator.ofFloat(this, mPropertyPointDX, mBounds.centerX()),
                ObjectAnimator.ofFloat(this, mPropertyPointDY, mBounds.bottom),

                ObjectAnimator.ofFloat(this, mRotationProperty, 0f, 1f),
                ObjectAnimator.ofObject(this, mLineColorProperty, mArgbEvaluator, Color.WHITE),
                ObjectAnimator.ofObject(this, mBackgroundColorProperty, mArgbEvaluator, mTickColor)
        );
        set.setDuration(1);
        set.setInterpolator(ANIMATION_INTERPOLATOR);
        set.start();
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    private Property<TickPlusDrawable, Integer> mBackgroundColorProperty = new Property<TickPlusDrawable, Integer>(Integer.class, "bg_color") {
        @Override
        public Integer get(TickPlusDrawable object) {
            return object.mBackgroundPaint.getColor();
        }

        @Override
        public void set(TickPlusDrawable object, Integer value) {
            object.mBackgroundPaint.setColor(value);
        }
    };

    private Property<TickPlusDrawable, Integer> mLineColorProperty = new Property<TickPlusDrawable, Integer>(Integer.class, "line_color") {
        @Override
        public Integer get(TickPlusDrawable object) {
            return object.mLinePaint.getColor();
        }

        @Override
        public void set(TickPlusDrawable object, Integer value) {
            object.mLinePaint.setColor(value);
        }
    };

    private Property<TickPlusDrawable, Float> mRotationProperty = new Property<TickPlusDrawable, Float>(Float.class, "rotation") {
        @Override
        public Float get(TickPlusDrawable object) {
            return object.mRotation;
        }

        @Override
        public void set(TickPlusDrawable object, Float value) {
            object.mRotation = value;
        }
    };

    private Property<TickPlusDrawable, Float> mCircleProperty = new Property<TickPlusDrawable, Float>(Float.class, "rotation") {
        @Override
        public Float get(TickPlusDrawable object) {
            return object.mRadius;
        }

        @Override
        public void set(TickPlusDrawable object, Float value) {
            object.mRadius = value;
        }
    };


    private PointProperty mPropertyPointAX = new XPointProperty(0);
    private PointProperty mPropertyPointAY = new YPointProperty(0);
    private PointProperty mPropertyPointBX = new XPointProperty(1);
    private PointProperty mPropertyPointBY = new YPointProperty(1);
    private PointProperty mPropertyPointCX = new XPointProperty(2);
    private PointProperty mPropertyPointCY = new YPointProperty(2);
    private PointProperty mPropertyPointDX = new XPointProperty(3);
    private PointProperty mPropertyPointDY = new YPointProperty(3);

    private abstract class PointProperty extends Property<TickPlusDrawable, Float> {

        protected int mPointIndex;

        private PointProperty(int pointIndex) {
            super(Float.class, "point_" + pointIndex);
            mPointIndex = pointIndex;
        }
    }

    private class XPointProperty extends PointProperty {

        private XPointProperty(int pointIndex) {
            super(pointIndex);
        }

        @Override
        public Float get(TickPlusDrawable object) {
            Log.e("TAG", "X-->" + mPointIndex + "--" + object.x(mPointIndex));

            return object.x(mPointIndex);
        }

        @Override
        public void set(TickPlusDrawable object, Float value) {

            object.mPoints[object.xPosition(mPointIndex)] = value;
            Log.e("TAG", "invalidateSelf");

            invalidateSelf();
        }
    }

    /**
     * 获取执行后的Y
     */
    private class YPointProperty extends PointProperty {

        private YPointProperty(int pointIndex) {
            super(pointIndex);
        }

        @Override
        public Float get(TickPlusDrawable object) {
            Log.e("TAG", "Y-->" + mPointIndex + "--" + object.y(mPointIndex));
            return object.y(mPointIndex);
        }

        @Override
        public void set(TickPlusDrawable object, Float value) {
            object.mPoints[object.yPosition(mPointIndex)] = value;
            invalidateSelf();
        }
    }
}
