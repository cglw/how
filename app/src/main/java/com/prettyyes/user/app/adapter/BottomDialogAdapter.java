package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;


/**
 * Created by chengang on 2017/5/31.
 */

public class BottomDialogAdapter extends AbsRecycleAdapter<String> {
    public BottomDialogAdapter(Context context) {
        super(context, R.layout.item_text);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final String s, int position) {
        textView.setText(s);
    }

    private TextView textView;

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        textView = holder.getView(R.id.tv_click);
    }
}
