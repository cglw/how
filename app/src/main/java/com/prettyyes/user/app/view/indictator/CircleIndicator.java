package com.prettyyes.user.app.view.indictator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.prettyyes.user.R;
import com.prettyyes.user.app.view.ClipViewPager;
import com.prettyyes.user.core.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xiayong on 2015/9/29.
 */
public class CircleIndicator extends View {
    private ViewPager viewPager;
    private int vpchildcount = 0;
    private List<ShapeHolder> tabItems;
    private ShapeHolder movingItem;

    //config list
    private int mCurItemPosition;
    private float mCurItemPositionOffset;
    private float mIndicatorRadius;
    private float mIndicatorMargin;
    private int mIndicatorBackground;
    private int mIndicatorSelectedBackground;
    private Gravity mIndicatorLayoutGravity;
    private Mode mIndicatorMode;

    //default value
    private final int DEFAULT_INDICATOR_RADIUS = 10;
    private final int DEFAULT_INDICATOR_MARGIN = 40;
    private final int DEFAULT_INDICATOR_BACKGROUND = Color.BLUE;
    private final int DEFAULT_INDICATOR_SELECTED_BACKGROUND = Color.RED;
    private final int DEFAULT_INDICATOR_LAYOUT_GRAVITY = Gravity.CENTER.ordinal();
    private final int DEFAULT_INDICATOR_MODE = Mode.SOLO.ordinal();

    public boolean isMaxScroll = false;
    private int currentItem;

    public enum Gravity {
        LEFT,
        CENTER,
        RIGHT
    }

    public enum Mode {
        INSIDE,
        OUTSIDE,
        SOLO
    }

    public CircleIndicator(Context context) {
        super(context);
        init(context, null);
    }

