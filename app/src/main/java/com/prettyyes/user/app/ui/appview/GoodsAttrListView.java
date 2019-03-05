package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.prettyyes.user.model.applocal.AttrName;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/7/3.
 */

public class GoodsAttrListView extends LinearLayout {


    public GoodsAttrListView(Context context) {
        super(context);
        initSomething();
    }

    public GoodsAttrListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initSomething();

    }

    private void initSomething() {
        setOrientation(VERTICAL);
    }

    public void setAttrNames(ArrayList<AttrName> attrnames) {
        this.removeAllViews();
        for (int i = 0; i < attrnames.size(); i++) {
            GoodsAttrSelectView view = new GoodsAttrSelectView(getContext());
            view.setAttrName(attrnames.get(i));
            this.addView(view);
        }
    }


    public GoodsAttrListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSomething();

    }
}
