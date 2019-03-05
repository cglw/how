package com.prettyyes.user.app.view.recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.core.utils.LogUtil;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view.recycle
 * Author: SmileChen
 * Created on: 2016/12/22
 * Description: Nothing
 */
public class EmptyRecyclerView extends RecyclerView {

    private View emptyView;
    private static final String TAG = "hiwhitley";

    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            //  Log.i(TAG, "onItemRangeInserted" + itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    public EmptyRecyclerView(Context context) {
        super(context);
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs,
                             int defStyle) {
        super(context, attrs, defStyle);
    }

    public void checkIfEmpty() {
        LogUtil.i(TAG,"checkIfEmpty"+emptyView+"-->"+getAdapter());
        if (emptyView != null && getAdapter() != null) {

            final boolean emptyViewVisible =
                    absRecycleAdapter.getDataCount() == 0;
            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            setVisibility(emptyViewVisible ? GONE : VISIBLE);
            LogUtil.i(TAG, "visiable-->" + emptyViewVisible);

        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }

        checkIfEmpty();
    }

    @Override
    public Adapter getAdapter() {
        return super.getAdapter();
    }

    private AbsRecycleAdapter absRecycleAdapter;

    public void setEmptyView(View emptyView, AbsRecycleAdapter absRecycleAdapter) {
        this.emptyView = emptyView;
        this.absRecycleAdapter = absRecycleAdapter;
        checkIfEmpty();

    }



}