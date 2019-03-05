package com.prettyyes.user.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.app.base.AbsMutiRvAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.app.view.lvandgrid.SwipeRecycleView;
import com.prettyyes.user.core.utils.AppUtil;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

import static android.view.View.OVER_SCROLL_NEVER;

/**
 * Created by chengang on 2017/7/10.
 */

public abstract class SingleListFragment<T> extends BaseFragment implements NetReqCallback<T> {

    public int page = 1;
    @ViewInject(R.id.swipe_rv)
    public SwipeRecycleView swipeRv;
    public AbsRecycleAdapter adapter;


    public SwipeRecycleView getSwipeRv() {
        return swipeRv;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_simple_list;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void setListener() {
        super.setListener();
        swipeRv.setListener(new SwipeRecycleView.LoadCallback() {
            @Override
            public void loadList(int page) {
                requestPageData(page);
                SingleListFragment.this.page = page;
            }
        });
    }

    public abstract void requestPageData(int page);

    @Override
    public void initView(View view) {
        adapter = createAdapter();
        swipeRv.getRecycleView().setOverScrollMode(OVER_SCROLL_NEVER);
        if (adapter != null)
            swipeRv.setAdapter(adapter);
        setDrividHeightPx(0);


    }

    public DividerItemDecoration setDrividHeightPx(int height) {
        DividerItemDecoration dividerItemDecoration = setDrividHeightPx(height, R.color.backgroundWhit);
        return dividerItemDecoration;
    }

    public DividerItemDecoration setDrividHeightPx(int height, int color) {
        DividerItemDecoration decor = new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST, AppUtil.dip2px(height), color);
        swipeRv.getRecycleView().addItemDecoration(decor);
        return decor;
    }

    public AbsRecycleAdapter createAdapter() {
        return new AbsMutiRvAdapter(getContext());
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void lazyInitBusiness(Context mContext) {
        if (swipeRv != null)
            swipeRv.loadPageData();

    }

    @Override
    public void apiRequestFail(String message, String method) {
        if (swipeRv != null)
            swipeRv.loadingfail();
        showSnake(message);
        if (page == 1)
            loadFail();
    }

    @Override
    public void apiRequestSuccess(T t, String method) {
        if (page == 1)
            swipeRv.getRecycleView().scrollToPosition(0);
        if (swipeRv != null)
            swipeRv.loadingSuccessHavedata();
        data = getListData(t);
        if (data != null) {
            adapter.addAll(data);
            if (data.size() < AppConfig.MIN_PAGE_SIZE)
                swipeRv.loadingEnd();

        }
        loadSuccess();

    }

    public List getListData(T t) {
        if (t instanceof List)
            return (List) t;
        return null;
    }


    public List<Object> data;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (swipeRv != null)
            swipeRv.clearSelf();
    }
}
