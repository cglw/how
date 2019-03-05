package com.prettyyes.user.app.adapter.moments_myList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.view.app.RoundView;
import com.prettyyes.user.model.v8.ItemTextModel;

/**
 * Created by chengang on 2017/7/14.
 */

public class SimpleTextHolder extends MutiTypeViewHolder<ItemTextModel> {
    public SimpleTextHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(ItemTextModel modle, int position, RecyclerView.Adapter adpter) {
        tv_click.setText(modle.getText());
        if (modle.getUnread_num() > 0)
            roundview_unread.setVisibility(View.VISIBLE);
        else
            roundview_unread.setVisibility(View.GONE);
    }

    @Override
    public void bindView() {
        tv_click = getView(R.id.tv_click);
        roundview_unread = getView(R.id.roundview_unread);
    }

    private TextView tv_click;
    private RoundView roundview_unread;
}
