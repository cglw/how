package com.prettyyes.user.app.ui.common;

import android.support.v7.widget.SearchView;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.app.base.AbsMutiRvAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.app.view.lvandgrid.SwipeRecycleView;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.SoftKeyboardUtil;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

public abstract class SingleListActivity<T> extends BaseActivity implements NetReqCallback<T> {

    @ViewInject(R.id.swipe_rv)
    public SwipeRecycleView swipeRv;
    @ViewInject(R.id.searchView)
    public SearchView searchView;
    public AbsRecycleAdapter adapter;
    public int page = 1;

    public boolean needsearch = false;
    private long lastInputTime;

    public void needSearch(boolean needsearch) {
        this.needsearch = needsearch;
    }

    @Override
    protected int bindLayout() {
        needSearch(false);
        if (needsearch)
            return R.layout.activity_simple_list;
        else
            return R.layout.layout_swipe;
    }

    protected abstract void requestData(int page);


    @Override
    protected void initViews() {
        setActivtytitle("");
        adapter = createAdapter();
        setAdapter();
        setDrividHeightPx(0);
        getRootView().setFocusable(true);
        getRootView().setFocusableInTouchMode(true);
        getRootView().requestFocus();


    }

    public DividerItemDecoration setDrividHeightPx(int height) {
        DividerItemDecoration dividerItemDecoration = setDrividHeightPx(height, R.color.backgroundWhit);
        return dividerItemDecoration;
    }

    public DividerItemDecoration setDrividHeightPx(int height, int color) {
        DividerItemDecoration decor = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST, AppUtil.dip2px(height), color);
        swipeRv.getRecycleView().addItemDecoration(decor);
        return decor;
    }

    public String inputText = "";

    public void setAdapter() {
        if (adapter != null)
            swipeRv.setAdapter(adapter);
    }


    public AbsRecycleAdapter createAdapter() {
        return new AbsMutiRvAdapter(this);
    }

    @Override
    protected void setListener() {
        super.setListener();
        if (adapter == null) {
            AppUtil.showToastShort("you should set adapter");
            return;
        }
        if (swipeRv != null)
            swipeRv.setListener(new SwipeRecycleView.LoadCallback() {
                @Override
                public void loadList(int page) {
                    SingleListActivity.this.page = page;
                    requestData(page);
                }
            });
        if (searchView != null)
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    inputText = query;
                    getRootView().setFocusable(true);
                    getRootView().setFocusableInTouchMode(true);
                    getRootView().requestFocus();
                    swipeRv.loadingFistEnter();
                    if (searchView != null)
                        SoftKeyboardUtil.closeInputMethod(getThis(), searchView);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    inputText = newText;
                    lastInputTime = System.currentTimeMillis();
                    return false;
                }
            });
    }

//    private String searchTxt = "";
//
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == 1 &&
//                    !lastSearchTxt.equals(inputText)
//                    && ((System.currentTimeMillis() - lastInputTime) > 1000)) {
//
//
//                lastSearchTxt = inputText;
//                handler.sendEmptyMessageDelayed(1, 1000);
//
//            }
//
//        }
//    };
//    private String lastSearchTxt = "";

    @Override
    protected void loadData() {
        if (swipeRv != null && adapter != null)
            swipeRv.loadPageData();
    }

    @Override
    public void apiRequestSuccess(T t, String method) {
        if (swipeRv != null)
            swipeRv.loadingSuccessHavedata();

        data = getListData(t);
        if (data != null) {
            adapter.addAll(data);
            loadend(data);
        }
        loadSuccess();

    }

    public void loadend(List data) {
        if (data.size() < AppConfig.MIN_PAGE_SIZE)
            swipeRv.loadingEnd();
    }

    public List data;

    public abstract List getListData(T t);

    @Override
    public void apiRequestFail(String message, String method) {
        LogUtil.i(TAG,"single_apiRequestFail"+ message);
        if (page == 1)
            loadFail();
        showSnack(message);
        if (swipeRv != null)
            swipeRv.loadingfail();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (swipeRv != null)
            swipeRv.clearSelf();

    }
}
