package com.prettyyes.user.app.fragments.collect;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.TaskImpl;
import com.prettyyes.user.app.adapter.detail.CollectKolActAdapter;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.view.lvandgrid.EmptyListener;
import com.prettyyes.user.app.view.lvandgrid.SwipeListByView;
import com.prettyyes.user.model.task.FollActList;

import org.xutils.view.annotation.ViewInject;

import static com.prettyyes.user.AppConfig.MIN_PAGE_SIZE;

public class CollectKolActFragment extends BaseFragment implements EmptyListener {
    @ViewInject(R.id.swipeList_collectkolActfmt)
    private SwipeListByView swipeListView;
    private CollectKolActAdapter collectKolListAdapter;

    @Override
    public int bindLayout() {
        return R.layout.fragment_collect_kol_act;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {

    }


    @Override
    public void initView(View view) {
        collectKolListAdapter = new CollectKolActAdapter(getActivity());
        swipeListView.setAdapter(collectKolListAdapter);
        swipeListView.setEmpty_listener(this);
    }

    @Override
    public void setListener() {
        swipeListView.setListener(new SwipeListByView.LoadCallback() {
            @Override
            public void loadList(int page) {
                getData(page);
            }
        });

    }

    private void getData(final int page) {
        new TaskImpl().followActList(getUUId(), page, new NetReqCallback<FollActList>() {
            @Override
            public void apiRequestFail(String message, String method) {
                swipeListView.loadingfail();
                if (page == 1)
                    loadFail();

            }

            @Override
            public void apiRequestSuccess(FollActList follactlist, String method) {
                loadSuccess();
                if (page == 1)
                    collectKolListAdapter.clear();
                if (follactlist == null) {
                    swipeListView.loadingEnd();
                    return;
                } else if (follactlist.getList().size() < MIN_PAGE_SIZE) {
                    swipeListView.loadingEnd();
                } else {
                    swipeListView.loadingSuccessHavedata();
                }
                collectKolListAdapter.addAll(follactlist.getList());

            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void lazyInitBusiness(Context mContext) {
        swipeListView.loadPageData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        swipeListView.clearSelf();
    }

    @Override
    public void setEmpty(LinearLayout ll, Button button) {
        TextView tv1 = (TextView) ll.getChildAt(0);
        TextView tv2 = (TextView) ll.getChildAt(1);
        TextView tv3 = (TextView) ll.getChildAt(2);
        tv1.setText("鸟儿愿为一朵云，云儿愿为一只鸟。");
        tv2.setText("——泰戈尔");
        tv3.setText("您目前还没关注任何一场专场活动，请耐心等待下一场活动的邀请函。");
        button.setVisibility(View.GONE);
    }
}
