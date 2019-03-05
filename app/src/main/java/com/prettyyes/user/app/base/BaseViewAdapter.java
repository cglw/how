package com.prettyyes.user.app.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by cc on 16/9/22.
 */
public abstract class BaseViewAdapter<T, V> extends BaseAdapter {

    private List<T> datas;
    public Activity activity;
    public View view;

    public BaseViewAdapter(Activity activity, List datas) {
        this.activity = activity;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        if (i < datas.size()) {
            return datas.get(i);
        }

        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (null == view) {
            view = (View) getLayoutView(activity);
        }
            setData((V) view, i, datas.get(i));

        return view;
    }


    public abstract V getLayoutView(Activity context);

    public abstract void setData(V view, int i, T data);

    public void addAll(List<T> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public void clear() {
        this.datas.clear();
    }

    public void add(T data) {
        this.datas.add(data);
        notifyDataSetChanged();
    }

    public T get(int position) {
        return datas.get(position);
    }

    public void remove(int i)
    {
        this.datas.remove(i);
        notifyDataSetChanged();
    }
}
