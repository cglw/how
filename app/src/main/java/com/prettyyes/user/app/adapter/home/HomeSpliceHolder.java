package com.prettyyes.user.app.adapter.home;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.model.v8.HomeTaskEntity;

/**
 * Created by chengang on 2017/7/20.
 */

public class HomeSpliceHolder extends MutiTypeViewHolder<HomeTaskEntity> {


    public HomeSpliceHolder(ViewGroup parent) {
        super(parent.getContext(), parent, R.layout.item_rv_home_splice);
    }

    @Override
    public void bindData(HomeTaskEntity data, int position, RecyclerView.Adapter adpter) {
        tv_time.setText(data.getUpdate_date());
        tv_desc.setText(String.format("我们从%s个问答中帮你选择了%s个", data.getNew_task(), data.getHome_task_count()));
    }

    @Override
    public void bindView() {
        tv_desc = getView(R.id.tv_splitview_desc);
        tv_time = getView(R.id.tv_splitview_time);
    }

    private TextView tv_time;
    private TextView tv_desc;

}
