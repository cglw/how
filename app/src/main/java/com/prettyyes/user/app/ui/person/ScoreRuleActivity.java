package com.prettyyes.user.app.ui.person;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.HowScoreRequest;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.appview.WithdrawHowScoreDialog;
import com.prettyyes.user.app.view.tvbtnetv.CustumBgTextView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.model.v8.HowScoreEntity;

import org.xutils.view.annotation.ViewInject;

import static com.prettyyes.user.AppConfig.HOW_RULE;

public class ScoreRuleActivity extends BaseActivity {

    @ViewInject(R.id.view_lool_detail)
    private View view_lool_detail;
    @ViewInject(R.id.tv_score)
    private TextView tv_score;
    @ViewInject(R.id.custumtv_withdraw)
    private CustumBgTextView custumtv_withdraw;
    @ViewInject(R.id.custumtv_set_accout)
    private CustumBgTextView custumtv_set_accout;
    @ViewInject(R.id.tv_total_score)
    private TextView tv_total_score;
    private HowScoreEntity howScoreEntity;

    @Override
    public boolean isRemove_loadingLayout() {
        return true;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_score_detail;
    }

    @Override
    protected void initViews() {
        setActivtytitle("How值详情");
        setRightTvListener("规则", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_how_rule();
            }
        });

    }

    public void go_how_rule() {
        JumpPageManager.getManager().goWebActivity(this, HOW_RULE);
    }

    @Override
    protected void setListener() {
        super.setListener();
        view_lool_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goMyScoreListDetailActivity(getThis());
            }
        });
        custumtv_set_accout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goMyAccountActivity(getThis());
            }
        });
        custumtv_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (howScoreEntity != null)
                    new WithdrawHowScoreDialog(getThis(), howScoreEntity.getApply_money() + "", new WithdrawHowScoreDialog.WithDrawCallback() {
                        @Override
                        public void withdrawSuccess() {
                            loadData();
                        }
                    }).show();
            }
        });
    }

    @Override
    protected void loadData() {
        new HowScoreRequest().post(new NetReqCallback<HowScoreEntity>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(HowScoreEntity howScoreEntity, String method) {
                ScoreRuleActivity.this.howScoreEntity = howScoreEntity;
                if (howScoreEntity != null) {
                    tv_total_score.setText(String.format("How总值：%s", howScoreEntity.getHow_score()));
                    tv_score.setText(howScoreEntity.getApply_score() + "");
                    custumtv_withdraw.setText(String.format("可兑换：%s元", howScoreEntity.getApply_money()));
                    if (Float.parseFloat(howScoreEntity.getApply_money()) > 0) {
                        custumtv_withdraw.setSolidColor(ContextCompat.getColor(getThis(), R.color.withdraw_enable));
                    } else {
                        custumtv_withdraw.setSolidColor(ContextCompat.getColor(getThis(), R.color.withdraw_unenable));
                    }
//                    RxBus.getInstance().post(new HowScoreChangeEvent(howScoreEntity.getHow_score() + ""));

                }
            }
        });
    }
}
