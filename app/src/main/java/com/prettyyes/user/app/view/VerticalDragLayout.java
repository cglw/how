package com.prettyyes.user.app.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.prettyyes.user.core.utils.AppUtil;


/**
 * 这是一个viewGroup容器，实现上下两个frameLayout拖动切换
 *
 * @author sistone.Zhang
 */
@SuppressLint("NewApi")
public class VerticalDragLayout extends LinearLayout {

    private static final String TAG = "DragLayout";
    /* 拖拽工具类 */
    private final ViewDragHelper mDragHelper;

    //    private int viewHeight = 2400;
    private static  int VEL_THRESHOLD = 100; // 滑动速度的阈值，超过这个绝对值认为是上下
    private static final int DISTANCE_THRESHOLD = 100; // 单位是像素，当上下滑动速度不够时，通过这个阈值来判定是应该粘到顶部还是底部

    private ShowNextPageNotifier nextPageListener; // 手指松开是否加载下一页的notifier

    public VerticalDragLayout(Context context) {
        this(context, null);
    }

    public VerticalDragLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalDragLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mDragHelper = ViewDragHelper
                .create(this, 10f, new DragHelperCallback());
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_BOTTOM);
        setOrientation(VERTICAL);
        VEL_THRESHOLD = AppUtil.dip2px(30);
    }


    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /**
     * 这是拖拽效果的主要逻辑
     */
    private class DragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public void onViewPositionChanged(View changedView, int left, int top,
                                          int dx, int dy) {
            onViewPosChanged(indexOfChild(changedView), dy, top);
        }


        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            // 两个子View都需要跟踪，返回true
            return true;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            // 这个用来控制拖拽过程中松手后，自动滑行的速度，暂时给一个随意的数值
            return 2;
        }


        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            // 滑动松开后，需要向上或者乡下粘到特定的位置
            animTopOrBottom(releasedChild, yvel);
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            int finalTop = top;
            Log.e(TAG, data_page_index + "data_page_index" + "-->top" + top + "dy" + dy);

            if (data_page_index == data_count - 1 && data_page_index == 0) {
                finalTop = 0;
            } else if (data_page_index == data_count - 1) {
                // 上啦
                if (top < 0) {
                    // 不让第二个view网上拖，因为底部会白板
                    finalTop = 0;
                }

            } else if (data_page_index == 0) {
                // 下啦
                if (top > 0) {
                    // 不让第一个view往下拖，因为顶部会白板
                    finalTop = 0;
                }
            }
            Log.e(TAG, (child.getTop() + (finalTop - child.getTop()) / 3) + "cd");

            // finalTop代表的是理论上应该拖动到的位置。此处计算拖动的距离除以一个参数(3)，是让滑动的速度变慢。数值越大，滑动的越慢
            return (int) (child.getTop() + (finalTop - child.getTop()));
        }
    }


    public void moveTopostion(final int data_page_index) {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                openSizeChanged = true;

                VerticalDragLayout.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (data_page_index == 0)
                    return;
                VerticalDragLayout.this.data_page_index = data_page_index;
                View childAt = getChildAt(0);
                int finalTop = -childAt.getMeasuredHeight();
                mDragHelper.smoothSlideViewTo(childAt, 0, finalTop);

            }
        });
    }

    public int bottom = 0;


    public int data_page_index = 0;
    public int data_count = 0;
    public boolean needlayout = true;
    public boolean openSizeChanged = false;

    /**
     * 滑动时view位置改变协调处理
     *
     * @param viewIndex 滑动view的index(1或2)
     * @param dy        滑动View的top位置
     */
    private void onViewPosChanged(int viewIndex, int dy, int top) {
        for (int i = 0; i < getChildCount(); i++) {
            if (i != viewIndex)
                getChildAt(i).offsetTopAndBottom(dy);
        }
    }

    public void animTopOrBottom(View releasedChild, float yvel) {


        Log.e(TAG, "yvel" + yvel + "getBottom" + getBottom() + " __?>");
        int top = releasedChild.getTop();
        int finalTop = 0; // 默认是粘到最顶端
        int releasedChildIndex = indexOfChild(releasedChild);

        Log.e(TAG, "yvel" + yvel + "getBottom" + releasedChild.getBottom() + "bottom" + bottom + " __?>" + (bottom - VEL_THRESHOLD));
        Log.e(TAG, "top" + releasedChild.getTop());

        // 拖动第一个view松手，上啦
        if (top < 0) {


            View childAt = getChildAt(releasedChildIndex);

            if (childAt.getBottom() < bottom - VEL_THRESHOLD) {

                finalTop = -getChildAt(releasedChildIndex).getHeight();
                if (data_page_index < data_count - 1)
                    data_page_index++;
                else
                    return;


                if (data_page_index >= data_count)
                    return;
                if (null != nextPageListener) {
                    nextPageListener.onDragNext(releasedChildIndex, data_page_index);
                }
                if (mDragHelper.smoothSlideViewTo(releasedChild, 0, finalTop)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
            }
//            else
//            {
//                                finalTop = -top;
//
//                if (mDragHelper.smoothSlideViewTo(releasedChild, 0, finalTop)) {
//                    ViewCompat.postInvalidateOnAnimation(this);
//                }
//            }

//            else if (childAt.getBottom() < bottom && childAt.getBottom() >= bottom - VEL_THRESHOLD) {
//                finalTop = -top;
//
//                if (mDragHelper.smoothSlideViewTo(releasedChild, 0, finalTop)) {
//                    ViewCompat.postInvalidateOnAnimation(this);
//                }
//
//            }


        }
        //下啦
        else if (top > 0) {

            View childAt = getChildAt(releasedChildIndex - 1);
            Log.e(TAG, "childAt" + childAt.getBottom() + "VEL_THRESHOLD" + VEL_THRESHOLD);

            if (childAt == null)
                return;

            if (childAt.getBottom() > VEL_THRESHOLD) {
                finalTop = childAt.getHeight();
                data_page_index--;
                if (null != nextPageListener) {
                    nextPageListener.onDragBack(releasedChildIndex, data_page_index);
                }
                if (mDragHelper.smoothSlideViewTo(releasedChild, 0, finalTop)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }

            }
//            else if (childAt.getBottom() > 0 && childAt.getBottom() <= VEL_THRESHOLD) {
//                finalTop = -top;
//                if (mDragHelper.smoothSlideViewTo(releasedChild, 0, finalTop)) {
//                    ViewCompat.postInvalidateOnAnimation(this);
//                }
//            }
        }

    }

    /* touch事件的拦截与处理都交给mDraghelper来处理 */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "onInterceptTouchEvent" + mDragHelper.shouldInterceptTouchEvent(ev));

        return mDragHelper.shouldInterceptTouchEvent(ev);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (openSizeChanged)
            needlayout = false;

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (needlayout) {
            super.onLayout(changed, l, t, r, b);
        } else {
            needlayout = true;
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);

        measureChildren(widthMeasureSpec, expandSpec);
