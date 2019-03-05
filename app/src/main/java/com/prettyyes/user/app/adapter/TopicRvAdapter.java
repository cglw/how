package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.detail.CommonReplyAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.ui.appview.BottomDoView2;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.HomeTaskEntity;

/**
 * Created by chengang on 2017/5/25.
 */

public class TopicRvAdapter extends CommonReplyAdapter<HomeTaskEntity> {


    public TopicRvAdapter(Context context) {
        super(context, R.layout.item_rv_topic);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final HomeTaskEntity data, int position) {
        super.bindData(holder, data, position);

        setCenterData(holder, data.getAnswer_info(), position);
        setQuestionData(data);

        if (data.getAnswer_info() == null || data.getAnswer_info().getAnswer_id() == null) {
            ll_reply.setVisibility(View.GONE);

        } else {
            ll_reply.setVisibility(View.VISIBLE);
        }

        ll_comment.setVisibility(View.GONE);
        bottomView2_mocmtAdp.setCommentNum(data.getAnswer_info().getComment_count());
        questionView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goMoreSpuReply(context, data.getTask_id());
            }
        });

        bottomView2_mocmtAdp.getCommentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goCommentActivity(context, data.getAnswer_info().getAnswer_id(),data.getTask_id());
            }
        });


    }

    @Override
    protected void bindView(AbsRecycleViewHolder vHolder) {
        super.bindView(vHolder);
        ll_reply = vHolder.getView(R.id.ll_reply);
    }

    private LinearLayout ll_reply;

    @Override
    public void goDetail(AnswerInfoEntity replyModel) {
        DataCenter.SELLRT_ID_CURRENT = Integer.parseInt(replyModel.getSeller_id());
        DataCenter.ANSWER_ID_CURRENT = Integer.parseInt(replyModel.getAnswer_id());
        JumpPageManager.getManager().goMoreSpuReply(context, replyModel.getTask_id());

    }

    @Override
    public void setCommentData(LinearLayout ll_comment, View view_line, BottomDoView2 bottomDoView2, AnswerInfoEntity data) {

    }
}
