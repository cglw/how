package com.prettyyes.user.app.ui.person;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.RichRequest;
import com.prettyyes.user.api.netXutils.response.RichesRes;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.event.ChangeAccountSuccess;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.v8.AliAccountEntity;
import com.prettyyes.user.model.v8.BankEntity;

public class MyAccountActivity extends BaseActivity {


    @Override
    protected int bindLayout() {
        return R.layout.activity_my_account;
    }

    @Override
    protected void initViews() {
        bindViews();
        setActivtytitle(R.string.title_activity_myaccount);
        setRightTvListener("提现", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goWithdrawRecordActivity(getThis());
            }
        });

    }

    @Override
    protected void loadData() {

        new RichRequest().post(new NetReqCallback<RichesRes>() {
            @Override
            public void apiRequestFail(String message, String method) {
                loadFail();
                showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(RichesRes richesRes, String method) {
                setData(richesRes);
                loadSuccess();
            }
        });

    }

    private TextView tv_get_money;
    private TextView tv_reward_money;
    private TextView tv_total_money;
    private TextView tv_alipy;
    private TextView tv_bank;
    private TextView tv_rules;

    private void bindViews() {
        tv_get_money = (TextView) findViewById(R.id.tv_get_money);
        tv_reward_money = (TextView) findViewById(R.id.tv_reward_money);
        tv_total_money = (TextView) findViewById(R.id.tv_total_money);
        tv_alipy = (TextView) findViewById(R.id.tv_alipy);
        tv_bank = (TextView) findViewById(R.id.tv_bank);
        tv_rules = (TextView) findViewById(R.id.tv_rules);
    }

    public void withdraw() {
        JumpPageManager.getManager().goWithdrawRecordActivity(this);
//        new ApplyRichesRequest().post(new NetReqCallback<Object>() {
//            @Override
//            public void apiRequestFail(String message, String method) {
//                showSnack(message);
//            }
//
//            @Override
//            public void apiRequestSuccess(Object o, String method) {
//                showSnack("提现成功");
//            }
//        });

    }

    @Override
    protected void setListener() {
        ((View) (tv_reward_money.getParent())).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                withdraw();
            }
        });
        ((View) tv_bank.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bankmodel.getBank_card_string() != null) {
                    JumpPageManager.getManager().goBankAccount(getThis(), bankmodel);
                } else
                    JumpPageManager.getManager().goBankAccount(getThis(), null);
            }
        });
        ((View) tv_alipy.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aliAccount.getAlipay_account() != null)
                    JumpPageManager.getManager().goAliAccount(getThis(), aliAccount);
                else
                    JumpPageManager.getManager().goAliAccount(getThis(), null);

            }
        });
//        mSubscription = AppRxBus.getInstance().toObservable(ChangeAccountSuccess.class).subscribe(new RxAction1<ChangeAccountSuccess>() {
//            @Override
//            public void callback(ChangeAccountSuccess changeAccountSuccess) {
//                loadData();
//            }
//        });
        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<ChangeAccountSuccess>() {
            @Override
            protected void back(ChangeAccountSuccess obj) {
                loadData();

            }
        });
        tv_rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ZBundleCore.getInstance().getTopActivity());
                builder.setMessage(getString(R.string.rule_get_money))
                        .setPositiveButton("确定", null)
                        .setTitle("提现规则")
                        .setCancelable(false);
                builder.show();

            }
        });
    }

    public void setData(RichesRes data) {
        RichesRes.InfoBean info = data.getInfo();
        tv_get_money.setText(String.format("¥ %s", info.getGet_money()));
        tv_reward_money.setText(String.format("本周收益：¥ %s", info.getWeek_money()));
        tv_total_money.setText(String.format("总资产：¥ %s", info.getTotal_money()));
        tv_alipy.setText(info.getAlipay_account());
        tv_bank.setText(info.getBank_card_string());

        bankmodel.setBank_card(info.getBank_card());
        bankmodel.setBank_card_string(info.getBank_card_string());
        bankmodel.setBank_id(info.getBank_id());
        bankmodel.setBank_name(info.getBank_name());
        bankmodel.setBank_telephone(info.getBank_telephone());
        bankmodel.setBank_uname(info.getBank_uname());
        bankmodel.setBank_branch(info.getBank_branch());

        aliAccount.setAlipay_account(info.getAlipay_account());
        aliAccount.setAlipay_telephone(info.getAlipay_telephone());
        aliAccount.setAlipay_username(info.getAlipay_username());

    }

    private BankEntity bankmodel = new BankEntity();
    private AliAccountEntity aliAccount = new AliAccountEntity();

}
