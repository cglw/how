package com.prettyyes.user.app.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.model.v8.ScoreEntity;

/**
 * Created by chengang on 2017/9/4.
 */

public class ScoreListHolder extends MutiTypeViewHolder<ScoreEntity> {
    public ScoreListHolder(ViewGroup parent) {
        super(parent.getContext(), parent, R.layout.item_score_detail);
    }

    @Override
    public void bindData(ScoreEntity modle, int position, RecyclerView.Adapter adpter) {


        tv_title.setText(modle.getContent());
        tv_time.setText(modle.getCreate_time());
        float score = Float.parseFloat(modle.getScore());
        if (score <= 2 && score >= 0)
            tv_score.setBackgroundResource(R.drawable.bg_round_grey_origin);
        if (score < 0)
            tv_score.setBackgroundResource(R.drawable.bg_round_grey_100);
        if (score > 2)
            tv_score.setBackgroundResource(R.drawable.bg_round_red_round100);

        tv_score.setText(modle.getScore());
        tv_number.setText(modle.getCount());

        if (modle.getCountNum() > 0) {
            tv_number.setTextColor(ContextCompat.getColor(context, R.color.theme_red));
        } else {
            tv_number.setTextColor(ContextCompat.getColor(context, R.color.grey_tv_main));

        }


        if (position == 0)
            tv_number.setVisibility(View.INVISIBLE);
        else
            tv_number.setVisibility(View.VISIBLE);
    }

    @Override
    public void bindView() {
        tv_title = getView(R.id.tv_title);
        tv_time = getView(R.id.tv_time);
        tv_score = getView(R.id.tv_score);
        tv_number = getView(R.id.tv_number);

    }

    private TextView tv_title;
    private TextView tv_time;
    private TextView tv_score;
    private TextView tv_number;
}
