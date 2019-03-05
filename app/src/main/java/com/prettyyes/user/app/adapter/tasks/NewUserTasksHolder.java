package com.prettyyes.user.app.adapter.tasks;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.core.PushHandler;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.model.v8.TaskNewUserItemEntity;

/**
 * Created by chengang on 2017/12/27.
 */

public class NewUserTasksHolder extends MutiTypeViewHolder<TaskNewUserItemEntity> {
    public NewUserTasksHolder(ViewGroup parent) {
        super(parent.getContext(), parent, R.layout.item_rv_task_newuser);
    }

    @Override
    public void bindData(final TaskNewUserItemEntity modle, int position, RecyclerView.Adapter adpter) {
        tv_title.setText(modle.getContent());
        tv_complete.setText(modle.isComplete() ? "已完成" : "未完成");
        tv_score.setText(String.format("%s分", modle.getScore()));
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PushHandler(context).handReceiveData(modle.getRule());

            }
        });

        if (modle.getRule() != null && modle.getRule().contains("login")) {
            if (SpMananger.getUUID() == null)
                img_right.setVisibility(View.VISIBLE);
            else
                img_right.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void bindView() {
        tv_title = getView(R.id.tv_title);
        tv_complete = getView(R.id.tv_complete);
        tv_score = getView(R.id.tv_score);
        img_right = getView(R.id.img_right);

    }

    private TextView tv_title;
    private TextView tv_complete;
    private TextView tv_score;
    private ImageView img_right;
}