    public CircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        tabItems = new ArrayList<>();
        handleTypedArray(context, attrs);
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null)
            return;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicator);
        mIndicatorRadius = typedArray.getDimensionPixelSize(R.styleable.CircleIndicator_ci_radius, DEFAULT_INDICATOR_RADIUS);
        mIndicatorMargin = typedArray.getDimensionPixelSize(R.styleable.CircleIndicator_ci_margin, DEFAULT_INDICATOR_MARGIN);
        mIndicatorBackground = typedArray.getColor(R.styleable.CircleIndicator_ci_background, DEFAULT_INDICATOR_BACKGROUND);
        mIndicatorSelectedBackground = typedArray.getColor(R.styleable.CircleIndicator_ci_selected_background, DEFAULT_INDICATOR_SELECTED_BACKGROUND);
        int gravity = typedArray.getInt(R.styleable.CircleIndicator_ci_gravity, DEFAULT_INDICATOR_LAYOUT_GRAVITY);
        mIndicatorLayoutGravity = Gravity.values()[gravity];
        int mode = typedArray.getInt(R.styleable.CircleIndicator_ci_mode, DEFAULT_INDICATOR_MODE);
        mIndicatorMode = Mode.values()[mode];
        typedArray.recycle();
    }

    public void setViewPager(final ViewPager viewPager) {
        this.vpchildcount = viewPager.getChildCount();
        this.viewPager = viewPager;
        createTabItems();
        createMovingItem();
        setUpListener();
    }

    public void setViewPager(final ViewPager viewPager, int vpchildcount) {
        this.vpchildcount = vpchildcount;
        this.viewPager = viewPager;
        createTabItems();
        createMovingItem();
        setUpListener();
    }

    private void setUpListener() {
        if (!isMaxScroll) {
            viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    if (mIndicatorMode != Mode.SOLO) {
                        trigger(position, positionOffset);
                    }
                }

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    if (mIndicatorMode == Mode.SOLO) {
                        trigger(position, 0);
                    }
                }
            });
        } else {
            viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                    if (position == 0) {
                        currentItem = vpchildcount - 2;
                        positionOffset = 0;
                    } else if (position == vpchildcount - 1) {
                        currentItem = 1;
                        positionOffset = 0;

                    } else {
                        currentItem = position;
                        if (position == vpchildcount - 2) {
                            positionOffset = 0;
                        }

                    }

                    if (mIndicatorMode != Mode.SOLO) {
                        trigger(currentItem - 1, positionOffset);
                    }
                }

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    if (position == 0) {
                        currentItem = vpchildcount - 2;
                    } else if (position == vpchildcount - 1) {
                        currentItem = 1;
                    } else
                        currentItem = position;

                    if (mIndicatorMode == Mode.SOLO) {
                        trigger(currentItem - 1, 0);
                    }
                }
            });

        }
    }

    /**
     * trigger to redraw the indicator when the ViewPager's selected item changed!
     *
     * @param position
     * @param positionOffset
     */
    private void trigger(int position, float positionOffset) {
        CircleIndicator.this.mCurItemPosition = position;
        CircleIndicator.this.mCurItemPositionOffset = positionOffset;
        Log.e("CircleIndicator", "onPageScrolled()" + position + ":" + positionOffset);
        requestLayout();
        invalidate();
    }

    private void createTabItems() {
        int count = 0;
        if (isMaxScroll) {
            count = vpchildcount - 2;
        } else {
            count = vpchildcount;
        }
        for (int i = 0; i < count; i++) {
            OvalShape circle = new OvalShape();
            ShapeDrawable drawable = new ShapeDrawable(circle);
            ShapeHolder shapeHolder = new ShapeHolder(drawable);
            Paint paint = drawable.getPaint();
            paint.setColor(mIndicatorBackground);
            paint.setAntiAlias(true);
            shapeHolder.setPaint(paint);
            tabItems.add(shapeHolder);
        }

    }

    private void createMovingItem() {
        OvalShape circle = new OvalShape();
        ShapeDrawable drawable = new ShapeDrawable(circle);
        movingItem = new ShapeHolder(drawable);
        Paint paint = drawable.getPaint();
        paint.setColor(mIndicatorSelectedBackground);
        paint.setAntiAlias(true);

        switch (mIndicatorMode) {
            case INSIDE:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
                break;
            case OUTSIDE:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
                break;
            case SOLO:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
                break;
        }

        movingItem.setPaint(paint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e("CircleIndicator", "onLayout()");
        super.onLayout(changed, left, top, right, bottom);
        final int width = getWidth();
        final int height = getHeight();
        layoutTabItems(width, height);
        layoutMovingItem(mCurItemPosition, mCurItemPositionOffset);
    }

    private void layoutTabItems(final int containerWidth, final int containerHeight) {
        if (tabItems == null) {
            throw new IllegalStateException("forget to config tabItems?");
        }
        final float yCoordinate = containerHeight * 0.5f;
        final float startPosition = startDrawPosition(containerWidth);
        for (int i = 0; i < tabItems.size(); i++) {
            ShapeHolder item = tabItems.get(i);
            item.resizeShape(2 * mIndicatorRadius, 2 * mIndicatorRadius);
            item.setY(yCoordinate - mIndicatorRadius);
            float x = startPosition + (mIndicatorMargin + mIndicatorRadius * 2) * i;
            item.setX(x);
        }

    }

    private float startDrawPosition(final int containerWidth) {
        if (mIndicatorLayoutGravity == Gravity.LEFT)
            return 0;
        float tabItemsLength = tabItems.size() * (2 * mIndicatorRadius + mIndicatorMargin) - mIndicatorMargin;
        if (containerWidth < tabItemsLength) {
            return 0;
        }
        if (mIndicatorLayoutGravity == Gravity.CENTER) {
            return (containerWidth - tabItemsLength) / 2;
        }
        return containerWidth - tabItemsLength;
    }

    private void layoutMovingItem(final int position, final float positionOffset) {
        if (movingItem == null) {
            return;
            //throw new IllegalStateException("forget to config movingItem?");
        }

        if (tabItems.size() == 0) {
            return;
        }
        ShapeHolder item = tabItems.get(position);
        movingItem.resizeShape(item.getWidth(), item.getHeight());
        float x = item.getX() + (mIndicatorMargin + mIndicatorRadius * 2) * positionOffset;
        movingItem.setX(x);
        movingItem.setY(item.getY());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("CircleIndicator", "onDraw()");
        super.onDraw(canvas);
        int sc = canvas.saveLayer(0, 0, getWidth(), getHeight(), null,
                Canvas.MATRIX_SAVE_FLAG |
                        Canvas.CLIP_SAVE_FLAG |
                        Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                        Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                        Canvas.CLIP_TO_LAYER_SAVE_FLAG);
        for (ShapeHolder item : tabItems) {
            drawItem(canvas, item);
        }

        if (movingItem != null) {
            drawItem(canvas, movingItem);
        }
        canvas.restoreToCount(sc);
    }

    private void drawItem(Canvas canvas, ShapeHolder shapeHolder) {
        canvas.save();
        canvas.translate(shapeHolder.getX(), shapeHolder.getY());
        shapeHolder.getShape().draw(canvas);
        canvas.restore();
    }

    public void setIndicatorRadius(float mIndicatorRadius) {
        this.mIndicatorRadius = mIndicatorRadius;
    }

    public void setIndicatorMargin(float mIndicatorMargin) {
        this.mIndicatorMargin = mIndicatorMargin;
    }

    public void setIndicatorBackground(int mIndicatorBackground) {
        this.mIndicatorBackground = mIndicatorBackground;
    }

    public void setIndicatorSelectedBackground(int mIndicatorSelectedBackground) {
        this.mIndicatorSelectedBackground = mIndicatorSelectedBackground;
    }

    public void setIndicatorLayoutGravity(Gravity mIndicatorLayoutGravity) {
        this.mIndicatorLayoutGravity = mIndicatorLayoutGravity;
    }

    public void setIndicatorMode(Mode mIndicatorMode) {
        this.mIndicatorMode = mIndicatorMode;
    }


    public static void initIndicator(LinearLayout lin, ClipViewPager vp, CircleIndicator.Gravity gravity, Context context, int number) {
        for (int i = 0; i < lin.getChildCount(); i++) {
            if (lin.getChildAt(i) instanceof CircleIndicator) {
                lin.removeView(lin.getChildAt(i));
            }
        }
        CircleIndicator circleIndicator = new CircleIndicator(context);
        lin.addView(circleIndicator);
        LinearLayout.LayoutParams layparam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, DensityUtil.dip2px(context, 6));
        circleIndicator.setIndicatorMargin(DensityUtil.dip2px(context, 7));
        circleIndicator.setIndicatorLayoutGravity(gravity);
        circleIndicator.setIndicatorRadius(DensityUtil.dip2px(context, 3));
        circleIndicator.setLayoutParams(layparam);
        circleIndicator.setIndicatorSelectedBackground(context.getResources().getColor(R.color.black));
        circleIndicator.setIndicatorBackground(Color.rgb(216, 216, 216));
        circleIndicator.setIndicatorMode(CircleIndicator.Mode.OUTSIDE);
        circleIndicator.setViewPager(vp, number);
    }
}
