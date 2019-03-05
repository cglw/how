package com.prettyyes.user.app.view.lvandgrid;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/1/21.
 * 可以实现复杂的item 特效
 */

public class NbLayoutManager extends RecyclerView.LayoutManager {
    protected float offset = 0; //总偏移量
    private ArrayList<Property> propertySparseArray = new ArrayList<>();
    private SparseArray<Integer> topArrary = new SparseArray<>();
    //存放
    private SparseBooleanArray itemAttached = new SparseBooleanArray();
    private SparseArray<Float> itemsOffset = new SparseArray<>();
    private static int MAX_ITEM_COUNT = 100;


    private ArrayList<View> viewcache = new ArrayList<>();


    private int firstmargintop = 40; // Size of each items
    protected int mChildWidth;
    protected int mChildHeight;
    //子view距离屏幕最左的偏移，也可以理解为第一个子view在初始状态下距离屏幕左侧的位移，默认居中
    protected int startLeft;
    //子view距离屏幕最左的偏移，也可以理解为第一个子view在初始状态下距离屏幕左侧的位移，默认居中
    protected int startTop;

    private int itemSpace = 20;//下面item的间距
    private int bigPageTop = 60;//显示的page距离顶部的距离
    public float pageOffset;//每活动多少距离 切换一页
    private int topMove = 0;
    private int topnums = 3;
    private float topscale = 0.9f;
    private float bottomscale = 0.8f;
    private float splitscale = 0.2f;
    private float bottomalphaChange = 0.1f;

    //Flags of scroll dirction
    private static int SCROLL_TOP = 1;//下拉
    private static int SCROLL_BOTTOM = 2;//上滑
    private static int CURRENT_DIR = 1;//下拉
    private int minChildcout = 0;
    private Property zeroProperty;

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        zeroProperty = new Property();
        zeroProperty.with = 0;
        zeroProperty.bottom = 0;
        zeroProperty.height = 0;
        zeroProperty.alpha = 0;
        zeroProperty.top = 0;


        if (getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
            offset = 0;
            return;
        }
        if (getChildCount() == 0) {
            //获得
            View scrap = recycler.getViewForPosition(0);
            addView(scrap);
            // Size of each items
            measureChildWithMargins(scrap, 0, 0);

            mChildWidth = getDecoratedMeasuredWidth(scrap);
            mChildHeight = getDecoratedMeasuredHeight(scrap);
            //开始距离左边的
            //开始距离左边的
            startLeft = (getHorizontalSpace() - mChildWidth) / 2;
            startTop = 0;
            //测量

            pageOffset = (float) (mChildHeight * 3 / 5);
            Log.e("TAG", "pageoffset" + pageOffset + "  getVerticalSpace-->" + getVerticalSpace());
            topMove = bigPageTop / 3;

            detachAndScrapView(scrap, recycler);
            //初始化
        }

        //预先获取每个view的属性
        for (int i = 0; i < getItemCount(); i++) {
            //初始化状态
            itemAttached.put(i, false);
            itemsOffset.put(i, i * pageOffset);
        }
        detachAndScrapAttachedViews(recycler);
        preDrawViews();

        //绘制
        handleOutOfRange();
        offset = -3*pageOffset;

