package com.prettyyes.user.app.adapter.detail;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.SpecilAbsAdapter;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.LikeDisLikeHandler;
import com.prettyyes.user.model.task.TaskLikeList;

import java.util.ArrayList;


/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter
 * Author: SmileChen
 * Created on: 2016/8/11
 * Description: Nothing
 */
public class CollectQueListAdapter extends SpecilAbsAdapter<TaskLikeList.ListEntity> {


    public CollectQueListAdapter(Context context) {
        super(R.layout.item_lv_collectquestion, new ArrayList<TaskLikeList.ListEntity>(), context);
    }

    @Override
    public void showData(ViewHolder vHolder, final TaskLikeList.ListEntity data, final int position) {
        tv_collectQueFmt_name.setText(data.getNickname());
        tv_collectQueFmt_question.setText(data.getDesc());
        tv_collectQueFmt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel(data, position);
            }
        });
    }

    private void cancel(TaskLikeList.ListEntity data, int position) {
        new LikeDisLikeHandler().LikeDisLikeQuestion(BaseApplication.UUID, "like", 0, data.getTask_id());
        CollectQueListAdapter.this.remove(position);

    }

    @Override
    public void bindView(ViewHolder viewHolder) {
        tv_collectQueFmt_name = (TextView) viewHolder.getView(R.id.tv_collectQueFmt_name);
        tv_collectQueFmt_question = (TextView) viewHolder.getView(R.id.tv_collectQueFmt_question);
        tv_collectQueFmt_cancel = (TextView) viewHolder.getView(R.id.tv_collectQueFmt_cancel);
    }

    private TextView tv_collectQueFmt_name;
    private TextView tv_collectQueFmt_question;
    private TextView tv_collectQueFmt_cancel;


}
