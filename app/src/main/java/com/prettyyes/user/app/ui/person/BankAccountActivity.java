package com.prettyyes.user.app.ui.person;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.BindCardRequest;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.dialog.BankSelectDialog;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.ChangeAccountSuccess;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.v8.BankEntity;
import com.ta.utdid2.android.utils.StringUtils;

public class BankAccountActivity extends BaseActivity {


    @Override
    protected int bindLayout() {
        return R.layout.activity_bank_account;
    }

    @Override
    protected void initViews() {
        bindViews();
        setActivtytitle(R.string.title_activity_bankaccount);
        IntentParams intentParams = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParams != null) {
            setData(intentParams.getBankEntity());
        }
    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected void setListener() {
        super.setListener();
        setRightTvListener("提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(etv_bank_account.getText().toString())) {
                    showSnack("银行账户不允许为空");
                    return;
                }
                if (StringUtils.isEmpty(etv_bank_uname.getText().toString())) {
                    showSnack("用户名不允许为空");
                    return;
                }
                if (StringUtils.isEmpty(etv_bank_phone.getText().toString())) {
                    showSnack("银行绑定手机号不允许为空");
                    return;
                }
                if (StringUtils.isEmpty(bank_id)) {
                    showSnack("选择银行允许为空");
                    return;
                }
                BindCardRequest bindCardAPi = new BindCardRequest();
                bindCardAPi.setBank_name(bank_id);
                bindCardAPi.setBank_uname(etv_bank_uname.getText().toString());
                bindCardAPi.setBank_telephone(etv_bank_phone.getText().toString());
                bindCardAPi.setBank_card(etv_bank_account.getText().toString());
                bindCardAPi.setBank_branch(etv_bank_branch.getText().toString());
                showProgressDialog(getString(R.string.upload_ing));
                bindCardAPi.post(new NetReqCallback<Object>() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        dismissProgressDialog();
                        showToastShort(message);
                    }

                    @Override
                    public void apiRequestSuccess(Object s, String method) {
                        dismissProgressDialog();
                        showToastShort("操作成功");
                        finish();
                        AppRxBus.getInstance().post(new ChangeAccountSuccess());
                    }
                });

            }
        });
        rel_slelct_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankSelectDialog bankSelectDialog = new BankSelectDialog(getThis());
                bankSelectDialog.show();
                bankSelectDialog.setBankListener(new BankSelectDialog.OnBankListener() {
                    @Override
                    public void onClick(BankEntity selectBank) {
                        if (selectBank != null) {
                            bank_id = selectBank.getBank_id();
                            etv_bank_name.setText(selectBank.getBank_name());
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void loadData() {

    }

    private EditTextDel etv_bank_account;
    private RelativeLayout rel_slelct_bank;
    private TextView etv_bank_name;
    private EditTextDel etv_bank_phone;
    private EditTextDel etv_bank_uname;
    private EditTextDel etv_bank_branch;
    private String bank_id = "";

    private void setData(BankEntity bankModel) {
        if (bankModel == null)
            return;
        getRight_tv().setText("修改");
        etv_bank_account.setText(bankModel.getBank_card());
        etv_bank_name.setText(bankModel.getBank_name());
        etv_bank_phone.setText(bankModel.getBank_telephone());
        etv_bank_uname.setText(bankModel.getBank_uname());
        etv_bank_branch.setText(bankModel.getBank_branch());
        bank_id = bankModel.getBank_id();
    }

    private void bindViews() {

        etv_bank_account = (EditTextDel) findViewById(R.id.etv_bank_account);
        rel_slelct_bank = (RelativeLayout) findViewById(R.id.rel_slelct_bank);
        etv_bank_name = (TextView) findViewById(R.id.etv_bank_name);
        etv_bank_phone = (EditTextDel) findViewById(R.id.etv_bank_phone);
        etv_bank_uname = (EditTextDel) findViewById(R.id.etv_bank_uname);
        etv_bank_branch = (EditTextDel) findViewById(R.id.etv_bank_branch);
    }
}
