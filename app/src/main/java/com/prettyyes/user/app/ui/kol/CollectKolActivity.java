package com.prettyyes.user.app.ui.kol;

import android.view.View;
import android.widget.AdapterView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.detail.CollectKolListAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpPresenter.CollectKolPresenter;
import com.prettyyes.user.app.mvpView.CollectKolView;
import com.prettyyes.user.app.view.lvandgrid.SwipeListView;

import org.xutils.view.annotation.ViewInject;

public class CollectKolActivity extends BaseActivity implements CollectKolView {

    @ViewInject(R.id.swipeList_collectkolAct)
    private SwipeListView swipeListView_kollist;
    private CollectKolListAdapter collectKolListAdapter;
    public CollectKolPresenter collectKolPresenter = new CollectKolPresenter(this);

    @Override
    protected int bindLayout() {
        return R.layout.activity_collect_kol;
    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected void initViews() {
        setActivtytitle(getString(R.string.collect_kol));
        collectKolListAdapter = new CollectKolListAdapter(this);
        swipeListView_kollist.setAdapter(collectKolListAdapter);
    }

    @Override
    protected void setListener() {
        swipeListView_kollist.setListener(new SwipeListView.LoadCallback() {
            @Override
            public void loadList(int page) {
                collectKolPresenter.getCollectKollist(page);
            }
        });
        swipeListView_kollist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position <= collectKolListAdapter.getCount() - 1)
                    collectKolPresenter.goKolInfoActivity(position);
            }
        });
    }

    @Override
    protected void loadData() {
        swipeListView_kollist.loadPageData();
    }

    @Override
    public void showFailedError(String tv) {
        showToastShort(tv);
    }

    @Override
    public BaseActivity getThis() {
        return this;
    }

    @Override
    public SwipeListView getSwipe() {
        return swipeListView_kollist;
    }

    @Override
    public CollectKolListAdapter getAdapter() {
        return collectKolListAdapter;
    }
}


