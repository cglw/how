package com.prettyyes.user.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.SuitApiImpl;
import com.prettyyes.user.api.netXutils.ApiImpls.TaskImpl;
import com.prettyyes.user.app.adapter.CollectGoodsAdapter;
import com.prettyyes.user.app.adapter.TopicCollectAdapter;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.view.lvandgrid.SwipeRecycleView;
import com.prettyyes.user.model.Suit.SkuCollectEntity;
import com.prettyyes.user.model.TopicCollectRes;

import org.xutils.view.annotation.ViewInject;

import static com.prettyyes.user.AppConfig.MIN_PAGE_SIZE;


public class SimpleListFragment extends BaseFragment {


    @ViewInject(R.id.swipeList)
    private SwipeRecycleView swipeListView;

    private String type = "goods";
    private CollectGoodsAdapter collectAdapter;
    private TopicCollectAdapter topicCollectAdapter;

    public static SimpleListFragment newInstance(String type) {
        SimpleListFragment simple = new SimpleListFragment();
        Bundle args = new Bundle();
        args.putString("type", type);
        simple.setArguments(args);
        return simple;
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
        type = parms.getString("type");
    }

    @Override
    public void initView(View view) {

        switch (type) {
            case "goods":
                collectAdapter = new CollectGoodsAdapter(getActivity());
                swipeListView.setAdapter(collectAdapter);
                break;
            case "topic":
                topicCollectAdapter = new TopicCollectAdapter(getActivity());
                swipeListView.setAdapter(topicCollectAdapter);
                break;

        }
    }

    @Override
    public void doBusiness(Context mContext) {
    }

    @Override
    public void setListener() {
        super.setListener();
        swipeListView.setListener(new SwipeRecycleView.LoadCallback() {
            @Override
            public void loadList(int page) {
                getList(page);
            }
        });
    }

    private void getList(int page) {
        if (type.equals("goods"))
            new SuitApiImpl().suitFavouriteSuitList(getUUId(), page, new NetReqCallback<SkuCollectEntity>() {
                @Override
                public void apiRequestFail(String message,String method) {
                    swipeListView.loadingfail();
                }

                @Override
                public void apiRequestSuccess(SkuCollectEntity skuCollectEntity, String method) {
//                    if (skuCollectEntity.getList().size() < 5) {
//                        swipeListView.loadingEnd();
//                    } else {
//                        swipeListView.loadingSuccessHavedata();
//                    }
//                    collectAdapter.addAll(skuCollectEntity.getList());
//                    if (skuCollectEntity.getList().size() < 5)
//                        swipeListView.loadingEndWithout();
                }
            });
        else if (type.equals("topic")) {
            new TaskImpl().followTopicList(getUUId(), page, new NetReqCallback<TopicCollectRes>() {
                @Override
                public void apiRequestFail(String message,String method) {
                    swipeListView.loadingfail();
                }

                @Override
                public void apiRequestSuccess(TopicCollectRes topicCollectRes,String method) {
                    swipeListView.loadingSuccessHavedata();
                    topicCollectAdapter.addAll(topicCollectRes.getList());
                    if (topicCollectRes.getList().size() < MIN_PAGE_SIZE) {
                        swipeListView.loadingEnd();
                    }

                }

            });

        }

    }

    @Override
    public void lazyInitBusiness(Context mContext) {
        swipeListView.loadPageData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (swipeListView != null)
            swipeListView.clearSelf();
    }
}
