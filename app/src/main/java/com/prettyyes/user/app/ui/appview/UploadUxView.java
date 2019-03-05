package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;

/**
 * Created by chengang on 2017/12/13.
 */

public class UploadUxView extends AbsLinearlayoutView {
    public UploadUxView(Context context) {
        super(context);
    }

    public UploadUxView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.view_ux;
    }

    private TextView tv_text;
    private LinearLayout ll_close;

    @Override
    public void initViews() {
        tv_text = (TextView) getView(R.id.tv_text);
        ll_close = (LinearLayout) getView(R.id.ll_close);

    }

    public void setProgressText(String text) {
        tv_text.setText(text);
    }


    public void setText(String text) {
        tv_text.setText(text);
    }


    public LinearLayout getImg_close() {
        return ll_close;
    }

    @Override
    public void setDataByModel(Object data) {

    }
}
