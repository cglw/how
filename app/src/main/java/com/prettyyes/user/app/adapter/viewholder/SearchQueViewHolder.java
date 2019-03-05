package com.prettyyes.user.app.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.model.search.SearchBaseEntity;

/**
 * Created by chengang on 2017/7/13.
 */

public class SearchQueViewHolder extends MutiTypeViewHolder<SearchBaseEntity> {


    public SearchQueViewHolder(Context context, ViewGroup parent) {
        super(context,parent, R.layout.layout_search);
    }

    @Override
    public void bindData(SearchBaseEntity modle, int position, RecyclerView.Adapter adpter) {

    }

    @Override
    public void bindView() {

    }

    private TextView tv_title;
}
