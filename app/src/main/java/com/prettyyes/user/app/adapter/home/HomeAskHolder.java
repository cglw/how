package com.prettyyes.user.app.adapter.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.ui.appview.HomeAskView;
import com.prettyyes.user.app.ui.how.AskActivity;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.model.v8.HomeTaskEntity;

/**
 * Created by chengang on 2017/7/20.
 */

public class HomeAskHolder extends MutiTypeViewHolder<HomeTaskEntity> {

    public HomeAskHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(HomeTaskEntity data, final int position, final RecyclerView.Adapter adpter) {
        HomeAskView rootView = (HomeAskView) itemView;
        rootView.setFocusable(false);
        rootView.setAskAndColor(data.getAsk_text(), data.getAsk_color());

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AskActivity.goAskActivity(context);
                AppStatistics.onEvent(context, "ask_guide", "cell_index", position + "");
            }
        });

    }

    @Override
    public void bindView() {

    }
}
