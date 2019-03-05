package com.prettyyes.user.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.holder_presenter.MutiSpuHolderViewImpl;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.QuestionEntity;


/**
 * Created by chengang on 2017/7/20.
 */

public class CommentHeadHolder extends MutiTypeViewHolder {
    public CommentHeadHolder(ViewGroup parent) {
        super(parent.getContext(), parent, R.layout.item_comment_head);
    }

    public CommentHeadHolder(ViewGroup parent, boolean activity) {
        super(parent);
    }


//
//    public CommentHeadHolder(ViewGroup parent, int layout_id) {
//        super(parent, layout_id);
//    }


    public void bindData(final AnswerInfoEntity answerInfo, final QuestionEntity questionEntity) {

        new CommentHeadHolderImpl(this, 0, answerInfo).setQuestionEntity(questionEntity).bindRoot();
       new MutiSpuHolderViewImpl(this, 0, answerInfo.getAnswer_data()).bindMutiSpu();


//        ll_task_head.setVisibility(View.VISIBLE);
//        setHeadData(questionEntity, 0);
//        if ((answerInfo != null &&
//                answerInfo.getAnswer_id() != null)
//                ) {
//            ll_task_bottom.setVisibility(View.VISIBLE);
//            setBottomData(answerInfo);
//
//        }
//        bottomInfoView.setVisibility(View.GONE);
//        tv_want_reply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                JumpPageManager.getManager().goKolReplyActivity(context, questionEntity.getTask_id());
//            }
//        });
    }


    @Override
    public void bindData(Object modle, int position, RecyclerView.Adapter adpter) {

    }

    @Override
    public void bindView() {

    }

}
