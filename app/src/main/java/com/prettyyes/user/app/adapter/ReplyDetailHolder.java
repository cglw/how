package com.prettyyes.user.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.holder_presenter.MutiSpuHolderViewImpl;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.QuestionEntity;

/**
 * Created by chengang on 2017/8/24.
 */

public class ReplyDetailHolder extends MutiTypeViewHolder {


    public ReplyDetailHolder(ViewGroup parent) {
        super(parent, R.layout.item_comment_head);
    }

    @Override
    public void bindData(Object modle, int position, RecyclerView.Adapter adpter) {


    }

    public void bindData(final AnswerInfoEntity answerInfo, final QuestionEntity questionEntity) {
        CommentHeadHolderImpl replyHolderView = (CommentHeadHolderImpl) new CommentHeadHolderImpl(this, 0, answerInfo).setQuestionEntity(questionEntity).bindRoot();
        replyHolderView.hideQuestion();
        new MutiSpuHolderViewImpl(this, 0, answerInfo.getAnswer_data()).bindMutiSpu();

    }


    @Override
    public void bindView() {

    }
}
