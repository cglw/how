package com.prettyyes.user.app.fragments;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.app.adapter.RewardAdapter;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.view.lvandgrid.SwipeListByView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.model.type.RewardListEntity;

import org.xutils.view.annotation.ViewInject;

import static com.prettyyes.user.AppConfig.MIN_PAGE_SIZE;
import static com.prettyyes.user.app.account.Constant.TYPE_PAPER;


public class RewardPaperFragment extends BaseFragment {

    @ViewInject(R.id.swipeList_rewardfragment)
    private SwipeListByView swipeListView;
    private RewardAdapter rewardAdapter;

    @Override
    public int bindLayout() {
        return R.layout.fragment_reward_paper;
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
        rewardAdapter = new RewardAdapter(getActivity());
        swipeListView.setAdapter(rewardAdapter);
        swipeListView.getListView().setDivider(new ColorDrawable(ContextCompat.getColor(getContext(),R.color.backgroundWhit)));
        swipeListView.getListView().setDividerHeight(AppUtil.dip2px(8));

    }

    @Override
    public void setListener() {
        super.setListener();
        swipeListView.setListener(new SwipeListByView.LoadCallback() {
            @Override
            public void loadList(int page) {
                getListData(page);
            }
        });
        swipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!ClickUtils.isFastDoubleClick())
                    JumpPageManager.getManager().goSkuActivity(getActivity(), TYPE_PAPER, rewardAdapter.get(position).getReward().getPaper_id() + "");
//                    PaperWebviewActivity.goPaperActivity((BaseActivity) getActivity(), rewardAdapter.get(position).getReward().getPaper_id()
//                    );
            }
        });
    }

    private void getListData(final int page) {
        new OrderApiImpl().getRewardList(getUUId(), page, new NetReqCallback<RewardListEntity>() {
            @Override
            public void apiRequestFail(String message, String method) {
                swipeListView.loadingfail();
                if (page == 1)
                    loadFail();
            }

            @Override
            public void apiRequestSuccess(RewardListEntity rewardListEntity, String method) {
                loadSuccess();
                if (rewardListEntity.getInfo().size() < MIN_PAGE_SIZE) {
                    swipeListView.loadingEnd();
                } else {
                    swipeListView.loadingSuccessHavedata();
                }
                rewardAdapter.addAll(rewardListEntity.getInfo());
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
        if (swipeListView != null)
            swipeListView.clearSelf();
    }
}
