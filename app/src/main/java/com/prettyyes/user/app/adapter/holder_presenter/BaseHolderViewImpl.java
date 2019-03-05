package com.prettyyes.user.app.adapter.holder_presenter;

import android.content.Context;
import android.view.View;

import com.prettyyes.user.app.base.MutiTypeViewHolder;

/**
 * Created by chengang on 2017/8/10.
 */

public class BaseHolderViewImpl<T> {

    public String TAG = this.getClass().getName();
    public MutiTypeViewHolder mutiTypeViewHolder;
    public T data;
    public int position;
    public Context context;

    public void setData(T data) {
        this.data = data;
    }

    public BaseHolderViewImpl() {

    }

    public BaseHolderViewImpl(MutiTypeViewHolder mutiTypeViewHolder) {
        this.mutiTypeViewHolder = mutiTypeViewHolder;
        this.context = mutiTypeViewHolder.context;
    }

    public BaseHolderViewImpl(MutiTypeViewHolder mutiTypeViewHolder, int position, T data) {
        this.mutiTypeViewHolder = mutiTypeViewHolder;
        this.data = data;
        this.position = position;
        this.context = mutiTypeViewHolder.context;
    }

    public View getRootView() {
        return mutiTypeViewHolder.getRootView();
    }


    public <V extends View> V getView(int id) {

        return mutiTypeViewHolder.getView(id);
    }

    public View findViewById(int id) {
        return mutiTypeViewHolder.getView(id);
    }

    public Context getContext() {
        return context;
    }
}
