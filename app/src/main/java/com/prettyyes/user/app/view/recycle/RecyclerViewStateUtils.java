package com.prettyyes.user.app.view.recycle;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


/**
 * Created by cundong on 2015/11/9.
 * <p>
 * 分页展示数据时，RecyclerView的FooterView State 操作工具类
 * <p>
 * RecyclerView一共有几种State：Normal/Loading/Error/TheEnd
 */
public class RecyclerViewStateUtils {

    private static final String TAG = "RecyclerViewStateUtils";

    public static void setFoorterViewHeight(Activity instance, RecyclerView recyclerView, int height) {
        if (instance == null || instance.isFinishing()) {
            return;
        }

        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if (outerAdapter == null || !(outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)) {
            return;
        }

        HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = (HeaderAndFooterRecyclerViewAdapter) outerAdapter;


        TextView view = new TextView(instance);
        view.setHeight(0);
        headerAndFooterAdapter.addFooterView(view);
        if (headerAndFooterAdapter.getFooterViewsCount() > 0)
        {
          View  footerView = (LoadingFooter) headerAndFooterAdapter.getFooterView();

        }
        //  recyclerView.scrollToPosition(headerAndFooterAdapter.getItemCount() - 1);


    }
    public static void removeAllView(Activity instance, RecyclerView recyclerView) {
        if (instance == null || instance.isFinishing()) {
            return;
        }

        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if (outerAdapter == null || !(outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)) {
            return;
        }

        HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = (HeaderAndFooterRecyclerViewAdapter) outerAdapter;

        for (int i = 0; i <recyclerView.getChildCount() ; i++) {
            recyclerView.removeView(recyclerView.getChildAt(i));
        }

    }


    /**
     * 设置headerAndFooterAdapter的FooterView State
     *
     * @param instance      context
     * @param recyclerView  recyclerView
     * @param pageSize      分页展示时，recyclerView每一页的数量
     * @param state         FooterView State
     * @param errorListener FooterView处于Error状态时的点击事件
     */

    public static void setFooterViewState(Activity instance, RecyclerView recyclerView, int pageSize, LoadingFooter.State state, View.OnClickListener errorListener) {

        if (instance == null || instance.isFinishing()) {
            return;
        }

        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if (outerAdapter == null || !(outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)) {
            return;
        }

        HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = (HeaderAndFooterRecyclerViewAdapter) outerAdapter;
        LoadingFooter footerView;

//        //只有一页的时候，就别加什么FooterView了
//        if (headerAndFooterAdapter.getInnerAdapter().getItemCount() < pageSize) {
//            footerView = (LoadingFooter) headerAndFooterAdapter.getFooterView();
//            footerView.setState( LoadingFooter.State.TheEnd);
//            return;
//        }

        //    LoadingFooter footerView;

        //已经有footerView了
        if (headerAndFooterAdapter.getFooterViewsCount() > 0) {
            footerView = (LoadingFooter) headerAndFooterAdapter.getFooterView();
            footerView.setState(state);

            if (state == LoadingFooter.State.NetWorkError) {
                footerView.setOnClickListener(errorListener);
            }

            //  recyclerView.scrollToPosition(headerAndFooterAdapter.getItemCount() - 1);
        } else {
            footerView = new LoadingFooter(instance);
            footerView.setState(state);

            if (state == LoadingFooter.State.NetWorkError) {
                footerView.setOnClickListener(errorListener);
            }

            headerAndFooterAdapter.addFooterView(footerView);
            //  recyclerView.scrollToPosition(headerAndFooterAdapter.getItemCount() - 1);
        }
    }

    /**
     * 获取当前RecyclerView.FooterView的状态
     *
     * @param recyclerView
     */
    public static LoadingFooter.State getFooterViewState(RecyclerView recyclerView) {

        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter) {
            if (((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getFooterViewsCount() > 0) {
                LoadingFooter footerView = (LoadingFooter) ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getFooterView();
                return footerView.getState();
            }
        }

        return LoadingFooter.State.Normal;
    }

    /**
     * 设置当前RecyclerView.FooterView的状态
     *
     * @param recyclerView
     * @param state
     */
    public static void setFooterViewState(RecyclerView recyclerView, LoadingFooter.State state) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter) {
            if (((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getFooterViewsCount() > 0) {
                LoadingFooter footerView = (LoadingFooter) ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getFooterView();
                footerView.setState(state);
            }
        }
    }
}
