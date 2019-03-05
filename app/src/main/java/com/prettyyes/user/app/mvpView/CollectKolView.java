package com.prettyyes.user.app.mvpView;

import com.prettyyes.user.app.adapter.detail.CollectKolListAdapter;
import com.prettyyes.user.app.view.lvandgrid.SwipeListView;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpView
 * Author: SmileChen
 * Created on: 2016/11/4
 * Description: Nothing
 */
public interface CollectKolView extends BaseView {
    public SwipeListView getSwipe();
    public CollectKolListAdapter getAdapter();
}
