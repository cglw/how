package com.prettyyes.user.app.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.recyclePagerAdapter.RecyclingPagerAdapter;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.base
 * Author: SmileChen
 * Created on: 2016/10/13
 * Description: Nothing
 */
public abstract class AbsVpAdapter<T> extends RecyclingPagerAdapter {
    public String TAG = getClass().getName();
    public Context context;
    private ArrayList<T> datas;
    private int layoutRes; //item布局资源标识

    public AbsVpAdapter(Context context, ArrayList<T> datas, int layoutRes) {
        this.context = context;
        this.datas = datas;
        this.layoutRes = layoutRes;

    }

    public AbsVpAdapter(Context context, int layoutRes) {
        this.context = context;
        this.datas = new ArrayList<>();
        this.layoutRes = layoutRes;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        ViewHolder vHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutRes, null);
            vHolder = new ViewHolder(convertView);
            convertView.setTag(R.id.holder, vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag(R.id.holder);
        }

        bindView(vHolder);
        //显示数据
        showData(vHolder, datas.get(position), position);
        return convertView;
    }


    public abstract void bindView(ViewHolder vHolder);

    public abstract void showData(ViewHolder vHolder, T data, int position);

    @Override
    public int getCount() {
        return datas.size();
    }

    public class ViewHolder extends MutiTypeViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(Object modle, int position, RecyclerView.Adapter adpter) {

        }

        @Override
        public void bindView() {

        }
    }

    public void addAll(ArrayList<T> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public void add(T data) {
        this.datas.add(data);
        notifyDataSetChanged();
    }

    public void add(int position, T data) {
        this.datas.add(position, data);
        notifyDataSetChanged();
    }

    public void clear() {
        this.datas.clear();
        notifyDataSetChanged();
    }

    public T get(int index) {
        return datas.get(index);
    }

    public ArrayList<T> getData() {
        return datas;
    }
}
