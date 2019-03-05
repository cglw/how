package com.prettyyes.user.app.ui.spu;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.SuitApiImpl;
import com.prettyyes.user.app.adapter.detail.KolGoodsListAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.lvandgrid.SwipeRecycleView;
import com.prettyyes.user.model.Suit.SuitAnswerList;
import com.weavey.loading.lib.LoadingLayout;

import org.xutils.view.annotation.ViewInject;

import static com.prettyyes.user.AppConfig.MIN_PAGE_SIZE;

public class SuitListActivity extends BaseActivity {

    @ViewInject(R.id.swipelist_suitListAct)
    private SwipeRecycleView swipeListView;
    private KolGoodsListAdapter adapter;

    private int seller_id;

    @Override
    protected void initVariables() {
        super.initVariables();
        seller_id = getIntent().getIntExtra("seller_id", 0);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_suit_list;
    }

    @Override
    protected void initViews() {
        setActivtytitle("推荐套系");
        adapter = new KolGoodsListAdapter(this);
        swipeListView.setAdapter(adapter);
    }

    @Override
    protected void setListener() {
        super.setListener();
        swipeListView.setListener(new SwipeRecycleView.LoadCallback() {
            @Override
            public void loadList(int page) {
                getList(page);
            }
        });

    }


    private void getList(int page) {

        new SuitApiImpl().userGetSuitListBySellerId(getUUId(), seller_id + "", page, new NetReqCallback<SuitAnswerList>() {
            @Override
            public void apiRequestFail(String message, String method) {
                showToastShort(message);
                loadFail();
                loadingLayout.setStatus(LoadingLayout.Error);
            }

            @Override
            public void apiRequestSuccess(SuitAnswerList suitList, String method) {
                loadSuccess();
                if (suitList.getSuits().size() < MIN_PAGE_SIZE) {
                    swipeListView.loadingEnd();
                } else {
                    swipeListView.loadingSuccessHavedata();
                }
                adapter.addAll(suitList.getSuits());
            }
        });
    }

    @Override
    protected void loadData() {
        swipeListView.loadPageData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        adapter.clear();
        if (swipeListView != null)
            swipeListView.clearSelf();

    }
}
