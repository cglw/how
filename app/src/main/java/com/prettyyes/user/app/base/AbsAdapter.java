package com.prettyyes.user.app.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public abstract class AbsAdapter<T> extends BaseAdapter {

    public Context context;
    public ArrayList<T> datas;
    private int layoutRes; //item布局资源标识

    public AbsAdapter(int layoutRes, ArrayList<T> datas) {
        this.context = BaseApplication.getAppContext();
        this.datas = datas;
        this.layoutRes = layoutRes;
    }
    public AbsAdapter(int layoutRes, ArrayList<T> datas,Context context) {
        this.context = context;
        this.datas = datas;
        this.layoutRes = layoutRes;
    }
    public AbsAdapter(int layoutRes,Context context) {
        this.context = context;
        this.datas = new ArrayList<>();
        this.layoutRes = layoutRes;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
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
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        //显示数据
        showData(vHolder, datas.get(position));
        this.position=position;
        return convertView;
    }

    private int position=0;
    public int getPosition()
    {
        return  position;
    }
    public abstract void showData(ViewHolder vHolder, T data);

    public class ViewHolder {
        private Map<Integer, View> views;  //用于封装从item布局中查找的控件
        private View itemView; //item布局对象

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
        public View getRootView()
        {
            return itemView;
        }
    }

    public void addAll(ArrayList<T> datas) {
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
