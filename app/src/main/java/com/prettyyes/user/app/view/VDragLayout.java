package com.prettyyes.user.app.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;


/**
 * 这是一个viewGroup容器，实现上下两个frameLayout拖动切换
 *
 * @author sistone.Zhang
 */
@SuppressLint("NewApi")
public class VDragLayout extends LinearLayout {

    private static final String TAG = "DragLayout3";
    /* 拖拽工具类 */
    public final ViewDragHelper mDragHelper;
    private GestureDetectorCompat gestureDetector;

    /* 上下两个frameLayout，在Activity中注入fragment */
    private int viewHeight;
    private static final int VEL_THRESHOLD = 100; // 滑动速度的阈值，超过这个绝对值认为是上下
    private static final int DISTANCE_THRESHOLD = 100; // 单位是像素，当上下滑动速度不够时，通过这个阈值来判定是应该粘到顶部还是底部
    private int downTop1; // 手指按下的时候，frameView1的getTop值
    private YScrollDetector yScrollDetector;
    private ShowNextPageNotifier nextPageListener; // 手指松开是否加载下一页的notifier
    private float lastY;

    public VDragLayout(Context context) {
        this(context, null);
    }

    public VDragLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VDragLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mDragHelper = ViewDragHelper
                .create(this, 10f, new DragHelperCallback());
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_BOTTOM);
        yScrollDetector = new YScrollDetector();
        gestureDetector = new GestureDetectorCompat(context,
                yScrollDetector);
        setOrientation(VERTICAL);
    }

    public int data_index;
    public int data_count;
    private VerticalScrollView verticalScrollView;


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof VerticalScrollView) {
                verticalScrollView = (VerticalScrollView) childAt;
                break;
            }
        }
    }

    class YScrollDetector extends SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float dx,
                                float dy) {

            // 垂直滑动时dy>dx，才被认定是上下拖动
            return Math.abs(dy) > Math.abs(dx);
        }
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


            // 一个view位置改变，另一个view的位置要跟进
            onViewPosChanged(changedView, dy);
        }

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            // 两个子View都需要跟踪，返回true
            if (child instanceof VerticalScrollView)
                return true;
            return false;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            // 这个用来控制拖拽过程中松手后，自动滑行的速度，暂时给一个随意的数值
            return 1;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            // 滑动松开后，需要向上或者乡下粘到特定的位置
//            animTopOrBottom(releasedChild, yvel);
            int top = releasedChild.getTop();
            Log.e(TAG, "onViewReleased" + top + "data_count-->" + data_count + "-->data_index" + data_index);

            if (nextPageListener != null) {
                if (releasedChild.getTop() < -AppUtil.dip2px(16)) {
                    if (data_index == data_count - 1) {
                        if (mDragHelper.smoothSlideViewTo(releasedChild, 0, 0)) {
                            ViewCompat.postInvalidateOnAnimation(VDragLayout.this);
                        }//
                        return;
                    }
                    nextPageListener.onDragNext(releasedChild);
                    lastY=0;
                    if (mDragHelper.smoothSlideViewTo(releasedChild, 0, top)) {
                        ViewCompat.postInvalidateOnAnimation(VDragLayout.this);
                    }//
                    return;

                } else if (releasedChild.getTop() > AppUtil.dip2px(16)) {

                    if (data_index == 0) {
                        if (mDragHelper.smoothSlideViewTo(releasedChild, 0, 0)) {
                            ViewCompat.postInvalidateOnAnimation(VDragLayout.this);
                        }//
                        return;

                    }
                    nextPageListener.onDragBefore(releasedChild);
                    lastY=0;
                    if (mDragHelper.smoothSlideViewTo(releasedChild, 0, top)) {
                        ViewCompat.postInvalidateOnAnimation(VDragLayout.this);
                    }//                    mDragHelper.settleCapturedViewAt(0, top);
                    return;
                }
            }

            if (mDragHelper.smoothSlideViewTo(releasedChild, 0, 0)) {
                ViewCompat.postInvalidateOnAnimation(VDragLayout.this);
            }//
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            int finalTop = top;


