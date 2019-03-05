package com.prettyyes.user.app.ui.person;

import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.MedalListRequest;
import com.prettyyes.user.api.netXutils.requests.UserMonthScoreOfYearRequest;
import com.prettyyes.user.api.netXutils.response.MedalListRes;
import com.prettyyes.user.api.netXutils.response.MonthScoreRes;
import com.prettyyes.user.app.adapter.MedalVpAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.AutoViewPager;
import com.prettyyes.user.app.view.app.BezierScoreView;
import com.prettyyes.user.app.view.pagetransformer.ScaleVpTransformer;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.HowScoreChangeEvent;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.v8.MedalEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class MedalAndScoreActivity extends BaseActivity implements NetReqCallback<MedalListRes> {

    private MedalVpAdapter inputabsVpAdapter;

    @Override
    public boolean isRemove_loadingLayout() {
        return true;
    }

    @ViewInject(R.id.bezier)
    private BezierScoreView bezier;
    @ViewInject(R.id.tv_more)
    private TextView tv_more;
    @ViewInject(R.id.view_go_score_detail)
    private View view_go_score_detail;

    @ViewInject(R.id.auto_vp)
    private AutoViewPager auto_vp;
    @ViewInject(R.id.tv_total_score)
    private TextView tv_total_score;

    @ViewInject(R.id.tv_this_month_score)
    private TextView tv_this_month_score;

    @ViewInject(R.id.tv_score_do_something)
    private TextView tv_score_do_something;


    @Override
    protected int bindLayout() {
        return R.layout.activity_my_medal_new;
    }

    @Override
    protected void initViews() {
        inputabsVpAdapter = new MedalVpAdapter(this);
        setActivtytitle("我的How值");
        inputabsVpAdapter.setViewPager(auto_vp.getVp());
        setRightTvListener("商城", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goHowShopActivity(getThis());
            }
        });


    }

    private List<Float> scores;

    @Override
    protected void loadData() {
        new UserMonthScoreOfYearRequest().post(new NetReqCallback<MonthScoreRes>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(MonthScoreRes res, String method) {

                AppRxBus.getInstance().post(new HowScoreChangeEvent(res.getHow_score()));

                tv_total_score.setText(res.getHow_score());
                tv_this_month_score.setText(res.getThis_month_score());
                tv_score_do_something.setText(res.getApply_txt());

                scores = new ArrayList<Float>();
                for (int i = 0; i < res.getList().size(); i++) {
                    scores.add(res.getList().get(i).getScore());
                }
                bezier.setPoints(scores);

            }
        });
        new MedalListRequest().post(this);
    }

    @Override
    protected void setListener() {
        super.setListener();
        tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goMedalListActivity(getThis());
            }
        });
        view_go_score_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goMyScoreRuleActivity(getThis());
            }
        });
        tv_score_do_something.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (tv_score_do_something.getText().toString().contains("去提现")) {
                JumpPageManager.getManager().goMyScoreRuleActivity(getThis());
//                }
            }
        });
    }

    @Override
    public void apiRequestFail(String message, String method) {

    }

    @Override
    public void apiRequestSuccess(MedalListRes medalListRes, String method) {
        auto_vp.setVpMargin(DensityUtil.dip2px(40), 0);
        auto_vp.setClip();
        auto_vp.setIsneedIndict(true);
        auto_vp.setNeedAutoScroll(false);

        auto_vp.getVp().setPageTransformer(true, new ScaleVpTransformer(0.95f, auto_vp.getVp()));
        List<MedalEntity> list = medalListRes.getList();
        List<MedalEntity> target = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i == 3)
                break;
            target.add(list.get(i));
        }
        auto_vp.getVp().setOffscreenPageLimit(target.size());
        inputabsVpAdapter.addAll((ArrayList<MedalEntity>) target);
        auto_vp.setAbsVpAdapter(inputabsVpAdapter);
        auto_vp.initIndicatorBottom();

    }


}
