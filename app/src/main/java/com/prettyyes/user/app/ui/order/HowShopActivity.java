package com.prettyyes.user.app.ui.order;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.HowScoreGoodsListReq;
import com.prettyyes.user.api.netXutils.requests.HowScoreRequest;
import com.prettyyes.user.api.netXutils.response.HowScoreGoodsListRes;
import com.prettyyes.user.app.adapter.HowScoreGoodsHolder;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.app.view.recycle.HeaderSpanSizeLookup;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.ReceiveHowScoreGoodsSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.v8.HowScoreEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

public class HowShopActivity extends SingleListActivity<HowScoreGoodsListRes> {


    @ViewInject(R.id.tv_go_how_rule)
    private TextView tv_go_how_rule;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle("积分商城");
        getRight_tv().setTextSize(12);
        setRightTvListener("", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goMyScoreRuleActivity(getThis());

            }
        });
        GridLayoutManager layout = new GridLayoutManager(this, 2);

        swipeRv.getRecycleView().setLayoutManager(layout);
        layout.setSpanSizeLookup(new HeaderSpanSizeLookup(swipeRv.getHeaderAndFooterRecyclerViewAdapter(), layout.getSpanCount()));

        swipeRv.getRecycleView().addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.BOTH_SET, AppUtil.dip2px(8), R.color.backgroundWhit));

    }

    @Override
    protected void loadData() {
        super.loadData();
        getHowScore();

    }

    private void getHowScore() {
        new HowScoreRequest().post(new NetReqCallback<HowScoreEntity>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(HowScoreEntity howScoreEntity, String method) {
                setHowScore(howScoreEntity.getApply_score());
                try {
                    HowScoreGoodsHolder.how_apply_score = Float.parseFloat(howScoreEntity.getApply_score());
                } catch (Exception ee) {
                    HowScoreGoodsHolder.how_apply_score = 0;

                }
            }
        });
    }

    private void setHowScore(String how) {
        getRight_tv().setText(String.format("积分:%s", how));
    }

    @Override
    protected void setListener() {
        super.setListener();
        tv_go_how_rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goMyScoreRuleActivity(getThis());
            }
        });
        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<ReceiveHowScoreGoodsSuccessEvent>() {
            @Override
            protected void back(ReceiveHowScoreGoodsSuccessEvent obj) {
                getHowScore();
            }
        });
    }

    @Override
    public List getListData(HowScoreGoodsListRes howScoreGoods) {
        return howScoreGoods.getData();
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_how_shop;
    }

    @Override
    protected void requestData(int page) {
        new HowScoreGoodsListReq().setPage(page).post(this);
    }


}