////            // 拖动的时第一个view
//            if (top > 0) {
//                // 不让第一个view往下拖，因为顶部会白板
//                finalTop = AppUtil.dip2px(200);
//            } else if (top < 0) {
//                // 不让第二个view网上拖，因为底部会白板
//                finalTop = -AppUtil.dip2px(200);
//            }
            // finalTop代表的是理论上应该拖动到的位置。此处计算拖动的距离除以一个参数(3)，是让滑动的速度变慢。数值越大，滑动的越慢
            return child.getTop() + (finalTop - child.getTop()) / 5;
        }
    }


    /**
     * 滑动时view位置改变协调处理
     *
     * @param view 滑动view的index(1或2)
     * @param dy   滑动View的top位置
     */
    private void onViewPosChanged(View view, int dy) {
//        if (viewIndex == 1) {
//            int offsetTopBottom = viewHeight + frameView1.getTop()
//                    - frameView2.getTop();
//            frameView2.offsetTopAndBottom(offsetTopBottom);
//        } else if (viewIndex == 2) {
//            int offsetTopBottom = frameView2.getTop() - viewHeight
//                    - frameView1.getTop();

        View top = getChildAt(0);
        View next = getChildAt(2);
        if (top != null)
            top.offsetTopAndBottom(dy);
        if (next != null)
            next.offsetTopAndBottom(dy);
        // 有的时候会默认白板，这个很恶心。后面有时间再优化
        invalidate();
    }

    private void animTopOrBottom(View releasedChild, float yvel) {
        int finalTop = 0; // 默认是粘到最顶端


        int top = releasedChild.getTop();
        Log.e(TAG, "top" + top);

        // 拖动第一个view松手
        if (top < 0) {
            // 向上的速度足够大，就滑动到顶端
            // 向上滑动的距离超过某个阈值，就滑动到顶端
            finalTop = 0;

            // 下一页可以初始化了
            if (null != nextPageListener) {
                nextPageListener.onDragNext(releasedChild);
            }
        }


        // 拖动第二个view松手
        if (top > 0) {
            // 保持原地不动
            finalTop = 0;
            if (null != nextPageListener) {
                nextPageListener.onDragNext(releasedChild);
            }
        }


//        finalTop=-top;
        if (mDragHelper.smoothSlideViewTo(releasedChild, 0, finalTop)) {
//            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* touch事件的拦截与处理都交给mDraghelper来处理 */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {


        boolean yScroll = gestureDetector.onTouchEvent(ev);
        boolean shouldIntercept = false;
        try {
            shouldIntercept = mDragHelper.shouldInterceptTouchEvent(ev);
        } catch (Exception e) {
        }


        return shouldIntercept;
    }


    public boolean canScroll(MotionEvent event) {

        float dy = event.getY();
        float distance = dy - lastY;
        lastY = dy;
        //滚动的距离
        int scrollY = verticalScrollView.getScrollY();
        int scrollH = verticalScrollView.getHeight();
        //控件内容的总高度
        int scrollRange = verticalScrollView.getChildAt(0).getHeight() - scrollH;
        //控件实际显示的高度

        LogUtil.i(TAG, "distance" + distance + "-->scrollY" + scrollY +"-->range"+scrollRange+ "-->H" + scrollH + "-->child" + verticalScrollView.getChildAt(0).getHeight());


        //下啦
        if (distance > 0) {

            if (scrollY > 0)
                return true;
            else
                return false;

        }
        //上啦
        else if (distance < 0) {
            if (scrollY < scrollRange) {
                return true;
            }
            return false;

        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (canScroll(e)) {
            return false;
        }
        // 统一交给mDragHelper处理，由DragHelperCallback实现拖动效果
        mDragHelper.processTouchEvent(e); // 该行代码可能会抛异常，正式发布时请将这行代码加上try catch
        return true;

    }
//
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        // 只在初始化的时候调用
//        // 一些参数作为全局变量保存起来
//
//        if (getChildAt(0).getTop() == 0) {
//            // 只在初始化的时候调用
//            // 一些参数作为全局变量保存起来
//            getChildAt(0).layout(l, 0, r, b - t);
//
//            viewHeight = getChildAt(0).getMeasuredHeight();
//        } else {
//            // 如果已被初始化，这次onLayout只需要将之前的状态存入即可
//            getChildAt(0).layout(l, getChildAt(0).getTop(), r, getChildAt(0).getBottom());
//        }
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//                MeasureSpec.AT_MOST);
////        super.onMeasure(widthMeasureSpec, expandSpec);
//
//        measureChildren(widthMeasureSpec, expandSpec);
//
//        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
//        int maxHeight = MeasureSpec.getSize(heightMeasureSpec);
//        setMeasuredDimension(
//                resolveSizeAndState(maxWidth, widthMeasureSpec, 0),
//                resolveSizeAndState(maxHeight, heightMeasureSpec, 0));
//    }

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

    public void setNextPageListener(ShowNextPageNotifier nextPageListener) {
        this.nextPageListener = nextPageListener;
    }

    public interface ShowNextPageNotifier {
        void onDragNext(View view);

        void onDragBefore(View view);
    }
}
