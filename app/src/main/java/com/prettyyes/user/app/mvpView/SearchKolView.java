package com.prettyyes.user.app.mvpView;

import com.prettyyes.user.app.adapter.detail.SearchSellerListAdapter;
import com.prettyyes.user.app.view.lvandgrid.SwipeListView;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpView
 * Author: SmileChen
 * Created on: 2016/11/7
 * Description: Nothing
 */
public interface SearchKolView extends BaseView{
    String getSearchString();
    SearchSellerListAdapter getAdapter();
    SwipeListView getLv();
}
