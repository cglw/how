package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;

/**
 * Created by chengang on 2017/6/28.
 */

public class SpecificationTvView extends AbsLinearlayoutView {
    public SpecificationTvView(Context context) {
        super(context);
        setLayoutParams(new MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public SpecificationTvView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.view_tv_specification;
    }

    private TextView tv;

    @Override
    public void initViews() {
        tv = (TextView) getView(R.id.tv);
    }

    public void setText(String txt) {
        tv.setText(txt);
    }

    @Override
    public void setDataByModel(Object data) {

    }

    public String getInfo() {
        return tv.getText().toString();
    }
}
