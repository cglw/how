package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.ui.appview.SuitListQuestionView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.model.Suit.AnswerListBySku;

/**
 * Created by chengang on 2017/5/3.
 */

public class SuitAnswerAdapter extends AbsRecycleAdapter<AnswerListBySku.DataBean> {
    public SuitAnswerAdapter(Context context) {
        super(context, R.layout.item_rv_suit_question_list);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final AnswerListBySku.DataBean dataBean, int position) {

        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goReplyDetailActivity(context,dataBean.getTask_id()+"",dataBean.getAnswer_id()+"");
            }
        });
        int result = position % 4;
        switch (result) {
            case 0:
                suitListQuestionView.setBg(R.drawable.how_question_container1);
                break;
            case 1:
                suitListQuestionView.setBg(R.drawable.how_question_container2);
                break;
            case 2:
                suitListQuestionView.setBg(R.drawable.how_question_container3);
                break;
            case 3:
                suitListQuestionView.setBg(R.drawable.how_question_container4);
                break;
        }
        suitListQuestionView.setDataByAnswerList(dataBean, R.id.activity_suit_to_quesion,img);

    }

    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public SuitListQuestionView getSuitListQuestionView() {
        return suitListQuestionView;
    }

    public void setSuitListQuestionView(SuitListQuestionView suitListQuestionView) {
        this.suitListQuestionView = suitListQuestionView;
    }

    private SuitListQuestionView suitListQuestionView;

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        suitListQuestionView = holder.getView(R.id.suitListQuestionView);

    }
}