        layoutItems(recycler, state, SCROLL_BOTTOM);
        minChildcout = getChildCount();

    }

    @Override
    public void scrollToPosition(int position) {
        if (position < 0 || position > getItemCount() - 1) return;
        //判断方向
        float targetRotate = position * pageOffset;
        if (targetRotate == offset) return;
        offset = targetRotate;
        //处理边界
        handleOutOfRange();
        //重新绘制
        requestLayout();
    }


    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int willScroll = dy;
        //滑动距离px
        float realDy = dy / getDistanceRatio();
        //算出来的偏移量

        float targetOffset = offset + realDy;
        Log.e("TAG", targetOffset + "offset--->getChildCount" + "max-->" + getMaxOffset());

        //handle the boundary实际偏移量
        if (targetOffset < getMinOffset()) {
            willScroll = 0;
        } else if (targetOffset > getMaxOffset()) {
            willScroll = 0;
        }
        realDy = willScroll / getDistanceRatio();
        //实际偏移量
        offset += realDy;
        float alpha = 0;
        int top = 0;
        int h = 0;
        int w = 0;
        int b = 0;
        int j = 0;


        for (int i = 0; i < getChildCount(); i++) {

            View scrap = getChildAt(i);


//                float target = itemsOffset.get(i) - offset;
//                Log.e("TAG", "i-->" + i + "  target" + target);
//
//                j = (int) (targetOffset / pageOffset);
//                //float more=
//
//                // Log.e("TAG", "num1  " + (int) (itemsOffset.get(i) / pageOffset) + "num2  " + (int) (offset / pageOffset) + "  pageoffset" + pageOffset);
//
//                int index = 0;
//                float more = 0;
//                if (offset <= 0) {
//                    index = j + 2;
//                } else {
//                    index = i + 1;
//                }
//                more = realDy;
//
//                top = (int) getValue(index, "top", more);
//                h = (int) getValue(index, "h", more);
//                w = (int) getValue(index, "w", more);
//                b = (int) getValue(index, "b", more);
//                alpha = getValue(index, "alpha", more);
            // layoutScrap(scrap, top, h, w, b, alpha);
            int position = getPosition(scrap);

            layoutScrapByTargetOffset(scrap, itemsOffset.get(position) - offset);


        }

        if (dy < 0)
            layoutItems(recycler, state, SCROLL_TOP);
        else
            layoutItems(recycler, state, SCROLL_BOTTOM);
        //caculateDraw(recycler, state);
        return willScroll;
    }

    private float getA(int i) {
        return offset - i * pageOffset;
    }


    private float getValue(int i, String type, float more) {


        Property propertA = null;
        Property propertB = null;
        propertA = propertySparseArray.get(i);


        if (i > 0) {
            propertB = propertySparseArray.get(i - 1);
        } else {
            propertB = zeroProperty;

        }
        float a = more / pageOffset;
        Log.e("TAG", "more-->" + more);

        float value = 0;
        switch (type) {
            case "top":
                value = propertA.getTop() + (propertB.getTop() - propertA.getTop()) * a;
                break;
            case "h":
                value = propertA.getHeight() + (propertB.getHeight() - propertA.getHeight()) * a;
                break;
            case "w":
                value = propertA.getWith() + (propertB.getWith() - propertA.getWith()) * a;
                break;
            case "b":
                value = propertA.getBottom() + (propertB.getBottom() - propertA.getBottom()) * a;
                break;
            case "alpha":
                value = propertA.getAlpha() + (propertB.getAlpha() - propertA.getAlpha()) * a;
                break;
        }
        return value;
    }


    private static int MAX_DISPLAY_ITEM_COUNT = 100;


    private void preDrawViews() {
        int top = 0;
        int h = 0;
        int w = 0;
        int b = 0;
        float alpha = 1;
        int W = mChildWidth;
        int H = mChildHeight;
        propertySparseArray.clear();
        for (int i = 0; i < 30; i++) {
            if (i <= topnums) {
                alpha = (float) (1 - ((topnums - i) * 1.0 / (topnums + 1)));
                top = (int) (bigPageTop * (1 - ((topnums - i) * 1.0 / topnums)));
                h = (int) (H * Math.pow(topscale, (topnums - i)));
                w = (int) (W * Math.pow(topscale, (topnums - i)));
            } else if (i == topnums + 1) {
                alpha = 1 - (i - topnums) * bottomalphaChange;
                top = itemSpace + H + bigPageTop;
                h = (int) (H * splitscale);
                w = (int) (w * bottomscale);
            } else if (i > topnums + 1) {
                alpha = propertySparseArray.get(i - 1).getAlpha() - bottomalphaChange;
                h = (int) (propertySparseArray.get(i - 1).getHeight() * bottomscale);
                w = (int) (propertySparseArray.get(i - 1).getWith() * bottomscale);
                top = propertySparseArray.get(i - 1).getHeight() + propertySparseArray.get(i - 1).getTop() + itemSpace;
            }
            b = top + h;
            Property p = new Property();
            p.alpha = alpha;
            p.top = top;
            p.bottom = b;
            p.with = w;
            p.height = h;
            propertySparseArray.add(p);
        }
        Property p0 = new Property();
        p0.alpha = 0;
        p0.top = 0;
        p0.with = (int) (W * Math.pow(topscale, topnums));
        p0.height = (int) (H * Math.pow(topscale, topnums));
        p0.bottom = b;

        propertySparseArray.add(0, p0);


    }

    private void layoutItems(RecyclerView.Recycler recycler,
                             RecyclerView.State state, int dir) {
        //在布局的时候
        if (state.isPreLayout()) return;

//        //remove the views which out of range
        //Log.e("TAG", offset + "offset--->getChildCount" + getChildCount() + "dy-->" + dy + " 4page" + 4 * pageOffset);
        float alpha = 0;
        int top = 0;
        int h = 0;
        int w = 0;
        int b = 0;
        int j = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view != null) {
                int position = getPosition(view);
                if (removeCondition(itemsOffset.get(position) - offset)) {
                    itemAttached.put(position, false);
                    removeAndRecycleView(view, recycler);
                }
            }
        }
        int begin = getCurrentPosition() - MAX_ITEM_COUNT / 2;
        int end = getCurrentPosition() + MAX_ITEM_COUNT / 2;
        if (begin < 0) begin = 0;
        if (end > getItemCount()) end = getItemCount();

        for (int i = begin; i < end; i++) {

            View scrap = null;
            if (!removeCondition(itemsOffset.get(i) - offset)) {
                if (!itemAttached.get(i)) {
                    scrap = recycler.getViewForPosition(i);

                    measureChildWithMargins(scrap, 0, 0);

                    if (dir == SCROLL_TOP) {
                        addView(scrap, 0);
                        Log.e("TAG", "上面添加view" + i);
                    } else {
                        Log.e("TAG", "下面添加view" + i);
                        addView(scrap);
                    }
//
//                    float targetOffset = itemsOffset.get(i) - offset;
//                    Log.e("TAG", "i-->" + i + "  target" + targetOffset);
//
//                    j = (int) (targetOffset / pageOffset);
//                    //float more=
//
//                    // Log.e("TAG", "num1  " + (int) (itemsOffset.get(i) / pageOffset) + "num2  " + (int) (offset / pageOffset) + "  pageoffset" + pageOffset);
//
//                    int index = 0;
//                    float more = 0;
//                    if (offset <= 0) {
//                        index = j + 2;
//                        more = pageOffset - targetOffset % pageOffset;
//                    } else {
//                        index = i + 1;
//                        more = pageOffset - targetOffset % pageOffset;
//                    }
//
//                    top = (int) getValue(index, "top", more);
//                    h = (int) getValue(index, "h", more);
//                    w = (int) getValue(index, "w", more);
//                    b = (int) getValue(index, "b", more);
//                    alpha = getValue(index, "alpha", more);
//                    layoutScrap(scrap, top, h, w, b, alpha);
                    layoutScrapByTargetOffset(scrap, itemsOffset.get(i) - offset);
                    itemAttached.put(i, true);

                }
            }
        }


    }


