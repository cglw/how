package com.prettyyes.user.app.adapter;

import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.holder_presenter.ReplyHolderViewImpl;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.ui.appview.QuestionTextView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.model.v8.AnswerInfoEntity;

/**
 * Created by chengang on 2017/8/16.
 */

public class CommentHeadHolderImpl extends ReplyHolderViewImpl {

    public CommentHeadHolderImpl(MutiTypeViewHolder mutiTypeViewHolder, int position, AnswerInfoEntity data) {
        super(mutiTypeViewHolder, position, data);
    }


    public QuestionTextView tv_question;
    private TextView tv_que_nickname;
    private TextView tv_reply_num;

    @Override
    public ReplyHolderViewImpl bindBottom() {
        tv_question = getView(R.id.tv_question);
        tv_reply_num = getView(R.id.tv_reply_num);
        tv_que_nickname = getView(R.id.tv_question_name);
        setBottomData();
        return this;
    }

    public void hideQuestion() {

        View parent = (View) tv_question.getParent();
        parent.setVisibility(View.GONE);
    }

    @Override
    public void setAnswerClick() {

    }

    @Override
    public void setBottomData() {
        if (questionEntity != null)
            tv_question.setTextWithData(new QuestionTextView.QuestionInnerModel()
                    .setDesc(questionEntity.getDesc())
                    .setHash_tag(questionEntity.getHash_tag())
                    .setTopic_hash_tag(questionEntity.getTopic_hash_tag())
                    .setTask_act_id(questionEntity.getTask_act_id())
                    .setTopic_id(questionEntity.getTopic_id()));
        if (questionEntity != null) {
            String format = "";
            if (questionEntity.getTask_count() - 1 > 0)
                format = String.format("%d个回复>", questionEntity.getTask_count());
            tv_reply_num.setText(format);
        }

        if (tv_que_nickname != null && questionEntity != null)
            tv_que_nickname.setText(String.format("%s正在问：", questionEntity.getNickname()));

        if (tv_question != null) {
            View parent = (View) tv_question.getParent();
            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (questionEntity != null)
                        JumpPageManager.getManager().goMoreSpuReply(context, questionEntity.getTask_id());
                }
            });
        }
        if (tv_reply_num != null)
            tv_reply_num.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (questionEntity != null)
                        JumpPageManager.getManager().goMoreSpuReply(context, questionEntity.getTask_id());
                }
            });


    }
}
