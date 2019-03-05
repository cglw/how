package com.prettyyes.user.app.adapter.searchholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;

/**
 * Created by chengang on 2017/7/14.
 */

public class SearchTitleHolder extends MutiTypeViewHolder<SearchTitltModel> {


    public SearchTitleHolder(Context context, ViewGroup parent) {
        super(context, parent, R.layout.item_search_title);
    }

    @Override
    public void bindData(SearchTitltModel modle, int position, RecyclerView.Adapter adpter) {
        String head = "";
        if (modle.getKey_word() == null || modle.getKey_word().length() <= 0) {
            head = "热门";
            img_hot.setVisibility(View.VISIBLE);
        } else {
            img_hot.setVisibility(View.GONE);
        }

        tv_title.setText(String.format("%s%s", head, modle.getTitle()));
    }

    @Override
    public void bindView() {
        tv_title = getView(R.id.tv_title);
        img_hot = getView(R.id.img_hot);
    }

    private TextView tv_title;
    private ImageView img_hot;
}