//
//
//        // 获取宽-测量规则的模式和大小
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//
//        // 获取高-测量规则的模式和大小
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        // 设置wrap_content的默认宽 / 高值
//        // 默认宽/高的设定并无固定依据,根据需要灵活设置
//        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
//        int mWidth = 400;
//        int mHeight = 400;
//
//        // 当布局参数设置为wrap_content时，设置默认值
//        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
//            setMeasuredDimension(mWidth, mHeight);
//            // 宽 / 高任意一个布局参数为= wrap_content时，都设置默认值
//        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
//            setMeasuredDimension(mWidth, heightSize);
//        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
//            setMeasuredDimension(widthSize, mHeight);
//        }


        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int maxHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(
                resolveSizeAndState(maxWidth, widthMeasureSpec, 0),
                resolveSizeAndState(maxHeight, heightMeasureSpec, 0));
    }


    /**
     * 这是View的方法，该方法不支持android低版本（2.2、2.3）的操作系统，所以手动复制过来以免强制退出
     */
    public static int resolveSizeAndState(int size, int measureSpec,
                                          int childMeasuredState) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
                if (specSize < size) {
                    result = specSize | MEASURED_STATE_TOO_SMALL;
                } else {
                    result = size;
                }
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result | (childMeasuredState & MEASURED_STATE_MASK);
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        mDragHelper.processTouchEvent(e); // 该行代码可能会抛异常，正式发布时请将这行代码加上try catch
        return true;
    }

    public void setNextPageListener(ShowNextPageNotifier nextPageListener) {
        this.nextPageListener = nextPageListener;
    }

    public interface ShowNextPageNotifier {
        void onDragNext(int index, int data_index);

        void onDragBack(int index, int data_index);
    }


}
