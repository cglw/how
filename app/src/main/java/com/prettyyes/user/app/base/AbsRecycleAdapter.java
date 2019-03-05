package com.prettyyes.user.app.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prettyyes.user.core.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.base
 * Author: SmileChen
 * Created on: 2016/12/20
 * Description: Nothing
 */
public abstract class AbsRecycleAdapter<T> extends RecyclerView.Adapter<AbsRecycleViewHolder> {
    public final String TAG = this.getClass().getName();
    public Context context;
    public LayoutInflater inflater;
    public List<T> items;
    private int layoutId;

    public AbsRecycleAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>();

    }

    public AbsRecycleAdapter(Context context, int layoutId) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        items = new ArrayList<>();
    }

    @Override
    public MutiTypeViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MutiTypeViewHolder<T>(inflater.inflate(layoutId, parent, false)) {
            @Override
            public void bindData(T modle, int position, RecyclerView.Adapter adpter) {

            }

            @Override
            public void bindView() {

            }
        };
    }
    //
//    @Override
//    public AbsRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new AbsRecycleViewHolder(inflater.inflate(layoutId, parent, false));
//    }


    @Override
    public void onBindViewHolder(final AbsRecycleViewHolder holder, final int position) {

        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnItemClickListener(holder, v, items.get(position), position);

                if (onItemClickListener != null) {
                    onItemClickListener.clickItem(holder, v, items.get(position), position);
                }

            }
        });
        bindView(holder);
        T t = null;
        if (position <= items.size() - 1)
            t = items.get(position);
        else
            t = null;
        bindData(holder, t, position);

    }


    private AbsRecycleViewHolder holder;

    public AbsRecycleViewHolder getHolder() {
        return holder;
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    public Context getContext() {
        return context;
    }

    public void addAll(List<T> list) {
        this.items.addAll(list);
        notifyDataSetChanged();
    }

    public void add(T data) {
        this.items.add(data);
        LogUtil.i(TAG, "abscount" + this.items.size());
        notifyDataSetChanged();
    }

    public void add(int position, T data) {
        this.items.add(position, data);
        notifyDataSetChanged();
    }

    public T getItemData(int position) {
        return items.get(position);
    }

    public List<T> getItems() {
        return items;
    }

    public void setItemData(int position, T data) {
        items.set(position, data);
        notifyItemChanged(position);
    }

    public void removeAll() {
        this.items = null;
        notifyDataSetChanged();
    }

    public void remove(int position) {
        notifyItemRemoved(position);
        this.items.remove(position);
        notifyItemRangeChanged(0, getItemCount());
        notifyDataSetChanged();

    }

    public void remove(int position, int count) {
        notifyItemRangeRemoved(position, count);
        for (int i = position + count-1; i >= position; i--) {
            this.items.remove(position);
        }
        notifyItemRangeChanged(0, getItemCount());
        notifyDataSetChanged();

    }

    public void clear() {
        this.items.clear();
        notifyDataSetChanged();
    }

    public void clearData() {
        this.items.clear();
    }

    public int getDataCount() {
        return this.items.size();
    }

    protected abstract void bindData(AbsRecycleViewHolder holder, T t, int position);

    protected abstract void bindView(AbsRecycleViewHolder holder);

    public void setOnItemClickListener(AbsRecycleViewHolder holder, View view, T t, int position) {
    }

    public AbsRecycleAdapter getThis() {
        return this;
    }

    private OnMyItemClickListener onItemClickListener;

    public void setMyOnItemClickListener(OnMyItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnMyItemClickListener<T> {
        public void clickItem(AbsRecycleViewHolder holder, View view, T t, int position);
    }

}
