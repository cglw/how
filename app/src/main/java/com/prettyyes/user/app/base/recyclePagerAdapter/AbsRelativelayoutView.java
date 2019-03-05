package com.prettyyes.user.app.base.recyclePagerAdapter;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import org.xutils.x;

public abstract class AbsRelativelayoutView<T> extends RelativeLayout {

    private View view;
    private Activity activity;
    public T data;
    private int tag_inner;
    private View view_tag;


    public View getView_tag() {
        return view_tag;
    }

    public void setView_tag(View view_tag) {
        this.view_tag = view_tag;
    }

    public int getTag_inner() {
        return tag_inner;
    }

    public void setTag_inner(int tag_inner) {
        this.tag_inner = tag_inner;
    }


    public AbsRelativelayoutView(Context context) {
        super(context);
        initView(context);
    }

    public AbsRelativelayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public AbsRelativelayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);


    }

    private void initView(Context context) {
        activity = (Activity) context;
        if (bindLayout() != 0) {
            view = LayoutInflater.from(context).inflate(bindLayout(), this);
            x.view().inject(this, view);
        }

        initViews();
        setListener();

    }

    public void setListener() {
    }

    public Activity getActivity() {
        return activity;
    }

    public abstract int bindLayout();

    public View bindLayoutView() {
        return new View(getActivity());
    }

    public View getView(int id) {
        return view.findViewById(id);
    }


    public abstract void initViews();

    public abstract void setDataByModel(T data);


    public void setData(T data) {
        this.data = data;
    }
    public void setData(String... data) {
    }


    public void setIntData(Integer... data) {
    }


}
