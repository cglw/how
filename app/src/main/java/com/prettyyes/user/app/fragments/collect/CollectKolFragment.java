package com.prettyyes.user.app.fragments.collect;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.app.adapter.detail.CollectKolListAdapter;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.app.view.lvandgrid.EmptyListener;
import com.prettyyes.user.app.view.lvandgrid.SwipeListView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.model.user.UserLikeseller;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import static com.prettyyes.user.AppConfig.MIN_PAGE_SIZE;


public class CollectKolFragment extends BaseFragment implements EmptyListener {
    @ViewInject(R.id.swipeList_collectkolfmt)
    private SwipeListView swipeListView;
    private CollectKolListAdapter collectKolListAdapter;

    @Override
    public int bindLayout() {
        return R.layout.fragment_collect_kol;
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
        collectKolListAdapter = new CollectKolListAdapter(getActivity());
        swipeListView.setAdapter(collectKolListAdapter);
        swipeListView.setEmpty_listener(this);
    }

    @Override
    public void setListener() {
        swipeListView.setListener(new SwipeListView.LoadCallback() {
            @Override
            public void loadList(int page) {
                getData(page);
            }
        });
        swipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int seller_id = collectKolListAdapter.get(position).getSeller_id();
                if (position <= collectKolListAdapter.getCount() - 1) {
                    JumpPageManager.getManager().goKolInfoActivity(getActivity(), seller_id + "");
                }
            }
        });
    }

    private void getData(final int page) {
        new UserApiImpl().userLikeSeller(getUUId(), page, new NetReqCallback<UserLikeseller>() {
            @Override
            public void apiRequestFail(String message, String method) {
                swipeListView.loadingfail();
                if (page == 1)
                    loadFail();
            }

            @Override
            public void apiRequestSuccess(UserLikeseller userLikeseller, String method) {
                loadSuccess();
                if (userLikeseller == null) {
                    swipeListView.loadingEnd();
                    return;
                } else if (userLikeseller.getList().size() < MIN_PAGE_SIZE) {
                    swipeListView.loadingEnd();
                } else {
                    swipeListView.loadingSuccessHavedata();
                }
                collectKolListAdapter.addAll((ArrayList<UserLikeseller.ListEntity>) userLikeseller.getList());

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
        button.setVisibility(View.VISIBLE);
        TextView tv1 = (TextView) ll.getChildAt(0);
        TextView tv2 = (TextView) ll.getChildAt(1);
        TextView tv3 = (TextView) ll.getChildAt(2);
        tv1.setText("我的心挂在树上，你摘便是。");
        tv2.setText("——艾侣霞");
        tv3.setText("您还没有收藏任何一位好手，但思维的火花会因你们的进一步接触而愈演愈烈。");
        button.setText("去收藏");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.goMainActivity(getActivity(), 1);
            }
        });

    }
}
