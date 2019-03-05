package com.prettyyes.user.app.adapter.searchholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.AnswerInfoEntity;

/**
 * Created by chengang on 2017/7/14.
 */

public class SearchQueHolder extends MutiTypeViewHolder<SearchQueModel> {


    public SearchQueHolder(Context context, ViewGroup parent) {
        super(context, parent, R.layout.item_search_ques);
    }

    @Override
    public void bindData(final SearchQueModel modle, int position, RecyclerView.Adapter adpter) {

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer_id = "";
                if (modle.getAnswer_list() != null && modle.getAnswer_list().size() > 0) {
                    AnswerInfoEntity answerInfoEntity = modle.getAnswer_list().get(0);
                    answer_id = answerInfoEntity.getAnswer_id();
                }
                AppStatistics.onEvent(getActivity(),"search_task;"+answer_id);

                JumpPageManager.getManager().goReplyDetailActivity(context, modle.getTask().getTask_id(), answer_id);
            }
        });

        SpannableString desc = StringUtils.matcherSearchTitle(modle.getTask().getDesc(), modle.getKey_word());
        if (modle.getAnswer_list() != null && modle.getAnswer_list().size() > 0) {
            AnswerInfoEntity answerInfoEntity = modle.getAnswer_list().get(0);
            if (answerInfoEntity != null) {
                SpannableString ts_reason = StringUtils.matcherSearchTitle(answerInfoEntity.getTs_reason(), modle.getKey_word());
                tv_que_ts_reason.setText(ts_reason);

            }
        } else
            tv_que_ts_reason.setText("暂无回复");
        tv_que_title.setText(desc);
        String format = "%s个回复  %s个收藏";
        tv_que_num.setText(String.format(format, modle.getTask().getTip(), modle.getTask().getLike_num() + ""));
    }

    @Override
    public void bindView() {
        tv_que_title = getView(R.id.tv_que_title);
        tv_que_ts_reason = getView(R.id.tv_que_ts_reason);
        tv_que_num = getView(R.id.tv_que_num);

    }

    private TextView tv_que_title;
    private TextView tv_que_ts_reason;
    private TextView tv_que_num;
}