//    private boolean removeCondition(int i) {
//
//        boolean result = (int) (itemsOffset.get(i) - offset) <= -(int) pageOffset || itemsOffset.get(i) - offset >= (3 + topnums) * pageOffset;
//        return itemsOffset.get(i)-offset<-pageOffset||itemsOffset;
//    }

    private boolean removeCondition(float targetOffset) {
        // Log.e("TAG","targetOffset"+targetOffset+"maxRemoveOffset"+maxRemoveOffset()+"minRemoveOffset"+minRemoveOffset());
        return targetOffset > maxRemoveOffset() || targetOffset < minRemoveOffset();
    }


    //重新布局
    private void layoutScrap(View scrap, int top, int h, int w, int b, float alpha) {
        TextView tv = (TextView) ((RelativeLayout) scrap).getChildAt(1);
        Log.e("TAG", " top-->" + top + " h-->" + h + " w-->" + w + " b-->" + b + " alph" + alpha + "文本" + tv.getText() + "");

        layoutDecorated(scrap, startLeft + (mChildWidth - w) / 2, top,
                (mChildWidth - w) / 2 + startLeft + w, b);
        scrap.setAlpha(alpha);

    }

    public int findLastCompletelyVisibleItemPosition() {
        int position = 0;
        View view = getChildAt(getChildCount() - 1);
        if (view != null)
            position = getPosition(view);
        return position;
    }

    private void layoutScrapByTargetOffset(View scrap, float target) {
        int top = 0;
        int h = 0;
        int w = 0;
        int b = 0;
        float alpha = 1;
        int W = mChildWidth;
        int H = mChildHeight;

        if (target <= -pageOffset) {
            w = (int) (0.7 * W);
            h = (int) (0.7 * H);
            top = 0;
            alpha = 0;
        } else if (target <= 0) {
            w = (int) (0.7 * W);
            h = (int) (0.7 * H);
            top = 0;
            alpha = (float) (0.25 + target * 1.0 / (4 * pageOffset));

        } else if (target <= 3 * pageOffset) {
            w = (int) (W * (0.7 + 0.1 * target / pageOffset));
            h = (int) (H * (0.7 + 0.1 * target / pageOffset));
            alpha = (float) (0.25 + target * 1.0 / (4 * pageOffset));
            top = (int) (target * bigPageTop / (3 * pageOffset));
        } else if (target > 3 * pageOffset && target <= 4 * pageOffset) {
            w = (int) (W * (1 - 0.2 * (target - 3 * pageOffset) / pageOffset));
            h = (int) (H * (1 - 0.8 * (target - 3 * pageOffset) / pageOffset));
            alpha = (float) (1 - 0.1 * (target - 3 * pageOffset) / pageOffset);
            top = (int) (bigPageTop + (H + itemSpace) * (target - 3 * pageOffset) / pageOffset);
        } else if (target > 4 * pageOffset) {
            w = (int) (W * (1 - 0.2 * (target - 3 * pageOffset) / pageOffset));
            h = (int) (0.2 * H);
            alpha = (float) (1 - 0.1 * (target - 3 * pageOffset) / pageOffset);
            top = (int) ((bigPageTop + H + itemSpace) + (0.2 * H + itemSpace) * (target - 4 * pageOffset) / pageOffset);
        }
        b = top + h;
//        Log.e("TAG", " top-->" + top + " h-->" + h + " w-->" + w + " b-->" + b + " alph-->" + alpha + "target-->" + target + "文本-->"+tv.getText().toString());

        layoutDecorated(scrap, startLeft + (mChildWidth - w) / 2, top,
                (mChildWidth - w) / 2 + startLeft + w, b);
        scrap.setAlpha(alpha);


    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    //适配器改变
    @Override
    public void onAdapterChanged(RecyclerView.Adapter oldAdapter, RecyclerView.Adapter newAdapter) {
        removeAllViews();
        offset = 0;
    }

    public int getCurrentPosition() {

        return Math.round((offset) / pageOffset);
    }

    public int get_TopView_IDLE_Offset() {
        return (int) ((getCurrentPosition() - 1) * pageOffset);
    }

    //得到水平空间
    protected int getHorizontalSpace() {
        return getWidth() - getPaddingRight() - getPaddingLeft();
    }
    //得到竖直高度

    protected int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }

    protected float maxRemoveOffset() {
        return 6 * pageOffset;
    }

    protected float minRemoveOffset() {
        return -pageOffset;
    }

    //边界的判断                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    一
    private void handleOutOfRange() {
        if (offset < getMinOffset()) {
            offset = getMinOffset();
        }
        if (offset > getMaxOffset()) {
            offset = getMaxOffset();
        }
    }

    private float getMinOffset() {
        return -3 * pageOffset;
    }

    private float getMaxOffset() {
        return (getItemCount() - 4) * pageOffset;
    }

    protected float getDistanceRatio() {
        return 1f;
    }

    private float getMoreOffset() {
        return offset % pageOffset;
    }

    public int getOffsetCenterView() {
        // Log.e("TAG","位置"+getCurrentPosition());
        return (int) ((getCurrentPosition() * pageOffset - offset) * getDistanceRatio());
    }


}
