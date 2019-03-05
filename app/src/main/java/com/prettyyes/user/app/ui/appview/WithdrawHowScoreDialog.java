package com.prettyyes.user.app.ui.appview;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.ApplyHowScoreRequest;
import com.prettyyes.user.api.netXutils.requests.RichRequest;
import com.prettyyes.user.api.netXutils.response.RichesRes;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.ChangeAccountSuccess;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.BankEntity;

/**
 * Created by chengang on 2017/6/2.
 */

public class WithdrawHowScoreDialog extends Dialog {


    private TextView tv_go_bank;
    private TextView tv_withdraw;
    private BankEntity bankmodel;
    private TextView tv_price;
    private TextView tv_extra;

    public WithdrawHowScoreDialog(final Context context, String price, final WithDrawCallback withDrawCallback) {
        super(context, R.style.simple_dialog);
        setContentView(R.layout.dialog_withdraw_how_score);

        tv_go_bank = (TextView) findViewById(R.id.tv_go_bank);
        tv_withdraw = (TextView) findViewById(R.id.tv_withdraw);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_extra = (TextView) findViewById(R.id.tv_extra);
        tv_price.setText(price);

        tv_extra.setText(String.format("额外扣除手续费 ¥%.2f", Float.parseFloat(price) * 0.3*0.01));


        tv_go_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JumpPageManager.getManager().goBankAccount(context, bankmodel);
            }
        });
        this.withDrawCallback = withDrawCallback;
        tv_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ApplyHowScoreRequest().post(new NetReqCallback<Object>() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        AppUtil.showToastShort(message);
                    }

                    @Override
                    public void apiRequestSuccess(Object o, String method) {
                        AppUtil.showToastShort("兑换成功");
                        if (withDrawCallback != null)
                            withDrawCallback.withdrawSuccess();
                    }
                });

            }
        });

//        ((BaseActivity) context).mSubscription = AppRxBus.getInstance().toObservable(ChangeAccountSuccess.class).subscribe(new Action1<ChangeAccountSuccess>() {
//            @Override
//            public void call(ChangeAccountSuccess changeAccountSuccess) {
//                loadData();
//            }
//        });
        ((BaseActivity)context).mSubscription=  AppRxBus.getInstance().subscribeEvent(new RxCallback<ChangeAccountSuccess>() {
            @Override
            protected void back(ChangeAccountSuccess obj) {
                loadData();
            }
        });

        loadData();


    }

    private WithDrawCallback withDrawCallback;

    public interface WithDrawCallback {
        void withdrawSuccess();
    }

    private void loadData() {
        new RichRequest().post(new NetReqCallback<RichesRes>() {
            @Override
            public void apiRequestFail(String message, String method) {
                AppUtil.showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(RichesRes richesRes, String method) {
                setData(richesRes);
            }
        });

    }

    private void setData(RichesRes richesRes) {
        RichesRes.InfoBean info = richesRes.getInfo();
        String bank_name = info.getBank_name();
        String bank_card = richesRes.getInfo().getBank_card();
        bankmodel = new BankEntity();

        bankmodel.setBank_card(info.getBank_card());
        bankmodel.setBank_card_string(info.getBank_card_string());
        bankmodel.setBank_id(info.getBank_id());
        bankmodel.setBank_name(info.getBank_name());
        bankmodel.setBank_telephone(info.getBank_telephone());
        bankmodel.setBank_uname(info.getBank_uname());
        bankmodel.setBank_branch(info.getBank_branch());

        String fmt = "%s(%s)";
        if (StringUtils.strIsEmpty(bank_name) || StringUtils.strIsEmpty(bank_card)) {
            tv_go_bank.setText("点击设置银行卡");

        } else

        {
            if (bank_card.length() <= 4)
                tv_go_bank.setText(String.format(fmt, bank_name, bank_card));
            else
                tv_go_bank.setText(String.format(fmt, bank_name, bank_card.substring(bank_card.length() - 4, bank_card.length())));
        }
    }


}
