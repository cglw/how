package com.prettyyes.user.app.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by chengang on 2017/9/21.
 */

public class ShapLinearlayout extends LinearLayout {
    public ShapLinearlayout(Context context) {
        super(context);

    }

    public ShapLinearlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        init(context,attrs);
    }

    public ShapLinearlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
//
//    private GradientDrawable gd;
//    //填充色
//    private int solidColor = 0;
//    //边框色
//    private int strokeColor = 0;
//    //按下填充色
//    private int solidTouchColor = 0;
//    //按下边框色
//    private int strokeTouchColor = 0;
//    //边框宽度
//    private int strokeWith = 0;
//    private int shapeTpe = 0;
//    //按下色
//    private int touchColor = 0;
//    //字体色
//    private Drawable defalut_drawable;
//
//    float dashGap = 0;
//    float dashWidth = 0;
//
//    public void init(Context context, AttributeSet attrs) {
//        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ShapLinearlayout, 0, 0);
//
//        solidColor = ta.getInteger(R.styleable.ShapLinearlayout_solidColor, 0x00000000);
//        strokeColor = ta.getInteger(R.styleable.ShapLinearlayout_strokeColor, 0x00000000);
//
//        solidTouchColor = ta.getInteger(R.styleable.ShapLinearlayout_solidTouchColor, 0x00000000);
//        strokeTouchColor = ta.getInteger(R.styleable.ShapLinearlayout_strokeTouchColor, 0x00000000);
//        touchColor = ta.getInteger(R.styleable.ShapLinearlayout_touchColor, 0x00000000);
//        strokeWith = ta.getInteger(R.styleable.ShapLinearlayout_strokeWith, 0);
//
//        float radius = ta.getDimension(R.styleable.ShapLinearlayout_radius, 0);
//        float topLeftRadius = ta.getDimension(R.styleable.ShapLinearlayout_topLeftRadius, 0);
//        float topRightRadius = ta.getDimension(R.styleable.ShapLinearlayout_topRightRadius, 0);
//        float bottomLeftRadius = ta.getDimension(R.styleable.ShapLinearlayout_bottomLeftRadius, 0);
//        float bottomRightRadius = ta.getDimension(R.styleable.ShapLinearlayout_bottomRightRadius, 0);
//        dashGap = ta.getDimension(R.styleable.ShapLinearlayout_dashGap, 0);
//        dashWidth = ta.getDimension(R.styleable.ShapLinearlayout_dashWidth, 0);
//        defalut_drawable = getBackground();
//
//        shapeTpe = ta.getInt(R.styleable.ShapLinearlayout_shapeTpe, GradientDrawable.RECTANGLE);
//
//
//        gd = new GradientDrawable();
//
//        gd.setShape(shapeTpe);
//        //矩形
//        if (shapeTpe == GradientDrawable.RECTANGLE) {
//            gd.setShape(GradientDrawable.RECTANGLE);
//            if (radius != 0) {
//                gd.setCornerRadius(radius);
//            } else {
//                //分别表示 左上 右上 右下 左下
//                gd.setCornerRadii(new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius, bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius});
//            }
//        }
//
//
//        drowBackgroud(false);
//        ta.recycle();
//    }
//
//    public void setSelection(boolean selection) {
//        drowBackgroud(selection);
//    }
//
//    /**
//     * 设置按下颜色值
//     */
//    private void drowBackgroud(boolean isTouch) {
//        if (isTouch) {
//            if (solidTouchColor != 0)
//                gd.setColor(solidTouchColor);
//
//            if (strokeWith != 0 && strokeTouchColor != 0) {
//                if (dashGap != 0 && dashGap != 0)
//                    gd.setStroke(strokeWith, strokeTouchColor, dashGap, dashGap);
//                else
//                    gd.setStroke(strokeWith, strokeTouchColor);
//            }
//            if (touchColor != 0)
//                setBackgroundColor(touchColor);
//        } else {
//            if (solidColor != 0)
//                gd.setColor(solidColor);
//            else
//                gd.setColor(Color.TRANSPARENT);
//
//            if (strokeWith != 0 && strokeColor != 0) {
//
//                if (dashGap != 0 && dashGap != 0)
//                    gd.setStroke(strokeWith, strokeColor, dashGap, dashGap);
//                else
//                    gd.setStroke(strokeWith, strokeColor);
//            } else
//                gd.setStroke(0, Color.TRANSPARENT);
//
//
//            if (defalut_drawable != null)
//                setBackground(defalut_drawable);
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            setBackground(gd);
//        }
//        postInvalidate();
//
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            drowBackgroud(true);
//        } else {
//            drowBackgroud(false);
//        }
//
//        return super.onTouchEvent(event);
//    }
//
//    //    @Override
////    public boolean onTouchEvent(MotionEvent event) {
////
////        if (event.getAction() == MotionEvent.ACTION_DOWN) {
////            drowBackgroud(true);
////        } else {
////            drowBackgroud(false);
////        }
////
////        return true;
////    }
}
