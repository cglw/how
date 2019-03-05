package com.prettyyes.user.app.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.prettyyes.user.model.user.UserInfo;

/**
 * Created by chengang on 2017/7/13.
 */

public  class TestViewHolder extends MutiTypeViewHolder<UserInfo> {
    public TestViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(UserInfo modle, int position, RecyclerView.Adapter adpter) {

    }

    @Override
    public void bindView() {

    }


}
