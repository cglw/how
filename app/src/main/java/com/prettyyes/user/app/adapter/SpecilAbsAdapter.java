package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 调用的时候重写两个方法就可以bindview 跟setData
 *
 * @param <T>
 */
public abstract class SpecilAbsAdapter<T> extends BaseAdapter {

    public Context context;
    public ArrayList<T> datas;
    private int layoutRes; //item布局资源标识

    public SpecilAbsAdapter(int layoutRes, ArrayList<T> datas) {
        this.context = BaseApplication.getAppContext();
        this.datas = datas;
        this.layoutRes = layoutRes;
    }

    public SpecilAbsAdapter(int layoutRes, Context context) {
        this.context = context;
        this.datas = new ArrayList<>();
        this.layoutRes = layoutRes;
    }

    public SpecilAbsAdapter(int layoutRes, ArrayList<T> datas, Context context) {
        this.context = context;
        this.datas = datas;
        this.layoutRes = layoutRes;
    }

    @Override
    public int getCount() {
        if (null != datas)
            return datas.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (null != datas && position < datas.size())
            return datas.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutRes, null);
            vHolder = new ViewHolder(convertView);
            convertView.setTag(R.id.holder,vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag(R.id.holder);
        }
        bindView(vHolder);
        //显示数据
        showData(vHolder, datas.get(position), position);
        this.position = position;
        return convertView;
    }

    private int position = 0;

    public int getPosition() {
        return position;
    }

    public abstract void bindView(ViewHolder vHolder);

    public abstract void showData(ViewHolder vHolder, T data, int position);

    public class ViewHolder {
        private Map<Integer, View> views;  //用于封装从item布局中查找的控件
        private View itemView; //item布局对象

        public View getRootView() {
            return itemView;
        }

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            views = new HashMap<Integer, View>();
        }

        public View getView(int id) {
            View view = views.get(id);
            if (view == null) { //第一次查找指定id的控件
                view = itemView.findViewById(id);
                views.put(id, view);
            }
            return view;
        }
    }

    public void addAll(ArrayList<T> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<T> datas, int index) {
        for (int i = datas.size() - 1; i >= 0; i++) {
            this.datas.add(index, datas.get(i));
        }
        notifyDataSetChanged();
    }

    public void clear() {
        this.datas.clear();
        notifyDataSetChanged();
    }

    public void add(T data) {
        this.datas.add(data);
        notifyDataSetChanged();
    }

    public ArrayList<T> getData() {
        return datas;
    }

    public T get(int position) {
        if (null != datas && position < datas.size())
            return datas.get(position);
        return null;
    }

    public void remove(int i) {
        this.datas.remove(i);
        notifyDataSetChanged();
    }
}
