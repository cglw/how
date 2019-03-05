package com.prettyyes.user.app.ui.common;

import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.lvandgrid.SwipeRecycleView;
import com.prettyyes.user.core.utils.AppUtil;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/6/26.
 */

public abstract class MutiListActivity extends BaseActivity  {

    ArrayList<SwipeRecycleView> swipeRecycleViews = new ArrayList<>();
    ArrayList<AbsRecycleAdapter> absRecycleAdapters = new ArrayList<>();

    @Override
    protected int bindLayout() {
        return 0;
    }

    public abstract void addSwipes();

    public abstract void addabsRecycleAdapters();

    @Override
    protected void initViews() {
        addSwipes();
        addabsRecycleAdapters();
        if (swipeRecycleViews.size() != absRecycleAdapters.size()) {
            AppUtil.showToastShort("rv size not equals adapter size");
            return;
        }
        for (int i = 0; i < swipeRecycleViews.size(); i++) {
            swipeRecycleViews.get(i).setAdapter(absRecycleAdapters.get(i));
        }


    }

    private int index = 0;

    @Override
    protected void setListener() {
        super.setListener();
        for (int i = 0; i < swipeRecycleViews.size(); i++) {
            index = i;
            swipeRecycleViews.get(i).setListener(new SwipeRecycleView.LoadCallback() {
                @Override
                public void loadList(int page) {
                    requestData(index, page);
                }
            });
        }
    }


    protected abstract void requestData(int index, int page);

    @Override
    protected void loadData() {
        for (int i = 0; i <swipeRecycleViews.size() ; i++) {
            swipeRecycleViews.get(i).loadPageData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (int i = 0; i <swipeRecycleViews.size() ; i++) {
            swipeRecycleViews.get(i).clearSelf();
        }
    }
}
