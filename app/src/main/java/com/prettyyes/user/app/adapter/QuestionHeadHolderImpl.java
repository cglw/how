package com.prettyyes.user.app.adapter;

import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.holder_presenter.QuestionHolderViewImpl;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.core.containter.JumpPageManager;

/**
 * Created by chengang on 2017/8/30.
 */

public class QuestionHeadHolderImpl extends QuestionHolderViewImpl {


    private TextView tv_reply_num;

    public QuestionHeadHolderImpl(MutiTypeViewHolder mutiTypeViewHolder) {
        super(mutiTypeViewHolder);
    }

    @Override
    public QuestionHolderViewImpl bindQuestion() {
        tv_reply_num = getView(R.id.tv_reply_num);
        return super.bindQuestion();
    }

    @Override
    public void setQuestion() {
        super.setQuestion();
        bindQueOther();

        if (data != null) {
            String format = "";
            if (data.getTask_count() > 0)
                format = String.format("%d个回复>", data.getTask_count());
            tv_reply_num.setText(format);
        }
        if (tv_que_name != null && data != null)
            tv_que_name.setText(String.format("%s正在问：", data.getNickname()));
        tv_reply_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goMoreSpuReply(context, data.getTask_id());
            }
        });
        tv_que_desc.setOnClickListener(null);

    }


}
