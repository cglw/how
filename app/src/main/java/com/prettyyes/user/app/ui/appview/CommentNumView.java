package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by chengang on 2017/5/22.
 */

public class CommentNumView extends AbsLinearlayoutView {

    @ViewInject(R.id.tv_comment_total)
    TextView tv_total;

    public CommentNumView(Context context) {
        super(context);
    }

    public CommentNumView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public int bindLayout() {
        return R.layout.view_comment_num;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void setDataByModel(Object data) {

    }

    public void setTotalComment(int num) {
        tv_total.setText(String.format("评论 %s", num));

    }

}
