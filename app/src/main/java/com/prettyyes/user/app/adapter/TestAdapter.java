package com.prettyyes.user.app.adapter;

import android.content.Context;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;

/**
 * Created by chengang on 2017/7/13.
 */

public class TestAdapter extends AbsRecycleAdapter<Object> {
    public TestAdapter(Context context) {
        super(context, R.layout.item_text);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, Object s, int position) {

    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {

    }
}
