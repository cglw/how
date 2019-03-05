package com.prettyyes.user.app.adapter.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.ui.appview.KolHistoryHomeListView;
import com.prettyyes.user.model.v8.HomeTaskEntity;

/**
 * Created by chengang on 2017/7/20.
 */

public class HomeKolActHistoryHolder extends MutiTypeViewHolder<HomeTaskEntity> {
    public HomeKolActHistoryHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(HomeTaskEntity modle, int position, RecyclerView.Adapter adpter) {
        KolHistoryHomeListView historyHomeListView = (KolHistoryHomeListView) getRootView();
        historyHomeListView.setFocusable(false);
        historyHomeListView.setData(modle.getData());
    }

    @Override
    public void bindView() {

    }
}
