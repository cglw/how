package com.prettyyes.user.app.adapter.tasks;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.core.PushHandler;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.model.v8.ScoreGetEntity;

/**
 * Created by chengang on 2017/12/27.
 */

public class ScoreGetHolder extends MutiTypeViewHolder<ScoreGetEntity> {
    public ScoreGetHolder(ViewGroup parent) {
        super(parent.getContext(), parent, R.layout.item_rv_get_score);
    }

    @Override
    public void bindData(final ScoreGetEntity modle, int position, RecyclerView.Adapter adpter) {
//        tv_title.setText(modle.getContent());
//        tv_complete.setText(modle.isComplete() ? "已完成" : "未完成");
//        tv_score.setText(String.format("%s分", modle.getScore()));

        tv_title.setText(modle.getText());
        tv_hint.setText(modle.getSub_head());
        tv_score.setText(modle.getScore() + "分");
        if ("seller".equals(modle.getClient())) {
            if (AccountDataRepo.getAccountManager().getAccount() != null && AccountDataRepo.getAccountManager().getAccount().isSeller())
                view_unclock.setVisibility(View.GONE);
            else
                view_unclock.setVisibility(View.VISIBLE);
        } else {
            view_unclock.setVisibility(View.GONE);
        }


        if (modle.getRule() == null || modle.getRule().length() < 1) {
            img_right.setVisibility(View.INVISIBLE);
        } else {
            img_right.setVisibility(View.VISIBLE);

        }
        if (modle.getRule() != null && modle.getRule().contains("login")) {
            if (SpMananger.getUUID() == null)
                img_right.setVisibility(View.VISIBLE);
            else
                img_right.setVisibility(View.INVISIBLE);
        }

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PushHandler(context).handReceiveData(modle.getRule());
            }
        });

    }

    @Override
    public void bindView() {
        tv_title = getView(R.id.tv_title);
        tv_score = getView(R.id.tv_score);
        tv_hint = getView(R.id.tv_hint);
        view_unclock = getView(R.id.ll_unclock);
        img_right = getView(R.id.img_right);

    }

    private TextView tv_title;
    private TextView tv_score;
    private TextView tv_hint;
    private View view_unclock;
    private View img_right;
}
