package com.prettyyes.user.app.view.lvandgrid;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view
 * Author: SmileChen
 * Created on: 2016/8/17
 * Description: 在scrollview里面高度自适应
 */
public class MyGridView extends GridView {
    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context) {
        super(context);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}