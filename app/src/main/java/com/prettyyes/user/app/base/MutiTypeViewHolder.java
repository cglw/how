package com.prettyyes.user.app.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prettyyes.user.core.utils.LogUtil;

/**
 * Created by chengang on 2017/7/13.
 */

public abstract class MutiTypeViewHolder<T> extends AbsRecycleViewHolder {

    public String TAG = this.getClass().getName();
    public Context context;
    public AbsMutiRvAdapter absMutiRvAdapter;
    public View parent;

    public void setAbsMutiRvAdapter(AbsMutiRvAdapter absMutiRvAdapter) {
        this.absMutiRvAdapter = absMutiRvAdapter;
    }


    public MutiTypeViewHolder(View itemView) {

        super(itemView);
        LogUtil.i(TAG, "context" + itemView.getContext());

        context = itemView.getContext();
        LogUtil.i(TAG, "context" + context);
        this.parent = itemView;

    }

    public MutiTypeViewHolder(Context context, ViewGroup parent, int layout_id) {
        super(LayoutInflater.from(context).inflate(layout_id, parent, false));
        this.views = new SparseArray<>();
        this.context = context;
        this.parent = parent;
    }


    public MutiTypeViewHolder(ViewGroup parent, int layout_id) {
        super(LayoutInflater.from(parent.getContext()).inflate(layout_id, parent, false));
        this.views = new SparseArray<>();
        this.context = parent.getContext();
        this.parent = parent;

    }

    public AbsMutiRvAdapter getAbsMutiRvAdapter() {
        return absMutiRvAdapter;
    }

    public void autoBind(T modle, int position) {
        bindView();
        bindData(modle, position, null);
        setListener(modle, position, null);
    }

    public void setListener(T modle, int position, Object o) {
    }

    public abstract void bindData(T modle, int position, RecyclerView.Adapter adpter);

    public abstract void bindView();

    public BaseActivity getActivity() {
        if (context instanceof BaseActivity)
            return (BaseActivity) context;
        return null;

    }

}
