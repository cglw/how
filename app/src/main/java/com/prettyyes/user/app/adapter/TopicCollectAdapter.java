package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.ui.how.TopicActivity;
import com.prettyyes.user.model.TopicCollectRes;

/**
 * Created by chengang on 2017/5/25.
 */

public class TopicCollectAdapter extends AbsRecycleAdapter<TopicCollectRes.ListBean> {
    public TopicCollectAdapter(Context context) {
        super(context, R.layout.item_rv_collect_topic);
    }


    private TextView tv_word_num;
    private TextView tv_title;
    private TextView tv_ask_num;
    private View view_bg_color;

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final TopicCollectRes.ListBean topicEntity, int position) {
        tv_ask_num.setText(topicEntity.getAsker_desc());
        tv_word_num.setText(topicEntity.getReply_desc());
        tv_title.setText(topicEntity.getTopic_name());
        int color = Color.parseColor("#FDB4A2");

        try {
            color = Color.parseColor(topicEntity.getTopic_bg_color());
        } catch (Exception ee) {

        }
        view_bg_color.setBackgroundColor(color);
        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopicActivity.goQuestionActivity(context, topicEntity.getTopic_id());
            }
        });
    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        tv_ask_num = holder.getView(R.id.tv_ask_num);
        tv_title = holder.getView(R.id.tv_title);
        tv_word_num = holder.getView(R.id.tv_word_num);
        view_bg_color = holder.getView(R.id.view_bg_color);
    }

}
