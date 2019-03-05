package com.prettyyes.user.app.view.recycle;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.bumptech.glide.Glide;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;

/**
 * Created by cundong on 2015/10/9.
 * <p/>
 * 继承自RecyclerView.OnScrollListener，可以监听到是否滑动到页面最低部
 */
public class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener implements OnListLoadNextPageListener {
    public EndlessRecyclerOnScrollListener(Context context) {
        this.context = context;
    }

    private Context context;
    /**
     * 当前RecyclerView类型
     */
    protected LayoutManagerType layoutManagerType;

    /**
     * 最后一个的位置
     */
    private int[] lastPositions;

    /**
     * 最后一个可见的item的位置
     */
    private int lastVisibleItemPosition;

    /**
     * 当前滑动的状态
     */
    private int currentScrollState = 0;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        if (layoutManagerType == null) {
            if (layoutManager instanceof LinearLayoutManager) {
                layoutManagerType = LayoutManagerType.LinearLayout;
            } else if (layoutManager instanceof GridLayoutManager) {
                layoutManagerType = LayoutManagerType.GridLayout;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                layoutManagerType = LayoutManagerType.StaggeredGridLayout;
            } else {
                throw new RuntimeException(
                        "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }

        switch (layoutManagerType) {
            case LinearLayout:
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case GridLayout:
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case StaggeredGridLayout:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (lastPositions == null) {
                    lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                lastVisibleItemPosition = findMax(lastPositions);
                break;
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        currentScrollState = newState;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        if ((visibleItemCount > 0 && currentScrollState == SCROLL_STATE_IDLE && (lastVisibleItemPosition) >= totalItemCount * 2 / 3)) {
            onLoadNextPage(recyclerView);
        }

        switch (newState) {
            case SCROLL_STATE_IDLE: // The RecyclerView is not currently scrolling.
                //当屏幕停止滚动，加载图片
                try {
                    if (context != null) Glide.with(context).resumeRequests();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case SCROLL_STATE_DRAGGING: // The RecyclerView is currently being dragged by outside input such as user touch input.
                //当屏幕滚动且用户使用的触碰或手指还在屏幕上，停止加载图片
                try {
                    if (context != null) Glide.with(context).pauseRequests();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case SCROLL_STATE_SETTLING: // The RecyclerView is currently animating to a final position while not under outside control.
                //由于用户的操作，屏幕产生惯性滑动，停止加载图片
                try {
                    if (context != null) Glide.with(context).pauseRequests();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * 取数组中最大值
     *
     * @param lastPositions
     * @return
     */
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }

        return max;
    }

    @Override
    public void onLoadNextPage(final View view) {
    }

    public static enum LayoutManagerType {
        LinearLayout,
        StaggeredGridLayout,
        GridLayout
    }
}
