package com.prettyyes.user.app.adapter.searchholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.core.containter.JumpPageManager;

/**
 * Created by chengang on 2017/7/14.
 */

public class SearchLookMoreHolder extends MutiTypeViewHolder<SearchLookMoreModel> {

    public SearchLookMoreHolder(Context context, ViewGroup parnet) {
        super(context, parnet, R.layout.layout_look_more);
    }

    @Override
    public void bindData(final SearchLookMoreModel modle, int position, RecyclerView.Adapter adpter) {
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goSearchListActivity(context, modle.getSearch_type(), modle.getKey_word());
            }
        });
    }

    @Override
    public void bindView() {

    }
}