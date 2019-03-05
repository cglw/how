package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.HomeTaskEntity;

/**
 * Created by chengang on 2017/8/1.
 */

public class MyReplyListAdapter extends AbsRecycleAdapter<HomeTaskEntity> {

    public MyReplyListAdapter(Context context) {
        super(context, R.layout.item_reply_question);
    }

    @Override
    protected void bindData(final AbsRecycleViewHolder holder, final HomeTaskEntity homeTaskEntity, int position) {
        tv_question.setText(homeTaskEntity.getDesc());
        final AnswerInfoEntity answer_info = homeTaskEntity.getMy_answer_info();
        if (answer_info != null) {
            tv_desc.setText(answer_info.getTs_reason());
        } else {
            tv_desc.setText("");
        }
        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnswerInfoEntity my_answer_info = homeTaskEntity.getMy_answer_info();
                if (my_answer_info != null)
                    JumpPageManager.getManager().goReplyDetailActivity(context, homeTaskEntity.getTask_id(),my_answer_info.getAnswer_id());
            }
        });

    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        tv_question = holder.getView(R.id.tv_question);
        tv_desc = holder.getView(R.id.tv_desc);
    }

    private TextView tv_question;
    private TextView tv_desc;
}
