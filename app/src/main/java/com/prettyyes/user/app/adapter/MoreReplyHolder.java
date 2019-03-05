package com.prettyyes.user.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.holder_presenter.MoreReplyHolderViewImpl;
import com.prettyyes.user.app.adapter.holder_presenter.MutiSpuHolderViewImpl;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.ui.comment.MoreReplyActivity;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.model.v8.AnswerInfoEntity;

/**
 * Created by chengang on 2017/8/10.
 */

public class MoreReplyHolder extends MutiTypeViewHolder<AnswerInfoEntity> {
    public MoreReplyHolder(ViewGroup parent) {
        super(parent, R.layout.item_rv_more_reply_v8);
    }

    @Override
    public void bindData(final AnswerInfoEntity modle, final int position, RecyclerView.Adapter adpter) {

        new MutiSpuHolderViewImpl(this, position, modle.getAnswer_data()).bindMutiSpu();
        new MoreReplyHolderViewImpl(this, position, modle).bindRoot();
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((context instanceof MoreReplyActivity)) {

//                    JumpPageManager.getManager().goReplyDetailActivity(context,
//                            ((MoreReplyActivity) context).getAnswers(),
//                            ((MoreReplyActivity) context).getTaskInfo(),
//                            position, ((MoreReplyActivity) context).getPage());

                    JumpPageManager.getManager().goReplyDetailActivity(context,modle.getTask_id(),modle.getAnswer_id());
                }
            }
        });

    }


    @Override
    public void bindView() {
    }

}
