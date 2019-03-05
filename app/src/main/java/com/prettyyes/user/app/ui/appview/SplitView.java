package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;

/**
 * Created by chengang on 2017/3/30.
 */

public class SplitView extends AbsLinearlayoutView {
    public SplitView(Context context) {
        super(context);
    }

    public SplitView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private TextView tv_time;
    private TextView tv_desc;

    @Override
    public int bindLayout() {
        return R.layout.item_rv_home_splice;
    }

    @Override
    public void initViews() {
        tv_desc = (TextView) getView(R.id.tv_splitview_desc);
        tv_time = (TextView) getView(R.id.tv_splitview_time);

    }

    @Override
    public void setDataByModel(Object data) {

    }

    public void setTimeAndDesc(String time, String desc) {
        tv_time.setText(time);
        tv_desc.setText(desc);
    }
}
